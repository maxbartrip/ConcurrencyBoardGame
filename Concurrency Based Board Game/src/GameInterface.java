import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    this.gameBox = new HBox(20);
    TextField inputField = new TextField();
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
    this.gameScene = new Scene(this.gameBox);
  }
  
  /**
   * This method is used to return the Scene to the javaFX class, so that it can be displayed to the user.
   * 
   * @return the Scene gameScene which shows the board and pieces.
   */
  public static Scene getScene() {
    return gameScene;
  }
}