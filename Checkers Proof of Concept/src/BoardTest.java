import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  
  private Board gameBoard;
  private Piece[][] boardArray;
  
  @BeforeEach
  void setupBoard() {
    gameBoard = new Board(GameType.GAME_CHECKERS);
    gameBoard.setupCheckers();
    boardArray = gameBoard.getBoard();
  }
  
  @Test
  void testSetup() {
    Piece whitePiece = boardArray[1][5];
    Piece nullPiece = boardArray[3][4];
    Piece blackPiece = boardArray[5][7];
    assertEquals(whitePiece.getColour(),"white", "This test checks that a white piece is in the correct position.");
    assertNull(nullPiece, "A tile that has no piece in it should return null.");
    assertEquals(blackPiece.getColour(),"black", "This test checks that a black piece is in the correct position.");
  }
}
