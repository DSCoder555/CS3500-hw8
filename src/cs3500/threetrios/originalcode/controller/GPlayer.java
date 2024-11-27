package cs3500.threetrios.originalcode.controller;

import cs3500.threetrios.originalcode.model.Player;

/**
 * Describes the behaviors for an AI strategy such as getting the best move, determining whether
 * the player is a human and resetting a move.
 */
public interface GPlayer {

  /**
   * Based on the AI strategy, finds the best move to make and returns a custom Move object
   * containing the index of the card in the hand to play and the grid row and column indices.
   * Indices are all 0-based.
   *
   * @return a custom Move object containing the index of the card in the hand to play
   *         and the grid row and column indices.
   */
  Move getMove();

  /**
   * Returns whether the player is human.
   *
   * @return whether the player is human
   */
  boolean isHuman();

  /**
   * Sets the row to the specified row.
   *
   * @param newRow The specified row.
   * @throws IllegalStateException if the player is AI as an AI cannot set a row.
   */
  void setRow(int newRow);

  /**
   * Sets the column to the specified column.
   *
   * @param newCol The specified column.
   * @throws IllegalStateException if the player is AI as an AI cannot set a column.
   */
  void setCol(int newCol);

  /**
   * Sets the index of a card into a hand to a specified index.
   *
   * @param newCardNum The specified card index.
   * @throws IllegalStateException if the player is AI as an AI cannot set a card index.
   */
  void setCardNum(int newCardNum);

  /**
   * Resets the move after a move has been made. Makes the card, row, and col indices -1.
   *
   * @throws IllegalStateException if an AI tries to reset a move.
   */
  void resetMove();

  /**
   * Returns a flag indicating whether we are using or must use the fallback AI strategy of
   * choosing the first available move (uppermost-leftmost, respectively).
   *
   * @return whether we must use the fallback AI strategy of choosing the first available move
   */
  boolean isDefaultMove();

  /**
   * Returns the current player.
   *
   * @return the current player.
   */
  Player getPlayer();
}
