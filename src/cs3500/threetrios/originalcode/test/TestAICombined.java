package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.controller.AIPlayerCombiner;
import cs3500.threetrios.originalcode.controller.AIPlayerUnflippable;
import cs3500.threetrios.originalcode.controller.AIPlayerFlipper;
import cs3500.threetrios.originalcode.controller.AIPlayerCorner;
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
 * Tests different scenarios of the strategy that combines the different AI strategies
 * to determine the best on to use.
 */
public class TestAICombined {

  private GPlayer gamePlayer1;
  private GPlayer gamePlayer2;
  private MockModel mockModel;

  @Before
  public void setUp() {
    // this.gamePlayer = new A
    Appendable log = new StringBuilder();
    List<PlayableCard> cardList = new ArrayList<>();
    Card[][] playingGridNoReach = new Card[0][];
    try {
      cardList = ReadCardConfigFile.readCardFile(new File("src" +
              File.separator + "controller" + File.separator + "hw5" + File.separator
              + "cards3.txt"));
      playingGridNoReach = ReadGridConfigFile.readBoardFile(
              new File("src" + File.separator + "controller" +
                      File.separator + "hw5" + File.separator + "BoardSmallCardCells.txt"));
      // this.playingGridReach = ReadGridConfigFile.readBoardFile()
    } catch (IOException e) {
      System.out.println("I/O error occurred");
    }
    mockModel = new MockModel(log, playingGridNoReach, cardList);
    mockModel.startGame();
    gamePlayer1 = new AIPlayerCombiner(mockModel, Player.Red,
            List.of(new AIPlayerCorner(mockModel, Player.Red),
                    new AIPlayerFlipper(mockModel, Player.Red)));
    gamePlayer2 = new AIPlayerCombiner(mockModel, Player.Blue,
            List.of(new AIPlayerCorner(mockModel, Player.Blue),
                    new AIPlayerUnflippable(mockModel, Player.Blue)));
  }

  @Test
  public void testAICombinedStartGame() {
    Assert.assertEquals(new Move(0, 0, 0), gamePlayer1.getMove());
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V2, CardValues.V2, CardValues.V2,
            CardValues.V2, Player.Red);
    mockModel.gameState = GameState.Blue;
    Assert.assertEquals(new Move(4, 0, 2), gamePlayer2.getMove());
  }

  @Test
  public void testAICombinedFirstStrategy() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V2, CardValues.V2, CardValues.V2,
            CardValues.V2, Player.Red);
    mockModel.gameState = GameState.Blue;
    Assert.assertEquals(new Move(4, 0, 2), gamePlayer2.getMove());
    mockModel.grid[0][2] = new PlayCard("test", CardValues.V2, CardValues.V2, CardValues.V2,
            CardValues.V2, Player.Blue);
    mockModel.gameState = GameState.Red;
    Assert.assertEquals(new Move(2, 2, 2), gamePlayer1.getMove());
    mockModel.grid[2][2] = new PlayCard("test", CardValues.V2, CardValues.V2, CardValues.V2,
            CardValues.V2, Player.Red);
    mockModel.gameState = GameState.Blue;
    Assert.assertEquals(new Move(3, 2, 0), gamePlayer2.getMove());
  }

  @Test
  public void testAICombinedSecondStrategy() {
    mockModel.grid[0][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[2][0] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[0][2] = new PlayCard("test", CardValues.V10, CardValues.V10,
            CardValues.V10, CardValues.V10, Player.Blue);
    mockModel.grid[2][2] = new PlayCard("test", CardValues.V1, CardValues.V1,
            CardValues.V1, CardValues.V1, Player.Blue);
    Assert.assertEquals(new Move(0, 1, 2), gamePlayer1.getMove());
    mockModel.gameState = GameState.Blue;
    Assert.assertEquals(new Move(2, 0, 1), gamePlayer2.getMove());

  }

  @Test
  public void testAICombinedEndGame() {
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
