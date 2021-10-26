import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
  
  private int boardX;
  private int boardY;
  
  public Tile(boolean dark, int boardX, int boardY) {
    setWidth(64);
    setHeight(64);
    setFill(dark ? Color.DARKSEAGREEN : Color.BEIGE);
    this.boardX = boardX;
    this.boardY = boardY;
  }
}
