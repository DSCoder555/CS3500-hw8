package cs3500.threetrios.originalcode.controller;

import java.util.Objects;

/**
 * A custom move class that returns a move that a player or AI has made, consists of 0-based hand,
 * row, and column indices.
 */
public class Move {
  private final int handIdx;
  private final int rowNum;
  private final int colNum;

  /**
   * Initializes the hand, row, and col indices to what's given.
   *
   * @param hand The hand index.
   * @param row  The row index.
   * @param col  The column index.
   * @throws IllegalArgumentException if any of the indices are negative.
   */
  public Move(int hand, int row, int col) {
    handIdx = hand;
    rowNum = row;
    colNum = col;
  }

  /**
   * Returns the hand index.
   *
   * @return the hand index.
   */
  public int getHandIdx() {
    return handIdx;
  }

  /**
   * Returns the row index.
   *
   * @return the row index.
   */
  public int getRowNum() {
    return rowNum;
  }

  /**
   * Returns the column index.
   *
   * @return the column index.
   */
  public int getColNum() {
    return colNum;
  }

  /**
   * Determines whether two Move objects are equal by comparing each of their hand, row, and col
   * indices.
   *
   * @param obj the other object to determine equality against.
   * @return whether two Move objects are equal by comparing each of their hand, row, and col
   *         indices.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Move) {
      Move otherMove = (Move) obj;
      if (handIdx == otherMove.getHandIdx() && rowNum == otherMove.getRowNum()
              && colNum == otherMove.getColNum()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the hash code of the hand, row, and col indices.
   *
   * @return the hash code of the hand, row, and col indices.
   */
  @Override
  public int hashCode() {
    return Objects.hash(handIdx, rowNum, colNum);
  }
}
