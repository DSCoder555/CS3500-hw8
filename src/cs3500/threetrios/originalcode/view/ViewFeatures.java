package cs3500.threetrios.originalcode.view;

import cs3500.threetrios.originalcode.model.Player;

/**
 * Defines the interactive features for a Three Trios game view, enabling user actions like
 * selecting grid positions, selecting cards from the player's hand, and quitting the game.
 */

public interface ViewFeatures {

  /**
   * Outputs what row and column location the user clicked on the playing grid.
   *
   * @param row The 0-based row index.
   * @param col The 0-based column index.
   * @throws IllegalArgumentException if the move is incomplete. Will be fully implemented in
   *                                  part 3.
   */
  void selectedGrid(int row, int col);

  /**
   * Outputs what card index the current player clicked on their hand.
   *
   * @param cardNum The card index.
   * @throws IllegalArgumentException if the move isn't complete (Will be fully implemented in
   *                                  part 3)
   */
  void selectedHand(int cardNum);

  Player getPlayer();

  /**
   * Will be implemented in Part 3, but is intended to allow
   * the user to quit from a game of Three Trios.
   */
  void quit();
}
