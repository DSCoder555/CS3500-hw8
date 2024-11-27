package cs3500.threetrios.originalcode.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Represents the panel that displays a player's hand of cards in a vertical stack.
 * It contains multiple CardPanel instances and draws dividing lines between each card.
 */
public class PlayerHandPanel extends JPanel implements PlayerHandPanelInterface {
  // TODO: Decide whether any of these should be final
  private ReadOnlyThreeTriosModel model;
  private List<Card> playerHand;
  private List<CardPanel> cardPanels;
  private final Border blackLine = BorderFactory.createLineBorder(Color.black, 5);

  /**
   * Initializes the player's hand, the player type, and the list of CardPanel objects
   * to allow for functionality like removing a card.
   *
   * @param playerHand The player's hand.
   * @throws IllegalArgumentException if player type is null or none, if the playerHand or
   *                                  cardPanels lists are null or empty, or if width and height of
   *                                  card are negative.
   */
  public PlayerHandPanel(List<Card> playerHand, int cardWidth, int cardHeight,
                         ReadOnlyThreeTriosModel model) {
    if (playerHand == null || playerHand.isEmpty()) {
      throw new IllegalArgumentException("Player hand or card panels must have at least one card");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model and/or controller cannot be null");
    }
    if (cardWidth < 0 || cardHeight < 0) {
      throw new IllegalArgumentException("Card width and height must not be negative");
    }
    this.playerHand = playerHand;
    int handSize = playerHand.size();
    this.cardPanels = new ArrayList<>();

    // Set layout to stack cards vertically
    this.setLayout(new GridLayout(handSize, 1));

    // Add a CardPanel for each card in the hand
    for (Card card : playerHand) {
      CardPanel cardPanel = new CardPanel(card);
      cardPanel.setBackground(card.getBackgroundColor());
      cardPanels.add(cardPanel);
      add(cardPanel);
    }
    this.revalidate();
    this.repaint();
  }

  /**
   * Sets the layout to a grid layout and renders the current hand.
   *
   * @param playerHand The hand of the current player.
   */
  public void renderHandState(List<Card> playerHand) {
    if (playerHand == null) {
      throw new IllegalArgumentException("Player hand cannot be null.");
    }
    this.removeAll();
    this.setLayout(new GridLayout(cardPanels.size(), 1));
    // resetting a layout on previous size
    for (Component card : cardPanels) {
      this.add(card);
    }
    this.revalidate();
    this.repaint();
    if (playerHand.isEmpty()) {
      this.removeAll();
      this.setLayout(new GridLayout(0, 1));
      this.setVisible(false);
      this.setBorder(null);
      this.revalidate();
      this.repaint();
    }
  }

  @Override
  public void renderGameState() {
    renderHandState(playerHand);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (cardPanels == null || cardPanels.isEmpty()) {
      return;
    }
    Graphics2D g2d = (Graphics2D) g;
    for (int i = 1; i < this.playerHand.size(); i++) {
      int y = CardPanel.CARD_HEIGHT * i; // Adjust this based on the fixed height of each card
      g2d.drawLine(0, y, getWidth(), y);
      g2d.setBackground(playerHand.get(i).getBackgroundColor());
    }
    this.revalidate();
    this.repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    int totalHeight = CardPanel.CARD_HEIGHT * this.playerHand.size();
    return new Dimension(CardPanel.CARD_WIDTH, totalHeight);
  }

  // TODO: Set to 100 by 100 dimensions for the entire gui view, open to change
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }

  @Override
  public void highlightCard(int cardIdx) {
    if (cardIdx < 0 || cardIdx >= this.playerHand.size()) {
      throw new IllegalArgumentException("Card index out of bounds");
    }
    boolean alreadyHighlighted = this.cardPanels.get(cardIdx).getBorder() == blackLine;

    for (JPanel cardPanel : this.cardPanels) {
      cardPanel.setBorder(null);
    }
    if (!alreadyHighlighted) {
      this.cardPanels.get(cardIdx).setBorder(blackLine);
    }
    setLayout(new GridLayout(cardPanels.size(), 1));
    this.revalidate();
    this.repaint();
    this.renderGameState();
  }


  @Override
  public void deselectCard() {
    for (JPanel cardPanel : this.cardPanels) {
      if (blackLine.equals(cardPanel.getBorder())) {
        cardPanel.setBorder(null);
      }
    }
    this.revalidate();
    this.repaint();
  }

  @Override
  public void removeCard(int cardIdx) {
    if (cardIdx < 0 || cardIdx >= this.playerHand.size()) {
      throw new IllegalArgumentException("Card index out of bounds");
    }
    this.remove(this.cardPanels.get(cardIdx));
    cardPanels.remove(cardIdx);
    setLayout(new GridLayout(cardPanels.size(), 1));
    this.revalidate();
    this.repaint();
    // if the card panels size becomes 0, remove all the extra lines on the hand panel
    if (cardPanels.isEmpty()) {
      this.removeAll();
      this.setVisible(false);
      this.setLayout(new GridLayout(cardPanels.size(), 1));
      this.setBorder(null);
      this.revalidate();
      this.repaint();
    }
  }


  @Override
  public void playCardToGrid(int cardIdx, int rowIdx, int colIdx) {
    if (cardIdx < 0 || cardIdx >= this.playerHand.size()) {
      throw new IllegalArgumentException("Card index out of bounds");
    }

  }

  // Note: controller will use this method and do model.getHand()
  @Override
  public void updateHand(List<Card> playerHand) {
    // TODO: Add click listeners?
    this.removeAll();
    this.cardPanels.clear();
    this.setLayout(new GridLayout(playerHand.size(), 1));
    for (Card card : playerHand) {
      // TODO: Do I need to add a listener to each CardPanel that captures the click event and
      //  informs the controller which card the player clicked?
      // Get the coordinate of where the card is created
      int curIdx = playerHand.indexOf(card);
      CardPanel cardPanel = new CardPanel(card);
      cardPanel.setBackground(card.getBackgroundColor());
      cardPanels.add(cardPanel);
      add(cardPanel);
    }
    this.revalidate();
    this.repaint();

  }

}
