package cs3500.threetrios.originalcode.model;

import java.awt.*;

/**
 * Defines the observations and operations a client can perform for the card component of
 * ThreeTrios. Some observations include viewing a card's attack values, viewing the current player,
 * printing out the representation of a card, and determining if two cards are equal.
 * Operations include switching the current player.
 */
public interface Card {

  /**
   * Returns the north attack value or top value of a card.
   *
   * @return the north attack value or top value of a card.
   */
  int topValue();

  /**
   * Returns the south attack value or bottom value of a card.
   *
   * @return the south attack value or bottom value of a card.
   */
  int bottomValue();

  /**
   * Returns the west attack value or left value of a card.
   *
   * @return the west attack value or left value of a card.
   */
  int leftValue();

  /**
   * Returns the east attack value or right value of a card.
   *
   * @return the east attack value or right value of a card.
   */
  int rightValue();

  /**
   * Returns the background color of the card object. Yellow for Empty Cards, Gray for
   * Hole Cards, and for Playable Card, depending on the player, returns Red or Blue.
   *
   * @return the background color of the card object
   */
  Color getBackgroundColor();

  /**
   * Returns a boolean indicating whether a card on the grid can be replaced.
   * On the grid, An {@link EmptyCard} can be replaced while a {@link HoleCard} and
   * a {@link PlayCard} cannot.
   *
   * @return a boolean indicating whether a card on the grid can be replaced.
   */
  boolean canReplace();

  /**
   * Switches player's turns. If the current {@link Player} is red, switchPlayer makes the current
   * {@link Player} blue and vice versa.
   */
  void switchPlayer();

  /**
   * Returns the current player.
   *
   * @return the current player.
   */
  Player getPlayer();

  /**
   * Returns current object if it's a `Card`; otherwise, returns a copy if it's `PlayableCard`.
   *
   * @return the original object for `Card` instances, or a copy for `PlayableCard` instances.
   */
  Card copyOf();

  /**
   * Returns a string representation of each card, depending on the type of card.
   * {@link EmptyCard} is represented as "_" {@link HoleCard} is represented as " " and
   * {@link PlayCard} is represented as "B", with the character within the string being B to
   * indicate the blue player, R to indicate the red player, and N to indicate none player.
   *
   * @return a string representation of each card, depending on the type of card.
   */
  @Override
  String toString();

  /**
   * Returns whether two cards are equal. For them to be considered equal, they must either have
   * the same reference, or each of their characteristics, including their four attack values
   * and their corresponding player object must all be equal.
   *
   * @param obj The other card object to compare equality against.
   * @return whether two cards are equal.
   */
  @Override
  boolean equals(Object obj);

  /**
   * Returns a hash code value for this object. This value should be consistent with the `equals`
   * method: if two objects are equal, they must have the same hash code.
   *
   * @return a hash code for this object
   */
  @Override
  int hashCode();
}
