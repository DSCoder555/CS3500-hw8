package cs3500.threetrios.originalcode.model;

/**
 * Defines the possible values for the game state. PreGame is before the game has started, Red
 * means it is Red's turn, Blue means it is Blue's turn, and PostGame means the game is over.
 */
public enum GameState {
  PreGame,
  Red,
  Blue,
  PostGame;

  /**
   * Returns the appropriate game state. If the player is Red, switches and returns player Blue
   * and vice versa. If we have no current player, PostGame state is returned.
   *
   * @param curr The supplied game state.
   * @return the appropriate game state.
   */
  public static GameState switchPlayer(GameState curr) {
    if (curr == Red) {
      return Blue;
    } else if (curr == Blue) {
      return Red;
    } else {
      return PostGame;
    }
  }
}
