package cs3500.threetrios.originalcode.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

/**
 * This class reads and parses text from Three Trios board configurations
 * and adds the board contents to a 2D array which represents the playing board.
 */
public class ReadGridConfigFile {

  /**
   * Reads board data from the specified file, converts each line into a card object,
   * and adds it to a 2D array representing the playing board. Returns the complete board.
   *
   * @param boardFilePath the file path to read the board data from
   * @return a 2D array representing the game board
   * @throws FileNotFoundException          if the specified file cannot be found
   * @throws IOException                    if there is an issue with input or output during file
   *                                        reading
   * @throws NumberFormatException          if the row or column dimensions in the board
   *                                        configuration file are not valid numbers
   * @throws ArrayIndexOutOfBoundsException if the specified board dimensions (rows and columns)
   *                                        do not match the actual number of elements in the file
   * @throws IllegalArgumentException       if a cell value in the configuration file is not 'X' or
   *                                        'C'
   */
  public static Card[][] readBoardFile(File boardFilePath) throws IOException {
    BufferedReader bf = null;
    Card[][] board;
    try {
      bf = new BufferedReader(new FileReader(boardFilePath));
      String line = bf.readLine();
      String[] splitLine;
      splitLine = line.split(" ");
      int numRow = Integer.parseInt(splitLine[0]);
      int numCol = Integer.parseInt(splitLine[1]);
      board = new Card[numRow][numCol];
      line = bf.readLine();
      accessBoardValues(board, line, splitLine, bf);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + boardFilePath + " was not found");
    } catch (IOException e) {
      throw new IOException("Input/Output messed up");
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Row or column dimensions are not numbers");
    } catch (ArrayIndexOutOfBoundsException ex) {
      throw new ArrayIndexOutOfBoundsException("Error processing line: " + ex.getMessage());
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException(ex.getMessage());
    } finally {
      if (bf != null) {
        try {
          bf.close();
        } catch (IOException e) {
          System.out.println("Failed to close BufferedReader: " + e.getMessage());
        }
      }
    }
    return board;
  }

  private static void accessBoardValues(Card[][] board, String line, String[] splitLine,
                                        BufferedReader bf) throws IOException {
    int counter = 0;
    while (line != null) {
      try {
        splitLine = line.split(" ");
        if (splitLine.length != board[0].length) {
          throw new ArrayIndexOutOfBoundsException("Dimensions do not match");
        }
      } catch (PatternSyntaxException e) {
        System.out.println(e.getMessage());
      }
      for (int index = 0; index < splitLine.length; index++) {
        if (splitLine[index].equals("X")) {
          board[counter][index] = new HoleCard();
        } else if (splitLine[index].equals("C")) {
          board[counter][index] = new EmptyCard();
        } else {
          throw new IllegalArgumentException("Invalid board value");
        }
      }
      line = bf.readLine();
      counter++;
    }
  }


}
