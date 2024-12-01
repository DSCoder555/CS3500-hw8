package cs3500.threetrios.newcode;

import javax.swing.text.View;

import cs3500.threetrios.originalcode.controller.GPlayer;
import cs3500.threetrios.originalcode.controller.ThreesTrioController;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;
import cs3500.threetrios.originalcode.view.ThreeTriosGUI;
import cs3500.threetrios.providercode.controller.PlayerActionFeatures;
import cs3500.threetrios.providercode.model.PlayerColor;

public class ViewListenerAdapter extends ThreesTrioController implements PlayerActionFeatures {
  /**
   * Sets up the model, view, the two players of the game, the current player, and starts rendering
   * the view.
   *
   * @param model      The model to set up.
   * @param gamePlayer The player to initialize.
   * @param view       The view to set up.
   * @throws IllegalArgumentException throws if model, gamePlayer or view are null
   */
  private ThreeTriosModel model;
  private PlayerColor color;
  private int selectedCardNum;
  public ViewListenerAdapter(ThreeTriosModel model, GPlayer gamePlayer, ThreeTriosGUI view) {
    super(model, gamePlayer, view);
//    if (view instanceof ViewAdapter){
//      ViewAdapter adapter = (ViewAdapter) view;
//      adapter.ac
//    }
    this.model = model;
    if (gamePlayer.getPlayer()== Player.Red){
      color = PlayerColor.RED;
    }
    else{
      color = PlayerColor.BLUE;
    }
    selectedCardNum = -1;
  }

  /**
   * Outputs the 0-based index of the card in the player's hand, as well as its color/owner.
   * Chooses and highlights the chosen card in the hand.
   *
   * @param idx         index of the card
   * @param playerColor color that the card is on the grid
   */
  @Override
  public void highlightOrUnhighlightCardInHand(int idx, PlayerColor playerColor) {
    if (playerColor == color){
      super.selectedHand(idx);
      selectedCardNum = idx;
    }
  }

  /**
   * Outputs the coordinates of a chosen cell on a grid, and then moves accordingly to place the
   * card on the board if it's legal. Visual popups appear if a move is illegal. The card then does
   * battle with its neighbors, and the model and view update respectively. Lastly, the respective
   * player's turn ends. Returns true if the move was successfully completed, false otherwise.
   *
   * @param row 0-based row of grid cell
   * @param col 0-based column of grid cell
   * @throws IllegalArgumentException if the move is attempted with an invalid coordinate
   */
  @Override
  public boolean move(int row, int col) {
    if (selectedCardNum != -1 && model.isLegal(row,col)){
      super.selectedGrid(row,col);
      selectedCardNum = -1;
      return true;
    }
    return false;
  }

  /**
   * Tracks the index of a player's selected/highlighted card in their hand.
   *
   * @param handIdx index of the card in respect to the player's hand
   * @throws IllegalArgumentException if the hand index is invalid
   */
  @Override
  public void setCardSelectedIdx(int handIdx) {
    super.selectedHand(handIdx);
  }

  /**
   * Retrieves the index of a player's selected/highlighted card in their hand.
   *
   * @return the index of the card
   */
  @Override
  public int getCardSelectedIdx() {
    return 0;
  }
}
