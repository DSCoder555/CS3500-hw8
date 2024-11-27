package cs3500.threetrios.originalcode.view;

// pass it as a view feature
// make a class that manages printing out these notif, put it in view

import cs3500.threetrios.originalcode.model.Player;

/**
 * Defines the behaviors of the controller to listen for and respond to events coming
 * from the model such as notifying that it is a player's turn, notifying of an invalid
 * move/action, and notifying that the game is won with the corresponding player's score.
 * Messages are displayed using JOptionPane.
 */
public interface ModelListenerInterface {

  /**
   * Notifies the current player that it is their turn.
   *
   * @throws IllegalArgumentException if player is none or null.
   */
  void notifyPlayerTurn();

  /**
   * Notifies a player with a relevant message on a JOptionPane if they perform any of the following
   * invalid actions:
   * - trying to place cards on the grid before selecting a card from their hand
   * - choosing a card from the other player's hand at any point
   * - interacting with the GUI (clicking on the grid or the hands) when it's not their turn
   * this method prevents them from taking those
   * actions and outputs a relevant message on a JOptionPane.
   *
   * @throws IllegalArgumentException if message is null
   */
  void notifyInvalidAction(String message);

  /**
   * When the game is over, notifies which player won the game and what their score was.
   *
   * @param winner The player who won the game.
   * @param score  The score of the winning player.
   * @throws IllegalStateException    if game is not over yet.
   * @throws IllegalArgumentException if winner is none or null or score is negative.
   */
  void notifyGameWon(Player winner, int score);

  /**
   * Updates the game, including the hand and grid states and sets the current player as a JLabel
   * on the GUI.
   */
  void gameUpdated();

  /**
   * Returns the current player.
   *
   * @return the current player.
   */
  Player getPlayer();
}
