package cs3500.threetrios.originalcode.model;

/**
 * Extends the {@link Card} interface. Defines the observations and operations a client can perform
 * for a {@link PlayCard} in ThreeTrios. Some observations include viewing a card's string
 * representation, checking for equality with another card, and obtaining a unique hash code for the
 * card. Operations include setting a player.
 */
public interface PlayableCard extends Card {

  /**
   * Returns the string representation of a {@link PlayCard}.
   * It follows the provided format: "cardName + " " + top + " " + bottom + " "
   * + right + " " + left. An example is IceSpirit 3 4 1 2
   *
   * @return the string representation of a {@link PlayCard}.
   */
  String toHandString();

  @Override
  boolean equals(Object obj);

  @Override
  int hashCode();

  /**
   * Sets the player to the supplied player.
   *
   * @param player The supplied player to assign the current player to.
   * @return the receiving PlayableCard object with this newly assigned player.
   */
  PlayableCard setPlayer(Player player);
}
