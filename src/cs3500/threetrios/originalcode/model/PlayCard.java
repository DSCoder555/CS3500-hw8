package cs3500.threetrios.originalcode.model;

import java.awt.*;
import java.util.Objects;

/**
 * Defines the structure of a card, {@link PlayCard}, which can be played to a spot on the grid
 * It contains attributes such as a card name, top value, left value, right value, and
 * bottom value.
 */
public class PlayCard implements PlayableCard {
  private final String cardName;
  private final CardValues top;
  private final CardValues left;
  private final CardValues right;
  private final CardValues bottom;
  private Player player;

  /**
   * Creates a {@link PlayCard} object with the supplied arguments. Assigns the player to None
   * because we don't know who the current player is yet.
   *
   * @param name   The card name.
   * @param top    The top value of the card.
   * @param left   The left value of the card.
   * @param right  The right value of the card.
   * @param bottom The bottom value of the card.
   */
  public PlayCard(String name, CardValues top, CardValues left, CardValues right,
                  CardValues bottom) {
    cardName = name;
    this.top = top;
    this.left = left;
    this.right = right;
    this.bottom = bottom;
    player = Player.None;
  }

  /**
   * Creates a {@link PlayCard} object with the same arguments as above, except with a
   * {@link Player} to assign the current player to.
   *
   * @param name   The card name.
   * @param top    The top value of the card.
   * @param left   The left value of the card.
   * @param right  The right value of the card.
   * @param bottom The bottom value of the card.
   * @param player The player of the card.
   */
  public PlayCard(String name, CardValues top, CardValues left, CardValues right, CardValues bottom,
                  Player player) {
    if (name == null) {
      throw new IllegalArgumentException("Name provided for card creation was null.");
    }
    cardName = name;
    this.top = top;
    this.left = left;
    this.right = right;
    this.bottom = bottom;
    this.player = player;
  }

  @Override
  public int topValue() {
    return top.getValue();
  }

  @Override
  public int leftValue() {
    return left.getValue();
  }

  @Override
  public int rightValue() {
    return right.getValue();
  }

  @Override
  public int bottomValue() {
    return bottom.getValue();
  }

  @Override
  public Color getBackgroundColor() {
    if (this.player == Player.Blue) {
      return Color.BLUE;
    } else if (this.player == Player.Red) {
      return Color.RED;
    } else {
      throw new IllegalStateException("Player " + this.player + " cannot be none");
    }
  }

  @Override
  public boolean canReplace() {
    return false;
  }

  @Override
  public void switchPlayer() {
    if (player == Player.Red) {
      player = Player.Blue;
    } else if (player == Player.Blue) {
      player = Player.Red;
    } else {
      throw new IllegalStateException("Trying to flip a card in hand or without player.");
    }
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public Card copyOf() {
    return new PlayCard(cardName, top, left, right, bottom, player);
  }

  /**
   * Returns string representation of a {@link PlayCard}. An example is "B"
   *
   * @return string representation of a {@link PlayCard}
   */
  public String toString() {
    return "" + player.getChar();
  }

  @Override
  public PlayableCard setPlayer(Player player) {
    if (this.player != Player.None) {
      throw new IllegalStateException("Trying to setPlayer for card with player already");
    }
    this.player = player;
    return this;
  }

  @Override
  public String toHandString() {
    return cardName + " " + top + " " + bottom + " " + right + " " + left;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    PlayCard playCard = (PlayCard) o;
    return cardName.equals(playCard.cardName) &&
            this.topValue() == playCard.topValue() &&
            this.bottomValue() == playCard.bottomValue() &&
            this.leftValue() == playCard.leftValue() &&
            this.rightValue() == playCard.rightValue() &&
            this.getPlayer().equals(playCard.getPlayer());
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardName, topValue(), bottomValue(),
            leftValue(), rightValue(), getPlayer());
  }
}
