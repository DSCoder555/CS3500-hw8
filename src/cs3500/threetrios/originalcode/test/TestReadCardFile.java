package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.model.CardValues;
import cs3500.threetrios.originalcode.model.PlayCard;
import cs3500.threetrios.originalcode.model.PlayableCard;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadCardConfigFile;

/**
 * Tests the behaviors of reading in and parsing valid
 * and invalid card configuration files.
 */
public class TestReadCardFile {
  private List<PlayableCard> deck;

  @Before
  public void setUp() {
    deck = new ArrayList<>();
    deck.add(new PlayCard("HeroKnight", CardValues.V7, CardValues.V3,
            CardValues.V9, CardValues.V10, Player.Red));
    deck.add(new PlayCard("SkyWhale", CardValues.V5, CardValues.V4, CardValues.V8,
            CardValues.V7, Player.Red));
    deck.add(new PlayCard("AngryDragon", CardValues.V6, CardValues.V5, CardValues.V9,
            CardValues.V9, Player.Red));
    deck.add(new PlayCard("WindBird", CardValues.V3, CardValues.V7, CardValues.V2,
            CardValues.V4, Player.Red));
    deck.add(new PlayCard("WorldDragon", CardValues.V8, CardValues.V10,
            CardValues.V7, CardValues.V5, Player.Red));
    deck.add(new PlayCard("ThunderBeast", CardValues.V9, CardValues.V4,
            CardValues.V2, CardValues.V10, Player.Red));
    deck.add(new PlayCard("FirePhoenix", CardValues.V7, CardValues.V6,
            CardValues.V8, CardValues.V3, Player.Red));
    deck.add(new PlayCard("WaterSerpent", CardValues.V5, CardValues.V8,
            CardValues.V10, CardValues.V6, Player.Red));
    deck.add(new PlayCard("EarthGolem", CardValues.V9, CardValues.V3, CardValues.V7,
            CardValues.V8, Player.Red));
  }

  @Test
  public void testFileDoesNotExist() {
    Assert.assertThrows("Checking if an invalid file can be passed in",
            FileNotFoundException.class,
        () -> ReadCardConfigFile.readCardFile(new File("bad" + File.separator
                    + "file")));
  }

  @Test
  public void testFileWithInvalidLineSize() {
    Assert.assertThrows("Checking if a file that contains a line with " +
                    "fewer than 5 values can be passed in",
            IllegalStateException.class,
        () -> ReadCardConfigFile.readCardFile(new File("src" + File.separator + "controller"
        + File.separator + "hw5" + File.separator + "invalid_cards.txt")));
  }

  @Test
  public void testFileWithInconvertibleToChar() {
    Assert.assertThrows("Checking if a file that contains card values "
                    + "that cannot be converted to chars",
            IllegalStateException.class,
        () -> ReadCardConfigFile.readCardFile(new File("src" + File.separator + "controller"
        + File.separator + "hw5" + File.separator + "inconvertible_cards.txt")));
  }


}
