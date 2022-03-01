import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {
  
  private GameType selectedGame;

  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Main Menu");
    
    Text menuTitle = new Text();
    menuTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, 40));
    menuTitle.setText("Concurrency Based Board Game Collection");
    Image checkersLogo = null;
    ImageView checkersView = new ImageView();
    Image chessLogo = null;
    ImageView chessView = new ImageView();
    Image draughtsLogo = null;
    ImageView draughtsView = new ImageView();
    Image gomokuLogo = null;
    ImageView gomokuView = new ImageView();
    Image damaLogo = null;
    ImageView damaView = new ImageView();
    
    try {
      checkersLogo = new Image("resources/CheckersLogo.png");
      checkersView = new ImageView(checkersLogo);
      checkersView.setPreserveRatio(true);
      checkersView.setFitHeight(200);
      
      chessLogo = new Image("resources/ChessLogo.png");
      chessView = new ImageView(chessLogo);
      chessView.setPreserveRatio(true);
      chessView.setFitHeight(200);
      
      draughtsLogo = new Image("resources/DraughtsLogo.png");
      draughtsView = new ImageView(draughtsLogo);
      draughtsView.setPreserveRatio(true);
      draughtsView.setFitHeight(200);
      
      gomokuLogo = new Image("resources/GomokuLogo.png");
      gomokuView = new ImageView(gomokuLogo);
      gomokuView.setPreserveRatio(true);
      gomokuView.setFitHeight(200);
      
      damaLogo = new Image("resources/DamaLogo.png");
      damaView = new ImageView(damaLogo);
      damaView.setPreserveRatio(true);
      damaView.setFitHeight(200);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    ArrayList<Image> imgList = new ArrayList<>();
    imgList.add(checkersLogo);
    imgList.add(chessLogo);
    imgList.add(draughtsLogo);
    imgList.add(gomokuLogo);
    imgList.add(damaLogo);
    
    Font btnFont = Font.font("Century Gothic", 20);
    
    Button checkersBtn = new Button("Play Checkers");
    checkersBtn.setFont(btnFont);
    
    Button chessBtn = new Button("Play Chess");
    chessBtn.setFont(btnFont);
    chessBtn.setDisable(true);
    
    Button draughtsBtn = new Button("Play Polish Draughts");
    draughtsBtn.setFont(btnFont);
    draughtsBtn.setDisable(true);
    
    Button gomokuBtn = new Button("Play Gomoku");
    gomokuBtn.setFont(btnFont);
    gomokuBtn.setDisable(true);
    
    Button damaBtn = new Button("Play Dama");
    damaBtn.setFont(btnFont);
    damaBtn.setDisable(true);
    
    if(!(imgList.contains(null))) {
      checkersBtn.setGraphic(checkersView);
      checkersBtn.setContentDisplay(ContentDisplay.TOP);
      chessBtn.setGraphic(chessView);
      chessBtn.setContentDisplay(ContentDisplay.TOP);
      draughtsBtn.setGraphic(draughtsView);
      draughtsBtn.setContentDisplay(ContentDisplay.TOP);
      gomokuBtn.setGraphic(gomokuView);
      gomokuBtn.setContentDisplay(ContentDisplay.TOP);
      damaBtn.setGraphic(damaView);
      damaBtn.setContentDisplay(ContentDisplay.TOP);
    }
    
    Button startBtn = new Button("Start Game");
    startBtn.setFont(btnFont);
    
    checkersBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.CHECKERS;
      }
    });
    
    chessBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.CHESS;
      }
    });
    
    draughtsBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.DRAUGHTS;
      }
    });
    
    gomokuBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.GOMOKU;
      }
    });
    
    damaBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selectedGame = GameType.DAMA;
      }
    });
    
    startBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (selectedGame != null) {
          Stage newStage = new Stage();
          newStage.setScene(multiplayerMenu(selectedGame));
          newStage.show();
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
    startBtn.requestFocus();
  }
  
  public Scene multiplayerMenu(GameType selectedGame) {
    Font titleFont = Font.font("Century Gothic", FontWeight.BOLD, 40);
    Font btnFont = Font.font("Century Gothic", 20);
    
    Text menuTitle = new Text("Local or Online Multiplayer?");
    menuTitle.setFont(titleFont);
    
    Button local = new Button("Local");
    local.setFont(btnFont);
    
    Button online = new Button("Online");
    online.setFont(btnFont);
    //online.setDisable(true);
    
    Text onlineText = new Text("Would you like to host a game or join a game?");
    onlineText.setFont(titleFont);
    Button host = new Button("Host a game");
    host.setFont(btnFont);
    Button join = new Button("Join a game");
    join.setFont(btnFont);
    Button back = new Button("Back");
    back.setFont(btnFont);
    back.setLayoutX(0);
    back.setLayoutY(0);
    HBox onlineButtons = new HBox(20, host, join);
    onlineButtons.setAlignment(Pos.CENTER);
    VBox onlineLayout = new VBox(20, onlineText, onlineButtons);
    onlineLayout.setAlignment(Pos.CENTER);
    Pane onlineMenu = new Pane();
    Scene onlineScene = new Scene(onlineLayout, 1024, 576);
    
    HBox buttonLayout = new HBox(20, local, online);
    buttonLayout.setAlignment(Pos.CENTER);
    VBox menuLayout = new VBox(20, menuTitle, buttonLayout);
    menuLayout.setAlignment(Pos.CENTER);
    Scene multiplayerScene = new Scene(menuLayout, 1024, 576);
    
    // Button functionalities
    
    local.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Board gameBoard = new Board(selectedGame);
        GameInterface gameUI = new GameInterface(gameBoard);
        Scene gameScene = gameUI.getScene();
        Stage currentStage = (Stage) local.getScene().getWindow();
        currentStage.setTitle(selectedGame.toString());
        currentStage.setScene(gameScene);
        currentStage.setResizable(false);
      }
    });
    
    online.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage currentStage = (Stage) online.getScene().getWindow();
        currentStage.setScene(onlineScene);
        currentStage.show();
      }
    });
    
    return multiplayerScene;
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
}
