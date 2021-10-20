
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
  
  /**
   * This is the constructor for the board class, it checks which game the user wants to play and sets up a data structure to store pieces accordingly.
   * 
   * @param game The enum GameType representing the game which the user wants to play
   */
  public Board(GameType game) {
    this.game = game;
    switch(game) {
    case GAME_CHECKERS:
      this.size = 8;
      break;
      
    case GAME_CHESS:
      this.size = 8;
      break;
      
    case GAME_DRAUGHTS:
      this.size = 10;
      break;
      
    case GAME_GOMOKU:
      this.size = 15;
      break;
      
    case GAME_DAMA:
      this.size = 8;
      break;
    }
    this.tiles = new Piece[this.size][this.size];
  }
  
  /**
   * This method sets up checkers pieces in the correct positions on the board for the user to play.
   * It does this by setting creating Pieces in the correct x y positions on the tiles array.
   * 
   */
  public void setupCheckers() {
    for(int x = 0; x < 8; x++) {
      for(int y = 0; y < 8; y++) {
        // Setting up positions of white pieces
        if(y < 3 && ((x%2==0) && (y!=1)) || ((x%2!=0) && (y==1))) {
          this.tiles[y][x] = new Piece(Type.CHECKERS_MAN, x, y, "white");
        }
        // Setting up positions of black pieces
        if(y > 4 && ((x%2!=0) && (y!=6) || ((x%2==0) && (y==6)))) {
          this.tiles[y][x] = new Piece(Type.CHECKERS_MAN, x, y, "black");
        }
      }
    }
  }
  
  /**
   * This method checks if a move is legal under the rules of each game, checking what type of game is currently being played.
   * If the move is legal, it returns true, if the move is illegal, it returns false.
   * 
   * @param newX The integer of the new x position on the board of the selected piece.
   * @param newY The integer of the new y position on the board of the selected piece.
   * @param currentPiece The object Piece of the selected game piece that is going to be moved.
   * @return true if the move is deemed to be legal within the rules of the game and false if it is deemed to be illega.
   */
  public boolean isMoveLegal(int newX, int newY, Piece currentPiece) {
    Type pieceType = currentPiece.getType();
    String pieceColour = currentPiece.getColour();
    int currentX = currentPiece.getxPos();
    int currentY = currentPiece.getyPos();
    switch(this.game) {
    case GAME_CHECKERS:
      if((currentPiece != null) && (newX < 8) && (newY < 8) && (this.tiles[newY][newX] == null)) {
        if((pieceType==Type.CHECKERS_MAN) && ((currentY+1 == newY && pieceColour == "white") || (currentY-1 == newY && pieceColour == "black")) && (currentX+1 == newX || currentX-1 == newX)) {
          return true;
        }
        else if((pieceType==Type.CHECKERS_KING) && (newX+2 >= currentX || newX-2 <= currentX)) {
          return true;
        }
        else {
          return false;
        }
      }
      
      else {
        return false;
      }
    
    case GAME_CHESS:
      return false;
      
    case GAME_DRAUGHTS:
      return false;
      
    case GAME_GOMOKU:
      return false;
      
    case GAME_DAMA:
      return false;
      
    default:
      return false;
    }
  }
  
  /**
   * This method first checks if the move to be made is legal:
   * 
   * If it is legal, it sets the position in the tiles array where the selected piece resides to null and sets the new position on tiles to be the selected piece.
   * The current x and y of the Piece object is then updated to match its new position in the tiles array.
   * 
   * If it is not legal, it prints a message to the console stating that the move is not legal, and therefore the move has not been completed.
   * 
   * @param newX The integer of the new x position on the board of the selected piece.
   * @param newY The integer of the new y position on the board of the selected piece.
   * @param currentPiece The object Piece of the selected game piece that is going to be moved.
   */
  public void makeMove(int newX, int newY, Piece currentPiece) {
    if(isMoveLegal(newX, newY, currentPiece)) {
      int oldX = currentPiece.getxPos();
      int oldY = currentPiece.getyPos();
      this.tiles[oldY][oldX] = null;
      currentPiece.setXPos(newX);
      currentPiece.setYPos(newY);
      this.tiles[newY][newX] = currentPiece;
    }
    
    if((currentPiece.getColour()=="white" && newY == 7) || (currentPiece.getColour() == "black" && newY == 0)) {
      currentPiece.setType(Type.CHECKERS_KING);
    }
    
    else {
      System.out.println("That move is illegal.");
    }
  }
  
  /**
   * This is a simple getter method that is used for testing purposes.
   * 
   * @return the Piece[][] tiles which represents the board.
   */
  public Piece[][] getBoard() {
    return this.tiles;
  }
  
}
