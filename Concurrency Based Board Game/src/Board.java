/**
 * @author Max
 * 
 * This class holds creates and holds data about the game board as well as providing the ability to move pieces on the board.
 *
 */
public class Board {
  private GameType game;
  private Piece[][] tiles;
  private int size;
  private int whiteCount;
  private int blackCount;
  
  /**
   * This is the constructor for the board class, it checks which game the user wants to play and sets up a data structure to store pieces accordingly.
   * 
   * @param game The enum GameType representing the game which the user wants to play
   */
  public Board (GameType game) {
    this.game = game;
    this.whiteCount = 0;
    this.blackCount = 0;
    switch(game) {
    case CHECKERS:
      this.size = 8;
      break;
      
    case CHESS:
      this.size = 8;
      break;
      
    case DRAUGHTS:
      this.size = 10;
      break;
      
    case GOMOKU:
      this.size = 15;
      break;
      
    case DAMA:
      this.size = 8;
      break;
    }
    this.tiles = new Piece[this.size][this.size];
  }
  
  /**
   * This method checks if a given position is diagonal to the given piece.
   * 
   * @param newX The integer of the given position along the x axis.
   * @param newY The integer of the given position along the y axis.
   * @param currentPiece The object Piece of the given piece whose position is being checked.
   * @return true if the given position is diagonal to the position of the current piece, false if it is not.
   */
  public boolean isMoveDiagonal(int newX, int newY, Piece currentPiece) {
    return Math.abs(newY - currentPiece.getyPos()) == Math.abs(newX - currentPiece.getxPos());
  }
  
  /**
   * This method finds the distance from a given position on the board, to a given piece.
   * 
   * @param newX The integer of the given position along the x axis.
   * @param newY The integer of the given position along the y axis.
   * @param currentPiece The object Piece of the given piece whose distance from the position we want to find.
   * @return The integer of the distance from the position of the piece to the given position.
   */
  public int moveDistance(int newX, int newY, Piece currentPiece) {
    return Math.max(Math.abs(newY - currentPiece.getyPos()), Math.abs(newX - currentPiece.getxPos()));
  }
  
  /**
   * This is a simple getter method that is used for testing purposes.
   * 
   * @return the Piece[][] tiles which represents the board.
   */
  public Piece[][] getBoard() {
    return this.tiles;
  }
  
  /**
   * This is a simple getter method that returns the size of the board, this size-1 is the maximum index of the Piece array.
   * 
   * @return the integer size, which represents the size of the board.
   */
  public int getSize() {
    return this.size;
  }
}
