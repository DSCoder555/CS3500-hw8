package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;
import javax.swing.JPanel;

import model.Card;
import model.Direction;
import model.PlayerColor;
import player.IPlayer;

/**
 * Represents a player's hand panel that is drawn on either side of the grid in the visual view.
 */
public class GUIThreeTriosHandPanel extends JPanel implements ThreeTriosHandPanel {

  private final Color color;
  private Integer cardToHighlightIdx;

  private IPlayer side;

  /**
   * Constructs a hand panel for the visual view.
   * @param p player that the panel concerns
   */
  public GUIThreeTriosHandPanel(IPlayer p) {

    this.color = getColor(p.getPlayerColor());
    setAlignmentY(Component.TOP_ALIGNMENT);
    cardToHighlightIdx = null;
    side = p;

  }

  private List<Card> getSideHand() {
    return side.getHand();
  }

  @Override
  public int playerHandSize() {
    return getSideHand().size();
  }

  @Override
  public IPlayer getSide() {
    return side;
  }

  /**
   * Sets the color of a card in relation to a player's color.
   * @param pc player color
   */
  private Color getColor(PlayerColor pc) {
    if (pc == PlayerColor.RED) {
      return new Color(255, 102, 102);
    }
    return new Color(102, 178, 255);
  }

  @Override
  public Dimension getPreferredSize() {
    int totalWidth = getParent().getWidth();
    int height = getParent().getHeight();
    int handWidth = (int) (totalWidth * 0.17);
    return new Dimension(handWidth, height);
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    int availableHeight = this.getHeight();
    int cardWidth = this.getWidth();
    int cardLength = !getSideHand().isEmpty() ? availableHeight / getSideHand().size() : 0;
    int y = 0;

    for (Card currCard: getSideHand()) {
      g2d.setColor(color);
      VisualThreeTriosCard visualCard = new GUIVisualThreeTriosCard(cardWidth, cardLength);
      g2d.translate(0, y);
      g2d.fill((Shape) visualCard);
      g2d.setColor(Color.GRAY);
      g2d.draw((Shape) visualCard);
      g2d.translate(0, -y);

      Font originalFont = g2d.getFont();
      int fontSize = Math.min(cardWidth, cardLength) / 5;
      Font largerFont = originalFont.deriveFont((float) fontSize);
      g2d.setFont(largerFont);

      FontMetrics fm = g2d.getFontMetrics();

      g2d.setColor(Color.BLACK);

      int centerX = cardWidth / 2;
      int centerY = y + cardLength / 2;
      int distanceFromMiddle = Math.min(cardWidth, cardLength) / 4;

      drawAttackValue(g2d, currCard, Direction.North, centerX, centerY, distanceFromMiddle, fm);
      drawAttackValue(g2d, currCard, Direction.South, centerX, centerY, distanceFromMiddle, fm);
      drawAttackValue(g2d, currCard, Direction.East, centerX, centerY, distanceFromMiddle, fm);
      drawAttackValue(g2d, currCard, Direction.West, centerX, centerY, distanceFromMiddle, fm);

      y += cardLength;

    }

    if (cardToHighlightIdx != null) {
      int highlightY = cardToHighlightIdx * cardLength;
      VisualThreeTriosCard highlightedCard = new GUIVisualThreeTriosCard(cardWidth, cardLength);
      g2d.translate(0, highlightY);
      g2d.setColor(Color.DARK_GRAY);
      g2d.setStroke(new BasicStroke(5));
      g2d.draw((Shape) highlightedCard);
    }

  }

  /**
   * Draws an attack value on a card.
   * @param g2d graphics object to draw shapes
   * @param card card to be drawn
   * @param direction direction of attack value
   * @param centerX center x-coordinate of card
   * @param centerY center y-coordinate of card
   * @param distanceFromMiddle distance from the middle of the card for formatting
   * @param fm FontMetrics for formatting the attack value
   */
  private void drawAttackValue(Graphics2D g2d, Card card, Direction direction, int centerX,
                               int centerY, int distanceFromMiddle, FontMetrics fm) {
    String atkVal = String.valueOf(card.getAtkVals().get(direction).getAtkVal());
    int offsetX = 0;
    int offsetY = 0;

    switch (direction) {
      case North:
        offsetY = -distanceFromMiddle;
        break;
      case South:
        offsetY = distanceFromMiddle;
        break;
      case East:
        offsetX = distanceFromMiddle;
        break;
      case West:
        offsetX = -distanceFromMiddle;
        break;
      default: throw new IllegalArgumentException("Invalid direction.");
    }

    g2d.drawString(atkVal, centerX + offsetX - fm.stringWidth(atkVal) / 2, centerY + offsetY
            + fm.getAscent() / 2);
  }

  @Override
  public void highlightOrUnhighlightSingleCard(int index) {
    if (cardToHighlightIdx != null && cardToHighlightIdx == index) {
      cardToHighlightIdx = null;
    } else {
      cardToHighlightIdx = index;
    }
    repaint();
  }

  @Override
  public void unhighlightCurrentCard() {
    cardToHighlightIdx = null;
    repaint();
  }

}