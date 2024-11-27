package model;

import java.util.Map;

/**
 * Represents the behaviors of a card in the game of ThreeTrios.
 * Each card has a color associated with a player, a name, and attack values
 * corresponding to cardinal directions.
 */
public interface Card {

  /**
   * Retrieves the color associated with the player of this card.
   *
   * @return the color of the player as a PlayerColor enum
   */
  PlayerColor getPlayerColor();

  /**
   * Retrieves the name of this card.
   *
   * @return the name of the card as a String
   */
  String getName();

  /**
   * Retrieves the attack values of this card, where each direction (north, south, east, west)
   * has an associated attack value.
   *
   * @return a Map associating each Direction with its corresponding attack value
   */
  Map<Direction, AtkVal> getAtkVals();

  /**
   * Prints the card's name and its attack values.
   * The attack values are printed in the order of the cardinal directions they associate with. The
   * direction order is north, south, east and then west. The values range from 1-9.
   * Example: a card with the name WorldDragon and attack values of 8, 3, 5, and 7 is printed as
   * WorldDragon 8 3 5 7.
   * @return a character representation of the card
   */
  String toString();

}
