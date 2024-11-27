package cs3500.threetrios.player;

import java.util.List;

import cs3500.threetrios.controller.PlayerActionFeatures;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.PlayerColor;
import cs3500.threetrios.model.ReadOnlyThreeTriosModel;

/**
 * Represents the behaviors of a player in the game of ThreeTrios.
 * Each player has a color and a hand of cards that they can manage during the game.
 */
public interface IPlayer {

  /**
   * Retrieves the color associated with this player.
   *
   * @return the PlayerColor representing the player
   */
  PlayerColor getPlayerColor();

  /**
   * Returns a copy of the hand in the game. This means modifying the returned list
   * or the cards in the list has no effect on the game.
   * @return a new list containing the cards in the player's hand in the same order
   *     as in the current state of the game.
   * @throws IllegalStateException if the game has not started
   */
  List<Card> getHand();

  /**
   * Adds a card to the player's hand. This card is appended to the end of the hand.
   *
   * @param c the Card to add to the player's hand
   */
  void drawCardForHand(Card c);

  /**
   * Removes a card in the player's hand when they play it.
   * @param idx 0-based index of the card in the player's hand
   */
  void removeCardInHand(int idx);

  /**
   * Adds a player action listener that responds to player actions.
   * @param features player actions that are sent to the controller to update the game
   */
  void addPlayerActionListener(PlayerActionFeatures features);

  /**
   * Sets up a player before they make a move, either by waiting for a human player to click a card
   * in their hand and a cell on the board or a machine player determining their best move based on
   * their own strategy.
   * @param model current board state of the game
   */
  void setupPlay(ReadOnlyThreeTriosModel model);

  /**
   * Retrieves all the player action listeners for a specified player.
   * @return the list of listeners that emit events that allows the controller to update the game
   */
  List<PlayerActionFeatures> getPlayerActionListeners();

  /**
   * Retrieves a copy of the player of interest. Modifying this player will not affect the state/
   * outcome of the game.
   * @return copy/clone of the player
   */
  IPlayer copyPlayer();

}
