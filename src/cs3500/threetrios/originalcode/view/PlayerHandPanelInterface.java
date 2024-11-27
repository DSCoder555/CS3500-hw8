package cs3500.threetrios.originalcode.view;

import java.util.List;

import cs3500.threetrios.originalcode.model.Card;

/**
 * Describes the GUI behaviors for a player's hand, such as highlighting a certain card
 * and rendering their hand into a GUI visual representation.
 */
public interface PlayerHandPanelInterface extends IPanel {

  /**
   * Renders the current player's hand by displaying all cards visually.
   * Used for full re-renders, such as at the start of a new turn or after significant changes
   * to the hand (e.g., removing multiple cards). For smaller updates, like highlighting or
   * removing a single card, specific methods such as highlightCard and removeCard are used.
   *
   * @param playerHand The hand of the current player.
   * @throws IllegalArgumentException if playerHand is null.
   */
  void renderHandState(List<Card> playerHand);

  // TODO: Method param might become a mouse event as the coordiantes and owned player
  //  of the clicked card should be outputted. Might define a helper in the implementing class.

  /**
   * Highlights the selected card with a thick gray border. Prints out a message containing
   * the index of the card in the player's hand that was clicked and the name of the player who
   * owns this hand containing the card. If the player clicks outside the boundary of the grid or
   * hand, nothing happens.
   *
   * @param cardIdx The 0-based index of the chosen card.
   * @throws IllegalArgumentException if cardIdx is invalid.
   */
  void highlightCard(int cardIdx);

  /**
   * Deselects the most recently selected card. This event happens
   * only when the player clicks on the card again or clicks on another card,
   * in that case highlighting that new card.
   */
  void deselectCard();

  /**
   * Removes the specified card from the player's hand. Cards are automatically pushed
   * up to fill in the space of the removed card.
   *
   * @param cardIdx The 0-based index of the card in the player's hand to remove.
   * @throws IllegalArgumentException if cardIdx is invalid.
   */
  void removeCard(int cardIdx);

  /**
   * Plays the supplied card to the specified row and column location on the playing grid.
   * May trigger an event to the grid panel for further processing.
   *
   * @param cardIdx The 0-based index of the card in the player's hand to play.
   * @param rowIdx  The 0-based index of the row to play the card to.
   * @param colIdx  The 0-based index of the column to play the card to.
   * @throws IllegalArgumentException if cardIdx, rowIdx, or colIdx are invalid.
   */
  void playCardToGrid(int cardIdx, int rowIdx, int colIdx);

  /**
   * Removes all current card components from the panel, fetches the updated hand from the model
   * and recreates the panel's card components.
   */
  void updateHand(List<Card> playerHand);
}
