package controller;

import java.util.List;

import model.Card;
import model.GridCell;

/**
 * Represents a controller that is used to set up a Three Trios game, as well as play through it.
 */
public interface ReadingThreeTriosController {

 /**
  * Sets up a grid for the model to use. Grid coordinates are 0-based, where (3,3) would be the
  * fourth column in the fourth row of a grid. The origin is (0,0), which is the topmost, lefmost
  * cell in the grid. The x-axis is the second dimension of the grid (columns going from left to
  * right), and the y-axis is the first dimension of the grid (rows going from top to bottom). The
  * Cartesian coordinate of a cell in the grid is (row, column), where the x-coordinate is
  * determined by how far down the grid the cell is and the y-coordinate is determined by how far
  * right on the grid the cell is.
  * @param gridConfigFile the grid configuration file that is scanned to build a grid
  * @return the completed grid that the model can use
  * @throws IllegalArgumentException if the configuration file is invalid (i.e. columns do not
  *     match up with specified dimensions)
  * @throws IllegalStateException if the number of rows in the created grid does not match the
  *     specified number of rows (board is not being generated correctly)
  */
 GridCell[][] setupGrid(String gridConfigFile);

  /**
   * Sets up a deck for the model to use to initially distribute hands to the players to start the
   * game.
   * @param cardDataFile the card data configuration file that is scanned to build the deck
   * @return the completed deck that the model can use
   * @throws IllegalArgumentException if the configuration file is invalid (i.e. columns do not
   *     match up with specified dimensions)
   * @throws IllegalStateException if the number of card cells on the grid is not add
   * @throws IllegalArgumentException if cards are formatted incorrectly in the configuration file,
   *     have garbage values, or have invalid attack values
   */
  List<Card> setupDeck(String cardDataFile);

}
