package cs3500.threetrios.newcode;

import java.util.List;

import cs3500.threetrios.providercode.controller.ModelStatusFeatures;
import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.GridCell;
import cs3500.threetrios.providercode.model.ThreeTriosModel;
import cs3500.threetrios.providercode.player.IPlayer;

public class ModelAdapter implements ThreeTriosModel {
  private cs3500.threetrios.originalcode.model.ThreeTriosModel model;
  public ModelAdapter(){
    model = null;
  }
  /**
   * Checks if the game is over. The game ends when all cardholder cells are filled.
   *
   * @return true if the game is over, false otherwise
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public boolean isGameOver() {
    return false;
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
    return new GridCell[0][];
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
    return null;
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
    return 0;
  }

  /**
   * Retrieves the number of columns in the grid (x-dimension).
   *
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public int numColsInGrid() {
    return 0;
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
    return 0;
  }

  /**
   * Sets up the game by setting up the grid and initializing the deck.
   * Process configurations and produces the grid and deck from controller-read files.
   *
   * @param grid the grid/board that the game is going to start with
   * @param deck the deck that the game is going to start with; players' hands are created from
   *             this deck
   * @throws IllegalStateException    if the game has already started or the game is over
   * @throws IllegalArgumentException if the deck contains cards with the same name
   */
  @Override
  public void setupGame(GridCell[][] grid, List<Card> deck) {

  }

  /**
   * Places a card from the player's hand onto the specified grid location.
   *
   * @param p       player that is currently about to play a card
   * @param handIdx 0-based index of the card in the player's hand
   * @param row     the row of the grid where the card should be placed
   * @param col     the column of the grid where the card should be placed
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalArgumentException if the hand index is out of bounds, the given coordinates
   *                                  are invalid, the specified cell is a hole, or if the cell is already occupied
   * @throws IllegalStateException    if it is not the designated player's turn
   */
  @Override
  public void playToBoard(IPlayer p, int handIdx, int row, int col) {

  }

  /**
   * Initiates a battle with the provided card, deciding ownership of cards based on some variant
   * rule. When battling across coordinates, coordinates are 0-based.
   *
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalStateException    if a card has not been placed on the board this turn
   * @throws IllegalArgumentException if the card being referenced is not the last played card
   */
  @Override
  public void battle() {

  }

  /**
   * Determines the winning player of the game based on the current board state. A player wins if
   * they own more cards on the board/grid and their hand than their opponent.
   *
   * @return the player who has won the game, or null if it's a tie
   * @throws IllegalStateException if the game is started or the game is not over
   */
  @Override
  public IPlayer determineWinner() {
    return null;
  }

  /**
   * Adds a model-status feature listener to the list of listeners.
   *
   * @param features the model status listener that will allow transmission of model-status messages
   * @throws IllegalArgumentException if the feature listener is null
   */
  @Override
  public void addModelStatusFeaturesListener(ModelStatusFeatures features) {

  }

  /**
   * Commences formally starting the game, where the turn is set to the red turn. Cards are
   * distributed to a player's hand by back-and-forth. Because red plays first, blue gets
   * distributed the first card. Lastly, each player/their controller is notified that the red
   * player will be considering their first move.
   *
   * @throws IllegalStateException if hands cannot be filled appropriately to start the game
   */
  @Override
  public void start() {

  }

  /**
   * Keeps track of ending the turn for the current player once they have completed their move. Upon
   * checking if the game is over, it either notifies the players and their controllers that the
   * game is over or that the game is continuing and the current player/turn has changed.
   */
  @Override
  public void endTurn() {

  }

  /**
   * Adds the respective red and blue players to the game. Players can either be human or machine,
   * playing off of a strategy.
   *
   * @param redPlayer  red player
   * @param bluePlayer blue player
   * @throws IllegalArgumentException if the players themselves or their hands are invalid
   */
  @Override
  public void addPlayers(IPlayer redPlayer, IPlayer bluePlayer) {

  }

  /**
   * Notifies each player/controller that the turn has changed. This is achieved by each player/
   * controller listening in regard to the current turn.
   */
  @Override
  public void notifyEachPlayerTurnChanged() {

  }

  /**
   * Notifies each player/controller that something about the game state has changed, i.e. whether a
   * player has played to the board, cards are flipped, the game is over, etc.
   */
  @Override
  public void notifyChanged() {

  }
}
