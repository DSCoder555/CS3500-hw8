package cs3500.threetrios.originalcode.model;

/**
 * Defines the possible values for a {@link Player}: 'R' if the current player is Red,
 * 'B' if the current player is Blue, or 'N' if it is neither of the previous two (None).
 */
public enum Player {

  Red('R'),
  Blue('B'),
  None('N');

  private final char playerChar;

  Player(char pC) {
    playerChar = pC;
  }

  /**
   * Returns the current player as a character depending on the Player value. 'R' for Red,
   * 'B' for Blue, and 'N for None.
   *
   * @return The current player as a character.
   */
  public char getChar() {
    return playerChar;
  }
}
