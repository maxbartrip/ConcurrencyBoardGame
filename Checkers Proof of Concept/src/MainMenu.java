import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {
  
  private GameType selectedGame;

  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Main Menu");
    
    Text menuTitle = new Text();
    menuTitle.setFont(Font.font(null, 20));
    menuTitle.setText("Please choose a game to play");
    
    Button checkersBtn = new Button("Play Checkers");
    Button chessBtn = new Button("Play Chess");
    chessBtn.setDisable(true);
    Button draughtsBtn = new Button("Play Polish Draughts");
    draughtsBtn.setDisable(true);
    Button gomokuBtn = new Button("Play Gomoku");
    gomokuBtn.setDisable(true);
    Button damaBtn = new Button("Play Dama");
    damaBtn.setDisable(true);
    
    Button startBtn = new Button("Start Game");
    
    checkersBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.GAME_CHECKERS;
      }
    });
    
    chessBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.GAME_CHESS;
      }
    });
    
    draughtsBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.GAME_DRAUGHTS;
      }
    });
    
    gomokuBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.GAME_GOMOKU;
      }
    });
    
    damaBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.GAME_DAMA;
      }
    });
    
    startBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (selectedGame != null) {
          Board gameBoard = new Board(selectedGame);
          gameBoard.setupCheckers();
          // Temporary method that is only used in this part of the project
          GameInterface gameUI = new GameInterface(gameBoard);
          Scene gameScene = gameUI.getScene();
          stage.setScene(gameScene);
          stage.setResizable(false);
          stage.show();
        }
        else {
          Alert plsSelect = new Alert(AlertType.WARNING);
          plsSelect.setTitle("No game selected!");
          plsSelect.setHeaderText("No game has been selected!");
          plsSelect.setContentText("Please select a game before clicking start game.");
          plsSelect.showAndWait();
        }
      }
    });
    
    HBox buttonLayout = new HBox(20, checkersBtn, chessBtn, draughtsBtn, gomokuBtn, damaBtn);
    buttonLayout.setAlignment(Pos.CENTER);
    VBox menuLayout = new VBox(20, menuTitle, buttonLayout, startBtn);
    menuLayout.setAlignment(Pos.CENTER);
    Scene menuScene = new Scene(menuLayout, 1280, 720);
    stage.setScene(menuScene);
    stage.show();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
}
