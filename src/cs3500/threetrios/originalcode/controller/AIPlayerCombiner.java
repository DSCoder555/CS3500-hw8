package cs3500.threetrios.originalcode.controller;

import java.util.List;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Defines a structure and behaviors for combining the different AI strategies, delegates to
 * each strategy accordingly in its single method, getMove().
 */
public class AIPlayerCombiner extends AIPlayerBase {

  private final List<GPlayer> strategies;

  /**
   * Initializes the model, player, and list of possible strategies.
   *
   * @param model  The model to set up.
   * @param player The player to set up.
   * @param strats The list of possible strategies.
   * @throws IllegalArgumentException if strats is null.
   */
  public AIPlayerCombiner(ReadOnlyThreeTriosModel model, Player player, List<GPlayer> strats) {
    super(model, player);
    if (strats == null) {
      throw new IllegalArgumentException("List of strategies cannot be null");
    }
    strategies = strats;
  }

  @Override
  public Move getMove() {
    for (GPlayer strat : strategies) {
      Move currMove = strat.getMove();
      if (!strat.isDefaultMove()) {
        return currMove;
      }
    }
    return super.getMove();
  }

}
