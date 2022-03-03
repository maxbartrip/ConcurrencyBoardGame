import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public abstract class NetworkConnection {
  private ConnectionThread connThread;
  private Board gameBoard;
  
  /**
   * The constructor method for NetworkConnection, creates a ConnectionThread with the TextArea that is passed to it.
   * 
   * @param messages the TextArea on the interface where messages are displayed
   */
  public NetworkConnection(TextArea messages, Board gameBoard) {
    this.gameBoard = gameBoard;
    connThread = new ConnectionThread(messages);
    connThread.setDaemon(true);
  }
  
  /**
   * Starts the ConnectionThread, calls the run() method.
   * 
   * @throws Exception IllegalThreadStateException if the thread was already started.
   */
  public void startConnection() throws Exception {
    connThread.start();
  }
  
  /**
   * This method takes a String message and writes it to the DataOutputStream of the ConnectionThread allowing it to send messages to the Server/Client
   * 
   * @param message the String message that is to be sent
   * @throws Exception
   */
  public void send(String message) throws Exception {
    connThread.output.writeUTF(message);
  }
  
  /**
   * This method closes the socket of the connectionThread allowing the socket to safely close.
   * 
   * @throws Exception
   */
  public void closeConnection() throws Exception {
    connThread.socket.close();
  }
  
  protected abstract Boolean isServer();
  protected abstract String getIP();
  protected abstract int getPort();
  
  /**
   * @author Max
   *
   * This class is a thread that allows the program to constantly check for messages whilst the interface is running as well as interact with the interface to display messages.
   */
  private class ConnectionThread extends Thread {
    private Socket socket;
    private DataOutputStream output;
    private TextArea messages;
    
    /**
     * This is the constructor for the ConnectionThread class, setting messages to TextArea where messages are displayed so that the thread can interact with it.
     * 
     * @param messages the TextArea where messages are to displayed
     */
    ConnectionThread(TextArea messages) {
      this.messages = messages;
    }
    
    /**
     * This method contains the functionality of the ConnectionThread class, which creates a ServerSocket and or Socket depending on whether the NetworkConnection is a server or not.
     * It then creates the DataOutputStream and DataInputStream. It then starts a loop that is constantly checking for messages being received and appends them to the TextArea.
     *
     */
    @Override
    public void run() {
      try {
        
        if(isServer()) {
          ServerSocket server = new ServerSocket(getPort());
          socket = server.accept();
        }
        else {
          socket = new Socket(getIP(), getPort());
        }
        
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        
        this.socket = socket;
        this.output = output;
        socket.setTcpNoDelay(true);
        
        while (true) {
          String message = input.readUTF();
          Platform.runLater(() -> {
            if (message.substring(0, 3).equals("MSG")) {
              messages.appendText("Opponent: "+message.substring(3)+"\n");
            }
            if (message.substring(0, 3).equals("MOV")) {
              String move = message.substring(3);
              String[] moveArray = move.split(":");
              // Moves should be sent in the format "MOVpieceX:pieceY:newX:newY"
              int pieceX = Integer.parseInt(moveArray[0]);
              int pieceY = Integer.parseInt(moveArray[1]);
              int newX = Integer.parseInt(moveArray[2]);
              int newY = Integer.parseInt(moveArray[3]);
              Piece movePiece = gameBoard.getPiece(pieceX, pieceY);
              gameBoard.makeMove(newX, newY, movePiece);
            }
          });
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
