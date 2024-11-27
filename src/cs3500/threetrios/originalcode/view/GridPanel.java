package cs3500.threetrios.originalcode.view;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.EmptyCard;
import cs3500.threetrios.originalcode.model.HoleCard;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Represents the playing grid as a panel, displaying the cards with their appropriate
 * background colors.
 */
public class GridPanel extends JPanel implements GridPanelInterface {

  private Card[][] grid;
  private int gridWidth;
  private int gridHeight;
  private int cardWidth;
  private int gridCardHeight;
  private ReadOnlyThreeTriosModel model;


  /**
   * Sets up the playing grid and the width and height of each card.
   *
   * @param grid           The playing grid
   * @param cardWidth      The card width.
   * @param gridCardHeight The card height.
   * @throws IllegalArgumentException if grid is null or if the card width or height are 0 or
   *                                  negative.
   */
  public GridPanel(Card[][] grid, int cardWidth, int gridCardHeight,
                   ReadOnlyThreeTriosModel model) {
    if (grid == null || model == null) {
      throw new IllegalArgumentException("Grid and/or model are null");
    }
    if (cardWidth <= 0 || gridCardHeight <= 0) {
      throw new IllegalArgumentException("Card width and grid height must be positive values");
    }
    this.grid = grid;
    this.cardWidth = cardWidth;
    this.gridCardHeight = gridCardHeight;
  }


  @Override
  public void initGrid(Card[][] initialGrid) {
    CardPanel curCard;
    if (initialGrid == null) {
      throw new IllegalArgumentException("Grid cannot be null");
    }

    this.setLayout(new GridLayout(initialGrid.length, initialGrid[0].length));
    for (Card[] cards : initialGrid) {
      for (Card card : cards) {
        if (card == null) {
          throw new IllegalArgumentException("Card cannot be null");
        }
        if (card.getBackgroundColor() == Color.YELLOW) {
          curCard = new CardPanel(new EmptyCard());
        } else if (card.getBackgroundColor() == Color.GRAY) {
          curCard = new CardPanel(new HoleCard());
        } else {
          curCard = new CardPanel(card);
        }
        curCard.setBackground(card.getBackgroundColor());
        this.add(curCard);
      }
    }
    this.gridWidth = this.cardWidth * initialGrid[0].length;
    this.gridHeight = this.gridCardHeight * initialGrid.length;
    this.revalidate();
    this.repaint();
  }

  @Override
  public void resetGrid() {
    initGrid(grid);
  }

  @Override
  public void returnGridCoordinatesMouseClicked(MouseEvent e) {
    // Calculate individual cell dimensions
    int numRows = grid.length;
    int numCols = grid[0].length;
    int cellHeight = gridHeight / numRows;
    int cellWidth = gridWidth / numCols;

    // Determine the row and column based on mouse click coordinates
    int row = e.getY() / cellHeight;
    int col = e.getX() / cellWidth;

    if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
      System.out.println("Click is out of grid bounds.");
      return;
    }
    System.out.println("Clicked at row " + row + " and col " + col);
  }


  @Override
  public void displayCardOnGrid(int cardIdx, int rowIdx, int colIdx) {
    // needs to override but not used
  }

  @Override
  public void updateCell(int rowIdx, int colIdx, Card newCard) {
    // access the element at rowIdx & colIdx

  }

  @Override
  public void refreshGrid() {
    this.revalidate();
    this.repaint();
  }

  @Override
  public void renderGridState(Card[][] gridState) {
    if (gridState == null) {
      throw new IllegalArgumentException("Grid cannot be null");
    }
    this.revalidate();
    this.repaint();
  }

  // Note: controller will use this method and do model.getGrid()
  @Override
  public void updateGrid(Card[][] playingGrid) {
    // TODO: Add click listeners?
    CardPanel curCardPanel;
    this.removeAll();
    for (int row = 0; row < playingGrid.length; row++) {
      // TODO: DO I need to Add a listener to each grid cell (or CardPanel in the grid)
      //  to handle events like selecting a position to play a card?
      for (int col = 0; col < playingGrid[0].length; col++) {
        Card curCard = playingGrid[row][col];
        if (playingGrid[row] == null) {
          // Handle this more gracefully potentially
          throw new IllegalArgumentException("Card cannot be null");
        }
        if (curCard.getBackgroundColor() == Color.YELLOW) {
          curCardPanel = new CardPanel(new EmptyCard());
        } else if (curCard.getBackgroundColor() == Color.GRAY) {
          curCardPanel = new CardPanel(new HoleCard());
        } else {
          curCardPanel = new CardPanel(curCard);
        }
        curCardPanel.setBackground(curCard.getBackgroundColor());
        this.add(curCardPanel);
      }
    }
    refreshGrid();
  }


  @Override
  public void renderGameState() {
    renderGridState(grid);
  }

}
