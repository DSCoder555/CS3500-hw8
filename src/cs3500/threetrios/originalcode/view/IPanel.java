package cs3500.threetrios.originalcode.view;


/**
 * Interface for GUI panels in the game, defining a standard method to render
 * the current state of each panel independently, such as PlayerHandPanel or GridPanel.
 * The GameFrame class manages the overall game layout.
 */
public interface IPanel {


  /**
   * Renders the current game state for the panel. May include displaying
   * cards in each player's hand and the playing grid, depending on the panel.
   */
  void renderGameState();


}
