package cs3500.threetrios.newcode;

import java.util.List;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.providercode.controller.ModelStatusFeatures;
import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.GridCell;
import cs3500.threetrios.providercode.model.PlayerColor;
import cs3500.threetrios.providercode.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.providercode.model.ThreeTriosModel;
import cs3500.threetrios.providercode.player.IPlayer;

public class ModelAdapter implements ReadOnlyThreeTriosModel {
  private cs3500.threetrios.originalcode.model.ThreeTriosModel model;
  public ModelAdapter(cs3500.threetrios.originalcode.model.ThreeTriosModel model){
    this.model = model;
  }
  /**
   * Checks if the game is over. The game ends when all cardholder cells are filled.
   *
   * @return true if the game is over, false otherwise
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  /**
   * Returns a copy of the grid at the current game state. Modifying this grid will not have an
   * effect on the game. Indexes are 0-based.
   *
   * @return a copy of the grid used in the game
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public GridCell[][] getGrid() {
    cs3500.threetrios.originalcode.model.Card[][] tempGrid =  model.getGrid();
    GridCell[][] returnGrid = new GridCell[tempGrid.length][tempGrid[0].length];
    for (int row = 0; row < tempGrid.length; row++) {
      for (int col = 0; col < tempGrid[0].length; col++) {
          returnGrid[row][col] = new GridCellAdapter(tempGrid[row][col]);
      }
    }
    return returnGrid;
  }

  /**
   * Returns the player who is currently taking their turn. Modifying the returned player will have
   * no effect on the state of the game.
   *
   * @return player current making their move
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public IPlayer getTurn() {
    return null;
  }

  /**
   * Returns the players involved in a Three Trios game. Modifying the returned players will not
   * have an effect on the state of the game.
   *
   * @return all players in the gae
   */
  @Override
  public List<IPlayer> getPlayers() {
    return null;
  }

  /**
   * Gets the card at a coordinate placed in a cell if it exists.
   *
   * @param row the row (y-coordinate) on the grid
   * @param col the column (x-coordinate) on the grid
   * @return the card on the grid
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public Card cardAtCoordinate(int row, int col) {
    return new CardAdapter(model.getCard(row,col));
  }

  /**
   * Gets the owner of a card based off of a coordinate.
   *
   * @param row the row (y-coordinate) on the grid
   * @param col the column (x-coordinate) on the grid
   * @return the player/owner that currently has the card on the grid in their color
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public IPlayer ownerAtCoordinate(int row, int col) {
    return null;
  }

  /**
   * Determines how many cards a player can flip in a given turn with a card.
   *
   * @param p   player that is currently deliberating whether to play a card
   * @param c   card of interest that may be played
   * @param row the row (y-coordinate) on the grid
   * @param col the column (x-coordinate) on the grid
   * @return the number of cards that player can flip with their card
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalArgumentException if the given coordinates are invalid, the specified cell is a
   *                                  hole, or if the cell is already occupied
   * @throws IllegalArgumentException if the card does not exist in the player's hand
   * @throws IllegalStateException    if it is not the designated player's turn
   */
  @Override
  public int numCardsFlipped(IPlayer p, Card c, int row, int col) {
    return 0;
  }

  /**
   * Retrieves the number of rows in the grid (y-dimension).
   *
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public int numRowsInGrid() {
    return model.gridHeight();
  }

  /**
   * Retrieves the number of columns in the grid (x-dimension).
   *
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public int numColsInGrid() {
    return model.gridLength();
  }

  /**
   * Determines a player's score in a given game. A player earns a point for every card they own on
   * the grid and in their hand.
   *
   * @param player player whose score is to be calculated
   * @return number of cards that is in their color on the grid plus number of cards in the player's
   * hand
   * @throws IllegalStateException if the game is started or the game is not over
   */
  @Override
  public int determinePlayerScore(IPlayer player) {
    if (player.getPlayerColor() == PlayerColor.RED){
      return model.getPlayerScore(Player.Red);
    }
    else{
      return model.getPlayerScore(Player.Blue);
    }
  }
}
