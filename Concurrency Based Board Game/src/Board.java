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
  private Colour turn;
  
  /**
   * This is the constructor for the board class, it checks which game the user wants to play and sets up a data structure to store pieces accordingly.
   * 
   * @param game The enum GameType representing the game which the user wants to play.
   */
  public Board(GameType game) {
    this.game = game;
    this.whiteCount = 0;
    this.blackCount = 0;
    switch(game) {
    case CHECKERS:
      this.size = 8;
      this.tiles = new Piece[this.size][this.size];
      this.turn = Colour.BLACK;
      for(int x = 0; x < 8; x++) {
        for(int y = 0; y < 8; y++) {
          // Setting up positions of white pieces
          if(y < 3 && ((x%2!=0) && (y!=1)) || ((x%2==0) && (y==1))) {
            this.tiles[x][y] = new Piece(Type.CHECKERS_MAN, x, y, Colour.WHITE);
            this.whiteCount++;
          }
          // Setting up positions of black pieces
          if(y > 4 && ((x%2==0) && (y!=6) || ((x%2!=0) && (y==6)))) {
            this.tiles[x][y] = new Piece(Type.CHECKERS_MAN, x, y, Colour.BLACK);
            this.blackCount++;
          }
        }
      }
      break;
      
    case CHESS:
      this.size = 8;
      this.tiles = new Piece[this.size][this.size];
      break;
      
    case DRAUGHTS:
      this.size = 10;
      this.tiles = new Piece[this.size][this.size];
      break;
      
    case GOMOKU:
      this.size = 15;
      this.tiles = new Piece[this.size][this.size];
      break;
      
    case DAMA:
      this.size = 8;
      this.tiles = new Piece[this.size][this.size];
      break;
    }
    //this.tiles = new Piece[this.size][this.size];
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
   * This method checks if a given position is in a forward direction for a given piece.
   * 
   * @param newX The integer of the given position along the x axis.
   * @param newY The integer of the given position along the y axis.
   * @param currentPiece The object Piece of the given piece whose distance from the position we want to find.
   * @return true if the piece is moving in a forwards direction relative to its colour, false if it is not.
   */
  public boolean isForward(int newX, int newY, Piece currentPiece) {
    Colour pieceColour = currentPiece.getColour();
    int currentY = currentPiece.getyPos();
    if ((currentY+1 == newY && pieceColour == Colour.WHITE) || (currentY-1 == newY && pieceColour == Colour.BLACK)) {
      return true;
    }
    else {
      return false;
    }    
  }
  
  /**
   * This method takes a newX and newY position and a piece and checks if the move from the current x and y position to the new positions would take a piece or not.
   * If the move does take a piece, then it returns true, if it does not take a piece, then it returns false.
   * 
   * @param newX The integer of the new position along the x axis that this move would take the piece.
   * @param newY The integer of the new position along the y axis that this move would take the piece.
   * @param currentPiece The object of the selected game piece who's move is being checked.
   * @return Boolean true if the move takes a piece, false if it does not.
   */
  public boolean takesPiece(int newX, int newY, Piece currentPiece) {
    int currentX = currentPiece.getxPos();
    int currentY = currentPiece.getyPos();
    int betweenX = currentX + (newX - currentX)/2;
    int betweenY = currentY + (newY - currentY)/2;
    Piece takenPiece = this.tiles[betweenX][betweenY];
    Colour pieceColour = currentPiece.getColour();
    if(takenPiece!=null && isMoveDiagonal(newX, newY, currentPiece) && ((currentY+2 == newY && pieceColour == Colour.WHITE) || (currentY-2 == newY && pieceColour == Colour.BLACK) || currentPiece.getType() == Type.CHECKERS_KING) && takenPiece.getColour()!=currentPiece.getColour()) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * This method checks if a move is legal under the rules of each game, checking what type of game is currently being played.
   * If the move is legal, it returns true, if the move is illegal, it returns false.
   * 
   * @param newX The integer of the new x position on the board of the selected piece.
   * @param newY The integer of the new y position on the board of the selected piece.
   * @param currentPiece The object Piece of the selected game piece that is going to be moved.
   * @return true if the move is deemed to be legal within the rules of the game and false if it is deemed to be illegal.
   */
  public boolean isMoveLegal(int newX, int newY, Piece currentPiece) {
    Type pieceType = currentPiece.getType();
    switch(this.game) {
    case CHECKERS:
      if((currentPiece != null) && (newX < 8) && (newY < 8) && (this.tiles[newX][newY] == null)) {
        if((pieceType == Type.CHECKERS_MAN && isMoveDiagonal(newX, newY, currentPiece) && (moveDistance(newX, newY, currentPiece) == 1) && isForward(newX, newY, currentPiece))) {
          return true;
        }
        else if((pieceType==Type.CHECKERS_KING && isMoveDiagonal(newX, newY, currentPiece))) {
          return true;
        }
        else if(takesPiece(newX, newY, currentPiece)) {
          return true;
        }
        else {
          return false;
        }
      }
      
      else {
        return false;
      }
      
    case CHESS:
      return false;
      
    case DRAUGHTS:
      return false;
      
    case GOMOKU:
      return false;
      
    case DAMA:
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
    if((this.turn == currentPiece.getColour()) && (isMoveLegal(newX, newY, currentPiece))) {
      int oldX = currentPiece.getxPos();
      int oldY = currentPiece.getyPos();
      if (takesPiece(newX, newY, currentPiece)) {
        int betweenX = oldX + (newX-oldX)/2;
        int betweenY = oldY + (newY-oldY)/2;
        Colour takenColour = this.tiles[betweenX][betweenY].getColour();
        boolean isWhite = takenColour.equals(Colour.WHITE);
        if(isWhite) {
          this.whiteCount--;
        }
        else {
          this.blackCount--;
        }
        this.tiles[betweenX][betweenY] = null;
      }
      this.tiles[oldX][oldY] = null;
      currentPiece.setXPos(newX);
      currentPiece.setYPos(newY);
      this.tiles[newX][newY] = currentPiece;
      this.turn = currentPiece.getOpposite();
    }
    
    else {
      System.out.println("That move is illegal.");
    }
    
    if((currentPiece.getColour() == Colour.WHITE && newY == 7) || (currentPiece.getColour() == Colour.BLACK && newY == 0)) {
      currentPiece.setType(Type.CHECKERS_KING);
    }
  }
  
  /**
   * This is a simple getter method that returns the array used to store pieces.
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
  
  /**
   * This method simply returns the number of black or white pieces on the board.
   * 
   * @param colour the enum Colour of which piece count you would like to get e.g. if colour is BLACK it will return the number of black pieces.
   * @return the integer count of that coloured piece present on the board.
   */
  public int getCount(Colour colour) {
    if (colour == Colour.BLACK) {
      return this.blackCount;
    }
    else {
      return this.whiteCount;
    }
  } 
  
}
