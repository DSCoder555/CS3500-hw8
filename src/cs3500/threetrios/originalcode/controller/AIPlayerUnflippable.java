package cs3500.threetrios.originalcode.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Defines the behaviors for the strategy where the AI chooses cards that are less likely to be
 * flipped in general. It considers each possible position and card and calculates the lowest
 * likelihood of the opponent cards being flipped. Has a flag variable to keep track of whether
 * we have to fall back to the AIPlayerBase strategy in case this flipping strategy does not find
 * any moves and a variable to store the opponent player.
 */
public class AIPlayerUnflippable extends AIPlayerBase {

  private boolean defaultMove;

  /**
   * Sets up the model, current player.
   *
   * @param model  The model to set up.
   * @param player The player to initialize.
   */
  public AIPlayerUnflippable(ReadOnlyThreeTriosModel model, Player player) {
    super(model, player);
    defaultMove = false;
  }

  @Override
  public Move getMove() {
    defaultMove = false;
    List<Card> opponentsCards = new ArrayList<>();
    if (player == Player.Red) {
      opponentsCards.addAll(model.getHand(Player.Blue));
    } else {
      opponentsCards.addAll(model.getHand(Player.Red));
    }
    int flipScore = opponentsCards.size() * 10;
    List<Move> leastFlips = new ArrayList<>();
    leastFlips = getMoves(opponentsCards, flipScore, leastFlips);
    if (leastFlips.isEmpty()) {
      defaultMove = true;
      return super.getMove();
    } else {
      return leastFlips.get(0);
    }
  }

  private List<Move> getMoves(List<Card> opponentsCards, int flipScore, List<Move> leastFlips) {
    for (int row = 0; row < model.gridHeight(); row++) {
      for (int col = 0; col < model.gridLength(); col++) {
        if (model.isLegal(row, col)) {
          for (int cardNum = 0; cardNum <
                  model.getHand(model.getCurrentPlayer()).size(); cardNum++) {
            Card currCard = model.getHand(model.getCurrentPlayer()).get(cardNum);
            int tempFlipScore = 0;
            if (model.isLegal(row - 1, col)) {
              for (Card oppCard : opponentsCards) {
                if (oppCard.bottomValue() > currCard.topValue()) {
                  tempFlipScore++;
                }
              }
            }
            if (model.isLegal(row + 1, col)) {
              for (Card oppCard : opponentsCards) {
                if (oppCard.topValue() > currCard.bottomValue()) {
                  tempFlipScore++;
                }
              }
            }
            if (model.isLegal(row, col + 1)) {
              for (Card oppCard : opponentsCards) {
                if (oppCard.leftValue() > currCard.rightValue()) {
                  tempFlipScore++;
                }
              }
            }
            if (model.isLegal(row, col - 1)) {
              for (Card oppCard : opponentsCards) {
                if (oppCard.rightValue() > currCard.leftValue()) {
                  tempFlipScore++;
                }
              }
            }
            if (tempFlipScore == flipScore) {
              leastFlips.add(new Move(cardNum, row, col));
            } else if (tempFlipScore < flipScore) {
              flipScore = tempFlipScore;
              leastFlips = new ArrayList<>();
              leastFlips.add(new Move(cardNum, row, col));
            }
          }
        }
      }
    }
    return leastFlips;
  }

  @Override
  public boolean isDefaultMove() {
    return defaultMove;
  }
}
