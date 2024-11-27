package cs3500.threetrios.originalcode.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import cs3500.threetrios.originalcode.view.ModelListenerInterface;

/**
 * A mock implementation of the `ThreeTriosModel` for testing purposes,
 * logging interactions via an appendable and simulating game behavior
 * without affecting game logic.
 */
public class MockModel implements ThreeTriosModel {
  private final Appendable log;
  public Card[][] grid;
  public GameState gameState = GameState.PreGame;
  private List<PlayableCard> redPlayerCards = new ArrayList<>();
  private List<PlayableCard> bluePlayerCards = new ArrayList<>();

  /**
   * Initializes the mock model with a log.
   *
   * @param log The appendable log to capture interactions
   * @throws NullPointerException     if log is null
   * @throws IllegalArgumentException if the given grid is null or if any value within the grid is
   *                                  null or a Playable card.
   */
  public MockModel(Appendable log, Card[][] grid, List<PlayableCard> deck) {
    this.log = Objects.requireNonNull(log, "Log cannot be null");
    if (grid == null) {
      throw new IllegalArgumentException("The provided grid to start the game was empty.");
    }
    this.grid = new Card[grid.length][grid[0].length];
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == null) {
          throw new IllegalArgumentException("The provided grid has a null value at " + row + ", "
                  + col);
        }
        this.grid[row][col] = grid[row][col];
      }
    }
    try {
      log.append("A grid was created with dimensions: " + grid.length + " X "
              + grid[0].length + "\n");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
    if (gameState != GameState.PreGame) {
      throw new IllegalStateException("Game has already started");
    } else if (deck == null) {
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
    gameState = GameState.Red;
  }


  @Override
  public void startGame() {

    try {
      log.append("The game was started\n");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
  }

  @Override
  public void playCard(int handIdx, int row, int col) {
    if (row < 0 || col < 0 || row > grid.length || col > grid[0].length ||
            !grid[row][col].canReplace()) {
      throw new IllegalArgumentException("The inputs are invalid");
    }
    try {
      log.append(getCurrentPlayer() + " played " + getHand(getCurrentPlayer()).get(handIdx)
              + "at " + row + ", " + col);
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
  }

  @Override
  public boolean hasWon() {
    try {
      log.append("Warning: AI Player tried to check if someone has won");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
    return false;
  }

  @Override
  public Player getWinner() {
    try {
      log.append("Warning: AI Player tried to get the winner");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
    return Player.Red;
  }

  @Override
  public Player currentPlayer() {
    try {
      log.append("Warning: AI Player tried to get the current player");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
    if (gameState == GameState.Red) {
      return Player.Red;
    } else {
      return Player.Blue;
    }
  }

  @Override
  public String gridString() {
    try {
      log.append("Warning: AI Player tried to output the grid string");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
    return "";
  }

  @Override
  public String currentHandString() {
    try {
      log.append("Warning: AI Player tried to output the hand string");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
    return "";
  }

  @Override
  public void addModelListener(ModelListenerInterface listener) {
    try {
      log.append("User tried to add a listener");
    } catch (IOException ioe) {
      System.out.println("I/O error occurred: " + ioe.getMessage());
    }
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
    if (row < 0 || row >= gridHeight() || col < 0 || col >= gridLength()) {
      return 0;
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

  /**
   * Executes the attack phase by comparing the values of the specified card with adjacent cards
   * and switching ownership of opposing cards if conditions are met.
   *
   * @param row the 0-based row index of the card initiating the attack
   * @param col the 0-based column index of the card initiating the attack
   * @return a list of coordinates for cards whose ownership was switched during the attack
   */
  private List<Coordinate> attack(int row, int col, boolean realMove) {
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
    return gameState == GameState.PostGame;
  }

  @Override
  public Player getCurrentPlayer() {
    if (gameState == GameState.Red) {
      return Player.Red;
    } else if (gameState == GameState.Blue) {
      return Player.Blue;
    } else {
      throw new IllegalStateException("The game is over or not started");
    }
  }
}
