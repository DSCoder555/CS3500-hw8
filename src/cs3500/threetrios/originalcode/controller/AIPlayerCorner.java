package cs3500.threetrios.originalcode.controller;

import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Defines the behaviors for the strategy where the AI aims to play cards in the corners to expose
 * as few attack values as possible. Has a flag variable to keep track of whether we have to
 * fall back to the AIPlayerBase strategu.
 */
public class AIPlayerCorner extends AIPlayerBase {

  private boolean defaultMove;

  /**
   * Sets up the model and sets the default move to false.
   *
   * @param model  The model to set up.
   * @param player The player to initialize.
   */
  public AIPlayerCorner(ReadOnlyThreeTriosModel model, Player player) {
    super(model, player);
    defaultMove = false;
  }

  @Override
  public Move getMove() {
    defaultMove = false;
    Move bestMove = null;
    int bestFlipDifficulty = -1;
    if (model.isLegal(0, 0)) {
      for (int i = 0; i < model.getHand(player).size(); i++) {
        Card currCard = model.getHand(player).get(i);
        int flipDifficulty = currCard.bottomValue() + currCard.rightValue();
        if (flipDifficulty > bestFlipDifficulty) {
          bestMove = new Move(i, 0, 0);
          bestFlipDifficulty = flipDifficulty;
        }
      }
    }
    if (model.isLegal(0, model.gridLength() - 1)) {
      for (int i = 0; i < model.getHand(player).size(); i++) {
        Card currCard = model.getHand(player).get(i);
        int flipDifficulty = currCard.bottomValue() + currCard.leftValue();
        if (flipDifficulty > bestFlipDifficulty) {
          bestMove = new Move(i, 0, model.gridLength() - 1);
          bestFlipDifficulty = flipDifficulty;
        }
      }
    }
    if (model.isLegal(model.gridHeight() - 1, 0)) {
      for (int i = 0; i < model.getHand(player).size(); i++) {
        Card currCard = model.getHand(player).get(i);
        int flipDifficulty = currCard.topValue() + currCard.rightValue();
        if (flipDifficulty > bestFlipDifficulty) {
          bestMove = new Move(i, model.gridHeight() - 1, 0);
          bestFlipDifficulty = flipDifficulty;
        }
      }
    }
    if (model.isLegal(model.gridHeight() - 1, model.gridLength() - 1)) {
      for (int i = 0; i < model.getHand(player).size(); i++) {
        Card currCard = model.getHand(player).get(i);
        int flipDifficulty = currCard.topValue() + currCard.leftValue();
        if (flipDifficulty > bestFlipDifficulty) {
          bestMove = new Move(i, model.gridHeight() - 1, model.gridLength() - 1);
          bestFlipDifficulty = flipDifficulty;
        }
      }
    }
    if (bestMove == null) {
      defaultMove = true;
      return super.getMove();
    } else {
      return bestMove;
    }
  }

  @Override
  public boolean isDefaultMove() {
    return defaultMove;
  }
}
