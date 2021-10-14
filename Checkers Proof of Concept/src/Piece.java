
/**
 * @author Max
 * 
 * This is the object that will store information about a game piece such as the type, position on the board colour etc.
 *
 */
public class Piece {
  private Type type;
  private int xPos;
  private int yPos;
  private String colour;
    
  /**
   * This is the constructor which sets the information about the piece.
   * 
   * @param type The enum type that the piece is, this describes the game that the piece is from and it's type such as man, king, queen etc.
   * @param xPos The integer position along the x axis of the board that the piece lies.
   * @param yPos The integer position along the y axis of the board that the piece lies.
   * @param colour The string colour of the piece, signifying which team the piece belongs to.
   */
  public Piece(Type type, int xPos, int yPos, String colour) {
    this.type = type;
    this.xPos = xPos;
    this.yPos = yPos;
    this.colour = colour;
  }
  
  public Type getType() {
    return this.type;
  }
  
  public int getxPos() {
    return this.xPos;
  }
  
  public int getyPos() {
    return this.yPos;
  }
  
  public String getColour() {
    return this.colour;
  }
}
