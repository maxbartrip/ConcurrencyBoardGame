import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
  private Colour colour;
  
  /**
   * This is the constructor which sets the information about the piece.
   * 
   * @param type The enum type that the piece is, this describes the game that the piece is from and it's type such as man, king, queen etc.
   * @param xPos The integer position along the x axis of the board that the piece lies.
   * @param yPos The integer position along the y axis of the board that the piece lies.
   * @param colour The enum colour of the piece, signifying which team the piece belongs to.
   */
  public Piece(Type type, int xPos, int yPos, Colour colour) {
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
  
  public Colour getColour() {
    return this.colour;
  }
  
  /**
   * This is a setter method will be needed for promoting pieces during a game i.e. in checkers a man may become a king.
   * 
   * @param newType the enum Type for the new type of the piece
   */
  public void setType(Type newType) {
    this.type = newType;
  }
  
  /**
   * This is a setter for updating the position of the piece.
   * 
   * @param newXPos the integer of the new position along the x axis of the piece.
   */
  public void setXPos(int newXPos) {
    this.xPos = newXPos;
  }
  
  /**
   * This is a setter for updating the position of the piece.
   * 
   * @param newYPos the integer of the new position along the y axis of the piece.
   */
  public void setYPos(int newYPos) {
    this.yPos = newYPos;
  }
  
  /**
   * This method returns the InputStream for the location of the image corresponding to this piece.
   * 
   * @return the InputStream for the location of the image corresponding to the piece.
   * @throws FileNotFoundException if the location of the image cannot be found an appropriate exception is thrown.
   */
  public InputStream getImage() throws Exception {
    InputStream stream = null;
    String location = "src/resources/"+this.type+"_"+this.colour.toString()+".png";
    try {
      stream = new FileInputStream(location);
    }
    catch(FileNotFoundException e) {
      throw new FileNotFoundException("ERROR: Image cannot be located.");
    }
    return stream;
  }
  
  /**
   * This method returns the opposite colour of the piece, e.g. if the piece is a white piece, black will be returned and vice versa.
   * 
   * @return The opposite colour of the piece.
   */
  public Colour getOpposite() {
    return this.colour.getOpposite();
  }
}
