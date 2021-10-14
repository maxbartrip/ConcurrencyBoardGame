
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
    // Setting up positions of white pieces
    for(int x = 0; x < 8; x++) {
      for(int y = 0; y < 8; y++) {
        if(y >= 3) {
          if((x%2==0)&&(y!=1)) {
            this.tiles[x][y] = new Piece(Type.CHECKERS_MAN, x, y, "white");
          }
          else if((x%2!=0)&&y==1) {
            this.tiles[x][y] = new Piece(Type.CHECKERS_MAN, x, y, "white");
          }
        }
      }
    }
  }
  
  public Piece[][] getBoard() {
    return this.tiles;
  }
  
}
