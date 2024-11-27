package cs3500.threetrios.originalcode.model;

import java.util.List;

/**
 * Defines the observation methods for a game of ThreeTrios such as returning a copy of the playing
 * grid, returning a copy of a player's hand, getting the current player, getting the number of
 * possible flips, and getting the score of a given player.
 */
public interface ReadOnlyThreeTriosModel {

  /**
   * Returns a copy of the playing grid.
   *
   * @return a copy of the playing grid.
   */
  Card[][] getGrid();

  /**
   * Returns a copy of the player's hand.
   *
   * @param player The given player.
   * @return a copy of the player's hand.
   * @throws IllegalArgumentException if player is none or null.
   */
  List<Card> getHand(Player player);

  /**
   * Returns the number of rows of the playing grid.
   *
   * @return the number of rows of the playing grid.
   */
  int gridHeight();

  /**
   * Returns the number of columns of the playing grid.
   *
   * @return the number of columns of the playing grid.
   */
  int gridLength();

  /**
   * Returns a copy of the card at the current 0-based row and column index.
   *
   * @param row The row index.
   * @param col The column index.
   * @return a copy of the card at the current 0-based row and column index.
   * @throws IllegalArgumentException if row or col indices are invalid.
   */
  Card getCard(int row, int col);

  /**
   * Returns the plauer that owns a particular cell on the playing grid.
   *
   * @param row The row index.
   * @param col The column index.
   * @return the plauer that owns a particular cell on the playing grid.
   * @throws IllegalArgumentException if row or col indices are invalid.
   */
  Player getOwner(int row, int col);

  /**
   * Returns whether a specific row and column location on the playing grid is a legal spot to play
   * to.
   *
   * @param row The row index.
   * @param col The column index.
   * @return whether a specific row and column location on the playing grid is a legal spot to play
   *         to. Returns false if row or col indices are invalid.
   */
  boolean isLegal(int row, int col);

  /**
   * Returns the total number of possible flips given a card and a coordinate.
   *
   * @param cardIdx The index of the card in the player's hand to play.
   * @param row     The row index.
   * @param col     The column index.
   * @return the total number of possible flips given a card and a coordinate.
   * @throws IllegalArgumentException if cardIdx, row, or col are invalid.
   */
  int getPossibleFlips(int cardIdx, int row, int col);

  /**
   * Returns a supplied player's score in the game. A score is calculated by the number of cards
   * that player owns in their hand plus the number of cards that player owns on the grid.
   *
   * @param player The player to calculate the score of.
   * @return the player's score as an integer.
   */
  int getPlayerScore(Player player);

  /**
   * Returns whether the game is over, which happens when the Game State is in PostGame.
   *
   * @return whether the game is over
   */
  boolean isGameOver();

  /**
   * Returns the current player.
   *
   * @return the current player.
   */
  Player getCurrentPlayer();


}
