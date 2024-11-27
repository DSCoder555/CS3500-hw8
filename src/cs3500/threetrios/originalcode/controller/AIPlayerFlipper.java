package cs3500.threetrios.originalcode.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Defines the behaviors for the strategy where the AI aims to flip as many cards as possible by
 * choosing a card and position together. Has a flag variable to keep track of whether we have to
 * fall back to the AIPlayerBase strategy in case this flipping strategy does not find any moves.
 */
public class AIPlayerFlipper extends AIPlayerBase {

  private boolean defaultMove;

  /**
   * Sets up the model and sets the default move to false.
   *
   * @param model  The model to set up.
   * @param player The player to initialize.
   */
  public AIPlayerFlipper(ReadOnlyThreeTriosModel model, Player player) {
    super(model, player);
    defaultMove = false;
  }

  @Override
  public Move getMove() {
    defaultMove = false;
    if (model.isGameOver()) {
      throw new IllegalStateException();
    }
    int maxFlips = 0;
    List<Move> bestMoves = new ArrayList<>();
    for (int row = 0; row < model.gridHeight(); row++) {
      for (int col = 0; col < model.gridLength(); col++) {
        for (int cardNum = 0; cardNum < model.getHand(player).size(); cardNum++) {
          if (model.isLegal(row, col)) {
            int flips = model.getPossibleFlips(cardNum, row, col);
            if (flips > maxFlips) {
              maxFlips = flips;
              bestMoves = new ArrayList<>();
              bestMoves.add(new Move(cardNum, row, col));
            } else if (flips == maxFlips) {
              bestMoves.add(new Move(cardNum, row, col));
            }
          }
        }
      }
    }
    if (bestMoves.isEmpty()) {
      defaultMove = true;
      return super.getMove();
    } else {
      return bestMoves.get(0);
    }
  }

  @Override
  public boolean isDefaultMove() {
    return defaultMove;
  }
}
