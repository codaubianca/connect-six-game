package exceptions;

public class InputException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new InputException object that displays a message.
   * @param message The message to be displayed in case of an error.
   */
  public InputException(String message) {
    super(message);
  }
}
