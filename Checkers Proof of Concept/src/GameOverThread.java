import javafx.application.Platform;
import javafx.stage.Stage;

public class GameOverThread extends Thread {
  
  private Board gameBoard;
  private Stage gameStage;
  private Boolean gameOver;
  
  public GameOverThread(Board gameBoard, Stage gameStage) {
    this.gameBoard = gameBoard;
    this.gameStage = gameStage;
    this.gameOver = false;
  }
  
  @Override
  public void run() {
    //System.out.println("toto");
    while(true) {
      //System.out.println("yo");
      if(gameBoard.getWhiteCount() == 0 || gameBoard.getBlackCount() == 0) {
        this.gameOver = true;
      }
      
      if(this.gameOver) {
        Platform.runLater(() -> {
          gameStage.close();
        });
        //break;
      }
    }
    //return;
  }
  
}
