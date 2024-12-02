package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.controller.AIPlayerOutsmart;
import cs3500.threetrios.originalcode.controller.AIPlayerUnflippable;
import cs3500.threetrios.originalcode.controller.AIPlayerFlipper;
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
 * Tests different scenarios of the AI strategy that follows a minimax style, meaning that the AI
 * chooses move that leaves their opponent in a situation with no good moves. This strategy relies
 * on the other strategies to decide which one to use.
 */
public class TestAIOutsmart {

  private GPlayer gamePlayer1;
  private MockModel mockModel;

  @Before
  public void setUp() {
    // this.gamePlayer = new A
    Appendable log = new StringBuilder();
    List<PlayableCard> cardList = new ArrayList<>();
    Card[][] playingGridNoReach = new Card[0][0];
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
    gamePlayer1 = new AIPlayerOutsmart(mockModel, Player.Red);
    GPlayer gamePlayer2 = new AIPlayerOutsmart(mockModel, Player.Blue);
  }

  @Test
  public void testOutsmartAIBeginningGame() {
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer1.getMove());
  }

  @Test
  public void testOutsmartAICorners() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V5, CardValues.V5, CardValues.V5,
            CardValues.V5, Player.Blue);
    mockModel.grid[0][2] = new PlayCard("test", CardValues.V5, CardValues.V5, CardValues.V5,
            CardValues.V5, Player.Blue);
    GPlayer temp = new AIPlayerCorner(mockModel, Player.Red);
    Assert.assertEquals(new Move(2, 2, 2), gamePlayer1.getMove());
    mockModel.grid[2][2] = new PlayCard("test", CardValues.V5, CardValues.V5, CardValues.V5,
            CardValues.V5, Player.Blue);
    Assert.assertEquals(new Move(0, 2, 0), gamePlayer1.getMove());
    Assert.assertEquals(temp.getMove(), gamePlayer1.getMove());
  }

  @Test
  public void testOutsmartAIFlipper() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V5, Player.Blue);
    mockModel.grid[0][1] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    GPlayer temp = new AIPlayerFlipper(mockModel, Player.Red);
    Assert.assertEquals(new Move(0, 1, 0), gamePlayer1.getMove());
    Assert.assertEquals(temp.getMove(), gamePlayer1.getMove());
    mockModel.grid[1][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V1, Player.Blue);
    Assert.assertEquals(new Move(0, 2, 0), gamePlayer1.getMove());
    Assert.assertEquals(temp.getMove(), gamePlayer1.getMove());
  }

  @Test
  public void testOutsmartAIUnflip() {
    mockModel.grid[1][1] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Red);
    mockModel.grid[2][1] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Red);
    mockModel.grid[1][2] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Red);
    GPlayer temp = new AIPlayerUnflippable(mockModel, Player.Red);
    Assert.assertEquals(temp.getMove(), gamePlayer1.getMove());
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Red);
    mockModel.grid[0][2] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Red);
    Assert.assertEquals(temp.getMove(), gamePlayer1.getMove());
  }

  @Test
  public void testOutsmartAIEndGame() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[0][1] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[0][2] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[1][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[1][1] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[1][2] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[2][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[2][1] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[2][2] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    Assert.assertThrows(IllegalStateException.class, () -> gamePlayer1.getMove());
  }


}
