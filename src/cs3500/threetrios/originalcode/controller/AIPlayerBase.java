package cs3500.threetrios.originalcode.controller;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Handles the strategy where the AI chooses the first available move
 * (uppermost-leftmost, respectively) and determines whether an AI can perform certain actions
 * such as setting rows and cols.
 */
public class AIPlayerBase implements GPlayer {

  protected ReadOnlyThreeTriosModel model;
  protected Player player;

  /**
   * Initializes the read-only model and current player.
   *
   * @param model  The model to set up.
   * @param player The player to set up.
   * @throws IllegalArgumentException if model is null
   */
  public AIPlayerBase(ReadOnlyThreeTriosModel model, Player player) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    if (player == null || player == Player.None) {
      throw new IllegalArgumentException("player cannot be null or none");
    }
    this.model = model;
    this.player = player;
  }

  @Override
  public Move getMove() {
    if (model.isGameOver()) {
      throw new IllegalStateException();
    }
    for (int i = 0; i < model.gridHeight(); i++) {
      for (int j = 0; j < model.gridLength(); j++) {
        if (model.isLegal(i, j)) {
          return new Move(0, i, j);
        }
      }
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean isHuman() {
    return false;
  }


  public void setRow(int newRow) {
    throw new IllegalStateException();
  }

  public void setCol(int newCol) {
    throw new IllegalStateException();
  }

  public void setCardNum(int newCardNum) {
    throw new IllegalStateException();
  }

  @Override
  public void resetMove() {
    //do nothing for AI
  }

  @Override
  public boolean isDefaultMove() {
    return true;
  }

  @Override
  public Player getPlayer() {
    return player;
  }
}
