package game;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.InputException;
import io.Terminal;

public enum Command {
  /**
   * The place command adds two tokens on the game board.
   */
  PLACE("place (-?\\d+);(-?\\d+);(-?\\d+);(-?\\d+)") {
    @Override
    public void execute(MatchResult matcher, ConnectSixGame game) throws InputException {
      // Exception for big numbers
      try {

        int row1 = Integer.parseInt(matcher.group(1));
        int col1 = Integer.parseInt(matcher.group(2));
        int row2 = Integer.parseInt(matcher.group(3));
        int col2 = Integer.parseInt(matcher.group(4));
        Terminal.printLine(game.addMove(row1, col1, row2, col2));

      } catch (NumberFormatException e) {
        Terminal.printError(" numbers are too big.");
      }

    }
  },
  /**
   * The rowprint command is used to display one particular row from the board.
   */
  ROWPRINT("rowprint (-?\\d+)") {
    @Override
    public void execute(MatchResult matcher, ConnectSixGame game) throws InputException {
      Terminal.printLine(game.displayRow(Integer.parseInt(matcher.group(1))));
    }
  },
  /**
   * The colprint command is used to display on particular column from the board.
   */
  COLPRINT("colprint (-?\\d+)") {
    @Override
    public void execute(MatchResult matcher, ConnectSixGame game) throws InputException {
      Terminal.printLine(game.displayColumn(Integer.parseInt(matcher.group(1))));
    }
  },
  /**
   * The print command is used to display the whole board.
   */
  PRINT("print") {
    // print board
    @Override
    public void execute(MatchResult matcher, ConnectSixGame game) throws InputException {
      for (int i = 0; i < game.getBoard().getContent().length; i++) {
        Terminal.printLine(game.displayRow(i));
      }
    }
  },
  /**
   * The state command is used to display the current state of a particular cell
   * on the board.
   */
  STATE("state (-?\\d+);(-?\\d+)") {
    @Override
    public void execute(MatchResult matcher, ConnectSixGame game) throws InputException {
      int row = Integer.parseInt(matcher.group(1));
      int column = Integer.parseInt(matcher.group(2));

      Terminal.printLine(game.getCellValue(row, column));
    }
  },
  /**
   * The command reset is used to start a new game without changing the game mode
   * and preferences.
   */
  RESET("reset") {
    @Override
    public void execute(MatchResult matcher, ConnectSixGame game) throws InputException {
      game.reset();
      Terminal.printLine("OK");

    }
  },
  /**
   * The quit command is used to end the program.
   */
  QUIT("quit") {
    @Override
    public void execute(MatchResult matcher, ConnectSixGame game) throws InputException {
      isRunning = false;
    }
  };

  private static boolean isRunning = true;
  private Pattern pattern;

  /**
   * Constructor of a new command.
   * 
   * @param pattern
   *          is the regex pattern used to verify the validity of the written
   *          command.
   */
  Command(String pattern) {
    this.pattern = Pattern.compile(pattern);
  }

  /**
   * Executes a command.
   * 
   * @param matcher
   *          The regex pattern representing the new command.
   * @param game
   *          The instance of the Connect-Six-Game.
   * @throws InputException
   *           If there are errors in the new command.
   */
  public abstract void execute(MatchResult matcher, ConnectSixGame game) throws InputException;

  /**
   * Compares the newly written command with the valid commands and calls the
   * matching command.
   * 
   * @param newCommand
   *          The command written by the user.
   * @param game
   *          The instance of the Connect-Six-Game on which the command will be
   *          executed.
   * @return The executed command.
   * @throws InputException
   *           if command does not match any of the established patterns.
   */
  public static Command executeMatching(String newCommand, ConnectSixGame game)
      throws InputException {
    for (Command command : Command.values()) {
      Matcher matcher = command.pattern.matcher(newCommand);
      if (matcher.matches()) {
        command.execute(matcher, game);
        return command;
      }
    }

    throw new InputException("command is unkown.");
  }

  /**
   * Verifies if the program should still be running.
   * 
   * @return false if the program was exited and true otherwise.
   */
  public boolean isRunning() {
    return isRunning;
  }
}
