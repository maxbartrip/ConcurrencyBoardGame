import javafx.scene.control.TextArea;

/**
 * @author Max
 * 
 * This class is an extension of NetworkConnection for when a Server is to be created.
 *
 */
public class Server extends NetworkConnection {
  
  private int port;
  
  /**
   * This is the constructor for the Server class. It first runs the constructor of NetworkConnection, passing the TextArea from the interface to the NetworkConnection constructor.
   * 
   * @param port the Integer port of the server to be created
   * @param messages the TextArea on the interface where messages are to be displayed
   */
  public Server(int port, TextArea messages, Board gameBoard, GameInterface gameUI) {
    super(messages, gameBoard, gameUI);
    this.port = port;
  }
  
  /**
   * This method is a check to see if the NetworkConnection is a server or not, as this is a Server class it returns true.
   */
  @Override
  protected Boolean isServer() {
    return true;
  }

  /**
   * This method returns the IP of the server the client is to connect to, as this is a server it is null.
   */
  @Override
  protected String getIP() {
    return null;
  }

  /**
   * This method returns the port of the server that is to be created, so that the client can connect to it.
   */
  @Override
  protected int getPort() {
    return port;
  }

}
