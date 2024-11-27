package cs3500.threetrios.originalcode.view;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;

/**
 * Implements the behaviors needed for a view of the ThreeTrios implementation
 * that transmits information to the user. Requires the model of ThreeTrios.
 */

// TODO: Change the model type to the read only model!
public class ThreeTriosViewGame implements ThreeTriosView {
  private final ThreeTriosModel model;

  /**
   * Initializes the model.
   *
   * @param model The given model
   */
  public ThreeTriosViewGame(ThreeTriosModel model) {
    this.model = model;
  }

  @Override
  public String toString() {
    String result = "";
    if (model.getCurrentPlayer() == Player.Red) {
      result += "Player: RED\n";
    } else {
      result += "Player: BLUE\n";
    }
    result += model.gridString();
    result += "\n";
    result += "Hand:\n";
    result += model.currentHandString();
    return result;
  }
}
