package cs3500.threetrios.newcode;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.providercode.controller.PlayerActionFeatures;
import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.PlayerColor;
import cs3500.threetrios.providercode.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.providercode.player.IPlayer;

public class GamePlayer implements IPlayer {
  private PlayerColor color;
  private cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel model;
  public GamePlayer(PlayerColor color, cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel model){
    this.color = color;
    this.model = model;
  }

  /**
   * Retrieves the color associated with this player.
   *
   * @return the PlayerColor representing the player
   */
  @Override
  public PlayerColor getPlayerColor() {
    return color;
  }

  /**
   * Returns a copy of the hand in the game. This means modifying the returned list
   * or the cards in the list has no effect on the game.
   *
   * @return a new list containing the cards in the player's hand in the same order
   * as in the current state of the game.
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public List<Card> getHand() {
    List<cs3500.threetrios.originalcode.model.Card> tempHand;
    if (color == PlayerColor.RED){
      tempHand = model.getHand(Player.Red);
    }
    else{
      tempHand = model.getHand(Player.Blue);
    }
    List<Card> returnHand = new ArrayList<>();
    for (cs3500.threetrios.originalcode.model.Card card : tempHand){
      returnHand.add(new CardAdapter(card));
    }
    return returnHand;
  }

  /**
   * Adds a card to the player's hand. This card is appended to the end of the hand.
   *
   * @param c the Card to add to the player's hand
   */
  @Override
  public void drawCardForHand(Card c) {
    throw new IllegalArgumentException("Drawcardcalled");
  }

  /**
   * Removes a card in the player's hand when they play it.
   *
   * @param idx 0-based index of the card in the player's hand
   */
  @Override
  public void removeCardInHand(int idx) {
    throw new IllegalArgumentException("Removecardcalled");
  }

  /**
   * Adds a player action listener that responds to player actions.
   *
   * @param features player actions that are sent to the controller to update the game
   */
  @Override
  public void addPlayerActionListener(PlayerActionFeatures features) {
    throw new IllegalArgumentException("Addedlistenerscalled");
  }

  /**
   * Sets up a player before they make a move, either by waiting for a human player to click a card
   * in their hand and a cell on the board or a machine player determining their best move based on
   * their own strategy.
   *
   * @param model current board state of the game
   */
  @Override
  public void setupPlay(ReadOnlyThreeTriosModel model) {
    throw new IllegalArgumentException("SetupGamecalled");
  }

  /**
   * Retrieves all the player action listeners for a specified player.
   *
   * @return the list of listeners that emit events that allows the controller to update the game
   */
  @Override
  public List<PlayerActionFeatures> getPlayerActionListeners() {
    throw new IllegalArgumentException("getlistenerscalled");
  }

  /**
   * Retrieves a copy of the player of interest. Modifying this player will not affect the state/
   * outcome of the game.
   *
   * @return copy/clone of the player
   */
  @Override
  public IPlayer copyPlayer() {
    return new GamePlayer(color, model);
  }
}
