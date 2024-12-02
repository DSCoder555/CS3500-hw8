package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cs3500.threetrios.originalcode.controller.GPlayer;
import cs3500.threetrios.originalcode.controller.HumanPlayer;
import cs3500.threetrios.originalcode.controller.ThreesTrioController;
import cs3500.threetrios.originalcode.model.PlayableCard;
import cs3500.threetrios.originalcode.model.MockModel;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadCardConfigFile;
import cs3500.threetrios.originalcode.model.ReadGridConfigFile;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;
import cs3500.threetrios.originalcode.view.MockGUIView;
import cs3500.threetrios.originalcode.view.ThreeTriosGUI;

/**
 * Tests the controller with a mock model and mock GUI View.
 */
public class TestController {
  private ThreesTrioController controller1;
  private ThreesTrioController controller2;
  Appendable log;

  @Before
  public void setup() throws IOException {
    log = new StringBuilder();
    List<PlayableCard> cardList = ReadCardConfigFile.readCardFile(new File("src" +
            File.separator + "controller" + File.separator + "hw5" + File.separator
            + "cards3.txt"));
    ThreeTriosModel model = new MockModel(log,
            ReadGridConfigFile.readBoardFile(new File("src" + File.separator + "controller"
                    + File.separator + "hw5" + File.separator + "BoardCardCellsNoReach.txt")),
            cardList);

    ThreeTriosGUI viewPlayer1 = new MockGUIView(log);
    ThreeTriosGUI viewPlayer2 = new MockGUIView(log);
    GPlayer player1 = new HumanPlayer(model, Player.Red);
    GPlayer player2 = new HumanPlayer(model, Player.Blue);
    controller1 = new ThreesTrioController(model,player1, viewPlayer1);
    controller2 = new ThreesTrioController(model,player2, viewPlayer2);
  }

  @Test
  public void testControllerConstructor() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new ThreesTrioController(null,
            null,null));
  }

  @Test
  public void testControllerModelInputs() {
    controller1.getPlayer();
    controller2.getPlayer();
    Assert.assertEquals(log.toString(),"A grid was created with dimensions: 3 X 3\n" +
            "You created a view\n" +
            "You created a view\n" +
            "You added a feature listener this view\n" +
            "User tried to add a listenerYou started rendering this view\n" +
            "You displayed this view\n" +
            "You added a feature listener this view\n" +
            "User tried to add a listenerYou started rendering this view\n" +
            "You displayed this view\n");
  }

  @Test
  public void testControllerViewInputs() {
    controller1.gameUpdated();
    controller2.selectedHand(2);
    Assert.assertEquals(log.toString(),"A grid was created with dimensions: 3 X 3\n" +
            "You created a view\n" +
            "You created a view\n" +
            "You added a feature listener this view\n" +
            "User tried to add a listenerYou started rendering this view\n" +
            "You displayed this view\n" +
            "You added a feature listener this view\n" +
            "User tried to add a listenerYou started rendering this view\n" +
            "You displayed this view\n" +
            "You updated this view\n" +
            "Warning: AI Player tried to get the current playerYou displayed this message: " +
            "Not your turn\n");
  }
}
