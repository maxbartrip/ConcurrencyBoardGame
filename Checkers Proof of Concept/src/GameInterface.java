import java.io.InputStream;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
  private Piece selectedPiece;
  private Group pieceGroup;

  /**
   * This constructor takes a gameBoard and creates a visual board that can be displayed on a javaFX interface, based on the board that has been input.
   * 
   * @param gameBoard the gameBoard of the current game so that the board interface can be initialised
   */
  public GameInterface(Board gameBoard) {
    int boardSize = gameBoard.getSize();
    boolean dark;
    this.gamePane = new Pane();
    this.pieceGroup = new Group();
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
        newTile.setOnMousePressed(new EventHandler<MouseEvent>() {

          @Override
          public void handle(MouseEvent event) {
            //System.out.println("X = "+newTile.getBoardX()+" Y = "+newTile.getBoardY());
            if (selectedPiece==null) {
              Alert selectPiece = new Alert(AlertType.WARNING);
              selectPiece.setTitle("No piece selected!");
              selectPiece.setHeaderText("Please select a piece");
              selectPiece.setContentText("In order to make a move you must first select a piece by clicking on it.");
              selectPiece.showAndWait();
            }
            else if(!gameBoard.isMoveLegal(newTile.getBoardX(), newTile.getBoardY(), selectedPiece)){
              Alert illegalMove = new Alert(AlertType.WARNING);
              illegalMove.setTitle("Illegal Move!");
              illegalMove.setHeaderText("The move you attempted is illegal!");
              illegalMove.setContentText("A legal move for a man is only in the forward direction and must be diagonal, a king can move any direction as long as it is diagonal.");
              illegalMove.showAndWait();
            }
            else {
              gameBoard.makeMove(newTile.getBoardX(), newTile.getBoardY(), selectedPiece);
              updateBoard(gameBoard);
              selectedPiece=null;
              if (gameBoard.getWhiteCount() == 0) {
                Alert gameWon = new Alert(AlertType.WARNING);
                gameWon.setTitle("BLACK WINS!");
                gameWon.setHeaderText("GAME OVER!");
                gameWon.setContentText("THE WINNER IS BLACK!\nClosing game.");
                gameWon.showAndWait();
                Platform.exit();
                System.exit(0);
              }
              else if(gameBoard.getBlackCount() == 0) {
                Alert gameWon = new Alert(AlertType.WARNING);
                gameWon.setTitle("WHITE WINS!");
                gameWon.setHeaderText("GAME OVER!");
                gameWon.setContentText("THE WINNER IS WHITE!\nClosing game.");
                gameWon.showAndWait();
                Platform.exit();
                System.exit(0);
              }
            }
          }
          
        });
        
        tileGroup.getChildren().add(newTile);
      }
    }
    this.gamePane.getChildren().addAll(tileGroup, this.pieceGroup);
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
    this.pieceGroup.getChildren().clear();
    
    for(int i=0; i<boardSize; i++) {
      for(int j=0; j<boardSize; j++) {
        if (pieceArray[i][j] !=null) {
          try {
            InputStream stream = pieceArray[i][j].getImage();
            Image checkersPiece = new Image(stream);
            ImageView checkersView = new ImageView();
            checkersView.setImage(checkersPiece);
            checkersView.setFitHeight(128);
            checkersView.setFitWidth(128);
            checkersView.setPreserveRatio(true);
            checkersView.setSmooth(true);
            checkersView.setX(j*128);
            checkersView.setY(i*128);
            
            checkersView.setOnMouseClicked(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                int pieceX = (int) checkersView.getX()/128;
                int pieceY = (int) checkersView.getY()/128;
                selectedPiece = pieceArray[pieceY][pieceX];
                //System.out.println(selectedPiece.getColour());
              }
            });
            
            this.pieceGroup.getChildren().add(checkersView);
          }
          catch(Exception e) {
            e.printStackTrace();
          }
          
        }
      }
    }
        
  }
}
