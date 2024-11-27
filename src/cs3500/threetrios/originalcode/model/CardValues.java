package cs3500.threetrios.originalcode.model;

/**
 * Defines the possible card values for a {@link PlayCard}.
 */
public enum CardValues {
  V1('1'),
  V2('2'),
  V3('3'),
  V4('4'),
  V5('5'),
  V6('6'),
  V7('7'),
  V8('8'),
  V9('9'),
  V10('A');

  private final char value;

  /**
   * Assigns the instance variable character value to the supplied character.
   *
   * @param c The supplied character.
   * @throws IllegalArgumentException if the supplied character is not 'A' and cannot
   *                                  be converted to a digit.
   */
  CardValues(char c) {
    if (c != 'A' && !Character.isDigit(c)) {
      throw new IllegalArgumentException("The given input is not valid for a card value");
    }
    value = c;
  }

  /**
   * Returns the integer representation of the card value, returns 10 if the c is 'A'.
   *
   * @return the integer representation of the card value.
   */
  public int getValue() {
    if (Character.isDigit(value)) {
      return (value) - '0';
    }
    return 10;
  }

  /**
   * Returns a string representation of the card value.
   *
   * @return a string representation of the card value.
   */
  @Override
  public String toString() {
    return "" + value;
  }

  /**
   * Given a character, returns the corresponding card value.
   *
   * @param input The supplied character.
   * @return The corresponding card value.
   * @throws IllegalArgumentException if an invalid card value is given.
   */
  public static CardValues getCValue(char input) {
    switch (input) {
      case '1':
        return CardValues.V1;
      case '2':
        return CardValues.V2;
      case '3':
        return CardValues.V3;
      case '4':
        return CardValues.V4;
      case '5':
        return CardValues.V5;
      case '6':
        return CardValues.V6;
      case '7':
        return CardValues.V7;
      case '8':
        return CardValues.V8;
      case '9':
        return CardValues.V9;
      case 'A':
        return CardValues.V10;
      default:
        throw new IllegalArgumentException("This file has an invalid input " + input);
    }
  }
}
