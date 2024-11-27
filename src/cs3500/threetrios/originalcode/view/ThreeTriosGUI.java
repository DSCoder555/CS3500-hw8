package cs3500.threetrios.originalcode.view;

/**
 * Defines the behaviors for a GUI of ThreeTrios such as adding a feature listener,
 * rendering the components of the frame, displaying the window/frame, and
 * logging messages to indicate valid or invalid actions.
 */
public interface ThreeTriosGUI {

  /**
   * Adds a feature listener to the view to handle user interactions.
   *
   * @param features the ViewFeatures listener that handles specific user actions
   */
  void addFeatureListener(ViewFeatures features);

  /**
   * Renders the two main components: the player's hand
   * and the playing grid.
   */
  void startRendering();

  /**
   * Sets the visibility of the window based on the argument the client passes in.
   *
   * @param show A variable determining whether the window should be visible.
   */
  void display(boolean show);

  /**
   * Outputs a message "Yay!" to indicate a valid move or action has been made and
   * re-renders the game state.
   */
  void advance();

  /**
   * Outputs a message "OOPS!" to indicate an invalid move or action has been made and
   * re-renders the game state.
   */
  void error();

  /**
   * Displays the supplied message on an JOptionPane object.
   *
   * @param message The message to display.
   * @throws IllegalArgumentException if message is null.
   */
  void displayMessage(String message);

  /**
   * Displays the supplied error message on an JOptionPane object.
   *
   * @param message The message to display.
   * @throws IllegalArgumentException if message is null.
   */
  void errorMessage(String message);


}
