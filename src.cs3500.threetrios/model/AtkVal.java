package model;

/**
 * Enum that represent all possible attack values of a card, from 1-10 (A in Hexadecimal).
 */
public enum AtkVal {

  One(1),

  Two(2),

  Three(3),

  Four(4),

  Five(5),

  Six(6),

  Seven(7),

  Eight(8),

  Nine(9),

  A(10);

  private final int atkVal;

  AtkVal(int atkVal) {
    this.atkVal = atkVal;
  }

  /**
   * Allows the model to convert from the enum to their respective ints for more readable
   * battle logic/math.
   * @return the int associated with the respective enum
   */
  public int getAtkVal() {
    return atkVal;
  }

}


