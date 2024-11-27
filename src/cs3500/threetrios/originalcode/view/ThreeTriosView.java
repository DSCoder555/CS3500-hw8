package cs3500.threetrios.originalcode.view;

/**
 * Behaviors needed for a view of the ThreeTrios implementation
 * that transmits information to the user.
 */
public interface ThreeTriosView {

  /**
   * Returns a string representation of the game state, including the current player,
   * the grid, and the current player's hand.
   * Example output:
   * <pre>
   * Player: RED
   * XCX
   * CXC
   * XXC
   * Hand:
   * [IceGolem, FireSpirit, GoblinBarrel]
   * </pre>
   *
   * @return a string representation of the current game state
   */
  @Override
  String toString();
}
