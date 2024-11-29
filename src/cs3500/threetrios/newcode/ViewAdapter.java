package cs3500.threetrios.newcode;

import cs3500.threetrios.originalcode.view.ThreeTriosGUI;
import cs3500.threetrios.originalcode.view.ViewFeatures;
import cs3500.threetrios.providercode.view.VisualThreeTriosView;

public class ViewAdapter implements ThreeTriosGUI {
  private VisualThreeTriosView view;
  public ViewAdapter(VisualThreeTriosView view){
    this.view = view;
  }
  /**
   * Adds a feature listener to the view to handle user interactions.
   *
   * @param features the ViewFeatures listener that handles specific user actions
   */
  @Override
  public void addFeatureListener(ViewFeatures features) {

  }

  /**
   * Renders the two main components: the player's hand
   * and the playing grid.
   */
  @Override
  public void startRendering() {

  }

  /**
   * Sets the visibility of the window based on the argument the client passes in.
   *
   * @param show A variable determining whether the window should be visible.
   */
  @Override
  public void display(boolean show) {

  }

  /**
   * Outputs a message "Yay!" to indicate a valid move or action has been made and
   * re-renders the game state.
   */
  @Override
  public void advance() {

  }

  /**
   * Outputs a message "OOPS!" to indicate an invalid move or action has been made and
   * re-renders the game state.
   */
  @Override
  public void error() {

  }

  /**
   * Displays the supplied message on an JOptionPane object.
   *
   * @param message The message to display.
   * @throws IllegalArgumentException if message is null.
   */
  @Override
  public void displayMessage(String message) {

  }

  /**
   * Displays the supplied error message on an JOptionPane object.
   *
   * @param message The message to display.
   * @throws IllegalArgumentException if message is null.
   */
  @Override
  public void errorMessage(String message) {

  }
}
