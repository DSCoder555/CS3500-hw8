package cs3500.threetrios.providercode.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.CellType;
import cs3500.threetrios.providercode.model.Direction;
import cs3500.threetrios.providercode.model.PlayerColor;
import cs3500.threetrios.providercode.model.ReadOnlyThreeTriosModel;

/**
 * Represents a grid panel that is drawn for a visual view. The cells are drawn as rectangles that
 * fit the screen.
 */
public class GUIThreeTriosGridPanel extends JPanel implements ThreeTriosGridPanel {

  private ReadOnlyThreeTriosModel model;

  /**
   * Constructs a ThreeTriosGridPanel with a read-only model in its current state.
   * @param model model that is passed into the view for visualization
   */
  public GUIThreeTriosGridPanel(ReadOnlyThreeTriosModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;

  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    int numRows = model.numRowsInGrid();
    int numCols = model.numColsInGrid();
    int widthScale = this.getWidth() / numCols;
    int lengthScale = this.getHeight() / numRows;

    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        Card potentialCardInCell = model.cardAtCoordinate(r, c);
        if (potentialCardInCell != null) {
          drawCellWithCard(g2d, potentialCardInCell, c * widthScale, r * lengthScale, widthScale,
                  lengthScale);
        } else {
          drawCellWithNoCard(r, c, g2d, widthScale, lengthScale);
        }
      }
    }

  }

  /**
   * Sets the color of a card in relation to a player's color.
   * @param pc player color
   */
  private Color getColor(PlayerColor pc) {
    if (pc == PlayerColor.RED) {
      return new Color(255, 102, 102);
    }
    return new Color(102, 178, 255);
  }

  /**
   * Draws a cell with a card inside of it on the grid.
   * @param g2d graphics object to draw shapes
   * @param card card to be drawn
   * @param x x-coordinate on grid
   * @param y y-coordinate on grid
   * @param width width of card
   * @param length length of card
   */
  private void drawCellWithCard(Graphics2D g2d, Card card, int x, int y, int width, int length) {

    Color color = getColor(card.getPlayerColor());
    g2d.setColor(color);
    g2d.fillRect(x, y, width, length);
    g2d.setColor(Color.GRAY);
    g2d.drawRect(x, y, width, length);

    Font originalFont = g2d.getFont();
    int fontSize = Math.min(width, length) / 5;
    Font largerFont = originalFont.deriveFont((float) fontSize);
    g2d.setFont(largerFont);

    FontMetrics fm = g2d.getFontMetrics();

    g2d.setColor(Color.BLACK);

    int centerX = x + width / 2;
    int centerY = y + length / 2;
    int distanceFromMiddle = Math.min(width, length) / 4;

    drawAttackValue(g2d, card, Direction.North, centerX, centerY, distanceFromMiddle, fm);
    drawAttackValue(g2d, card, Direction.South, centerX, centerY, distanceFromMiddle, fm);
    drawAttackValue(g2d, card, Direction.East, centerX, centerY, distanceFromMiddle, fm);
    drawAttackValue(g2d, card, Direction.West, centerX, centerY, distanceFromMiddle, fm);

  }

  /**
   * Draws an attack value on a card.
   * @param g2d graphics object to draw shapes
   * @param card card to be drawn
   * @param direction direction of attack value
   * @param centerX center x-coordinate of card
   * @param centerY center y-coordinate of card
   * @param distanceFromMiddle distance from the middle of the card for formatting
   * @param fm FontMetrics for formatting the attack value
   */
  private void drawAttackValue(Graphics2D g2d, Card card, Direction direction, int centerX,
                               int centerY, int distanceFromMiddle, FontMetrics fm) {

    String atkVal = String.valueOf(card.getAtkVals().get(direction).getAtkVal());
    int offsetX = 0;
    int offsetY = 0;

    switch (direction) {
      case North:
        offsetY = -distanceFromMiddle;
        break;
      case South:
        offsetY = distanceFromMiddle;
        break;
      case East:
        offsetX = distanceFromMiddle;
        break;
      case West:
        offsetX = -distanceFromMiddle;
        break;
      default: throw new IllegalArgumentException("Invalid direction.");
    }

    g2d.drawString(atkVal, centerX + offsetX - fm.stringWidth(atkVal) / 2, centerY + offsetY
            + fm.getAscent() / 2);

  }

  /**
   * Draws a cell with no card. A gray cell is drawn for a hole, a yellow cell is drawn for a card
   * cell.
   * @param r row (y-coordinate) on grid
   * @param c column (x-coordinate) on grid
   * @param g2d graphics object to draw shapes
   * @param widthScale width of cell
   * @param length length (height) of cell
   */
  private void drawCellWithNoCard(int r, int c, Graphics2D g2d, int widthScale, int length) {

    if (model.getGrid()[r][c].getCellType() == CellType.Hole) {
      g2d.setColor(Color.LIGHT_GRAY);
    } else {
      g2d.setColor(Color.YELLOW);
    }

    g2d.fillRect(c * widthScale, r * length, widthScale, length);
    g2d.setColor(Color.GRAY);
    g2d.drawRect(c * widthScale, r * length, widthScale, length);

  }

  @Override
  public Dimension getPreferredSize() {
    int totalWidth = getParent().getWidth();
    int height = getParent().getHeight();
    int gridWidth = (int) (totalWidth * 0.66); // 66% of total width
    return new Dimension(gridWidth, height);
  }

}