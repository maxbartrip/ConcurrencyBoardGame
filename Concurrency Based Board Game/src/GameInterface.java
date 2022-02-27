import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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