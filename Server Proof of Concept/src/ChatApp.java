import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Max
 * 
 * This class creates a chat window which allows the user to send messages between a client and a server.
 * 
 * This program is partially based on: https://github.com/AlmasB/FXTutorials/tree/ebc1d95f2dee01e28b84efc14ef9f1b0102bf8f2/src/main/java/com/almasb/chat
 *
 */
public class ChatApp extends Application {
  
  private boolean isServer = true;
  /* 
   * This value can be modified to change whether a server or a client is created. When set to true it will create a server,
   * when set to false it will create a client that connects to that server. A server must already be running for a client to connect.
  */
  
  private TextArea messages = new TextArea();
  private NetworkConnection connection = isServer ? new Server(3001, messages) : new Client("127.0.0.1", 3001, messages);
  // If the connection isServer is true, connection is a new Server(), if it is false, connection is a new Client().
  
  /**
   * This method starts the NetworkConnection and creates and displays the interface that allows the user to send messages and see received messages.
   * 
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    connection.startConnection();
    // Start the server/client
    
    String title;
    if (isServer) {
      title = "Chat App: Server";
    }
    else {
      title = "Chat App: Client";
    }
    
    primaryStage.setTitle(title);
    
    messages.setEditable(false);
    messages.setPrefHeight(550);
    
    TextField input = new TextField();
    
    // When enter is pressed this event will activate
    input.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String message = input.getText();
        input.clear();
        messages.appendText(message+"\n");
        
        try {
          connection.send(message);
        }
        catch (Exception e) {
          messages.appendText("Error: Failed to send message\n");
          e.printStackTrace();
        }
      }
      
    });
    
    VBox chatLayout = new VBox(20, messages, input);
    chatLayout.setPrefSize(600, 600);
    Scene primaryScene = new Scene(chatLayout);
    primaryStage.setScene(primaryScene);
    primaryStage.show();
    input.requestFocus();
  }
  
  /**
   * This method closes the NetworkConnection when the interface is closed.
   */
  @Override
  public void stop() throws Exception {
    connection.closeConnection();
  }

  /**
   * This is the main method which launches the interface, running the start method.
   * 
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

}
