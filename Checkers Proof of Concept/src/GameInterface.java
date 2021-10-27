import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * @author Max
 * 
 * This class is used to create the board that can be displayed to the user and to create the visual display of the pieces on that board.
 *
 */
public class GameInterface {
  
  private static Scene gameScene;

  /**
   * This constructor takes a gameBoard and creates a visual board that can be displayed on a javaFX interface, based on the board that has been input.
   * 
   * @param gameBoard the gameBoard of the current game so that the board interface can be initialised
   */
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
  
  /**
   * This method is used to return the Scene to the javaFX class, so that it can be displayed to the user.
   * 
   * @return the Scene gameScene which shows the board and pieces.
   */
  public static Scene getScene() {
    return gameScene;
  }
  
  public void updateBoard(Board gameBoard) {
    
  }
}
