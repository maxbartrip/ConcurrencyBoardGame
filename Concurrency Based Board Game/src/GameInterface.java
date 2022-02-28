import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class GameInterface {
  
  private static Scene gameScene;
  private Pane boardPane;
  private Piece selectedPiece;
  private Group pieceGroup;
  private TextArea chat;
  private HBox gameBox;
  
  /**
   * This constructor takes a gameBoard and creates a visual board that can be displayed on a javaFX interface, based on the board that has been input.
   * 
   * @param gameBoard the gameBoard of the current game so that the board interface can be initialised
   */
  public GameInterface(Board gameBoard) {
    int boardSize = gameBoard.getSize();
    boolean dark;
    this.boardPane = new Pane();
    this.pieceGroup = new Group();
    this.chat = new TextArea();
    this.chat.setEditable(false);
    this.chat.setPrefHeight(450);
    this.chat.setWrapText(true);
    this.chat.setFont(Font.font("Century Gothic", 14));
    this.gameBox = new HBox(20);
    TextField inputField = new TextField();
    inputField.setFont(Font.font("Century Gothic", 14));
    
    inputField.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        String message = inputField.getText();
        inputField.clear();
        chat.appendText(message+"\n");
      }
      
    });
    
    Group tileGroup = new Group();
    VBox chatBox = new VBox(20, this.chat, inputField);
    
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
            if (selectedPiece==null) {
              chat.appendText("ERROR: No piece elected. Please select a piece by clicking on it before making a move.\n");
            }
            else if(!gameBoard.isMoveLegal(newTile.getBoardX(), newTile.getBoardY(), selectedPiece)){
              chat.appendText("ERROR: Illegal move. The move you attempted is illegal. A legal move for a man is only in the forward direction and must be diagonal, a king can move any direction as long as it is diagonal.\n");
            }
            
            else {
              gameBoard.makeMove(newTile.getBoardX(), newTile.getBoardY(), selectedPiece);
              updateBoard(gameBoard);
              selectedPiece=null;
              if (gameBoard.getCount(Colour.WHITE) == 0) {
                Alert gameWon = new Alert(AlertType.WARNING);
                gameWon.setTitle("BLACK WINS!");
                gameWon.setHeaderText("GAME OVER!");
                gameWon.setContentText("THE WINNER IS BLACK!\nClosing game.");
                gameWon.showAndWait();
                Window stage = newTile.getScene().getWindow();
                stage.hide();
              }
              else if(gameBoard.getCount(Colour.BLACK) == 0) {
                Alert gameWon = new Alert(AlertType.WARNING);
                gameWon.setTitle("WHITE WINS!");
                gameWon.setHeaderText("GAME OVER!");
                gameWon.setContentText("THE WINNER IS WHITE!\nClosing game.");
                gameWon.showAndWait();
                Window stage = newTile.getScene().getWindow();
                stage.hide();
              }
            }
          }
          
        });
        
        tileGroup.getChildren().add(newTile);
      }
    }
    this.boardPane.getChildren().addAll(tileGroup, this.pieceGroup);
    this.gameBox.getChildren().addAll(this.boardPane, chatBox);
    updateBoard(gameBoard);
    this.gameScene = new Scene(this.gameBox);
    inputField.requestFocus();
  }
  
  /**
   * This method is used to return the Scene to the javaFX class, so that it can be displayed to the user.
   * 
   * @return the Scene gameScene which shows the board and pieces.
   */
  public static Scene getScene() {
    return gameScene;
  }
  
  /**
   * This method updates the interface to display the position of the pieces based on the Board that is passed to it.
   * It also provides interaction with the pieces, allowing the user to select a piece and a new position on the interface.
   * 
   * @param gameBoard the Board of the game being played.
   */
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
            
            checkersView.setOnMouseClicked(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                int pieceX = (int) checkersView.getX()/64;
                int pieceY = (int) checkersView.getY()/64;
                Piece newSelectedPiece = pieceArray[pieceX][pieceY];
                if (newSelectedPiece.getColour() == gameBoard.getTurn()) {
                  selectedPiece = pieceArray[pieceX][pieceY];
                }
                else {
                  selectedPiece = null;
                  chat.appendText("ERROR: It is currently "+gameBoard.getTurn()+"'s turn. You can only select a piece when it is that colour's turn.\n");
                }
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