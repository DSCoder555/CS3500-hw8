package cs3500.threetrios.originalcode.model;

import java.util.Objects;

/**
 * Defines the coordinates of a particular location on the grid.
 */
public class Coordinate {
  private final int row;
  private final int col;

  /**
   * Assigns the row and column instance variables to the supplied row and column.
   *
   * @param row The supplied row.
   * @param col The supplied column.
   */
  public Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Returns the row of the coordinate.
   *
   * @return the row of the coordinate.
   */
  public int getRow() {
    return row;
  }

  /**
   * Returns the column of the coordinate.
   *
   * @return the column of the coordinate.
   */
  public int getCol() {
    return col;
  }

  public String toString() {
    return "(" + row + "," + col + ")";
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } else if (this.getClass() != other.getClass()) {
      return false;
    } else {
      Coordinate otherCoor = (Coordinate) other;
      return (row == otherCoor.row && col == otherCoor.col);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
