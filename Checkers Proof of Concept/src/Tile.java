import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
  
  private int boardX;
  private int boardY;
  
  public Tile(boolean dark, int boardX, int boardY) {
    setWidth(128);
    setHeight(128);
    setFill(dark ? Color.BEIGE : Color.DARKSEAGREEN);
    setX(boardX*128);
    setY(boardY*128);
    this.boardX = boardX;
    this.boardY = boardY;
  }
  
  public int getBoardX() {
    return this.boardX;
  }
  
  public int getBoardY() {
    return this.boardY;
  }
}
