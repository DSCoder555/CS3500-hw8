package view;

import cs3500.threetrios.player.IPlayer;

/**
 * Represents a hand panel for a visual Three trios View.
 */
public interface ThreeTriosHandPanel extends ThreeTriosPanel {

  /**
   * Gets the hand size of a specific player.
   * @return hand size of player relevant to respective panel
   */
  int playerHandSize();

  IPlayer getSide();

  /**
   * Highlights or unhighlights card in a hand given its index. This only concerns a single card
   * that can be highlighted/selected and unhighlighted/deselected.
   * @param index index of a card in the hand
   */
  void highlightOrUnhighlightSingleCard(int index);

  /**
   * Unhighlights the current card if another card is selected.
   */
  void unhighlightCurrentCard();

}
