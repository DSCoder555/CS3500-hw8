package cs3500.threetrios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs3500.threetrios.newcode.ModelAdapter;
import cs3500.threetrios.newcode.ViewAdapter;
import cs3500.threetrios.newcode.ViewListenerAdapter;
import cs3500.threetrios.originalcode.controller.AIPlayerCombiner;
import cs3500.threetrios.originalcode.controller.AIPlayerCorner;
import cs3500.threetrios.originalcode.controller.AIPlayerFlipper;
import cs3500.threetrios.originalcode.controller.AIPlayerOutsmart;
import cs3500.threetrios.originalcode.controller.AIPlayerUnflippable;
import cs3500.threetrios.originalcode.controller.GPlayer;
import cs3500.threetrios.originalcode.controller.HumanPlayer;
import cs3500.threetrios.originalcode.controller.ThreesTrioController;
import cs3500.threetrios.originalcode.model.PlayableCard;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadCardConfigFile;
import cs3500.threetrios.originalcode.model.ReadGridConfigFile;
import cs3500.threetrios.originalcode.model.ThreeTrioModelGame;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;
import cs3500.threetrios.originalcode.view.ThreeTriosGUI;
import cs3500.threetrios.originalcode.view.ThreesTriosGUIView;
import cs3500.threetrios.providercode.model.PlayerColor;
import cs3500.threetrios.providercode.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.providercode.view.GUIThreeTriosView;
import cs3500.threetrios.providercode.view.VisualThreeTriosView;


/**
 * The main entry point for the Three Trios game, setting up the model, reading configurations,
 * and starting the GUI view.
 */
public final class ThreeTrios {

  /**
   * Initializes and starts the Three Trios game, loading the board and card configurations,
   * and launching the GUI.
   *
   * @param args The command-line arguments specifying the strategies for the two players.
   *             These arguments allow for configurations such as:
   *             - Human versus any of the five possible AI strategies
   *             - Human versus Human
   *             - Two AIs using the same strategy against each other
   *             - Two AIs using different strategies against each other
   *             The strategies supported include "human", "strategy1", "strategy2", and
   *             "strategy3".
   * @throws IOException if any input/output error occurred
   */

  public static void main(String[] args) throws IOException {
    String firstPlayerStrategy = args[0];
    String secondPlayerStrategy = args[1];
    if (firstPlayerStrategy == null || secondPlayerStrategy == null) {
      throw new IllegalArgumentException("First player strategy and second player strategy "
              + "are null");
    }
    List<PlayableCard> cardList = ReadCardConfigFile.readCardFile(new File("src" +
            File.separator + "cs3500" +
            File.separator + "threetrios" +
            File.separator + "originalcode" +
            File.separator + "controller" + File.separator + "cards3.txt"));
    ThreeTriosModel model = new ThreeTrioModelGame(
            ReadGridConfigFile.readBoardFile(new File("src" +
                    File.separator + "cs3500" +
                    File.separator + "threetrios" +
                    File.separator + "originalcode" +
                    File.separator + "controller"
                    + File.separator + "BoardCardCellsNoReach.txt")),
            cardList, new Random());
    GPlayer playerOne = null;
    Result result = getResult(firstPlayerStrategy, playerOne, model, secondPlayerStrategy);
    playerOne = result.playerOne();
    GPlayer playerTwo = result.playerTwo();
    ThreesTriosGUIView viewPlayerOne = new ThreesTriosGUIView(model);

    ReadOnlyThreeTriosModel modelAdapt = new ModelAdapter(model);
    VisualThreeTriosView viewPlayer2 = new GUIThreeTriosView(modelAdapt);
    ThreeTriosGUI viewAdapt = new ViewAdapter(viewPlayer2, PlayerColor.BLUE);

    //ThreesTriosGUIView viewPlayerTwo = new ThreesTriosGUIView(model);
    ThreesTrioController controllerOne = new ThreesTrioController(model, result.playerOne(),
            viewPlayerOne);
    ThreesTrioController controllerTwo = new ViewListenerAdapter(model, result.playerTwo(),
            viewAdapt);
//    viewPlayerOne.setVisible(true);
//    viewPlayerTwo.setVisible(true);
    model.startGame();
    //    GameFrame gameFramePlayerOne = new GameFrame(model, result.playerOne());
    //    GameFrame gameFramePlayerTwo = new GameFrame(model, result.playerTwo());
    //    gameFramePlayerOne.toggleHintMode(true, playerOne);
    //    gameFramePlayerTwo.toggleHintMode(false, playerTwo);
    //    gameFramePlayerOne.setVisible(true);
    //    gameFramePlayerTwo.setVisible(true);
  }

  // This method was extracted out of the main function using Intellij's extract method feature.
  private static Result getResult(String firstPlayerStrategy, GPlayer playerOne,
                                  ThreeTriosModel model, String secondPlayerStrategy) {
    GPlayer playerTwo = null; // Initialize playerTwo
    List<GPlayer> playersAI = new ArrayList<>();
    playersAI.add(new AIPlayerFlipper(model, Player.Red));
    playersAI.add(new AIPlayerCorner(model, Player.Red));
    playersAI.add(new AIPlayerUnflippable(model, Player.Red));
    playersAI.add(new AIPlayerOutsmart(model, Player.Red));
    switch (firstPlayerStrategy.toLowerCase()) {
      case "human" : playerOne = new HumanPlayer(model, Player.Red);
      break;
      case "strategy1" : playerOne = new AIPlayerFlipper(model, Player.Red);
      break;
      case "strategy2" : playerOne = new AIPlayerCorner(model, Player.Red);
      break;
      case "strategy3" : playerOne = new AIPlayerUnflippable(model, Player.Red);
      break;
      case "strategy4" : playerOne = new AIPlayerOutsmart(model, Player.Red);
      break;
      case "strategy5" : playerOne = new AIPlayerCombiner(model, Player.Red,
              playersAI);
      break;
      default : throw new IllegalArgumentException("Invalid strategy for Player One");
    }

    switch (secondPlayerStrategy.toLowerCase()) {
      case "human" : playerTwo = new HumanPlayer(model, Player.Blue);
      break;
      case "strategy1" : playerTwo = new AIPlayerFlipper(model, Player.Blue);
      break;
      case "strategy2" : playerTwo = new AIPlayerCorner(model, Player.Blue);
      break;
      case "strategy3" : playerTwo = new AIPlayerUnflippable(model, Player.Blue);
      break;
      case "strategy4" : playerTwo = new AIPlayerOutsmart(model, Player.Blue);
      break;
      case "strategy5" : playerTwo = new AIPlayerCombiner(model, Player.Blue,playersAI);
      break;
      default : throw new IllegalArgumentException("Invalid strategy for Player Two");
    }

    return new Result(playerOne, playerTwo);
  }

  private static class Result {
    private GPlayer playerOne;
    private GPlayer playerTwo;

    private Result(GPlayer playerOne, GPlayer playerTwo) {
      this.playerOne = playerOne;
      this.playerTwo = playerTwo;
    }

    public GPlayer playerOne() {
      return playerOne;
    }

    public GPlayer playerTwo() {
      return playerTwo;
    }
  }
}