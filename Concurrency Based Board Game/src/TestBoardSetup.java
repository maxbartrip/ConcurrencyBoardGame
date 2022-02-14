import java.io.InputStream;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestBoardSetup extends Application {
  
  private Group pieceGroup;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    stage.setTitle("Testing File");
    this.pieceGroup = new Group();
    Board gameBoard = new Board(GameType.CHECKERS);
    Pane gamePane = new Pane();
    gamePane.getChildren().addAll(this.pieceGroup);
    updateBoard(gameBoard);
    Scene gameScene = new Scene(gamePane);
    stage.setScene(gameScene);
    stage.show();
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
            checkersView.setFitHeight(64);
            checkersView.setFitWidth(64);
            checkersView.setPreserveRatio(true);
            checkersView.setSmooth(true);
            checkersView.setX(i*64);
            checkersView.setY(j*64);
            
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
