package cs3500.threetrios.originalcode.controller;

import cs3500.threetrios.originalcode.view.ModelListenerInterface;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;
import cs3500.threetrios.originalcode.view.ThreeTriosGUI;
import cs3500.threetrios.originalcode.view.ViewFeatures;

/**
 * Defines the behaviors for a controller of Three Trios such as starting the game, this
 * will be completed in Part 3.
 */
public class ThreesTrioController implements ViewFeatures, ModelListenerInterface {
  private ThreeTriosModel model;
  private ThreeTriosGUI view;
  private GPlayer player;

  /**
   * Sets up the model, view, the two players of the game, the current player, and starts rendering
   * the view.
   *
   * @param model      The model to set up.
   * @param view       The view to set up.
   * @param gamePlayer The player to initialize.
   * @throws IllegalArgumentException throws if model, gamePlayer or view are null
   */
  public ThreesTrioController(ThreeTriosModel model, GPlayer gamePlayer, ThreeTriosGUI view) {
    if (model == null || gamePlayer == null || view == null) {
      throw new IllegalArgumentException("One of the inputs was null");
    }
    this.model = model;
    this.view = view;
    player = gamePlayer;
    view.addFeatureListener(this);
    model.addModelListener(this);
    view.startRendering();
    view.display(true);
  }

  private void move() {
    if (!model.isGameOver()) {
      Move currMove = player.getMove();
      model.playCard(currMove.getHandIdx(), currMove.getRowNum(), currMove.getColNum());
      player.resetMove();
      view.advance();
    }

  }


  @Override
  public void selectedGrid(int row, int col) {
    if (model.currentPlayer() == player.getPlayer()) {
      if (player.getMove().getHandIdx() != -1) {
        player.setRow(row);
        player.setCol(col);
        try {
          move();
        } catch (IllegalArgumentException ignored) {
          view.displayMessage("Invalid Move");
        }
      } else {
        view.displayMessage("Please select a card first");
      }
    } else {
      view.displayMessage("Not your turn");
    }
  }

  @Override
  public void selectedHand(int cardNum) {
    if (model.currentPlayer() == player.getPlayer()) {
      player.setCardNum(cardNum);
    } else {
      view.displayMessage("Not your turn");
    }
  }

  @Override
  public Player getPlayer() {
    return player.getPlayer();
  }

  @Override
  public void quit() {
    // Will be implemented in part 3 of ThreeTrios.
  }

  @Override
  public void notifyPlayerTurn() {
    view.advance();
    view.displayMessage("Player " + player.getPlayer().getChar()
            + ", it's your turn");
    if (!player.isHuman()) {
      move();
    }
  }

  @Override
  public void notifyInvalidAction(String message) {
    view.error();
  }

  @Override
  public void notifyGameWon(Player winner, int score) {
    view.displayMessage("Game Over! Winner: " + winner + " with score " + score);
  }

  // model will call when play successful
  @Override
  public void gameUpdated() {
    view.advance();
  }
}
