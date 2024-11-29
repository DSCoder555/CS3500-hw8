package cs3500.threetrios.newcode;

import java.util.HashMap;
import java.util.Map;

import cs3500.threetrios.originalcode.model.CardValues;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.providercode.model.AtkVal;
import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.Direction;
import cs3500.threetrios.providercode.model.PlayerColor;

public class CardAdapter implements Card {
  private PlayerColor color;
  private String name;
  private Map<Direction, AtkVal> vals;
  public CardAdapter(cs3500.threetrios.originalcode.model.Card cardData){
    if (cardData.getPlayer() == Player.Red){
      color = PlayerColor.RED;
    } else if (cardData.getPlayer() == Player.Blue) {
      color = PlayerColor.BLUE;
    }
    else {
      System.out.println("Should be a hole");
      throw new IllegalArgumentException("Bad card");
    }
    //TODO: find a way to get the card names
    name = "changed card";
    vals = new HashMap<Direction, AtkVal>();
    vals.put(Direction.North, cardValToAtkVal(cardData.topValue()));
    vals.put(Direction.West, cardValToAtkVal(cardData.leftValue()));
    vals.put(Direction.East, cardValToAtkVal(cardData.rightValue()));
    vals.put(Direction.South, cardValToAtkVal(cardData.bottomValue()));

  }
  /**
   * Retrieves the color associated with the player of this card.
   *
   * @return the color of the player as a PlayerColor enum
   */
  @Override
  public PlayerColor getPlayerColor() {
    return color;
  }

  /**
   * Retrieves the name of this card.
   *
   * @return the name of the card as a String
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the attack values of this card, where each direction (north, south, east, west)
   * has an associated attack value.
   *
   * @return a Map associating each Direction with its corresponding attack value
   */
  @Override
  public Map<Direction, AtkVal> getAtkVals() {
    return vals;
  }

  private AtkVal cardValToAtkVal(int val){
    switch (val){
      case 1:
        return AtkVal.One;
      case 2:
        return AtkVal.Two;
      case 3:
        return AtkVal.Three;
      case 4:
        return AtkVal.Four;
      case 5:
        return AtkVal.Five;
      case 6:
        return AtkVal.Six;
      case 7:
        return AtkVal.Seven;
      case 8:
        return AtkVal.Eight;
      case 9:
        return AtkVal.Nine;
      case 10:
        return AtkVal.A;
      default:
        throw new IllegalArgumentException("Bad Atk Val");
    }
  }
}
