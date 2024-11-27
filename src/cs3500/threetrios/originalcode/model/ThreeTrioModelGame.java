package cs3500.threetrios.originalcode.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cs3500.threetrios.originalcode.view.ModelListenerInterface;

/**
 * Implements the {@link ThreeTriosModel} interface and allows the user to perform operations
 * such as starting the game and playing a card to the grid.
 */
public class ThreeTrioModelGame implements ThreeTriosModel {

  /**
   * In this 2D array representation, the first index refers to the row, and the second index refers
   * to the column. Both row and column indices are 0-based.
   */
  private Card[][] grid;
  private List<PlayableCard> redPlayerCards;
  private List<PlayableCard> bluePlayerCards;
  private GameState state = GameState.PreGame;
  private List<ModelListenerInterface> modelListeners;
  //Class Invariant: the total sum of redPlayerCards.size, bluePlayerCards.size and the elements
  //in grid that are PlayableCards will always be even.

  /**
   * Sets up the grid based on a given grid, and initializes a random object which will be used
   * to shuffle the deck.
   *
   * @param grid The given grid.
   * @param rand The given random object.
   * @throws IllegalArgumentException if the given grid is null or if any value within the grid is
   *                                  null.
   */
  public ThreeTrioModelGame(Card[][] grid, List<PlayableCard> deck, Random rand) {
    if (grid == null) {
      throw new IllegalArgumentException("The provided grid to start the game was empty.");
    }
    this.grid = new Card[grid.length][grid[0].length];
    modelListeners = new ArrayList<>();
    // TODO: reminder for later: It might be better to use longer var names
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == null) {
          throw new IllegalArgumentException("The provided grid has a null value at " + i + ", " +
                  j);
        } else if (grid[i][j] instanceof PlayableCard) {
          throw new IllegalArgumentException("The provided grid has a card already on the board.");
        }
        this.grid[i][j] = grid[i][j];
      }
    }
    if (deck == null) {
      throw new IllegalArgumentException("The provided deck is null");
    }
    int playableSpaces = 0;
    for (Card[] row : grid) {
      for (Card card : row) {
        if (card.canReplace()) {
          playableSpaces++;
        }
      }
    }
    if (playableSpaces % 2 == 0) {
      throw new IllegalArgumentException("The provided grid has an odd number of card cells");
    }
    if (deck.size() < playableSpaces + 1) {
      throw new IllegalArgumentException("The provided deck is too small to start the game");
    }
    List<PlayableCard> tempDeck = new ArrayList<>(deck);
    if (rand != null) {
      Collections.shuffle(tempDeck, rand);
    }
    redPlayerCards = new ArrayList<>();
    bluePlayerCards = new ArrayList<>();
    Player addPlayer = Player.Red;
    for (int i = 0; i < playableSpaces + 1; i++) {
      if (addPlayer == Player.Red) {
        redPlayerCards.add(tempDeck.remove(0).setPlayer(Player.Red));
        addPlayer = Player.Blue;
      } else {
        bluePlayerCards.add(tempDeck.remove(0).setPlayer(Player.Blue));
        addPlayer = Player.Red;
      }
    }
    state = GameState.Red;
  }

  //add randomizer
  @Override
  public void startGame() {
    for (ModelListenerInterface listener : modelListeners) {
      if (listener.getPlayer() == currentPlayer()) {
        listener.notifyPlayerTurn();
      }
      listener.gameUpdated();
    }
  }

  @Override
  public void playCard(int handIdx, int row, int col) {
    if (state == GameState.PreGame || state == GameState.PostGame) {
      throw new IllegalStateException("The game is over or has not started yet");
    }
    if (row < 0 || col < 0 || row > grid.length || col > grid[0].length ||
            !grid[row][col].canReplace()) {
      throw new IllegalArgumentException("The inputs are invalid");
    }
    if (state == GameState.Red) {
      if (handIdx >= redPlayerCards.size() || handIdx < 0) {
        throw new IllegalArgumentException("The requested card does not exist");
      }
      grid[row][col] = redPlayerCards.remove(handIdx);
    } else if (state == GameState.Blue) {
      if (handIdx >= bluePlayerCards.size() || handIdx < 0) {
        throw new IllegalArgumentException("The requested card does not exist");
      }
      grid[row][col] = bluePlayerCards.remove(handIdx);
    } else {
      throw new IllegalStateException("The game is over or has not started");
    }
    List<Coordinate> changed = List.of(new Coordinate(row, col));
    do {
      List<Coordinate> newChanged = new ArrayList<>();
      for (Coordinate coordinate : changed) {
        newChanged.addAll(attack(coordinate.getRow(), coordinate.getCol(), true));
      }
      changed = newChanged;
    }
    while (!changed.isEmpty());
    for (ModelListenerInterface listener : modelListeners) {
      listener.gameUpdated();
    }
    if (hasWon()) {
      state = GameState.PostGame;
      for (ModelListenerInterface listener : modelListeners) {
        if (getWinner() == Player.None) {
          listener.notifyGameWon(getWinner(), getPlayerScore(Player.Red));
        } else {
          listener.notifyGameWon(getWinner(), getPlayerScore(getWinner()));
        }
      }
    } else {
      state = GameState.switchPlayer(state);
      for (ModelListenerInterface listener : modelListeners) {
        if (listener.getPlayer() == currentPlayer()) {
          listener.notifyPlayerTurn();
        }
        listener.gameUpdated();
      }
    }
  }

  /**
   * Executes the attack phase by comparing the values of the specified card with adjacent cards
   * and switching ownership of opposing cards if conditions are met.
   *
   * @param row the 0-based row index of the card initiating the attack
   * @param col the 0-based column index of the card initiating the attack
   * @return a list of coordinates for cards whose ownership was switched during the attack
   * @throws IllegalArgumentException if row or col indices are invalid.
   */
  private List<Coordinate> attack(int row, int col, boolean realMove) {
    if (row < 0 || row > gridHeight() || col < 0 || col > gridLength()) {
      throw new IllegalArgumentException("Invalid row or column index");
    }
    List<Coordinate> changedCoors = new ArrayList<>();
    if (row - 1 >= 0) {
      if (grid[row - 1][col].getPlayer() != currentPlayer() &&
              grid[row][col].topValue() > grid[row - 1][col].bottomValue()) {
        if (realMove) {
          grid[row - 1][col].switchPlayer();
        }
        changedCoors.add(new Coordinate(row - 1, col));
      }
    }
    if (row + 1 < grid.length) {
      if (grid[row + 1][col].getPlayer() != currentPlayer() &&
              grid[row][col].bottomValue() > grid[row + 1][col].topValue()) {
        if (realMove) {
          grid[row + 1][col].switchPlayer();
        }
        changedCoors.add(new Coordinate(row + 1, col));
      }
    }
    if (col - 1 >= 0) {
      if (grid[row][col - 1].getPlayer() != currentPlayer() &&
              grid[row][col].leftValue() > grid[row][col - 1].rightValue()) {
        if (realMove) {
          grid[row][col - 1].switchPlayer();
        }
        changedCoors.add(new Coordinate(row, col - 1));
      }
    }
    if (col + 1 < grid[0].length) {
      if (grid[row][col + 1].getPlayer() != currentPlayer() &&
              grid[row][col].rightValue() > grid[row][col + 1].leftValue()) {
        if (realMove) {
          grid[row][col + 1].switchPlayer();
        }
        changedCoors.add(new Coordinate(row, col + 1));
      }
    }

    return changedCoors;
  }

  @Override
  public boolean hasWon() {
    if (state == GameState.PostGame) {
      return true;
    } else if (state == GameState.PreGame) {
      throw new IllegalStateException("Game has not started yet");
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] instanceof EmptyCard) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public Player getWinner() {
    int bCards = bluePlayerCards.size();
    int rCards = redPlayerCards.size();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].getPlayer() == Player.Red) {
          rCards++;
        } else if (grid[i][j].getPlayer() == Player.Blue) {
          bCards++;
        }
      }
    }
    if (rCards > bCards) {
      return Player.Red;
    } else if (bCards > rCards) {
      return Player.Blue;
    } else {
      return Player.None;
    }
  }

  @Override
  public Player currentPlayer() {
    if (state == GameState.Red) {
      return Player.Red;
    } else if (state == GameState.Blue) {
      return Player.Blue;
    } else {
      return Player.None;
    }

  }

  @Override
  public String gridString() {
    String result = "";
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        result += grid[i][j];
      }
      if (i + 1 != grid.length) {
        result += "\n";
      }
    }
    return result;
  }

  @Override
  public String currentHandString() {
    String result = "";
    if (state == GameState.Red) {
      for (int i = 0; i < redPlayerCards.size(); i++) {
        result += redPlayerCards.get(i).toHandString() + "\n";
      }
    } else {
      for (int i = 0; i < bluePlayerCards.size(); i++) {
        result += bluePlayerCards.get(i).toHandString() + "\n";
      }
    }
    return result;
  }

  @Override
  public void addModelListener(ModelListenerInterface listener) {
    modelListeners.add(listener);
  }

  @Override
  public Card[][] getGrid() {
    Card[][] newGrid = new Card[gridHeight()][gridLength()];
    for (int i = 0; i < gridHeight(); i++) {
      for (int j = 0; j < gridLength(); j++) {
        newGrid[i][j] = getCard(i, j);
      }
    }
    return newGrid;
  }

  @Override
  public List<Card> getHand(Player player) {
    List result = new ArrayList<>();
    if (player == Player.Red) {
      for (Card card : redPlayerCards) {
        result.add(card.copyOf());
      }
    } else if (player == Player.Blue) {
      for (Card card : bluePlayerCards) {
        result.add(card.copyOf());
      }
    } else {
      throw new IllegalArgumentException();
    }
    return result;
  }

  @Override
  public int gridHeight() {
    return grid.length;
  }

  @Override
  public int gridLength() {
    return grid[0].length;
  }

  @Override
  public Card getCard(int row, int col) {
    if (row < 0 || row >= gridHeight() || col < 0 || col >= gridLength()) {
      throw new IllegalArgumentException("Row or col indices are invalid");
    }
    return grid[row][col].copyOf();
  }

  @Override
  public Player getOwner(int row, int col) {
    if (row < 0 || row >= gridHeight() || col < 0 || col >= gridLength()) {
      throw new IllegalArgumentException("Row or col indices are invalid");
    }
    return grid[row][col].getPlayer();
  }

  @Override
  public boolean isLegal(int row, int col) {
    if (row < 0 || row > gridHeight() - 1 || col < 0 || col > gridLength() - 1) {
      return false;
    }
    return grid[row][col].canReplace();
  }

  @Override
  public int getPossibleFlips(int cardIdx, int row, int col) {
    if (cardIdx < 0 || cardIdx > getHand(getCurrentPlayer()).size()
            || row < 0 || row > gridHeight() || col < 0 || col > gridLength() - 1) {
      throw new IllegalArgumentException("Card, row, or column indices are invalid");
    }
    PlayableCard card;
    if (currentPlayer() == Player.Red) {
      card = redPlayerCards.get(cardIdx);
    } else {
      card = bluePlayerCards.get(cardIdx);
    }
    grid[row][col] = card;
    Set<Coordinate> flippedCoors = new HashSet<>();
    List<Coordinate> newCoors = new ArrayList<>(List.of(new Coordinate(row, col)));
    do {
      flippedCoors.addAll(newCoors);
      newCoors = new ArrayList<>(List.of());
      for (Coordinate coor : flippedCoors) {
        newCoors.addAll(attack(coor.getRow(), coor.getCol(), false));
      }
    }
    while (!flippedCoors.containsAll(newCoors));
    grid[row][col] = new EmptyCard();
    return flippedCoors.size();
  }

  @Override
  public int getPlayerScore(Player player) {
    int points = 0;
    if (player == Player.Red) {
      points += redPlayerCards.size();
    } else if (player == Player.Blue) {
      points += bluePlayerCards.size();
    } else {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j].getPlayer() == player) {
          points++;
        }
      }
    }
    return points;
  }

  @Override
  public boolean isGameOver() {
    return state == GameState.PostGame;
  }

  @Override
  public Player getCurrentPlayer() {
    if (state == GameState.Red) {
      return Player.Red;
    } else if (state == GameState.Blue) {
      return Player.Blue;
    } else {
      return Player.None;
    }
  }


}
