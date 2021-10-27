import java.io.InputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Max
 * 
 * This class is used to create the board that can be displayed to the user and to create the visual display of the pieces on that board.
 *
 */
public class GameInterface {
  
  private static Scene gameScene;
  private Pane gamePane;

  /**
   * This constructor takes a gameBoard and creates a visual board that can be displayed on a javaFX interface, based on the board that has been input.
   * 
   * @param gameBoard the gameBoard of the current game so that the board interface can be initialised
   */
  public GameInterface(Board gameBoard) {
    int boardSize = gameBoard.getSize();
    boolean dark;
    this.gamePane = new Pane();
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
    this.gamePane.getChildren().addAll(tileGroup);
    updateBoard(gameBoard);
    this.gameScene = new Scene(this.gamePane);
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
    int boardSize = gameBoard.getSize();
    Piece[][] pieceArray = gameBoard.getBoard();
    
    for(int i=0; i<boardSize; i++) {
      for(int j=0; j<boardSize; j++) {
        if (pieceArray[i][j] !=null) {
          try {
            InputStream stream = pieceArray[i][j].getImage();
            Image checkersPiece = new Image(stream);
            ImageView checkersView = new ImageView();
            checkersView.setImage(checkersPiece);
            checkersView.setFitHeight(64);
            checkersView.setFitWidth(64);
            checkersView.setPreserveRatio(true);
            checkersView.setSmooth(true);
            checkersView.setX(j*64);
            checkersView.setY(i*64);
            this.gamePane.getChildren().add(checkersView);
          }
          catch(Exception e) {
            e.printStackTrace();
          }
          
        }
      }
    }
        
  }
}
