package cs3500.threetrios.originalcode.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads text from Three Trios card configuration and adds the cards
 * to a list which represents the deck.
 */
public class ReadCardConfigFile {

  /**
   * Reads card data from the specified file, converts each line into a card object,
   * and adds it to a list representing the deck. Returns the complete deck list.
   *
   * @param cardFilePath the file path to read the card data from
   * @throws FileNotFoundException if the specified file is not found
   * @throws IOException           if there is an issue with input or output during file reading
   * @throws IllegalStateException if a card in the file does not have exactly five elements,
   *                               or if a card's element cannot be converted to a character.
   */
  public static List<PlayableCard> readCardFile(File cardFilePath) throws IOException {
    BufferedReader bf = null;
    List<PlayableCard> deck = new ArrayList<>();
    try {
      bf = new BufferedReader(new FileReader(cardFilePath));
      String line = bf.readLine();
      accessCardValues(line, deck, bf);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + cardFilePath + " was not found");
    } catch (IOException ioe) {
      throw new IOException("Input/Output error: " + ioe.getMessage());
    } catch (IllegalStateException ex) {
      throw new IllegalStateException("Error processing line: " + ex.getMessage());
    } finally {
      if (bf != null) {
        try {
          bf.close();
        } catch (IOException e) {
          System.out.println("Failed to close BufferedReader: " + e.getMessage());
        }
      }
    }
    return deck;
  }

  private static void accessCardValues(String line, List<PlayableCard> deck,
                                       BufferedReader bf) throws IOException {
    while (line != null) {
      try {
        String[] splitLine = line.split(" ");
        PlayableCard curCard = getPlayableCard(splitLine);
        deck.add(curCard);
      } catch (IllegalStateException e) {
        throw new IllegalStateException("Error processing line: " + e.getMessage());
      }
      line = bf.readLine();
      if (line == null || line.trim().isEmpty()) {
        break;
      }
    }
  }

  private static PlayableCard getPlayableCard(String[] splitLine) {
    if (splitLine.length != 5) {
      throw new IllegalStateException("Each card must have 5 elements");
    }
    String cardName = splitLine[0];
    List<Character> compassValues = new ArrayList<>();
    for (int elemIdx = 1; elemIdx < splitLine.length; elemIdx++) {
      String curElem = splitLine[elemIdx];
      if (curElem.length() > 1) {
        throw new IllegalStateException(curElem + " cannot be converted to char");
      }
      compassValues.add(curElem.charAt(0));
    }
    CardValues northCardValue = CardValues.getCValue(compassValues.get(0));
    CardValues southCardValue = CardValues.getCValue(compassValues.get(1));
    CardValues eastCardValue = CardValues.getCValue(compassValues.get(2));
    CardValues westCardValue = CardValues.getCValue(compassValues.get(3));
    return new PlayCard(cardName, northCardValue, southCardValue,
            eastCardValue, westCardValue);
  }

}
