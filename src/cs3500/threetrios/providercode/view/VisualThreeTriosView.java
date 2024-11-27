package cs3500.threetrios.providercode.view;

import cs3500.threetrios.providercode.controller.PlayerActionFeatures;
import cs3500.threetrios.providercode.player.IPlayer;

/**
 * Represents a visual Three Trios view.
 */
public interface VisualThreeTriosView {

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Adds all respective features that implement view behaviors.
   * @param features feature that allow the GUI to behave in various ways
   */
  void addPlayerActionFeatures(PlayerActionFeatures features);

  /**
   * Enables the view to be used, where it can use JFrame functionalities.
   * @param bool whether the view will be enabled or not
   */
  void setEnabled(boolean bool);

  /**
   * Updates the title of the view of the respective player. The name (color) of the player never
   * changes obviously, but the current turn/player that is considering a move is constantly changed
   * to reflect the game.
   * @param p that is playing on that view
   * @throws IllegalArgumentException if the player is null/invalid
   */
  void updateTitle(IPlayer p);

  /**
   * Prevents clicking on the opponent's hand.
   * @param p respective player playing on the view.
   * @throws IllegalArgumentException if the player is null/invalid
   */
  void preventClickOppHand(IPlayer p);

  /**
   * Creates a popup message based on the occasion (game over, invalid move, etc.).
   * @param message message to be displayed
   * @param title title of message (see first line of javadoc)
   * @param msgType type of message to be displayed
   */
  void createPopup(Object message, String title, int msgType);

}
