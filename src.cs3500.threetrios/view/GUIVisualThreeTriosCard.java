package view;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents a visual card to be drawn in a visual view.
 */
public class GUIVisualThreeTriosCard extends Path2D.Double implements VisualThreeTriosCard {

  /**
   * Constructor for a visual card, which is a rectangle designed to fit either the grid or the
   * hands.
   * @param width width of the card in its respective context
   * @param length length of the card in its respective context
   */
  public GUIVisualThreeTriosCard(double width, double length) {

    Rectangle2D.Double visualCard = new Rectangle2D.Double(0, 0, width, length);
    append(visualCard, false);

  }

}
