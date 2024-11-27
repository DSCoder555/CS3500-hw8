package cs3500.threetrios.originalcode.model;

import java.awt.*;
import java.util.Objects;

/**
 * Defines the structure of an empty card on the grid. An {@link EmptyCard} is a spot on the
 * grid that contains no {@link PlayCard}. A {@link PlayCard} can be played to a
 * {@link EmptyCard} spot.
 */
public class EmptyCard implements Card {

  @Override
  public int topValue() {
    return 11;
  }

  @Override
  public int leftValue() {
    return 11;
  }

  @Override
  public int rightValue() {
    return 11;
  }

  @Override
  public int bottomValue() {
    return 11;
  }

  public Color getBackgroundColor() {
    return Color.YELLOW;
  }

  @Override
  public boolean canReplace() {
    return true;
  }

  @Override
  public void switchPlayer() {
    throw new IllegalStateException("Trying to switch HoleCard");
  }

  @Override
  public Player getPlayer() {
    return Player.None;
  }

  @Override
  public Card copyOf() {
    return this;
  }

  @Override
  public String toString() {
    return "_";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    EmptyCard emptyCard = (EmptyCard) o;
    return this.topValue() == emptyCard.topValue() &&
            this.bottomValue() == emptyCard.bottomValue() &&
            this.leftValue() == emptyCard.leftValue() &&
            this.rightValue() == emptyCard.rightValue() &&
            this.getPlayer().equals(emptyCard.getPlayer());
  }

  @Override
  public int hashCode() {
    return Objects.hash(topValue(), bottomValue(),
            leftValue(), rightValue(), getPlayer());
  }

}
