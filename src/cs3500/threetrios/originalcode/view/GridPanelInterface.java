package cs3500.threetrios.originalcode.view;

import java.awt.event.MouseEvent;

import cs3500.threetrios.originalcode.model.Card;

/**
 * Defines the GUI behaviors for the playing grid, including initializing the grid,
 * displaying a played card at a specified location, and refreshing the grid to reflect
 * changes in the game state.
 */
public interface GridPanelInterface extends IPanel {

  /**
   * Initializes the grid in a ready-to-play state, marking hole spots with a gray color
   * and empty spots with a yellow color.
   *
   * @param initialGrid The initial configuration of cards on the grid.
   * @throws IllegalArgumentException if initialGrid is null, if any card in initialGrid is null,
   *                                  or if there are any playable cards in initialGrid.
   */
  void initGrid(Card[][] initialGrid);

  /**
   * Resets the grid to its initial empty state, clearing all cards and highlights.
   */
  void resetGrid();

  /**
   * Outputs the coordinates and name of the player who clicked on a certain spot on the playing
   * grid in a ClickInfo custom class. If the player clicks outside the boundary of the grid or
   * hand, nothing happens.
   *
   * @param e The mouse event containing the coordinates of the click.
   */
  void returnGridCoordinatesMouseClicked(MouseEvent e);

  /**
   * Displays the specified card from the player's hand on the grid at the given row and column.
   *
   * @param cardIdx The 0-based index of the card in the player's hand to play.
   * @param rowIdx  The 0-based index of the row to play the card to.
   * @param colIdx  The 0-based index of the column to play the card to.
   * @throws IllegalArgumentException if cardIdx, rowIdx, or colIdx are invalid.
   */
  void displayCardOnGrid(int cardIdx, int rowIdx, int colIdx);

  /**
   * Updates the specified cell on the grid to reflect a change in the card, such as
   * flipping a card to indicate a change in ownership or color.
   * This method replaces the existing card in the cell with the provided new card.
   *
   * @param rowIdx  The 0-based row index of the cell.
   * @param colIdx  The 0-based column index of the cell.
   * @param newCard The card to replace the old card with.
   * @throws IllegalArgumentException if rowIdx or colIdx are invalid or if newCard is null.
   */
  void updateCell(int rowIdx, int colIdx, Card newCard);

  /**
   * Refreshes the grid view to reflect any changes in the game state.
   */
  void refreshGrid();

  /**
   * Renders the current state of the playing grid, displaying all cards visually.
   * Used for full re-renders, such as after significant updates to the grid.
   * For smaller updates, like highlighting or updating a single cell, use methods
   * such as highlightCell or updateCell.
   *
   * @param gridState The 2D array representing the current grid state.
   * @throws IllegalArgumentException if gridState is null.
   */
  void renderGridState(Card[][] gridState);

  /**
   * Removes all current grid components, fetches the updated grid state, and
   * rebuilds the grid based on the model's current state.
   */
  void updateGrid(Card[][] playingGrid);
}

