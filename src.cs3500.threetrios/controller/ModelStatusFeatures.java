package controller;

import player.IPlayer;

/**
 * Represents the model status features that the model sends as notifications to the controller to
 * keep a Three Trios game updated.
 */
public interface ModelStatusFeatures {

  /**
   * Notifies each player and their controller of the current turn/player that is making a move.
   * @param p current turn/player
   */
  void notifyCurrentPlayerTurn(IPlayer p);

  /**
   * Notification sent that something in the game has changed (player played a card, cards are
   * flipped, turns change, the game is over, etc.). The controller listens to this and updates the
   * game as specified.
   */
  void onChanged();

  /**
   * Notification sent that the game is over. Respective popup visuals appear upon the game being
   * over, with the winner and their score being displayed. If the game is a tie, the popup displays
   * that the game was a tie and the score both players achieved.
   */
  void onGameOver();

}
