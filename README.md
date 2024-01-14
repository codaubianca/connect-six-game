# Connect6 Game
How the game works:
- Players take turn to place tokens on the booard. Each player must place 2 tokens during his turn.
- First player to connect 6 of his tokens wins the game.
- Warning! The margins of the board are connected. This means that if player A places his tokens on the first three positions and the last three positions of the same row they have a connection of 6 tokens.

Start the game by runnning the Main file with the following three arguments:
1. Game mode: standard or torus
2. Board size: 18 or 20
3. Number of Players

Then you can play the game in your IDE console using the follwoing commands (case-sensitive!):
1. <code>place row1;col1;row2;col2</code> // places two tokens on the board for the current player 
2. <code>print</code> // shows the board
3. <code>rowprint row_nr</code> // prints row
4. <code>colprint col_nr</code> // prints column
5. <code>state row;col</code> // prints state of the cell [row, column]
6. <code>reset</code> // restats game
7. <code>quit</code> // exits game
