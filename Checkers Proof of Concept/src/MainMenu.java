import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

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
