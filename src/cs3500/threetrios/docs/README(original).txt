Configuration files for the cards in ThreeTrios and the playing board/grid are made to set up the
possible game states. These configuration files are the prerequisites for the setting up of the
playing board. When a client plays ThreeTrios, they do not have to worry about what is happening
behind the scenes, specifically where the configuration files are being read, parsed, and passed
into the model to put the game in a ready-to-play state.

The configuration files drive the entire game system. Without them, the playing cards the clients
see on the board and in their hand would not be readily available, and the playing board would
not be set up properly. Furthermore, it is important that regardless of the board and cards being
valid or invalid, the game setup must respond accordingly. Exceptions are thrown to prevent the
program from being played by the user, as it would crash. Only when the board and card
configuration files are valid, they are passed into the model so that the client can start the game.
Examples of valid configurations are provided below for the card and board configuration files,
respectively, with notes about what each line represents (to clarify, these are not in the
configuration files, they are just to explain each line. If these notes were in the configuration
files, they would be considered invalid and would throw code exceptions).

Example Card Configuration File:
IceGolem 3 4 5 A (One card, its name is IceGolem, north attack value is 3, south attack value is 4,
                  east attack value is 5, and west attack value is A.)

Example Board Configuration File:
2 2 (dimensions, first value is the number of rows and second value is the number of columns)
C C (Two EmptyCards represented with the character 'C')
C X (One EmptyCard represented with the character 'C' and one HoleCard
represented with the character 'X')

Note: To start the code, a code snippet is provided below with in-line comments and
an explanation of each step beneath the code.

public static void main(String[] args) throws IOException {
    try {
        // Initialize the model with a game board configuration file and a Random instance
        ThreeTriosModel model = new ThreeTrioModelGame(
                ReadGridConfigFile.readBoardFile(new File("src" + File.separator + "controller"
                        + File.separator + "hw5" + File.separator + "BoardCardCellsNoReach.txt")),
                new Random());

        // Load a list of playable cards from a configuration file
        List<PlayableCard> cardList = ReadCardConfigFile.readCardFile(new File("src" +
                File.separator + "controller" + File.separator + "hw5" + File.separator
                + "cards3.txt"));

        // Start the game with the loaded cards
        model.startGame(cardList);

        // Play a card at a specific grid position as an example move
        model.playCard(0, 0, 0);

        // Initialize the GUI view for the game
        ThreesTriosGUIView view = new ThreesTriosGUIView(model);

        // Set up the controller, linking the model and view, and start the game
        ThreesTrioController controller = new ThreesTrioController(model, view, new HumanPlayer(), new HumanPlayer());
        controller.startGame();
    } catch (IOException e) {
        throw new IOException("Input/output error while file reading");
    }
}

1. Initialize the model - Loads the game board configuration and creates a model with a random
generator.
2. Load playable cards - Reads a configuration file to get a list of playable cards.
3. Start the game - Initializes the game in the model with the loaded cards.
4. Play a card - Makes an example move by placing a card at the specified grid location.
5. Initialize the view - Sets up the GUI view, which displays the game state.
6. Set up the controller - Connects the model and view, and starts the game.


Note: Any configuration files that do not follow the format from earlier, this includes
lines with shorter or longer length, the number of dimensions in the board configuration
file not matching the number of rows and columns of cards, or any values that should be one
character (the attack values) are longer than one character, and therefore,
cannot be converted into a character. These invalid cases, others, and their corresponding
exceptions thrown are documented in the ReadBoardConfig File and ReadCardConfig file class.

The configuration files are located in the controller directory that is on the same level as the src
and test directories. The classes that have functions to read in and parse these configuration
files (ReadBoardConfigFile and ReadCardConfigFile) are located in the src directory.
A diagram of where these components and other components exist are located is displayed below.

+- src/
| +- controller/
| +- +- hw5/
| | | | +- AIPlayerBase class
| | | | +- AIPlayerCorner class
| | | | +- AIPlayerFlipper class
| | | | +- AIPlayerOutsmart class
| | | | +- AIPlayerUnflippable class
| | | | +- BoardCardCellsDimensionsInconvertibleToInt.txt
| | | | +- BoardCardCellsInvalidLineLength.txt
| | | | +- BoardCardCellsDimensionsNoReach.txt
| | | | +- BoardCardCellsDimensionsReach.txt
| | | | +- BoardCardCellsDimensionsInvalidDims.txt
| | | | +- BoardCardCellsDimensionsInconvertibleToChar.txt
| | | | +- boardNoHoles.txt
| | | | +- BoardSmallCardCells.txt
| | | | +- cards1.txt
| | | | +- cards2.txt
| | | | +- cards3.txt
| | | | +- cardsIncreasing.txt
| | | | +- cardsIncreasing.txt
| | | | +- GPlayer interface
| | | | +- HumanPlayer class
| | | | +- inconvertible_cards.txt
| | | | +- invalid_cards.txt
| | | | +- Move class
| | | | +- ReadMePlayerInterface.txt
| | | | +- ThreeTriosController class
| +- docs/
| +- +- hw5/
| | | | +- README.txt
| +- META-INF/
| | | | +- MANIFEST.MF
| +- model/
| +- +- hw5/
| | | | +- Card interface
| | | | +- CardValues enum
| | | | +- Coordinate class
| | | | +- EmptyCard class
| | | | +- GameState enum
| | | | +- HoleCard class
| | | | +- PlayableCard interface
| | | | +- PlayCard class
| | | | +- Player enum
| | | | +- ReadCardConfigFile
| | | | +- ReadGridConfigFile
| | | | +- ReadOnlyThreeTriosModel interface
| | | | +- ThreeTriosModelGame class
| | | | +- ThreeTriosModel interface
| +- screenshots/
| | | | | | | +- CS3500HW6-BlueSelectedScreenshot.png
| | | | | | | +- CS3500HW6-MidGameScreenshot.png
| | | | | | | +- CS3500HW6-RedSelectedScreenshot.png
| | | | | | | +- CS3500HW6-StartGameScreenshot.png
| +- view/
| +- +- hw5/
| | | | +- CardPanel class
| | | | +- GameFrame class
| | | | +- GridPanel class
| | | | +- GridPanelInterface
| | | | +- IPanel interface
| | | | +- PlayerHandPanel class
| | | | +- PlayerHandPanelInterface
| | | | +- ThreeTriosGUIView class
| | | | +- ThreeTriosGUI interface
| | | | +- ThreeTriosView interface
| | | | +- ThreeTriosViewGame class
| | | | +- ViewFeatures interface
+- test/
| | | +- TestGameModel
| | | +- TestGameView
| | | +- TestReadCardFile
| | | +- TestReadGridFile




+- test/
| +- ... (test classes)

After configuring a card file and a grid file, we can start using the model.  With a valid grid and
a valid deck, a model can be created and started.  (N+1)/2 cards (with N being the number of
playable spaces on the grid, which must be odd to be valid) are given to each player and the Red
player makes their first move, assuming it's valid, then their card is removed from their hand and
placed on the requested spot on the grid, and then it is the Blue player's turn. If a card is played
next to another card, it will attempt to attack and flip the neighboring card and if successful, the
process continues until no more cards are flipped.  The game keeps going until every playable space
has a card played in it.  The winner is determined by the number of cards a player has on the board
and in their hand by the end of the game.  If they have the same number of cards by the end, they
end in a tie.

The model uses many classes, enums and interfaces to function efficiently, here's what they do:
- Card - Represents a card on the grid, can be a played card, empty space or a hole
- - PlayableCard - an implementing class of Card that only has playable cards with names and a
player
- GameState enum - determine current state of game for play and handle state exceptions
- Player enum - stores the possible players to determine card ownership and current player
- Coordinates - stores positions on the grid, used solely for attacks internally, at the moment

Class invariant: Once start game has been run, the total number of playable cards in the game is
always even. This is enforced by monitoring the two ways card can be added to the game: grid setup
and filling hands  When the grid is set up, no spaces are allowed to be initialized as a Playable
card and then when the deck is set up, each played is given (N+1)/2 cards, meaning that there are
(N+1) cards in the game after setup (N is restricted to being odd or an error is thrown, therefore
N+1 must be even).  When a card is added to the grid, it is removed from the player's hand, keeping
the number of cards in the game the same.  So with no other ways to add or remove cards, the
invariant must stay true.



CHANGES FOR PART 2:

- To match the preparation requirements for Part 2, we created a ReadOnlyThreeTrios Interface with the
ability to make a copy of the grid, get the size of the grid, the contents of a cell at a particular
coordinate, get a player's hand, get the owner of a particular coordinate, whether a card can
be played at a coordinate, the possible flips if a card is played at a given coordinate, get a
player's score and whether the game is over.

- Our read-only model contains only observation methods such as returning a copy of the playing
grid, returning a copy of a player's hand, getting the current player, getting the number of
possible flips, and getting the score of a given player. Our non-read-only model, ThreeTriosModel,
contains the main operation methods such as starting the game and playing a card from a hand to the
grid. The expected functionality specified in the assignment was missing earlier because we did not
fully consider what future assignments might entail for ThreeTrios. However, we understand the
importance of each of these observation methods in making our models and the overall program more
robust.

Part 2 Additions:

Within Controller Package:

- We created six different classes for each AI strategy that implement an interface
named GPlayer. The GPlayer interface has methods that return the best move based on the strategy
of the AI, methods to set the row and column if the player is human, and to reset a move.
The first three AI classes are listed below with a brief description of their purpose.
(The last three will be explained in the extra credit section at the bottom of this file)

1. AIPlayerBase - Implements a strategy where the AI plays a card to the first available move it
finds on the playing grid, starting from the top left of the grid to the bottom right.
2. AIPlayerFlipper - Implements a strategy where the AI aims to maximize the number of cards they
flip on a given turn. Involves selecting a card and position together.
3. AIPlayerCorner - Implements a strategy where the AI goes for the corners and plays cards whose
two exposed attack values are high enough to make the card difficult to flip.

+- src/
| +- controller/
| +- +- hw5/
| | | | +- AIPlayerBase
| | | | +- AIPlayerFlipper
| | | | +- AIPlayerCorner

+- test/
| | | +- TestAIBase
| | | +- TestAIFlipper
| | | +- TestAICorner


Note: AIPlayerCorner and AIPlayerFlipper all extend
AIPlayerBase because if these strategies don't work, they fall back to the first strategy of finding
the first available move.

- We created a HumanPlayer class that also implements the GPlayer interface. It is responsible for
representing a human player in the ThreeTrios Game. As instructed by the assignment, human player
does not have in-built strategies like the AI player does.

- We also created a new class named Move, an object representation of a move that includes a hand
 index, row index, and column index. This class is used to return the move selected by different
 AI strategies and the human player.

- Lastly, we created a ThreeTriosController class, mainly designed to handle interactions
between the model and the view. Currently, the controller starts game play, manages moves,
identifies the current player, checks the game's status, and handles and returns
coordinates of a user's click on the grid and/or in-hand cards. We will build upon and refine the
controller based on additional requirements in the next assignment.

Within Model Package:

- We refactored our original model interface, ThreeTriosModel, to create a read-only version named
ReadOnlyThreeTriosModel. This design makes sure our view doesn't modify the model(s) it receives.
We implemented the required functionality in the read-only model interface as specified in the
assignment. The startGame method, defined in the ThreeTriosModel interface and implemented in
ThreeTriosModelGame, allows us to initialize a game using configuration files for both the grid
and cards. Specifically, startGame accepts a list of PlayableCards, which we generate with the
readCardFile function from ReadCardConfigFile. Additionally, our model’s constructor accepts a 2D
array to represent the grid, which we populate using readBoardFile from ReadBoardConfigFile.

- Our read-only model contains only observation methods such as returning a copy of the playing
grid, returning a copy of a player's hand, getting the current player, getting the number of
possible flips, and getting the score of a given player. Our non-read-only model, ThreeTriosModel,
contains the main operation methods such as starting the game and playing a card from a hand to the
grid. The expected functionality specified in the assignment was missing earlier because we did not
fully consider what future assignments might entail for ThreeTrios. However, we understand the
importance of each of these observation methods in making our models and the overall program more
robust.

Within View Package:

- We created an IPanel interface with a single method for rendering the current game state in each
panel. This interface makes it easy for GUI panels like PlayerHandPanel and GridPanel to display
game elements consistently. Each panel shows its part of the game, like player hands or the
grid, while the GameFrame class arranges all panels in the layout. PlayerHandPanel is composed of
CardPanels, which represent an individual card in a player's hand.
Below is a brief description of each interface and its implementing class(es)

GridPanelInterface:
- Defines behaviors for rendering the grid panel such as initializing the grid, returning the
coordinates of a spot on the grid a player clicked on, and refreshing the grid state after
significant updates to the grid such as flipped cards.
- Implementing class: GridPanel

PlayerHandPanelInterface:
- Defines behaviors for rendering the red and blue player hand panels such as highlighting a
certain panel and deselecting a card.
- Implementing class: PlayerHandPanel, which uses a separate class CardPanel to build a
complete hand panel.

ViewFeatures:
- Defines behaviors for handling user inputs and passing them into the controller.  This includes
selecting a grid spot or card in the hand, as well as an option to quit the game.
- Behaviors include outputting the 0-based row and column index of a grid spot a user clicks,
and outputting the 0-based row and column index of a card a user clicks on their hand, only if it
is the player's turn, and quitting from the game (not implemented yet), if this is an expected
functionality in the next assignment.
- Implementing class: ThreeTriosController - Described under the controller package section, but it
uses methods from view features to identify the player's chosen move and trigger actions based on
current gameplay rules.

GameFrame:
- Creates the GridPanel and the two PlayerHandPanels from a passed ReadOnlyModel and then handles
click functionality
- Sets the layout and the scaled size of the game based on the current window size
- If the user clicks on the game, it determines which part of the game was clicked and then sends
that information to each one of the view features, ex. the controller

ThreeTriosGUIView:
- Creates a GameFrame and passes controller commands to the gameFrame
- Implements the ThreeTriosGUI so the player can pass this into a controller

Main:
- We added a main class to test if our GUI is rendered correctly. In main, we set up the model and
view and delegate to the controller to start the game. We use the model to play multiple cards
and verify that the game state is rendered correctly to the players in different scenarios.


Test
- We tested the requested game states in the descriptions and placed screenshots of those in the
screenshots folder
- We added tests for verifying the behavior of our different strategies by using mocked
versions our model.

Extra credit strategies:

For the extra credit, we created two AI strategies and the combiner:
1. AIPlayerUnflippable - Implements a more advanced approach to leave the opponent with no good moves
by minimizing their best possible move (a minimax strategy). To pick the best move, this strategy
anticipates the opponent’s likely approach, which could be any of the strategies described so far.
2. AIPlayerOutsmart - Implements a strategy where the AI selects the card-position combination with
the lowest chance of being flipped. It does so by evaluating each possible move and choosing the one
least vulnerable to opponent flips.
3. AIPlayerCombiner - The user can combine whichever strategies they want, and they will be used in
the order that they were inputted.  If the first strategy has no valid moves (apart from the Base
strategy), then the next strategy will be used.  This configuration works best starting with
strategies with few moves that are good for the early game then switching to strategies with more
possible moves for the late game

The Extra Credit Strategies and their tests can be found in the following files

+- src/
| +- controller/
| +- +- hw5/
| | | | +- AIPlayerOutsmart
| | | | +- AIPlayerUnflippable
| | | | +- AIPlayerCombiner

+- test/
| | | +- TestAIOutsmart
| | | +- TestAIUnflippable
| | | +- TestAICombiner

Note: AIPlayerOutsmart, AIPlayerUnflippable and AIPlayerCombiner all extend
AIPlayerBase because if these strategies don't work, they fall back to the first strategy of finding
the first available move.


CHANGES FOR PART 3:

To meet the requirements for Part 3, the primary changes we made were adding functionality to our
incomplete controller from Part 2. We created a new interface called ModelListener, which contains
behaviors such as notifying the current player that it's their turn,
notifying the player if they have made an invalid action, (when the game is over),
notifying which player has won and what their score was, and updating the grid and hand states
in the GUI and setting the current player as a JLabel above the playing grid on the GUI.

Our controller ThreeTriosController implemented all this new functionality because once an
action event has been triggered by the view such as a card being played from the current player
to the playing grid, it is the controller's job to notify the players and update the model about the
player's input and then update the view state. In our ThreeTriosController class, we improved some
functionality in existing methods from part 2
such as a helper move function that gets the player's move, outputs it, delegates to the
model to play the card with that move, resets the move to prepare it for the next time the move()
function is called, and refreshes the view game state. We added functionality to our selectedGrid()
function, which takes a row and column index that a player clicked on and proceeds to make a move
if the current player is the one who performed the grid click. It checks if the player selected a
card first and delegates to the view to output relevant messages along the way if any of the
following things, which are not allowed, occur:
1. The player clicks on a spot on the grid before choosing a card from their hand.
2. The player tries to play an invalid move, such as to a hole card on the playing grid.
3. The player tries interacting with their view or the other player's view when it's not their turn.

We made similar changes to our selectedHand() function, which takes the index of the card clicked by
the player in their hand, by verifying if the current player in the model matches the player
associated with the controller and then outputting and setting the selected card's index accordingly.

To display these different messages appropriately on the GUI, we used a JOptionPane object in the
view class ThreeTriosView and its showMessageDialog method. In our GPlayer interface, which defines
the behaviors for an AI strategy, we added a getPlayer method in GPlayer interface to keep track
of the current player, whether it was a human or an AI. We added this method in our controller
interfaces as well (ViewFeatures and ModelListener) so that our controller could delegate to the
model to view who the current player is. Knowing who the current player is important to make sure a
player can interact with their hand and general playing grid only when it is their turn.

In addition, we updated the isLegal() function in our model class ThreeTrioModelGame to return
false when the row and column indices are invalid, instead of throwing an IllegalArgumentException.
This change resolved issues with Strategy 3 (AIPlayerUnflippable) not making a move on the GUI.
By avoiding the exception, we were able to perform additional checks in the AIPlayerUnflippable
class, such as verifying each position on the grid for legality in all four directions
(top, bottom, left, and right) without interruption. This allowed the AI to find a valid move
and correctly update the two GUIs as expected.

Lastly, we updated the model constructor to include a new parameter: a list of cards used to set up
each player's hand. Previously, the constructor only took a 2D array for the playing grid and a
random object. We moved hand setup to the constructor rather than the startGame method to better
separate responsibilities. The constructor handles all setup tasks, such as initializing the
player's hands and setting up listeners, while the startGame method focuses on starting
gameplay like notifying the current player, updating the GUI's current player status label
using a JLabel, and refreshing the hands and playing grid. To reiterate, the reason for this design
decision was to clearly separate initialization and gameplay logic.

Part 3 Additions:

In our main class ThreeTrios, we configured the command-line arguments such as using "human" to
indicate a human being one of the players or "strategy3" to indicate the AI is one of the players
and will use strategy 3 (corresponds to AIPlayerUnflippable) to make moves and play the game.
A configuration follows the template "player1 player2" and in this example, it would be
"human strategy3". This configuration helped us test out different variation of players of
our ThreeTriosGame such as human vs human, human vs AI, and AI vs AI.

Lastly, we added a boardUpdate method in our controller that executes a move when the player is an
AI, and it is their turn. This ensured that AI actions aligned with the game flow and that their
moves were automatically performed when appropriate.

CHANGES FOR PART 4:
For Part 4, we created adapters so that our code could work with the provider's implementation and
create GUI views using different implementation.  None of our (held in originalcode package) or the
provider's (held in providercode package) original code was modified except in the main class. The
adapters were placed in the newcode package.

CardAdapter: Converts an originalcode Card into a providercode Card (only words on PlayCards)
GamePlayer: Imitates the provider's IPlayer for the provider's View implementation
GridCellAdapter: Converts an originalcode Card into a providercode Cell (works on all Card Types)
ModelAdapter: Converts an originalcode ThreeTriosModel into a providercode ThreeTriosModel to pass
the grid info into the provider's view
ViewAdapter: Converts a providercode VisualThreeTriosGUI into a originalcode ThreeTriosGUI to be
used by the originalcode controller
ViewListenerAdapter: Converts a originalcode ThreeTriosController into a providercode ViewListener
so that the provider's view can return input to the controller

Unadaptable classes/functions
I was able to adapt all classes that I needed to implement the provider's view implementation, but
there were some functions I was unable to adapt because of their reliance on the provider's model
and controller which use variables that my interfaces do not give access to.

GamePlayer: Our ReadOnly Model keeps the original player hands private and does not allow moves, so
the provider's functions that play cards which adds/removes cards from the hand can not be used
since they require modifying variables it does not have access to.  Since these function only affect
the controller and model, the view implementation is still able to work normally.