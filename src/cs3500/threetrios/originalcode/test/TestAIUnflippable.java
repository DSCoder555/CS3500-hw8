package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.controller.AIPlayerUnflippable;
import cs3500.threetrios.originalcode.controller.GPlayer;
import cs3500.threetrios.originalcode.controller.Move;
import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.CardValues;
import cs3500.threetrios.originalcode.model.GameState;
import cs3500.threetrios.originalcode.model.PlayCard;
import cs3500.threetrios.originalcode.model.PlayableCard;
import cs3500.threetrios.originalcode.model.MockModel;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadCardConfigFile;
import cs3500.threetrios.originalcode.model.ReadGridConfigFile;

/**
 * Tests different scenarios of the AI strategy that chooses cards that are less likely to be
 * flipped in general. It considers each possible position and card and calculates the lowest
 * likelihood of the opponent cards being flipped.
 */
public class TestAIUnflippable {

  private GPlayer gamePlayer;
  private MockModel mockModel;

  @Before
  public void setUp() {
    Card[][] playingGridAllEmptyCards = new Card[0][];
    List<PlayableCard> cardList = new ArrayList<>();
    Appendable log = new StringBuilder();
    try {
      cardList = ReadCardConfigFile.readCardFile(new File("src" +
              File.separator + "controller" + File.separator + "hw5" + File.separator
              + "cards3.txt"));
      playingGridAllEmptyCards = ReadGridConfigFile.readBoardFile(
              new File("src" + File.separator + "controller" +
                      File.separator + "hw5" + File.separator + "BoardSmallCardCells.txt"));
    } catch (IOException e) {
      System.out.println("I/O error occurred");
    }
    mockModel = new MockModel(log, playingGridAllEmptyCards, cardList);
    gamePlayer = new AIPlayerUnflippable(mockModel, Player.Red);
    mockModel.startGame();
  }

  @Test
  public void testAllCardsFull() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    Assert.assertThrows(IllegalStateException.class, () -> gamePlayer.getMove());
  }

  @Test
  public void testAllSpotsFilledExceptRight() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V3,
            CardValues.V9, CardValues.V10, Player.Red);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V5, CardValues.V4,
            CardValues.V8, CardValues.V7, Player.Blue);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V9, CardValues.V4,
            CardValues.V2, CardValues.V10, Player.Blue);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V5, CardValues.V8,
            CardValues.V10, CardValues.V6, Player.Blue);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V3, CardValues.V7,
            CardValues.V2, CardValues.V4, Player.Blue);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V7, CardValues.V6,
            CardValues.V8, CardValues.V3, Player.Red);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V9, CardValues.V3,
            CardValues.V7, CardValues.V8, Player.Red);
    System.out.println("Hand index: " + gamePlayer.getMove().getHandIdx());
    System.out.println("Row number: " + gamePlayer.getMove().getRowNum());
    System.out.println("Column number: " + gamePlayer.getMove().getColNum());
    Assert.assertEquals(new Move(0, 0, 1), gamePlayer.getMove());
  }

  @Test
  public void AIUnflippableEightCardsFullFirstTest() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V7, CardValues.V7,
            CardValues.V7, CardValues.V7);
    Assert.assertEquals(new Move(0, 1, 1), gamePlayer.getMove());
  }

  @Test
  public void AIUnflippableEightCardsFullSecondTest() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V3, CardValues.V7,
            CardValues.V2, CardValues.V4);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V9, CardValues.V4,
            CardValues.V2, CardValues.V10);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V6, CardValues.V5,
            CardValues.V9, CardValues.V9);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V5, CardValues.V8,
            CardValues.V10, CardValues.V6);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V6, CardValues.V7,
            CardValues.V4, CardValues.V8);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V7, CardValues.V3,
            CardValues.V9, CardValues.V10);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V8, CardValues.V10,
            CardValues.V7, CardValues.V5);
    Assert.assertEquals(new Move(0, 1, 1), gamePlayer.getMove());
  }

  @Test
  public void AIUnflippableSevenFullCardsTest() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V3, CardValues.V7,
            CardValues.V2, CardValues.V4);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V7, CardValues.V3,
            CardValues.V9, CardValues.V10);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V9, CardValues.V4,
            CardValues.V2, CardValues.V10);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V8, CardValues.V10,
            CardValues.V7, CardValues.V5);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V6, CardValues.V7,
            CardValues.V4, CardValues.V8);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V5, CardValues.V4,
            CardValues.V8, CardValues.V7);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V6, CardValues.V5,
            CardValues.V9, CardValues.V9);
    Assert.assertEquals(new Move(0, 0, 2), gamePlayer.getMove());
  }

  @Test
  public void AIUnflippableSameValuesTest() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V5, CardValues.V5);
    Assert.assertEquals(new Move(0, 0, 2), gamePlayer.getMove());
  }

  @Test
  public void AIUnflippableSimilarValuesTest() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V5, CardValues.V4,
            CardValues.V5, CardValues.V5);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V5, CardValues.V5,
            CardValues.V4, CardValues.V6);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V5, CardValues.V6,
            CardValues.V4, CardValues.V5);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V6, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V4, CardValues.V5,
            CardValues.V5, CardValues.V5);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V6, CardValues.V5,
            CardValues.V5, CardValues.V4);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V5, CardValues.V4,
            CardValues.V6, CardValues.V5);
    Assert.assertEquals(new Move(0, 0, 2), gamePlayer.getMove());
  }

  @Test
  public void TwoCardsFullTest() {
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V9, CardValues.V4, CardValues.V2,
            CardValues.V10);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V6, CardValues.V7, CardValues.V4,
            CardValues.V8);
    System.out.println("Hand index: " + gamePlayer.getMove().getHandIdx());
    System.out.println("Row number: " + gamePlayer.getMove().getRowNum());
    System.out.println("Column number: " + gamePlayer.getMove().getColNum());
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer.getMove());
  }

  // 1 full card, 8 empty spots
  @Test
  public void OneCardFullTest() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V9, CardValues.V4, CardValues.V2,
            CardValues.V10);
    Assert.assertEquals(new Move(0, 0, 1), gamePlayer.getMove());
  }

  // 0 full cards
  @Test
  public void AIUnflippableZeroCardsFull() {
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer.getMove());
  }

  @Test
  public void testGameOver() {
    mockModel.gameState = GameState.PostGame;
    Assert.assertThrows(IllegalStateException.class, () -> gamePlayer.getMove());
  }

}
