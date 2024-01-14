package board;

public class TorusBoard extends Board {
  /**
   * Constructs a new TorusBoard object using the parent constructor.
   * 
   * @param boardSize
   *          The width and length of the new board.
   */
  public TorusBoard(int boardSize) {
    super(boardSize);
  }

  @Override
  public int getIndexValue(int index) {
    if (index < 0) {
      return (-1) * (index % getContent().length);
    }
    return index % getContent().length;
  }

  @Override
  public int getIndexValue(int index, int coord, int c) {
    int newIndex = index + coord * c;
    newIndex = getIndexValue(newIndex);
    return newIndex;
  }
}
