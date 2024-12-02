__Overview:__  
- The Three Trios game is a two-player card game played on a rectangular grid. The objective is to control the majority of the grid by placing cards with attack values and engaging in battles against the opponents cards. Players compete to occupy grid cells with cards of their color by beating the opponent’s cards in adjacent cells.

__Quick Start:__
```java
public class Main {
  public static void main(String[] args) {
    ThreeTriosModel game = new ClassicThreeTriosModel(); // This instantiates the model. When model is instantiated, Players are instantiated as well. One is assigned blue, while the other is assigned red.
    game.startGame("gridConfig.txt", "cardData.txt"); // Starts the game with the given configuration files that specify the board and card data.
    game.playToBoard(0, 1, 2); // Example move: plays the first card in hand at row 1, column 2.
    game.battle(game.getGrid()[1][2].getCard()); // Initiate a battle at the last played card.
  }
}
```

__Key Components (With Subcomponents):__  
- ClassicThreeTriosModel: This class is the primary driver of the game’s flow. It implements the core logic for gameplay, including setup, turn progression, and battle mechanics. ClassicThreeTriosModel manages the game board, distributes cards, handles turn interactions, and determines the game’s end. It actively controls the sequence of play, handling how ThreeTriosPlayer objects interact with the game grid and use ThreeTriosCard objects. Additionally, it uses Direction and AtkVal to conduct battles between cards, ensuring that each move follows to the rules of the game.
    - Direction: Enum defining the cardinal directions (North, East, South, West) used in the cards and card battles. It exists to standardize how attack values are compared during gameplay, letting ClassicThreeTriosModel determine attack strength based on direction. Direction is used when comparing ThreeTriosCard attack values during battles, but it is driven by the battle logic in ClassicThreeTriosModel.
    - AtkVal: Enum representing the possible attack values for cards, ranging from One (1) to Nine (9), with a special value A (10). It exists to create a consistent basis for comparing card strengths across different directions in the game. AtkVal is stored in each ThreeTriosCard and accessed by ClassicThreeTriosModel to determine the outcome of battles, but it is driven by the model’s control logic.


- GridCell: Represents an individual cell on the game grid, which can either hold a card or act as a placeholder (Cardholder or Hole). Each cell’s state is determined by the actions of ClassicThreeTriosModel, as it places ThreeTriosCard objects on the grid. GridCell plays a role in the visual and logical structure of the game’s board.
  - CellType: Enum defining the type of each grid cell, such as Cardholder (cells where cards can be placed) or Hole (cells that are unplayable). It exists to enforce placement rules on the board, helping ClassicThreeTriosModel validate moves based on cell type. CellType defines the properties of each GridCell and restricts actions within the game’s flow.


- ThreeTriosCard: This class represents a game card with a name, a player color, and attack values in four directions (North, East, South, and West). Each ThreeTriosCard holds AtkVal values for each direction, which are used to conduct battles. While cards are driven by player actions and game logic in ClassicThreeTriosModel, they are essential tools for gameplay based on their placement and strength.


- ThreeTriosPlayer: Represents each player in the game, defined by a color and a hand of cards. Players are participants in the flow, driven by the game model’s turn-based structure. Each player holds a set of ThreeTriosCard objects, which they place on the board to try and beat the opponent. ThreeTriosPlayer objects operate within the rules and sequence set by ClassicThreeTriosModel.
  - PlayerColor: Enum that specifies a player's color (Red or Blue). It exists to differentiate between the two players and associate cards with their respective owners. PlayerColor is used by ThreeTriosPlayer to establish this, which ClassicThreeTriosModel uses to manage turns and card ownership on the board.

__Source Organization:__
- ClassicThreeTriosModel.java: Located in model.
- GridCell.java: Located in model.
- CellType.java: Located in model.
- ThreeTriosCard.java: Located in model.
- AtkVal.java: Located in model.
- ThreeTriosPlayer.java: Located in model.
- PlayerColor.java: Located in model.
- Direction.java: Located in model.

__ReadMe Modifications:__
- The contents of a cell at a given coordinate are the CellType of the cell, and a card object which is null if the CellType is not cardholder.
- The player that owns the card in a cell at a given coordinate is the player whose PlayerColor is the same as the PlayerColor value of the card in that cell.
- Given a card and a coordinate, A player can flip as many cards as there are on the board before the card is placed at that position (All the cards on the board if they all have the other players color and all lose in the battle phase flow).

__Changes for Assignment 7:__
- Refactored getNeighborAndBattle method, reduced duplicate code
- Moved file-reading to controller
- Added cardAtCoordinate and ownerAtCoordinate methods, extracted accordingly
- Added score evaluation, changed determineWinner accordingly
- Added numCols and numRows method

__Changes for Assignment 8:__
- When re-running a game, please stop the current run before running again to prevent the ClassCast
  Exception (AWT-EventQueue-0).
- terminal now requires command-line arguments for jar command ("java -jar NameOfJARFile.jar human 
  maxflip" for instance. valid commands are "human", "maxflip", and "corner".
- Model now has a field, a list of ModelStatusFeatures listeners. These allow the model and the
  controller to communicate with one another.
- Model now notifies in regard to the current player's turn, when the turn changes, and whether
  the game is over.
- Since we now have different player variants, the model does not add set players anymore upon
  game setup: it adds two specified players based on their type to the game.
- There are now two implementations of a player, where players notify the controller upon making
  a specific action. The machine player obviously takes strategies into consideration.
- The strategies now return a move including the index of the suggested card to play in that
  player's hand instead of the card itself.
- The controller now communicates with the view to prevent card selection in the opponent's hand,
  as well as generating visual display messages.