package cs3500.threetrios.newcode;

import java.util.List;

import cs3500.threetrios.providercode.controller.ITTController;
import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.GridCell;
import cs3500.threetrios.providercode.view.VisualThreeTriosView;

public class ControllerAdapter implements ITTController {
  /**
   * Sets up the view and commences creating a visual view.
   *
   * @param view the view that is set up with all its respective components and behaviors
   */
  @Override
  public void setView(VisualThreeTriosView view) {

  }

  /**
   * Sets up a grid for the model to use. Grid coordinates are 0-based, where (3,3) would be the
   * fourth column in the fourth row of a grid. The origin is (0,0), which is the topmost, lefmost
   * cell in the grid. The x-axis is the second dimension of the grid (columns going from left to
   * right), and the y-axis is the first dimension of the grid (rows going from top to bottom). The
   * Cartesian coordinate of a cell in the grid is (row, column), where the x-coordinate is
   * determined by how far down the grid the cell is and the y-coordinate is determined by how far
   * right on the grid the cell is.
   *
   * @param gridConfigFile the grid configuration file that is scanned to build a grid
   * @return the completed grid that the model can use
   * @throws IllegalArgumentException if the configuration file is invalid (i.e. columns do not
   *                                  match up with specified dimensions)
   * @throws IllegalStateException    if the number of rows in the created grid does not match the
   *                                  specified number of rows (board is not being generated correctly)
   */
  @Override
  public GridCell[][] setupGrid(String gridConfigFile) {
    return new GridCell[0][];
  }

  /**
   * Sets up a deck for the model to use to initially distribute hands to the players to start the
   * game.
   *
   * @param cardDataFile the card data configuration file that is scanned to build the deck
   * @return the completed deck that the model can use
   * @throws IllegalArgumentException if the configuration file is invalid (i.e. columns do not
   *                                  match up with specified dimensions)
   * @throws IllegalStateException    if the number of card cells on the grid is not add
   * @throws IllegalArgumentException if cards are formatted incorrectly in the configuration file,
   *                                  have garbage values, or have invalid attack values
   */
  @Override
  public List<Card> setupDeck(String cardDataFile) {
    return null;
  }
}
