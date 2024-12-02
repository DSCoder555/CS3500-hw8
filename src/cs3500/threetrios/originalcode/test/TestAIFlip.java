package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.controller.AIPlayerFlipper;
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
 * Tests different scenarios of the strategy where the AI aims to flip as many cards as possible by
 * choosing a card and position together.
 */
public class TestAIFlip {

  private GPlayer gamePlayer1;
  private GPlayer gamePlayer2;
  private MockModel mockModel;

  @Before
  public void setUp() {
    Appendable log = new StringBuilder();
    Card[][] playingGridNoReach = new Card[0][];
    List<PlayableCard> cardList = new ArrayList<>();
    try {
      cardList = ReadCardConfigFile.readCardFile(new File("src" +
              File.separator + "controller" + File.separator + "hw5" + File.separator
              + "cards3.txt"));
      playingGridNoReach = ReadGridConfigFile.readBoardFile(
              new File("src" + File.separator + "controller" +
                      File.separator + "hw5" + File.separator + "BoardSmallCardCells.txt"));
    } catch (IOException e) {
      System.out.println("I/O error occurred");
    }
    mockModel = new MockModel(log, playingGridNoReach, cardList);
    mockModel.startGame();
    gamePlayer1 = new AIPlayerFlipper(mockModel, Player.Red);
    gamePlayer2 = new AIPlayerFlipper(mockModel, Player.Blue);
  }

  @Test
  public void testAIFlipBeginningGame() {
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer1.getMove());
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer2.getMove());
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    Assert.assertEquals(new Move(0, 0, 1), gamePlayer2.getMove());
  }

  @Test
  public void testAIFlipBestCard() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V6, CardValues.V6,
            CardValues.V6, CardValues.V6, Player.Red);
    mockModel.gameState = GameState.Blue;
    Assert.assertEquals(new Move(1, 0, 1), gamePlayer2.getMove());
  }

  @Test
  public void testAIFlipBestPosition() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V1, Player.Red);
    mockModel.gameState = GameState.Blue;
    System.out.println(mockModel.getHand(Player.Blue).get(3).rightValue());
    Assert.assertEquals(new Move(0, 1, 0), gamePlayer2.getMove());
  }

  @Test
  public void testAIFlipBestCardAndPosition() {
    mockModel.grid[2][2] = new PlayCard("test", CardValues.V10, CardValues.V9,
            CardValues.V10, CardValues.V10, Player.Red);
    mockModel.gameState = GameState.Blue;
    Assert.assertEquals(new Move(3, 2, 1), gamePlayer2.getMove());
  }

  @Test
  public void testAIFlipMultipleFlips() {
    mockModel.grid[0][2] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[1][1] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.gameState = GameState.Blue;
    Assert.assertEquals(new Move(0, 1, 2), gamePlayer2.getMove());
  }

  @Test
  public void testAIFlipEndGame() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[0][1] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[0][2] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[1][0] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[1][1] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[1][2] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[2][0] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[2][1] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.grid[2][2] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Red);
    mockModel.gameState = GameState.PostGame;
    Assert.assertThrows(IllegalStateException.class, () -> gamePlayer2.getMove());
  }
}
