package cs3500.threetrios.originalcode.controller;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Defines the actions for a human player of ThreeTrios. A human can set the row, column, and/or
 * card index in their hand.
 */
public class HumanPlayer implements GPlayer {
  private int cardNum;
  private int row;
  private int col;
  private Player player;

  /**
   * Sets the values of the column, row, and card index to -1.
   */
  public HumanPlayer(ReadOnlyThreeTriosModel model, Player player) {
    col = -1;
    row = -1;
    cardNum = -1;
    this.player = player;
  }

  @Override
  public Move getMove() {
    return new Move(cardNum, row, col);
  }

  @Override
  public void setRow(int newRow) {
    row = newRow;
  }

  @Override
  public void setCol(int newCol) {
    col = newCol;
  }

  @Override
  public void setCardNum(int newCardNum) {
    cardNum = newCardNum;
  }

  @Override
  public boolean isHuman() {
    return true;
  }

  @Override
  public void resetMove() {
    row = -1;
    col = -1;
    cardNum = -1;
  }

  @Override
  public boolean isDefaultMove() {
    return false;
  }

  @Override
  public Player getPlayer() {
    return player;
  }
}
