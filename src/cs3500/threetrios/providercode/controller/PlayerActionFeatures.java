package cs3500.threetrios.providercode.controller;

import cs3500.threetrios.providercode.model.PlayerColor;

/**
 * Represents the various operations that can be performed on a visual view.
 */
public interface PlayerActionFeatures {

  /**
   * Outputs the 0-based index of the card in the player's hand, as well as its color/owner.
   * Chooses and highlights the chosen card in the hand.
   * @param idx index of the card
   * @param playerColor color that the card is on the grid
   */
  void highlightOrUnhighlightCardInHand(int idx, PlayerColor playerColor);

  /**
   * Outputs the coordinates of a chosen cell on a grid, and then moves accordingly to place the
   * card on the board if it's legal. Visual popups appear if a move is illegal. The card then does
   * battle with its neighbors, and the model and view update respectively. Lastly, the respective
   * player's turn ends. Returns true if the move was successfully completed, false otherwise.
   * @param row 0-based row of grid cell
   * @param col 0-based column of grid cell
   * @throws IllegalArgumentException if the move is attempted with an invalid coordinate
   */
  boolean move(int row, int col);

  /**
   * Tracks the index of a player's selected/highlighted card in their hand.
   * @param handIdx index of the card in respect to the player's hand
   * @throws IllegalArgumentException if the hand index is invalid
   */
  void setCardSelectedIdx(int handIdx);

  /**
   * Retrieves the index of a player's selected/highlighted card in their hand.
   * @return the index of the card
   */
  int getCardSelectedIdx();

}
