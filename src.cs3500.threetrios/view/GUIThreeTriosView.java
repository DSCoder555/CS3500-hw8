package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.PlayerActionFeatures;
import model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.player.IPlayer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents a visual view of ThreeTrios.
 */
public class GUIThreeTriosView extends JFrame implements VisualThreeTriosView {

  private ReadOnlyThreeTriosModel model;

  private GUIThreeTriosGridPanel threeTriosGridPanel;
  private GUIThreeTriosHandPanel redPlayerHandPanel;

  private GUIThreeTriosHandPanel bluePlayerHandPanel;

  private GUIThreeTriosHandPanel highlightedPanel;
  private Integer highlightedIndex;

  /**
   * Constructor for a visual view of ThreeTrios.
   * @param model ReadOnly model that is passed in to the view, grabbing the game at its current
   *     state.
   */
  public GUIThreeTriosView(ReadOnlyThreeTriosModel model) {

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    GUIThreeTriosGridPanel threeTriosGridPanel = new GUIThreeTriosGridPanel(model);
    GUIThreeTriosHandPanel redPlayerHandPanel =
            new GUIThreeTriosHandPanel(model.getPlayers().get(0));
    GUIThreeTriosHandPanel bluePlayerHandPanel =
            new GUIThreeTriosHandPanel(model.getPlayers().get(1));

    this.model = model;

    this.add(redPlayerHandPanel, BorderLayout.WEST);
    this.add(threeTriosGridPanel, BorderLayout.CENTER);
    this.add( bluePlayerHandPanel, BorderLayout.EAST);

    this.threeTriosGridPanel = threeTriosGridPanel;
    this.redPlayerHandPanel = redPlayerHandPanel;
    this.bluePlayerHandPanel = bluePlayerHandPanel;

    highlightedPanel = null;
    highlightedIndex = null;

    setMinimumSize(new Dimension(400, 300));

  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800,600);
  }

  @Override
  public void addPlayerActionFeatures(PlayerActionFeatures playerActionFeatures) {

    threeTriosGridPanel.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {

        int numCols = model.numColsInGrid();
        int numRows = model.numRowsInGrid();

        int widthScale = threeTriosGridPanel.getWidth() / numCols;
        int heightScale = threeTriosGridPanel.getHeight() / numRows;

        int col = e.getX() / widthScale;
        int row = e.getY() / heightScale;

        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {

          if (playerActionFeatures.move(row, col)) {
            redPlayerHandPanel.unhighlightCurrentCard();
            bluePlayerHandPanel.unhighlightCurrentCard();
          }

        }

      }

      @Override
      public void mousePressed(MouseEvent e) {
        //implemented because of MouseListener interface
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        //implemented because of MouseListener interface
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        //implemented because of MouseListener interface
      }

      @Override
      public void mouseExited(MouseEvent e) {
        //implemented because of MouseListener interface
      }

    });

    addCardSelectionListener(redPlayerHandPanel, playerActionFeatures);
    addCardSelectionListener(bluePlayerHandPanel, playerActionFeatures);

  }

  @Override
  public void updateTitle(IPlayer p) {
    if (p == null) {
      throw new IllegalArgumentException("Player is null/invalid.");
    }
    setTitle(getTitleString(p));
  }

  private String getTitleString(IPlayer p) {
    return "Player: " + p.getPlayerColor()
            + " | Current Turn: " + model.getTurn().getPlayerColor();
  }

  /** Create a MouseListener for a hand panel.
   * @param handPanel hand panel that the mouseListener is being added to
   * @param playerActionFeatures player action features that allow the view to behave in certain
   *     ways upon using mouse inputs
   */
  private void addCardSelectionListener(GUIThreeTriosHandPanel handPanel, PlayerActionFeatures
          playerActionFeatures) {

    handPanel.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) {
        if (!handPanel.isEnabled()) {
          return;
        }
        handleCardSelection(handPanel, e);
        int clickedIndex = getClickedCardIndex(e, handPanel);
        playerActionFeatures.highlightOrUnhighlightCardInHand(clickedIndex,
                handPanel.getSide().getPlayerColor());

      }

      @Override
      public void mousePressed(MouseEvent e) {
        //implemented because of MouseListener interface
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        //implemented because of MouseListener interface
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        //implemented because of MouseListener interface
      }

      @Override
      public void mouseExited(MouseEvent e) {
        //implemented because of MouseListener interface
      }

    });
  }

  /**
   * Retrieves the 0-based index of a card in a hand that was clicked on.
   * @param e click that triggers calculating an index
   * @param handPanel hand panel that the click concerns
   */
  private int getClickedCardIndex(MouseEvent e, GUIThreeTriosHandPanel handPanel) {
    int handSize = handPanel.playerHandSize();
    int clickY = e.getY();
    int cardHeight = handPanel.getHeight() / handSize;
    return clickY / cardHeight;
  }

  /**
   * ensures that only one card can be highlighted at a time.
   * @param handPanel hand panel that most recently had a highlighted card
   * @param e event that triggers checking to see that only one card can be highlighted
   */
  private void handleCardSelection(GUIThreeTriosHandPanel handPanel, MouseEvent e) {
    int index = getClickedCardIndex(e, handPanel);

    if (highlightedPanel != null && highlightedPanel != handPanel) {
      highlightedPanel.unhighlightCurrentCard();
    }

    handPanel.highlightOrUnhighlightSingleCard(index);

    highlightedPanel = handPanel;
    highlightedIndex = index;

  }

  @Override
  public void preventClickOppHand(IPlayer p) {
    if (p == null) {
      throw new IllegalArgumentException("Player is null/invalid.");
    }
    if (p.equals(model.getPlayers().get(0))) {
      bluePlayerHandPanel.setEnabled(false);
    } else {
      redPlayerHandPanel.setEnabled(false);
    }
  }

  @Override
  public void createPopup(Object message, String title, int msgType) {
    JOptionPane.showMessageDialog(this, message, title, msgType);
  }

}