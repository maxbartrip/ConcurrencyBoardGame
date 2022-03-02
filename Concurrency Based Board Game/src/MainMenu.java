import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
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
    VBox onlineLayout = new VBox(20, onlineText, onlineButtons, back);
    onlineLayout.setAlignment(Pos.CENTER);
    Pane onlineMenu = new Pane();
    Scene onlineScene = new Scene(onlineLayout, 1024, 576);
    
    Text connectTitle = new Text("Connect to Server");
    connectTitle.setFont(titleFont);
    Text giveIP = new Text("Server address");
    giveIP.setFont(btnFont);
    TextField inputIP = new TextField();
    inputIP.setMaxWidth(500);
    inputIP.setFont(btnFont);
    Button connect = new Button("Join Server");
    connect.setFont(btnFont);
    Button cancel = new Button("Cancel");
    cancel.setFont(btnFont);
    HBox connectButtons = new HBox(20, connect, cancel);
    connectButtons.setAlignment(Pos.CENTER);
    VBox connectMenu = new VBox(20, connectTitle, giveIP, inputIP, connectButtons);
    connectMenu.setAlignment(Pos.CENTER);
    Scene connectScene = new Scene(connectMenu, 1024, 576);
    
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
        currentStage.setResizable(false);
      }
    });
    
    host.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Board gameBoard = new Board(selectedGame);
        Random rand = new Random();
        int port = rand.nextInt(65536);
        try {
          GameInterface gameUI = new GameInterface(gameBoard, port);
          Scene gameScene = gameUI.getScene();
          Stage currentStage = (Stage) host.getScene().getWindow();
          currentStage.setScene(gameScene);
          currentStage.setResizable(false);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
      }
    });
    
    join.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage currentStage = (Stage) join.getScene().getWindow();
        currentStage.setScene(connectScene);
        currentStage.setResizable(false);
      }
    });
    
    back.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage currentStage = (Stage) back.getScene().getWindow();
        currentStage.setScene(multiplayerScene);
        currentStage.setResizable(false);
      }
    });
    
    connect.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Board gameBoard = new Board(selectedGame);
        String fullIp = inputIP.getText();
        String[] ipArray = fullIp.split(":");
        String ip = ipArray[0];
        int port = Integer.parseInt(ipArray[1]);
        try {
          GameInterface gameUI = new GameInterface(gameBoard, ip, port);
          Scene gameScene = gameUI.getScene();
          Stage currentStage = (Stage) connect.getScene().getWindow();
          currentStage.setScene(gameScene);
          currentStage.setResizable(false);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
      }
    });
    
    cancel.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.setScene(onlineScene);
        currentStage.setResizable(false);
      }
    });
    
    return multiplayerScene;
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
}
