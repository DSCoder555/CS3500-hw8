package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.EmptyCard;
import cs3500.threetrios.originalcode.model.HoleCard;
import cs3500.threetrios.originalcode.model.ReadGridConfigFile;

/**
 * Tests the behaviors of reading in and parsing valid
 * and invalid grid configuration files.
 */
public class TestReadGridFile {
  private Card[][] board;

  @Before
  public void setUp() {
    board = new Card[3][3];
    board[0][0] = new EmptyCard();
    board[0][1] = new EmptyCard();
    board[0][2] = new HoleCard();
    board[1][0] = new HoleCard();
    board[1][1] = new HoleCard();
    board[1][2] = new HoleCard();
    board[2][0] = new EmptyCard();
    board[2][1] = new EmptyCard();
    board[2][2] = new EmptyCard();
  }

  @Test
  public void testValidReadBoardFile() throws IOException {
    Card[][] actualBoard = ReadGridConfigFile.readBoardFile(
            new File("src" + File.separator + "controller" +
                    File.separator + "hw5" + File.separator + "BoardCardCellsNoReach.txt"));
    Assert.assertEquals(
            board,
            actualBoard);
  }

  @Test
  public void testFileDoesNotExist() {
    Assert.assertThrows("Checking if an invalid file can be passed in",
            FileNotFoundException.class,
        () -> ReadGridConfigFile.readBoardFile(new File("bad" + File.separator + "file")));
  }

  @Test
  public void testFileWithBoardDimsDoNotMatch() {
    Assert.assertThrows("Checking if a file that contains a line with " +
                    "fewer than 3 values can be passed in",
            ArrayIndexOutOfBoundsException.class,
        () -> ReadGridConfigFile.readBoardFile(new File("src" + File.separator
              + "controller" + File.separator + "hw5" + File.separator
              + "BoardCardCellsInvalidLineLength.txt")));
  }

  @Test
  public void testFileWithDimensionsInconvertibleToInteger() {
    Assert.assertThrows("Checking if a file that contains dimensions "
                    + "that cannot be converted to integers",
            NumberFormatException.class,
        () -> ReadGridConfigFile.readBoardFile(new File("src" + File.separator
              + "controller" + File.separator + "hw5" + File.separator
              + "BoardCardCellsDimensionsInconvertibleToInt.txt")));
  }

  @Test
  public void testFileWithBoardValuesInconvertibleToChar() {
    Assert.assertThrows("Checking if a file that contains card values "
                    + "that cannot be converted to chars",
            IllegalArgumentException.class,
        () -> ReadGridConfigFile.readBoardFile(new File("src" + File.separator
              + "controller" + File.separator + "hw5" + File.separator
              + "BoardCardValsInconvertibleToChar.txt")));
  }

}
