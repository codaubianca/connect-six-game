package board;

import exceptions.InputException;

public abstract class Board {
  private String[][] content;

  /**
   * Constructs a new Board object.
   * 
   * @param boardSize
   *          The size of the array content, given by the user.
   */
  public Board(int boardSize) {
    content = new String[boardSize][boardSize];
  }

  /**
   * Verifies if the given index respects the rules in each game mode.
   * 
   * @param index
   *          The row or column index to be checked.
   * @return The right form of the index depending on the board type.
   * @throws InputException
   *           If the input is not of valid form in standard mode.
   */
  public abstract int getIndexValue(int index) throws InputException;

  /**
   * Calculates the new index used in the search for a winner.
   * 
   * @param index
   *          A given board column or row index.
   * @param coord
   *          The direction in which the index moves.
   * @param c
   *          A number indicating how many (up to 6) cells have been viewed.
   * @return The new index of a cell which is to be verified.
   */
  public abstract int getIndexValue(int index, int coord, int c);

  /**
   * Checks if there are six tokens from the same player in a row on a line,
   * column or diagonal of the board by counting how many of connected cells with
   * the same token can be found.
   * 
   * @param token
   *          The token of the player.
   * @param x
   *          The direction in which the row index will move.
   * @param y
   *          The direction in which the column index will move.
   * @return True if there is a winner, false otherwise.
   */
  public boolean isGameOver(String token, int x, int y) {

    int counter;

    for (int i = 0; i < getContent().length; i++) {
      for (int j = 0; j < getContent()[i].length; j++) {
        counter = 0;
        for (int c = 0; c < 6; c++) {

          int newI = getIndexValue(i, x, c);
          int newJ = getIndexValue(j, y, c);

          if (newI >= 0 && newJ >= 0
              && content[newI][newJ] != null
              && content[newI][newJ].equals(token)) {

            counter++;

          } else {
            counter = 0;
          }

          if (counter == 6) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Populates a cell from the game board with a players token.
   * 
   * @param row
   *          The row on which the cell is found.
   * @param column
   *          The column on which the cell is found.
   * @param token
   *          The players token which is to be placed on the cell.
   */
  public void addToken(int row, int column, String token) {
    content[row][column] = token;
  }

  /**
   * Concatenates all values in a row or column separated with a space in a
   * singular string.
   * 
   * @param index
   *          The index of the row or column to be transformed in one string.
   * @param type
   *          "row" if the index is of a row and "column" otherwise.
   * @return The new String with all the values from the requested row or column.
   * @throws InputException
   *           If the given index is not on the board.
   */
  public String toStringLine(int index, String type) throws InputException {
    int n = content.length - 1;
    // Following validation is not done via other methods as it is not dependent on
    // the board type.
    if (index < 0 || index >= content.length) {
      throw new InputException(" please choose numbers between 0 and " + n + ".");
    }

    String line = "";
    for (int j = 0; j <= n; j++) {

      String cell = null;
      if (type.equals("row")) {
        cell = content[index][j];
      }
      if (type.equals("column")) {
        cell = content[j][index];
      }

      if (cell == null) {
        line += "** ";
      } else if (cell.matches("P[1234]")) {
        line += (cell + " ");
      }
    }
    return line;
  }

  /**
   * Sets every cell to null, emptying the board without changing the game mode.
   */
  public void clear() {
    for (int i = 0; i < content.length; i++) {
      for (int j = 0; j < content[i].length; j++) {
        content[i][j] = null;
      }
    }
  }

  /**
   * Allows access to board content.
   * 
   * @return The content of the board.
   */
  public String[][] getContent() {
    return content;
  }

}
