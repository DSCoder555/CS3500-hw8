package cs3500.threetrios.originalcode.controller;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Defines behaviors for the AI strategy that follows a minimax style, meaning that the AI chooses
 * move that leaves their opponent in a situation with no good moves. This strategy relies on the
 * other strategies to decide which one to use.
 */
public class AIPlayerOutsmart extends AIPlayerBase {

  private Player oppPlayer;
  private boolean defaultMove;

  /**
   * Sets up the model, current player, and opponent player.
   *
   * @param model  The model to set up.
   * @param player The player to initialize.
   */
  public AIPlayerOutsmart(ReadOnlyThreeTriosModel model, Player player) {
    super(model, player);
    if (player == Player.Red) {
      oppPlayer = Player.Blue;
    } else {
      oppPlayer = Player.Red;
    }
    defaultMove = false;
  }

  @Override
  public Move getMove() {
    defaultMove = false;
    if ((model.getOwner(model.gridHeight() - 1, 0) == oppPlayer) ||
            (model.getOwner(0, model.gridLength() - 1) == oppPlayer) ||
            (model.getOwner(model.gridHeight() - 1, model.gridLength() - 1)
                    == oppPlayer)) {
      GPlayer temp = new AIPlayerCorner(model, player);
      Move tempMove = temp.getMove();
      if (!temp.isDefaultMove()) {
        return tempMove;
      }
    }
    if (model.getPlayerScore(oppPlayer) < model.getPlayerScore(player)) {
      GPlayer temp = new AIPlayerUnflippable(model, player);
      return temp.getMove();
    } else if (model.getPlayerScore(oppPlayer) > model.getPlayerScore(player)) {
      GPlayer temp = new AIPlayerFlipper(model, player);
      return temp.getMove();
    } else {
      return super.getMove();
    }
  }

  @Override
  public boolean isDefaultMove() {
    return defaultMove;
  }
}
