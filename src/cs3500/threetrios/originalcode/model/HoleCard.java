package cs3500.threetrios.originalcode.model;

import java.awt.*;
import java.util.Objects;

/**
 * Defines the structure of a hole card on the grid. An {@link HoleCard} is a spot on the
 * grid that is fixed and cannot be played to by a {@link PlayCard}.
 * Therefore, it cannot have any attributes of a {@link PlayCard}, such as its attack values and
 * current player.
 */
public class HoleCard implements Card {

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
    return Color.GRAY;
  }

  @Override
  public boolean canReplace() {
    return false;
  }

  @Override
  public void switchPlayer() {
    throw new IllegalStateException("Trying to switch HoleCard");
  }

  @Override
  public Card copyOf() {
    return this;
  }

  @Override
  public Player getPlayer() {
    return Player.None;
  }

  @Override
  public String toString() {
    return " ";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    HoleCard holeCard = (HoleCard) o;
    return this.topValue() == holeCard.topValue() &&
            this.bottomValue() == holeCard.bottomValue() &&
            this.leftValue() == holeCard.leftValue() &&
            this.rightValue() == holeCard.rightValue() &&
            this.getPlayer().equals(holeCard.getPlayer());
  }


  @Override
  public int hashCode() {
    return Objects.hash(topValue(), bottomValue(),
            leftValue(), rightValue(), getPlayer());
  }
}
