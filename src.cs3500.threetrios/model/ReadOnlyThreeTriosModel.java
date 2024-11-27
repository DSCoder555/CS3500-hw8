package model;

import java.util.List;

import cs3500.threetrios.player.IPlayer;

/**
 * Represents the behaviors of the ThreeTrios game model, including setting up
 * and managing gameplay on a grid. The objective of the game is for a player to have the most
 * cards in their color on the board and their hand at the end of the game. The Red player goes
 * first. All grid indexes are 0-based, and thus the methods using the grid are also 0-based for
 * indexing.
 */
public interface ReadOnlyThreeTriosModel {

  /**
   * Checks if the game is over. The game ends when all cardholder cells are filled.
   *
   * @return true if the game is over, false otherwise
   * @throws IllegalStateException if the game has not started
   */
  boolean isGameOver();

  /**
   * Returns a copy of the grid at the current game state. Modifying this grid will not have an
   * effect on the game. Indexes are 0-based.
   * @return a copy of the grid used in the game
   * @throws IllegalStateException if the game has not started
   */
  GridCell[][] getGrid();

  /**
   * Returns the player who is currently taking their turn. Modifying the returned player will have
   * no effect on the state of the game.
   * @return player current making their move
   * @throws IllegalStateException if the game has not started
   */
  IPlayer getTurn();

  /**
   * Returns the players involved in a Three Trios game. Modifying the returned players will not
   * have an effect on the state of the game.
   * @return all players in the gae
   */
  List<IPlayer> getPlayers();

  /**
   * Gets the card at a coordinate placed in a cell if it exists.
   * @param row the row (y-coordinate) on the grid
   * @param col the column (x-coordinate) on the grid
   * @return the card on the grid
   * @throws IllegalStateException if the game has not started
   */
  Card cardAtCoordinate(int row, int col);

  /**
   * Gets the owner of a card based off of a coordinate.
   * @param row the row (y-coordinate) on the grid
   * @param col the column (x-coordinate) on the grid
   * @return the player/owner that currently has the card on the grid in their color
   * @throws IllegalStateException if the game has not started
   */
  IPlayer ownerAtCoordinate(int row, int col);

  /**
   * Determines how many cards a player can flip in a given turn with a card.
   * @param p player that is currently deliberating whether to play a card
   * @param c card of interest that may be played
   * @param row the row (y-coordinate) on the grid
   * @param col the column (x-coordinate) on the grid
   * @return the number of cards that player can flip with their card
   * @throws IllegalStateException if the game has not started or the game is over
   * @throws IllegalArgumentException if the given coordinates are invalid, the specified cell is a
   *     hole, or if the cell is already occupied
   * @throws IllegalArgumentException if the card does not exist in the player's hand
   * @throws IllegalStateException if it is not the designated player's turn
   */
  int numCardsFlipped(IPlayer p, Card c, int row, int col);

  /**
   * Retrieves the number of rows in the grid (y-dimension).
   * @throws IllegalStateException if the game has not started
   */
  int numRowsInGrid();

  /**
   * Retrieves the number of columns in the grid (x-dimension).
   * @throws IllegalStateException if the game has not started
   */
  int numColsInGrid();

  /**
   * Determines a player's score in a given game. A player earns a point for every card they own on
   * the grid and in their hand.
   * @param player player whose score is to be calculated
   * @return number of cards that is in their color on the grid plus number of cards in the player's
   *     hand
   * @throws IllegalStateException if the game is started or the game is not over
   */
  int determinePlayerScore(IPlayer player);

}
