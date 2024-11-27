package cs3500.threetrios.originalcode.model;


import cs3500.threetrios.originalcode.view.ModelListenerInterface;

/**
 * Defines the behaviors and rules for a game of ThreeTrios, a two-player strategy game
 * played on a customizable grid with a set of unique cards. The game is a variation of
 * the classic card game Triple Triad.
 * The game consists of four main elements:
 * -A grid of cells where players place cards. The grid is customizable and layout
 * via a configuration file, and it must have an odd number of card cells to be valid.
 * -A color designation for each player: Red or Blue.
 * -A hand of cards dealt randomly to each player at the beginning of the game, with each
 * player receiving exactly (N+1)/2 cards, where N is the number of card cells on the grid.
 * -A collection of cards used to form the hands and fill the grid at the start of the game.
 * The objective of Three Trios is to control more cards on the grid than the opponent
 * by the end of the game. The game begins with an empty grid and each player placing cards
 * in turn, starting with Player Red. On each turn, two phases occur:
 * -Placing Phase: The current player places a card in an empty cell on the grid.
 * -Battle Phase: The placed card battles adjacent opponent cards. If any adjacent
 * cards lose the battle, they are flipped to the current player's color.
 * The game concludes when all card cells on the grid are filled. The winner is the player
 * with the most owned cards on the grid and in hand. If both players have the same number of
 * owned cards, the game ends in a tie.
 */
public interface ThreeTriosModel extends ReadOnlyThreeTriosModel {

  /**
   * Starts the game of Three Trios with the given deck. The supplied deck is used to set up
   * the player's hands and the grid.
   *
   * @throws IllegalStateException    if the game has already started.
   * @throws IllegalArgumentException if the supplied deck is null or if the supplied deck size
   *                                  is too small to start the game.
   */
  void startGame();

  /**
   * Plays the given card from the hand to the chosen location on the playing grid. If it's a
   * valid play, we enter the attack phase. After the attack phase, if the play causes the current
   * player to win, the game is over. Otherwise, we switch the current player.
   *
   * @param handIdx a 0-index number representing the card to play from the hand.
   * @param row     a 0-index number representing the row of the grid the client desires to play
   *                to.
   * @param col     a 0-index number representing the column of the grid the client desires to play
   *                to.
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalArgumentException if the requested card to play from the hand does not exist,
   *                                  if the requested row or column to play to does not exist on
   *                                  the grid, or if the location at the given row and column is a
   *                                  hole card.
   */
  void playCard(int handIdx, int row, int col);

  /**
   * Returns whether the game has been won. For the game to have been won, it must meet these
   * conditions:
   * The {@link GameState} must be PostGame.
   * If not above, every original empty card on the grid must be populated.
   *
   * @return whether the game has been won.
   * @throws IllegalStateException if the game has not started yet.
   */
  boolean hasWon();

  /**
   * Returns the current winner of ThreeTrios game. If there are more of Player Red's cards
   * on the grid, Player Red is the winner. Otherwise, Player Blue is the winner.
   *
   * @return the current winner of ThreeTrios game.
   */
  Player getWinner();

  /**
   * Returns the current player.
   *
   * @return the current player.
   * @throws IllegalStateException if the game has not started yet.
   */
  Player currentPlayer();

  /**
   * Returns a string representation of the grid with rows separated by newlines.
   * Example output for a 3x3 grid:
   * XOX
   * OXO
   * XXO
   *
   * @return a string representation of the grid with rows separated by newlines.
   */
  String gridString();

  /**
   * Returns a string representation of the player's hand, with each card on a new line.
   * Example output for a player's hand:
   * HeroKnight 7 3 9 A
   * SkyWhale 5 4 8 7
   * AngryDragon 6 5 9 9
   *
   * @return a string representation of the player's hand.
   */
  String currentHandString();

  /**
   * Adds the controller as a listener to the model.
   *
   * @param listener The listeners that the controller will subscribe as to the model.
   */
  void addModelListener(ModelListenerInterface listener);

}
