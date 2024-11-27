package model;

/**
 * Represents a cell in the game grid of ThreeTrios.
 * Each cell has a specific type and may hold a card.
 */
public interface GridCell {

  /**
   * Retrieves the type of this cell.
   *
   * @return the CellType representing the type of this cell
   */
  CellType getCellType();

  /**
   * Retrieves the card currently in this cell, if any.
   *
   * @return the Card present in this cell, or null if the cell is empty
   */
  Card getCard();

}
