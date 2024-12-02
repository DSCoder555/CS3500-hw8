package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cs3500.threetrios.originalcode.model.PlayableCard;
import cs3500.threetrios.originalcode.model.ReadCardConfigFile;
import cs3500.threetrios.originalcode.model.ReadGridConfigFile;
import cs3500.threetrios.originalcode.model.ThreeTrioModelGame;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;
import cs3500.threetrios.originalcode.view.ThreeTriosView;
import cs3500.threetrios.originalcode.view.ThreeTriosViewGame;

/**
 * Tests the behaviors needed for a view of the ThreeTrios implementation
 * that transmits information to the user.
 */
public class TestGameView {
  ThreeTriosModel model;
  ThreeTriosView view;
  List<PlayableCard> smallDeck;

  @Test
  public void smallGameViewTest() throws IOException {

    smallDeck = ReadCardConfigFile.readCardFile(new File("src" + File.separator +
            "controller" + File.separator + "hw5" + File.separator + "cards1.txt"));
    model = new ThreeTrioModelGame(ReadGridConfigFile.readBoardFile(new File("src" +
            File.separator + "controller" +
            File.separator + "hw5" + File.separator + "BoardCardCellsNoReach.txt")), smallDeck,
            null);
    view = new ThreeTriosViewGame(model);
    model.startGame();
    Assert.assertEquals(view.toString(), "Player: RED\n" +
            "__ \n" +
            "   \n" +
            "___\n" +
            "Hand:\n" +
            "HeroKnight 7 A 9 3\n" +
            "AngryDragon 6 9 9 5\n" +
            "WorldDragon 8 5 7 A\n");
    model.playCard(0, 0, 1);
    Assert.assertEquals(view.toString(), "Player: BLUE\n" +
            "_R \n" +
            "   \n" +
            "___\n" +
            "Hand:\n" +
            "SkyWhale 5 7 8 4\n" +
            "WindBird 3 4 2 7\n" +
            "ThunderBeast 9 A 2 4\n");
    model.playCard(0, 0, 0);
    Assert.assertEquals(view.toString(), "Player: RED\n" +
            "BB \n" +
            "   \n" +
            "___\n" +
            "Hand:\n" +
            "AngryDragon 6 9 9 5\n" +
            "WorldDragon 8 5 7 A\n");
    model.playCard(0, 2, 1);
    Assert.assertEquals(view.toString(), "Player: BLUE\n" +
            "BB \n" +
            "   \n" +
            "_R_\n" +
            "Hand:\n" +
            "WindBird 3 4 2 7\n" +
            "ThunderBeast 9 A 2 4\n");
    model.playCard(0, 2, 0);
    Assert.assertEquals(model.gridString(), "BB \n   \nBR_");
  }
}
