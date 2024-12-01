package cs3500.threetrios.newcode;

import java.util.List;

import cs3500.threetrios.originalcode.controller.GPlayer;
import cs3500.threetrios.originalcode.controller.ThreesTrioController;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;
import cs3500.threetrios.originalcode.view.ThreeTriosGUI;
import cs3500.threetrios.providercode.controller.ITTController;
import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.GridCell;
import cs3500.threetrios.providercode.view.VisualThreeTriosView;

public class ControllerAdapter extends ThreesTrioController {

  /**
   * Sets up the model, view, the two players of the game, the current player, and starts rendering
   * the view.
   *
   * @param model      The model to set up.
   * @param gamePlayer The player to initialize.
   * @param view       The view to set up.
   * @throws IllegalArgumentException throws if model, gamePlayer or view are null
   */
  public ControllerAdapter(ThreeTriosModel model, GPlayer gamePlayer, ThreeTriosGUI view) {
    super(model, gamePlayer, view);
  }
}
