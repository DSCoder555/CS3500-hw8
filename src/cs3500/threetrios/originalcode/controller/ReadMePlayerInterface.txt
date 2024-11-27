To make the game more robust and adaptable to both human and AI players, a Player interface has
been defined. The following methods, their purpose statement, and rationale for including
in the design are provided below.

1. getMove()
- Retrieves the coordinate that a human or AI player played their most recent card to on the grid
- Useful for tracking the player’s last action, helping them strategize their next move

2. undoMove()
- Allows the user or AI to undo their most recent move, the user or AI can only do this a set
number of times to allow for game fairness

3. presentAllMoveOptions()
- Lists all the possible coordinates a player (human or AI) can play their card to on the grid.
- Informs players of available options, helping them make a well-considered choice.

4. selectMove()
- Allows the player to choose a move by passing an integer representing their choice from a list
provided by presentAllMoveOptions.
- For a human player, this method directly processes their selection.
- For the AI, it utilizes a helper method, evaluateMove(), which evaluates the effectiveness of
each coordinate option to inform the selection.
- We plan this method to adapt to different difficulty levels. In a future design, the user may
choose between:
    - Easy mode: The AI selects a random move from available options.
    - Hard mode: The AI uses evaluateMove() to select the best possible move based on
    move effectiveness.

5. evaluateMove()
- Exclusive for AI players in hard mode to assess the effectiveness of each possible move.
- This method is essential for hard mode, where the AI aims to make the most strategic move.
- For easy mode, evaluateMove() may not be utilized, as the AI would select moves randomly instead.

Note: selectMove() is the main method for choosing a move, while evaluateMove() is a support method
for AI in hard mode, helping it make the optimal decision.

6. displayStatus()
- Shows the current player’s (human or AI) hand, the count of their color cards versus the
opponent’s on the grid, and statistics on previous moves that flipped the most cards.
- Provides insights to help the player assess their position in the game and make informed
strategic choices

7. clearMoves()
- Resets all actions for the human player, allowing them to restart without resetting the entire
 game.
- Useful for testing different strategies or replaying without losing overall game progress

