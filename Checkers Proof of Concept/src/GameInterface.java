import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameInterface {
  
  private static Scene gameScene;

  public GameInterface(Board gameBoard) {
    //InputStream stream = new FileInputStream("src/resources/CHECKERS_BLACK_KING.png");
    //Image checkersPiece = new Image(stream);
    
    //ImageView checkersView = new ImageView();
    //checkersView.setImage(checkersPiece);
    
    int boardSize = gameBoard.getSize();
    boolean dark;
    Pane gamePane = new Pane();
    Group tileGroup = new Group();
    
    for(int i=0; i<boardSize; i++) {
      for(int j=0; j<boardSize; j++) {
        if((i+j)%2==0) {
          dark = true;
        }
        else {
          dark = false;
        }
        Tile newTile = new Tile(dark, i, j);
        tileGroup.getChildren().add(newTile);
      }
    }
    gamePane.getChildren().addAll(tileGroup);
    this.gameScene = new Scene(gamePane);
  }
  
  public static Scene getScene() {
    return gameScene;
  }
  
  public void updateBoard() {
    
  }
}
