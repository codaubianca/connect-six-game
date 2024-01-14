package game;

import board.Board;
import exceptions.InputException;

public class ConnectSixGame {
  private static boolean isOver = false;
  private Board board;
  private int numberOfPlayers;
  private int currentTurn;


  /**
   * Constructs a new ConnectSixGame object by initializing its attributes.
   * 
   * @param numberOfPlayers
   *          How many players will take part in the game.
   * @param board
   *          The current game board.
   */
  public ConnectSixGame(int numberOfPlayers, Board board) {
    this.numberOfPlayers = numberOfPlayers;
    this.board = board;
    currentTurn = 0;
  }

  /**
   * Add the two new tokens of a player on the board and checks if game is over.
   * 
   * @param firstRow
   *          The index of the row on which the first cell is situated.
   * @param firstColumn
   *          The index of the column on which the first cell is situated.
   * @param secondRow
   *          The index of the row on which the second cell is situated.
   * @param secondColumn
   *          The index of the column on which the second cell is situated.
   * @return Px wins if one of the players has won, draw if nobody won but there
   *         no more possible moves and OK if the game can continue.
   * @throws InputException
   *           There are indices which do not respect the rules of the game mode.
   */
  public String addMove(int firstRow, int firstColumn, int secondRow, int secondColumn)
      throws InputException {

    int n = board.getContent().length;

    int row1 = board.getIndexValue(firstRow);
    int col1 = board.getIndexValue(firstColumn);
    int row2 = board.getIndexValue(secondRow);
    int col2 = board.getIndexValue(secondColumn);

    if (board.getContent()[row1][col1] == null && board.getContent()[row2][col2] == null
        && currentTurn < (n * n) / 2 && (row1 != row2 || col1 != col2)
        && isOver == false) {
      board.addToken(row1, col1, "P" + ((currentTurn % numberOfPlayers) + 1));
      board.addToken(row2, col2, "P" + ((currentTurn % numberOfPlayers) + 1));
      currentTurn++;
    } else {
      throw new InputException(
          " board cell is already occupied or the given rows and column are incorrect.");
    }

    // looks for a winner horizontally, vertically, on the first diagonal and on the
    // second diagonal
    if (board.isGameOver(board.getContent()[row1][col1], 0, 1)
        | board.isGameOver(board.getContent()[row1][col1], 1, 0)
        | board.isGameOver(board.getContent()[row1][col1], 1, 1)
        | board.isGameOver(board.getContent()[row1][col1], -1, 1)) {
      isOver = true;
      return board.getContent()[row1][col1] + " wins";
    }

    if (currentTurn >= (n * n) / 2) {
      return "draw";
    }

    return "OK";
  }

  /**
   * Puts all the values in one row in a singular string.
   * 
   * @param row
   *          The row index.
   * @return The row values in one string by spaces.
   * @throws InputException
   *           if the index is not a positive number, smaller than the height of
   *           the board.
   */
  public String displayRow(int row) throws InputException {
    return board.toStringLine(row, "row");
  }

  /**
   * Puts all the values in one column in a singular string.
   * 
   * @param column
   *          The column index.
   * @return The column values in one string separated by spaces.
   * @throws InputException
   *           if the index is not a positive number, smaller than the width of
   *           the board.
   */
  public String displayColumn(int column) throws InputException {
    return board.toStringLine(column, "column");
  }

  /**
   * Gets the value of the cell with the given indexes.
   * 
   * @param row
   *          The row index.
   * @param column
   *          The column index.
   * @return The value of the cell found on the given row and column.
   * @throws InputException
   *           If the given indexes are not on the board.
   */
  public String getCellValue(int row, int column) throws InputException {
    int rowIndex = board.getIndexValue(row);
    int colIndex = board.getIndexValue(column);

    if (board.getContent()[rowIndex][colIndex] != null) {
      return board.getContent()[rowIndex][colIndex];
    }
    return "**";
  }

  /**
   * Starts a new game in the same game mode.
   */
  public void reset() {
    board.clear();
    currentTurn = 0;
    isOver = false;
  }

  /**
   * Getter for the board attribute used to access its content.
   * 
   * @return the game board.
   */
  public Board getBoard() {
    return board;
  }
}