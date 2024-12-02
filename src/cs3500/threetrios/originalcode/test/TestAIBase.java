package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.controller.AIPlayerBase;
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
 * Tests different scenarios of the strategy where the AI chooses the first available move
 * (uppermost-leftmost, respectively).
 */
public class TestAIBase {

  private GPlayer gamePlayer;
  private Appendable log;
  private MockModel mockModel;


  @Before
  public void setUp() {
    // this.gamePlayer = new A
    this.log = new StringBuilder();
    List<PlayableCard> cardList = new ArrayList<>();
    Card[][] playingGridAllEmptyCards = new Card[0][];
    try {
      cardList = ReadCardConfigFile.readCardFile(new File("src"
              + File.separator + "controller" + File.separator + "hw5" + File.separator
              + "cards3.txt"));
      playingGridAllEmptyCards = ReadGridConfigFile.readBoardFile(
              new File("src" + File.separator + "controller"
                      + File.separator + "hw5" + File.separator + "BoardSmallCardCells.txt"));
    } catch (IOException e) {
      System.out.println("I/O error occurred");
    }
    mockModel = new MockModel(log, playingGridAllEmptyCards,cardList);
    gamePlayer = new AIPlayerBase(mockModel, Player.Red);
    mockModel.startGame();
  }

  @Test
  public void testAIBaseBeginningGame() {
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer.getMove());
    mockModel.playCard(0, 0, 0);
    System.out.println(log);
  }

  @Test
  public void testAIBaseFirstSpotFull() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(0, 0, 1), gamePlayer.getMove());
  }

  @Test
  public void testAIBaseFirstRowFull() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(0, 1, 0), gamePlayer.getMove());
  }

  @Test
  public void testAIBaseMidGameState() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(0, 0, 2), gamePlayer.getMove());
  }

  @Test
  public void testAIBaseSecondRowFull() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(0, 2, 0), gamePlayer.getMove());
  }

  @Test
  public void testAIBaseEveryRowButLastCardFull() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertEquals(new Move(0, 2, 2), gamePlayer.getMove());
  }

  @Test
  public void testAIBaseEveryRowFull() {
    mockModel.grid[0][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[0][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[1][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][0] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][1] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    mockModel.grid[2][2] = new PlayCard("Test", CardValues.V7, CardValues.V7, CardValues.V7,
            CardValues.V7);
    Assert.assertThrows(IllegalStateException.class, () -> gamePlayer.getMove());
  }

  @Test
  public void testAIBaseGameOver() {
    mockModel.gameState = GameState.PostGame;
    Assert.assertThrows(IllegalStateException.class, () -> gamePlayer.getMove());
  }

}
