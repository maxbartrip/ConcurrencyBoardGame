
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
   * This is the constructor for the board class, it checks which game the user wants to play and sets up a datastructure to store pieces accordingly.
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
  
  public boolean isMoveLegal(int newX, int newY, Piece currentPiece) {
    if(currentPiece != null) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public void makeMove(int newX, int newY, Piece currentPiece) {
    if(isMoveLegal(newX, newY, currentPiece)) {
      int oldX = currentPiece.getxPos();
      int oldY = currentPiece.getyPos();
      this.tiles[oldX][oldY] = null;
      currentPiece.setXPos(newX);
      currentPiece.setYPos(newY);
      this.tiles[newX][newY] = currentPiece;
    }
    
    else {
      System.out.println("That move is illegal.");
    }
  }
  
  public Piece[][] getBoard() {
    return this.tiles;
  }
  
}
