package app;

import exceptions.InputException;
import game.Command;
import game.ConnectSixGame;
import io.Terminal;

import java.io.IOException;

import board.Board;
import board.StandardBoard;
import board.TorusBoard;

public class Main {
  /**
   * Main method which starts the program.
   * 
   * @param args
   *          Array of command line arguments.
   */
  public static void main(String[] args) {
    if (args.length == 3 && args[1].matches("(18)|(20)") && args[2].matches("[234]")) {
      // Board gameBoard = null;
      Board board = null;
      switch (args[0]) {

        case "torus": {
          board = new TorusBoard(Integer.parseInt(args[1]));
          break;
        }

        case "standard": {
          board = new StandardBoard(Integer.parseInt(args[1]));
          break;
        }

        default: {
          Terminal.printError(" unknown game mode. Please, choose between 'standard' and 'torus'.");
          break;
        }
      }

      ConnectSixGame game = new ConnectSixGame(Integer.parseInt(args[2]), board);
      Command command = null;

      do {
        try {
          command = Command.executeMatching(Terminal.readLine(), game);
        } catch (IOException | InputException e) {
          Terminal.printError(e.getMessage());
        }
      } while (command == null || command.isRunning());

    } else {
      Terminal.printError(" game mode, board size and/or number of players are not valid.");
    }
  }
}
