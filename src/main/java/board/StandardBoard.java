package board;

import exceptions.InputException;

public class StandardBoard extends Board {
  /**
   * Constructs a new StandardBoard object using the parent constructor.
   * 
   * @param boardSize
   *          The width and length of the board.
   */
  public StandardBoard(int boardSize) {
    super(boardSize);
  }

  @Override
  public int getIndexValue(int index) throws InputException {
    int n = getContent().length - 1;
    if (index >= 0 && index <= n) {
      return index;
    }
    throw new InputException("please choose numbers between 0 and " + n + ".");
  }

  @Override
  public int getIndexValue(int index, int coord, int c) {
    int newIndex = index + coord * c;
    if (newIndex >= 0 && newIndex <= getContent().length - 1) {
      return newIndex;
    }
    return -1;
  }

}
