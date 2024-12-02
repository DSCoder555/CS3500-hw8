package cs3500.threetrios.originalcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.originalcode.model.CardValues;
import cs3500.threetrios.originalcode.model.EmptyCard;
import cs3500.threetrios.originalcode.model.HoleCard;
import cs3500.threetrios.originalcode.model.PlayCard;
import cs3500.threetrios.originalcode.model.PlayableCard;
import cs3500.threetrios.originalcode.model.Player;
import cs3500.threetrios.originalcode.model.ReadCardConfigFile;
import cs3500.threetrios.originalcode.model.ReadGridConfigFile;
import cs3500.threetrios.originalcode.model.ThreeTrioModelGame;
import cs3500.threetrios.originalcode.model.ThreeTriosModel;

/**
 * Tests the behaviors of the ThreeTrios model implementation
 * that allow users to play a card to the grid, check if the game is won,
 * view the winner of the game. Tests valid and invalid constructions and
 * methods of the different card (EmptyCard, HoleCard, PlayCard) classes.
 */
public class TestGameModel {
  private ThreeTriosModel model1;

  private ThreeTriosModel model2;
  private ThreeTriosModel model4;

  private EmptyCard emptyCard;
  private EmptyCard emptyCardTwo;
  private EmptyCard emptyCardThree;
  private EmptyCard emptyNullCard;
  private HoleCard holeCard;
  private HoleCard holeCardTwo;
  private HoleCard holeCardThree;
  private HoleCard holeNullCard;
  private PlayCard playRedCard;
  private PlayCard playRedCardTwo;
  private PlayCard playRedCardThree;
  private PlayCard playRedCardFour;
  private PlayCard playRedNullCard;
  private PlayCard playBlueCard;
  private PlayCard playBlueCardTwo;
  private PlayCard playBlueCardThree;
  private PlayCard playBlueCardFour;
  private PlayCard playBlueNullCard;
  private PlayCard playNoPlayerCard;
  private PlayCard playNoPlayerCardTwo;
  private PlayCard playNoPlayerCardThree;
  private PlayCard playNoPlayerCardFour;
  private PlayCard playNoPlayerNullCard;

  @Before
  public void setUp() throws IOException {
    List<PlayableCard> mediumDeck = ReadCardConfigFile.readCardFile(new File(
            "src" + File.separator + "controller" + File.separator + "hw5" +
                    File.separator + "cards3.txt"));
    List<PlayableCard> increasingDeck = ReadCardConfigFile.readCardFile(new File("src" +
            File.separator +
            "controller" + File.separator + "hw5" + File.separator + "cardsIncreasing.txt"));
    model1 = new ThreeTrioModelGame(ReadGridConfigFile.readBoardFile(new File("src" +
            File.separator + "controller" +
            File.separator + "hw5" + File.separator + "BoardCardCellsNoReach.txt")), mediumDeck,
            null);
    model2 = new ThreeTrioModelGame(ReadGridConfigFile.readBoardFile(new File("src" +
            File.separator + "controller" +
            File.separator + "hw5" + File.separator + "boardSmallCardCells.txt")), increasingDeck,
            null);
    List<PlayableCard> badDeck = new ArrayList(List.copyOf(mediumDeck));
    badDeck.add(null);
    emptyCard = new EmptyCard();
    emptyCardTwo = emptyCard;
    emptyCardThree = new EmptyCard();
    emptyNullCard = null;
    holeCard = new HoleCard();
    holeCardTwo = holeCard;
    holeCardThree = new HoleCard();
    holeNullCard = null;
    playRedCard = new PlayCard("RedFire", CardValues.V8, CardValues.V9,
            CardValues.V1, CardValues.V3, Player.Red);
    playRedCardTwo = playRedCard;
    playRedCardThree = new PlayCard("RedFire", CardValues.V8, CardValues.V9,
            CardValues.V1, CardValues.V3, Player.Red);
    playRedCardFour = new PlayCard("RedFire", CardValues.V8, CardValues.V9,
            CardValues.V3, CardValues.V3, Player.Red);
    playRedNullCard = null;
    playBlueCard = new PlayCard("BlueSea", CardValues.V3, CardValues.V7,
            CardValues.V6, CardValues.V10, Player.Blue);
    playBlueCardTwo = playBlueCard;
    playBlueCardThree = new PlayCard("BlueSea", CardValues.V3, CardValues.V7,
            CardValues.V6, CardValues.V10, Player.Blue);
    playBlueCardFour = new PlayCard("BlueSea", CardValues.V3, CardValues.V7,
            CardValues.V6, CardValues.V9, Player.Blue);
    playBlueNullCard = null;
    playNoPlayerCard = new PlayCard("WhiteFan", CardValues.V9, CardValues.V4,
            CardValues.V5, CardValues.V7, Player.None);
    playNoPlayerCardTwo = playNoPlayerCard;
    playNoPlayerCardThree = new PlayCard("WhiteFan", CardValues.V9, CardValues.V4,
            CardValues.V5, CardValues.V7, Player.None);
    playNoPlayerCardFour = new PlayCard("WhitePan", CardValues.V9, CardValues.V4,
            CardValues.V5, CardValues.V7, Player.None);
    playNoPlayerNullCard = null;
  }


  @Test
  public void playCardTest() {
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(0, -1, 1));
    model1.startGame();
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(0, -1, 1));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(0, 1, -1));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(-1, 0, 1));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(0, 1, 1));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(0, 10, 1));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(0, 1, 10));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(10, 0, 1));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(4, 0, 1));
    model1.playCard(0, 0, 1);
    model1.playCard(0, 0, 0);
    model1.playCard(0, 2, 1);
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(2, 2, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> model1.playCard(0, 0, 0));
  }

  @Test
  public void getBlueWinnerAndHasWonTest() {
    model1.startGame();
    model1.playCard(0, 0, 1);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 0, 0);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 2, 1);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 2, 0);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 2, 2);
    Assert.assertTrue(model1.hasWon());
    Assert.assertEquals(model1.getWinner(), Player.Blue);
  }

  @Test
  public void getNoneWinnerTieTest() {
    model1.startGame();
    model1.playCard(0, 2, 0);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 0, 0);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 2, 1);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 0, 1);
    Assert.assertFalse(model1.hasWon());
    model1.playCard(0, 2, 2);
    Assert.assertTrue(model1.hasWon());
    Assert.assertEquals(model1.getWinner(), Player.None);
  }

  @Test
  public void testComboFlipping() {
    model2.startGame();
    model2.playCard(0, 1, 1);
    model2.playCard(0, 1, 0);
    model2.playCard(0, 0, 0);
    model2.playCard(0, 0, 1);
    model2.playCard(0, 0, 2);
    model2.playCard(0, 1, 2);
    model2.playCard(0, 2, 2);
    model2.playCard(0, 2, 1);
    model2.playCard(0, 2, 0);
    Assert.assertTrue(model2.hasWon());
    Assert.assertEquals(model2.getWinner(), Player.Red);
  }

  @Test
  public void emptyCardTopValueTest() {
    Assert.assertEquals("Checking if top value of an empty card is 11",
            emptyCard.topValue(), 11);
  }

  @Test
  public void emptyCardBottomValueTest() {
    Assert.assertEquals("Checking if bottom value of an empty card is 11",
            emptyCard.bottomValue(), 11);
  }

  @Test
  public void emptyCardLeftValueTest() {
    Assert.assertEquals("Checking if left value of an empty card is 11",
            emptyCard.leftValue(), 11);
  }

  @Test
  public void emptyCardRightValueTest() {
    Assert.assertEquals("Checking if right value of an empty card is 11",
            emptyCard.rightValue(), 11);
  }

  @Test
  public void emptyCardCanReplaceTest() {
    Assert.assertTrue(emptyCard.canReplace());
  }

  @Test
  public void emptyCardSwitchPlayerThrowsExceptionTest() {
    Assert.assertThrows("Checking if you can switch players with an empty card",
            IllegalStateException.class, () -> emptyCard.switchPlayer());
  }

  @Test
  public void emptyCardToStringTest() {
    Assert.assertEquals("Checking emptyCard's string representation",
            "_", emptyCard.toString());
  }

  @Test
  public void emptyCardGetPlayerTest() {
    Assert.assertEquals("Checking the current player of empty card",
            Player.None, emptyCard.getPlayer());
  }

  @Test
  public void twoCardsEqualReferenceCaseTest() {
    Assert.assertEquals(emptyCard, emptyCardTwo);
  }

  @Test
  public void twoCardsEqualValuesCaseTest() {
    Assert.assertEquals(emptyCard, emptyCardThree);
  }

  @Test
  public void twoCardsNonEqualTest() {
    Assert.assertNotEquals(emptyCard, emptyNullCard);
  }

  @Test
  public void equalReferenceCardsHaveSameHashCodeTest() {
    Assert.assertEquals(emptyCard.hashCode(), emptyCardTwo.hashCode());
  }

  @Test
  public void equalValueCardsHaveSameHashCodeTest() {
    Assert.assertEquals(emptyCard.hashCode(), emptyCardThree.hashCode());
  }

  @Test
  public void holeCardTopValueTest() {
    Assert.assertEquals("Checking if top value of a hole card is 11", holeCard.topValue(),
            11);
  }

  @Test
  public void holeCardBottomValueTest() {
    Assert.assertEquals("Checking if bottom value of a hole card is 11",
            holeCard.bottomValue(), 11);
  }

  @Test
  public void holeCardLeftValueTest() {
    Assert.assertEquals("Checking if left value of an empty card is 11",
            holeCard.leftValue(), 11);
  }

  @Test
  public void holeCardRightValueTest() {
    Assert.assertEquals("Checking if top value of a hole card is 11",
            holeCard.rightValue(), 11);
  }

  @Test
  public void holeCardCanReplaceTest() {
    Assert.assertFalse(holeCard.canReplace());
  }

  @Test
  public void holeCardSwitchPlayerTest() {
    Assert.assertThrows("Checking if you can switch player for HoleCard",
            IllegalStateException.class, () -> holeCard.switchPlayer());
  }

  @Test
  public void holeCardGetPlayerTest() {
    Assert.assertEquals("Checking current player of holeCard", Player.None,
            holeCard.getPlayer());
  }

  @Test
  public void holeCardToStringTest() {
    Assert.assertEquals("Checking string representation of hole card",
            " ", holeCard.toString());
  }

  @Test
  public void twoHoleCardsReferenceEqualTest() {
    Assert.assertEquals(holeCard, holeCardTwo);
  }

  @Test
  public void twoHoleCardsValueEqualTest() {
    Assert.assertEquals(holeCard, holeCardThree);
  }

  @Test
  public void twoHoleCardsNonEqualTest() {
    Assert.assertNotEquals(holeCard, holeNullCard);
  }

  @Test
  public void equalReferenceHoleCardsHaveSameHashCodeTest() {
    Assert.assertEquals(holeCard.hashCode(), holeCardTwo.hashCode());
  }

  @Test
  public void equalValueHoleCardsHaveSameHashCodeTest() {
    Assert.assertEquals(holeCard.hashCode(), holeCardThree.hashCode());
  }

  @Test
  public void testPlayCardTopValue() {
    Assert.assertEquals("Checking the top value of PlayCard1", 8,
            playRedCard.topValue());
  }

  @Test
  public void testPlayRedCardBottomValue() {
    Assert.assertEquals("Checking the bottom value of PlayCard1", 3,
            playRedCard.bottomValue());
  }

  @Test
  public void testPlayRedCardRightValue() {
    Assert.assertEquals("Checking the right value of PlayCard1", 1,
            playRedCard.rightValue());
  }

  @Test
  public void testPlayRedCardLeftValue() {
    Assert.assertEquals("Checking the left value of PlayCard1", 9,
            playRedCard.leftValue());
  }

  @Test
  public void testPlayRedCardCanReplace() {
    Assert.assertFalse(playRedCard.canReplace());
  }

  @Test
  public void testPlayRedCardGetPlayer() {
    Assert.assertEquals("Checking if current player is red",
            Player.Red, playRedCard.getPlayer());
    playRedCard.switchPlayer();
    Assert.assertEquals("Checking if current player is now blue",
            Player.Blue, playRedCard.getPlayer());
  }

  @Test
  public void testPlayBlueCardGetPlayer() {
    Assert.assertEquals("Checking if current player is red",
            Player.Blue, playBlueCard.getPlayer());
    playBlueCard.switchPlayer();
    Assert.assertEquals("Checking if current player is now blue",
            Player.Red, playBlueCard.getPlayer());
  }

  @Test
  public void testPlayRedCardToString() {
    Assert.assertEquals("Checking string representation of red card", "R",
            playRedCard.toString());
  }

  @Test
  public void testBlueCardToString() {
    Assert.assertEquals("Checking string representation of blue card", "B",
            playBlueCard.toString());
  }

  @Test
  public void testNoneCardToString() {
    Assert.assertEquals("Checking string representation of none card", "N",
            playNoPlayerCard.toString());
  }



  @Test
  public void checkReferenceEqualityRedCardTest() {
    Assert.assertEquals("Checking if two red playable cards are equal via reference",
            playRedCard, playRedCardTwo);
  }

  @Test
  public void checkValueEqualityRedCardTest() {
    Assert.assertEquals("Checking if two red playable cards are equal via values",
            playRedCard, playRedCardThree);
  }

  @Test
  public void checkNonValueEqualityRedCardTest() {
    Assert.assertNotEquals("Checking if two red playable cards are not equal by value",
            playRedCardThree, playRedCardFour);
  }

  @Test
  public void checkNonReferenceEqualityRedCardTest() {
    Assert.assertNotEquals("Checking if two red playable cards are not equal by reference",
            playRedCardThree, playRedNullCard);
  }

  @Test
  public void equalReferenceRedPlayCardsHaveSameHashCode() {
    Assert.assertEquals(playRedCard.hashCode(), playRedCardTwo.hashCode());
  }

  @Test
  public void equalRedValueCardsHaveSameHashCodeTest() {
    Assert.assertEquals(playRedCard.hashCode(), playRedCardThree.hashCode());
  }

  @Test
  public void nonEqualRedValueCardsHaveDiffHashCodeTest() {
    Assert.assertNotEquals(playRedCard.hashCode(), playRedCardFour.hashCode());
  }

  @Test
  public void checkReferenceEqualityNoPlayerCardTest() {
    Assert.assertEquals("Checking if two no player playable cards are equal via reference",
            playNoPlayerCard, playNoPlayerCardTwo);
  }

  @Test
  public void checkValueEqualityNoPlayerCardTest() {
    Assert.assertEquals("Checking if two no player playable cards are equal via values",
            playNoPlayerCard, playNoPlayerCardThree);
  }

  @Test
  public void checkNonValueEqualityNoPlayerCardTest() {
    Assert.assertNotEquals("Checking if two no player playable cards are not equal "
            + "by value", playNoPlayerCard, playNoPlayerCardFour);
  }

  @Test
  public void checkNonReferenceEqualityNoPlayerCardTest() {
    Assert.assertNotEquals("Checking if two no player playable cards are not equal "
                    + "by reference",
            playNoPlayerNullCard, playNoPlayerCard);
  }

  @Test
  public void equalReferenceNoPlayerPlayCardsHaveSameHashCode() {
    Assert.assertEquals(playNoPlayerCard.hashCode(), playNoPlayerCardTwo.hashCode());
  }

  @Test
  public void equalNoPlayerValueCardsHaveSameHashCodeTest() {
    Assert.assertEquals(playNoPlayerCard.hashCode(), playNoPlayerCardThree.hashCode());
  }

  @Test
  public void nonEqualNoPlayerValueCardsHaveDiffHashCodeTest() {
    Assert.assertNotEquals(playNoPlayerCard.hashCode(), playNoPlayerCardFour.hashCode());
  }

  @Test
  public void checkReferenceEqualityNoneCardTest() {
    Assert.assertEquals("Checking if two blue playable cards are equal via reference",
            playNoPlayerCard, playNoPlayerCardTwo);
  }

  @Test
  public void checkValueEqualityBlueCardTest() {
    Assert.assertEquals("Checking if two blue playable cards are equal via values",
            playBlueCard, playBlueCardThree);
  }

  @Test
  public void checkNonValueEqualityBlueCardTest() {
    Assert.assertNotEquals("Checking if two blue playable cards are not equal by value",
            playBlueCardThree, playBlueCardFour);
  }

  @Test
  public void checkNonReferenceEqualityBlueCardTest() {
    Assert.assertNotEquals("Checking if two blue playable cards are not equal by reference",
            playBlueCard, playBlueNullCard);
  }

  @Test
  public void equalReferenceBluePlayCardsHaveSameHashCode() {
    Assert.assertEquals(playBlueCard.hashCode(), playBlueCardTwo.hashCode());
  }

  @Test
  public void equalBlueValueCardsHaveSameHashCodeTest() {
    Assert.assertEquals(playBlueCard.hashCode(), playBlueCardThree.hashCode());
  }

  @Test
  public void nonEqualBlueValueCardsHaveDiffHashCodeTest() {
    Assert.assertNotEquals(playBlueCard.hashCode(), playBlueCardFour.hashCode());
  }

}
