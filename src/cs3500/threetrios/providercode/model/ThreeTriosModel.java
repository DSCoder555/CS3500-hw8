package cs3500.threetrios.providercode.model;

import java.util.List;

import cs3500.threetrios.providercode.controller.ModelStatusFeatures;
import cs3500.threetrios.providercode.player.IPlayer;

/**
 * Represents a mutable model part of a game of ThreeTrios.
 */
public interface ThreeTriosModel extends ReadOnlyThreeTriosModel {

  /**
   * Sets up the game by setting up the grid and initializing the deck.
   * Process configurations and produces the grid and deck from controller-read files.
   *
   * @param grid the grid/board that the game is going to start with
   * @param deck the deck that the game is going to start with; players' hands are created from
   *             this deck
   * @throws IllegalStateException if the game has already started or the game is over
   * @throws IllegalArgumentException if the deck contains cards with the same name
   */
  void setupGame(GridCell[][] grid, List<Card> deck);

  /**
   * Places a card from the player's hand onto the specified grid location.
   *
   * @param p player that is currently about to play a card
   * @param handIdx 0-based index of the card in the player's hand
   * @param row the row of the grid where the card should be placed
   * @param col the column of the grid where the card should be placed
   * @throws IllegalStateException if the game has not started or the game is over
   * @throws IllegalArgumentException if the hand index is out of bounds, the given coordinates
   *     are invalid, the specified cell is a hole, or if the cell is already occupied
   * @throws IllegalStateException if it is not the designated player's turn
   */
  void playToBoard(IPlayer p, int handIdx, int row, int col);

  /**
   * Initiates a battle with the provided card, deciding ownership of cards based on some variant
   * rule. When battling across coordinates, coordinates are 0-based.
   *
   * @throws IllegalStateException if the game has not started or the game is over
   * @throws IllegalStateException if a card has not been placed on the board this turn
   * @throws IllegalArgumentException if the card being referenced is not the last played card
   */
  void battle();

  /**
   * Determines the winning player of the game based on the current board state. A player wins if
   * they own more cards on the board/grid and their hand than their opponent.
   *
   * @return the player who has won the game, or null if it's a tie
   * @throws IllegalStateException if the game is started or the game is not over
   */
  IPlayer determineWinner();

  /**
   * Adds a model-status feature listener to the list of listeners.
   * @param features the model status listener that will allow transmission of model-status messages
   * @throws IllegalArgumentException if the feature listener is null
   */
  void addModelStatusFeaturesListener(ModelStatusFeatures features);

  /**
   * Commences formally starting the game, where the turn is set to the red turn. Cards are
   * distributed to a player's hand by back-and-forth. Because red plays first, blue gets
   * distributed the first card. Lastly, each player/their controller is notified that the red
   * player will be considering their first move.
   * @throws IllegalStateException if hands cannot be filled appropriately to start the game
   */
  void start();

  /**
   * Keeps track of ending the turn for the current player once they have completed their move. Upon
   * checking if the game is over, it either notifies the players and their controllers that the
   * game is over or that the game is continuing and the current player/turn has changed.
   */
  void endTurn();

  /**
   * Adds the respective red and blue players to the game. Players can either be human or machine,
   * playing off of a strategy.
   * @param redPlayer red player
   * @param bluePlayer blue player
   * @throws IllegalArgumentException if the players themselves or their hands are invalid
   */
  void addPlayers(IPlayer redPlayer, IPlayer bluePlayer);

  /**
   * Notifies each player/controller that the turn has changed. This is achieved by each player/
   * controller listening in regard to the current turn.
   */
  void notifyEachPlayerTurnChanged();

  /**
   * Notifies each player/controller that something about the game state has changed, i.e. whether a
   * player has played to the board, cards are flipped, the game is over, etc.
   */
  void notifyChanged();

}
