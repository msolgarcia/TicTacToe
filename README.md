### _Github repo:_
[https://github.com/msolgarcia/TicTacToe](https://github.com/msolgarcia/TicTacToe)

### Instructions for Execution:

1- Download the sources from github:

[https://github.com/msolgarcia/TicTacToe](https://github.com/msolgarcia/TicTacToe)

2- Build the app

```bash
cd <TicTacToe dir>
mvn package
```
3- The generated app (jar + external config file) will be located at:

```bash
cd <TicTacToe dir>/target/TicTacToe-0.0.1-SNAPSHOT-bin/TicTacToe-0.0.1-SNAPSHOT/TicTacToe
```
4- Modify config file as needed (1)

```bash
cd resources
vim application.properties
```
5- Run app

```bash
java -jar TicTacToe-0.0.1-SNAPSHOT.jar
```


(1) - _The properties you could modify are:_
- Playground size, valid values are [3 - 10]
- Player one character: one single character to identify the human player one



#### Assumptions:
- For the boards bigger than 3x3, the winning condition is the same, have a complete row or column with the same character, or have one of central diagonals completed with the same character

#### Design decisions:
 
- Player character identifier could be any character (only one character) including digits and special characters
- Player characters can't be duplicated between players
- Player character and playground size must be configured, there is no default values for them

- Player I used template method pattern, where the abstract method is getPosToPlay, it's a hook to be implemented by the concrete players, each implementation will implement the way to pick the position in the board to be played. and the method Player.play() is the template method, that use the position obtained with getPosToPlay() to play it in the board.

- At the moment there are 2 implementations:
  - HumanPlayer, that uses the interface to get the selected value from the use
  - BegginerAIPlayer, that is the implementation for the computer.

- Computer AI is implemented in a very simply way (BegginerAIPlayer), it gets the lists of empty cells and choose one randomly. It could be implemented with more complex logic just extending the abstract class AIPlayer