# A Minesweeper Game

You are given a task of writing a simple console version of a minesweeper game.

At this time, the functionality of the program is quite limited but this might change in the future. In a nutshell, the program should work as follow:
1. Create a new game
2. Play the game by issuing various commands
3. Quit

| Command | Description |
|:--------|:------------|
| C w h n | Should create a new game of width `w`, height `h` and number of mines `n`.|
| D x y   | Should dig on the given coordinate `(x,y)`. If there was a bomb, the game automatically finishes. Otherwise uncover the field. If the field is not adjacent to any other bomb, then uncover all eight surrounding fields around it. Same logic applies for all newly uncovered fields. |
| F x y   | Should place a flag on the given coordinate `(x,y)`. One cannot dig on a field that is flagged. |
| Q       | Should quit the program. |