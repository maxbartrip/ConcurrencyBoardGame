import javafx.scene.control.TextArea;

/**
 * @author Max
 * 
 * This class is an extension of NetworkConnection for when a Client is to be created.
 *
 */
public class Client extends NetworkConnection {
  
  private String ip;
  private int port;
  
  /**
   * This is the constructor for the Client class. It first runs the constructor of NetworkConnection, passing the TextArea from the interface to the NetworkConnection constructor.
   * 
   * @param ip the String ip address of the server that is to be connected to
   * @param port the Integer port of the server that is to be connected to
   * @param messages the TextArea on the interface where messages are to be displayed
   */
  public Client(String ip, int port, TextArea messages, Board gameBoard, GameInterface gameUI) {
    super(messages, gameBoard, gameUI);
    this.ip = ip;
    this.port = port;
  }

  /**
   * This method is a check to see if the NetworkConnection is a server or not, as this is a Client class it returns false.
   */
  @Override
  protected Boolean isServer() {
    return false;
  }

  /**
   * This method returns the IP of the server the client is to connect to.
   */
  @Override
  protected String getIP() {
    return ip;
  }

  /**
   * This method returns the port of the server that is to be connected to.
   */
  @Override
  protected int getPort() {
    return port;
  }
  
}
