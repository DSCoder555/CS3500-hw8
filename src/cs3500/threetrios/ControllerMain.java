package cs3500.threetrios;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import cs3500.threetrios.newcode.ModelAdapter;
import cs3500.threetrios.newcode.ViewAdapter;
import cs3500.threetrios.newcode.ViewListenerAdapter;
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
public final class ControllerMain {

  /**
   * Initializes and starts the Three Trios game, loading the board and card configurations,
   * and launching the GUI.
   * @param args Not used in this main function.
   * @throws IOException if any input/output error occurred
   */
  public static void main(String[] args) throws IOException {

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

    ThreeTriosGUI viewPlayer1 = new ThreesTriosGUIView(model);
    ReadOnlyThreeTriosModel modelAdapt = new ModelAdapter(model);
    VisualThreeTriosView viewPlayer2 = new GUIThreeTriosView(modelAdapt);
    ThreeTriosGUI viewAdapt = new ViewAdapter(viewPlayer2, PlayerColor.BLUE);
    GPlayer player1 = new HumanPlayer(model, Player.Red);
    GPlayer player2 = new HumanPlayer(model, Player.Blue);
    ThreesTrioController controller1 = new ThreesTrioController(model, player1, viewPlayer1);
    ThreesTrioController controller2 = new ViewListenerAdapter(model, player2, viewAdapt);
    model.startGame();
  }
}
