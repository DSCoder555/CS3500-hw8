package cs3500.threetrios.originalcode.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * A GUI frame for displaying the game state, including the grid and player hands, with interactive
 * mouse controls for selecting cards and grid positions.
 */
public class GameFrame extends JFrame {

  private GridPanel gridPanel;
  private PlayerHandPanel redHandPanel;
  private PlayerHandPanel blueHandPanel;
  private static final int WINDOW_HEIGHT = 200;
  private static final int WINDOW_WIDTH = 200;
  private ReadOnlyThreeTriosModel model;
  private JLabel statusLabel;
  private boolean mouseIsDown;
  private final List<ViewFeatures> featuresListeners;

  /**
   * Constructs a `GameFrame` with the specified model, setting up the grid and player hand panels,
   * as well as the layout and event listeners for user interactions.
   *
   * @param model the read-only model of the game, used to initialize the display and interactions
   * @throws IOException              if an error occurs while reading resources
   * @throws IllegalArgumentException if the model is null
   */
  public GameFrame(ReadOnlyThreeTriosModel model)
          throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("playerHandPanel and model cannot be null");
    }
    this.featuresListeners = new ArrayList<>();
    this.model = model;
    mouseIsDown = false;
    List<Card> redHand = model.getHand(Player.Red);
    List<Card> blueHand = model.getHand(Player.Blue);
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
    int cardWidth = WINDOW_WIDTH / (model.gridLength() + 2);
    int gridCardHeight = WINDOW_HEIGHT / (model.gridHeight());
    int blueCardHeight = WINDOW_HEIGHT / (model.getHand(Player.Blue).size());
    int redCardHeight = WINDOW_HEIGHT / (model.getHand(Player.Red).size());
    gridPanel = new GridPanel(model.getGrid(), cardWidth, gridCardHeight, model);
    blueHandPanel = new PlayerHandPanel(model.getHand(Player.Blue), cardWidth,
            blueCardHeight, model);
    redHandPanel = new PlayerHandPanel(model.getHand(Player.Red), cardWidth, redCardHeight, model);
    statusLabel = new JLabel("Current player: " + model.getCurrentPlayer(),
            SwingConstants.CENTER);
    setupLayout();
  }

  private void setupLayout() {
    this.setLayout(new BorderLayout());
    this.add(statusLabel, BorderLayout.NORTH);
    this.add(gridPanel, BorderLayout.CENTER);
    this.add(redHandPanel, BorderLayout.WEST);
    this.add(blueHandPanel, BorderLayout.EAST);
    this.setSize(800, 600); // Set to desired dimensions
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Renders the two main components: the player's hand
   * and the playing grid.
   */
  public void startRendering() {
    this.gridPanel.initGrid(model.getGrid());
    List<Card> redPlayerHand = model.getHand(Player.Red);
    List<Card> bluePlayerHand = model.getHand(Player.Blue);
    this.redHandPanel.renderHandState(redPlayerHand);
    this.blueHandPanel.renderHandState(bluePlayerHand);
    this.gridPanel.renderGridState(model.getGrid());
  }

  /**
   * Adds the supplied view feature to the list of view features.
   *
   * @param features The specified view feature to add.
   */
  public void addFeaturesListener(ViewFeatures features) {
    this.featuresListeners.add(Objects.requireNonNull(features));
  }

  /**
   * Continues the game if a proper guess is made.
   */
  public void update() {
    if (model.getCurrentPlayer() == Player.None) {
      statusLabel.setText("The Game is Over!");
    } else {
      statusLabel.setText("Current player: " + model.getCurrentPlayer());
    }
    this.redHandPanel.updateHand(model.getHand(Player.Red));
    this.blueHandPanel.updateHand(model.getHand(Player.Blue));
    this.gridPanel.updateGrid(model.getGrid());
    this.repaint();
  }

  /**
   * Makes a message when an error occurs.
   */
  public void error() {
    System.err.println("OOPS!");
    this.repaint();
  }

  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      GameFrame.this.mouseIsDown = true;
      this.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      GameFrame.this.mouseIsDown = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      Point clickP = e.getPoint();
      if (clickP.getX() < redHandPanel.getWidth() && model.getCurrentPlayer() == Player.Red) {
        int index = (int) ((clickP.getY() - 53) /
                (redHandPanel.getHeight() / model.getHand(Player.Red).size()));
        redHandPanel.highlightCard(index);
        for (ViewFeatures fListener : featuresListeners) {
          fListener.selectedHand((int) ((clickP.getY() - 53) /
                  (redHandPanel.getHeight() / model.getHand(Player.Red).size())));
        }
      } else if (clickP.getX() > getWidth() - blueHandPanel.getWidth()) {
        int index = (int) ((clickP.getY() - 53) /
                (blueHandPanel.getHeight() / model.getHand(Player.Blue).size()));
        if (model.getCurrentPlayer() == Player.Blue) {
          blueHandPanel.highlightCard(index);
        }
        for (ViewFeatures fListener : featuresListeners) {
          fListener.selectedHand((int) ((clickP.getY() - 53) /
                  (blueHandPanel.getHeight() / model.getHand(Player.Blue).size())));
        }
      } else {
        for (ViewFeatures fListener : featuresListeners) {
          fListener.selectedGrid((int) ((clickP.getY() - 53) /
                  (gridPanel.getHeight() / model.gridHeight())), (int) ((clickP.getX()
                  - redHandPanel.getWidth()) / (gridPanel.getWidth() / model.gridLength())));
        }
      }
      GameFrame.this.redHandPanel.repaint();
    }
  }
}
