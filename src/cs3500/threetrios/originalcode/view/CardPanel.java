package cs3500.threetrios.originalcode.view;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.Player;

/**
 * Represents an individual game card, displaying its attack values on each side.
 * It customizes the appearance of a single card and handles its rendering.
 */
public class CardPanel extends JPanel {
  private Card card;
  private final int topValue;
  private final int bottomValue;
  private final int rightValue;
  private final int leftValue;
  private final int xOffset = 10;
  private final int yOffset = 15;
  // These two variables are public because they need to be used by the PlayerHandPanel
  // class's getPreferredSize.
  public static final int CARD_WIDTH = 120;
  public static final int CARD_HEIGHT = 100;

  /**
   * Initializes the attack values for each card based on a given PlayableCard.
   *
   * @param playCard The card whose attack values to extract and initialize.
   */
  public CardPanel(Card playCard) {
    Objects.requireNonNull(playCard);
    this.card = playCard;
    this.topValue = card.topValue();
    this.bottomValue = card.bottomValue();
    this.rightValue = card.rightValue();
    this.leftValue = card.leftValue();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawRect(0, 0, getWidth(), getHeight());
    if (this.card.getPlayer() != Player.None) {
      if (String.valueOf(topValue).equals("10")) {
        g2d.drawString("A", getWidth() / 2 - 5, yOffset);
      } else {
        g2d.drawString(String.valueOf(topValue), getWidth() / 2 - 5, yOffset);
      }
      if (String.valueOf(bottomValue).equals("10")) {
        g2d.drawString("A", getWidth() / 2 - 5, getHeight() - yOffset);
      } else {
        g2d.drawString(String.valueOf(bottomValue), getWidth() / 2 - 5, getHeight() - yOffset);
      }
      if (String.valueOf(rightValue).equals("10")) {
        g2d.drawString("A", getWidth() - xOffset - 8, getHeight() / 2);
      } else {
        g2d.drawString(String.valueOf(rightValue), getWidth() - xOffset - 8, getHeight() / 2);
      }
      if (String.valueOf(leftValue).equals("10")) {
        g2d.drawString("A", xOffset, getHeight() / 2);
      } else {
        g2d.drawString(String.valueOf(leftValue), xOffset, getHeight() / 2);
      }
      g2d.setBackground(card.getBackgroundColor());
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(CARD_WIDTH, CARD_HEIGHT);
  }

  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }

}
