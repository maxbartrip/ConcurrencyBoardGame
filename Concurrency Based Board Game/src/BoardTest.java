import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  
  private Board gameBoard;
  private Piece[][] boardArray;

  void setupCheckers() {
    gameBoard = new Board(GameType.CHECKERS);
    boardArray = gameBoard.getBoard();
  }

  @Test
  void testSetup() {
    setupCheckers();
    Piece whitePiece = boardArray[1][0];
    Piece nullPiece = boardArray[3][4];
    Piece blackPiece = boardArray[6][5];
    assertEquals(whitePiece.getColour(), Colour.WHITE, "This test checks that a white piece is in the correct position.");
    assertNull(nullPiece, "A tile that has no piece in it should return null.");
    assertEquals(blackPiece.getColour(), Colour.BLACK, "This test checks that a black piece is in the correct position.");
  }
}
