package cs3500.threetrios.newcode;

import java.awt.*;

import javax.swing.*;

import cs3500.threetrios.originalcode.view.ThreeTriosGUI;
import cs3500.threetrios.originalcode.view.ViewFeatures;
import cs3500.threetrios.providercode.model.PlayerColor;
import cs3500.threetrios.providercode.view.VisualThreeTriosView;

public class ViewAdapter implements ThreeTriosGUI {
  private VisualThreeTriosView view;
  private PlayerColor playerColor;
  public ViewAdapter(VisualThreeTriosView view, PlayerColor color){
    this.view = view;
    this.playerColor = color;
  }
  /**
   * Adds a feature listener to the view to handle user interactions.
   *
   * @param features the ViewFeatures listener that handles specific user actions
   */
  @Override
  public void addFeatureListener(ViewFeatures features) {
    if (features instanceof ViewListenerAdapter){
      ViewListenerAdapter adapter = (ViewListenerAdapter) features;
      view.addPlayerActionFeatures(adapter);
    }
  }

  /**
   * Renders the two main components: the player's hand
   * and the playing grid.
   */
  @Override
  public void startRendering() {
    view.makeVisible();
    view.updateTitle(new GamePlayer(playerColor, null));
  }

  /**
   * Sets the visibility of the window based on the argument the client passes in.
   *
   * @param show A variable determining whether the window should be visible.
   */
  @Override
  public void display(boolean show) {
    view.setEnabled(true);
  }

  /**
   * Outputs a message "Yay!" to indicate a valid move or action has been made and
   * re-renders the game state.
   */
  @Override
  public void advance() {
    view.refresh();
    view.updateTitle(new GamePlayer(playerColor, null));
  }

  /**
   * Outputs a message "OOPS!" to indicate an invalid move or action has been made and
   * re-renders the game state.
   */
  @Override
  public void error() {
    System.out.println("OOPS!");
    view.refresh();
  }

  /**
   * Displays the supplied message on an JOptionPane object.
   *
   * @param message The message to display.
   * @throws IllegalArgumentException if message is null.
   */
  @Override
  public void displayMessage(String message) {
    view.createPopup(message,"Message", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Displays the supplied error message on an JOptionPane object.
   *
   * @param message The message to display.
   * @throws IllegalArgumentException if message is null.
   */
  @Override
  public void errorMessage(String message) {
    view.createPopup(message,"Message", JOptionPane.ERROR_MESSAGE);
  }
}
