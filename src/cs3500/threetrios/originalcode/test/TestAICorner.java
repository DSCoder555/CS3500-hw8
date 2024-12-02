package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.controller.AIPlayerCorner;
import cs3500.threetrios.originalcode.controller.GPlayer;
import cs3500.threetrios.originalcode.controller.Move;
import cs3500.threetrios.originalcode.model.Card;
import cs3500.threetrios.originalcode.model.CardValues;
import cs3500.threetrios.originalcode.model.PlayCard;
import cs3500.threetrios.originalcode.model.PlayableCard;
import cs3500.threetrios.originalcode.model.MockModel;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadCardConfigFile;
import cs3500.threetrios.originalcode.model.ReadGridConfigFile;

/**
 * Tests different scenarios of the strategy where the AI plays cards to the corners to minimize
 * its chance of being flipped.
 */
public class TestAICorner {

  private GPlayer gamePlayer;
  private MockModel mockModel;

  @Before
  public void setUp() {
    List<PlayableCard> cardList = new ArrayList<>();
    Card[][] playingGridAllEmptyCards = new Card[0][];
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
    gamePlayer = new AIPlayerCorner(mockModel, Player.Red);
    mockModel.startGame();
  }

  @Test
  public void testAICornerBeginningGame() {
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer.getMove());
  }

  @Test
  public void testAICornerTopLeftCorner() {
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer.getMove());
  }

  @Test
  public void testAICornerTopRightCorner() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(2, 0, 2), gamePlayer.getMove());
  }

  @Test
  public void testAICornerBottomLeftCorner() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(0, 2, 0), gamePlayer.getMove());
  }

  @Test
  public void testAICornerBottomRightCorner() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(2, 2, 2), gamePlayer.getMove());
  }

  @Test
  public void testAICornerAllCornersFilled() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(0, 0, 1), gamePlayer.getMove());
  }

}
