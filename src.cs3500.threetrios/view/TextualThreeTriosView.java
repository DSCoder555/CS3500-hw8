package view;

/**
 * Textual view of a game of Three Trios.
 */
public interface TextualThreeTriosView {

  /**
   * Creates a String with a current player's game state.
   * This rendering includes
   * <ul>
   *   <li>The color of the current player</li>
   *   <li>The current board state, where each row in the game's grid board is represented by
   *   underscores for empty cells, white spaces for grids, R for cards belonging to the Red player
   *   and B for cards belonging to the blue player.</li>
   *   <li>The hand, where all cards are printed with a new line between them</li>
   * </ul>
   * An example below a potential game state of a Blue player:
   * Player: BLUE
   * BB   _
   * _B   _
   * _ R  _
   * _  _ _
   * _   _R
   * Hand:
   * CorruptKing 7 3 9 A
   * AngryDragon 2 8 9 9
   * WindBird 7 2 5 3
   * HeroKnight A 2 4 4
   * WorldDragon 8 3 5 7
   * SkyWhale 4 5 9 9
   * @return
   */
  String toString();

}
