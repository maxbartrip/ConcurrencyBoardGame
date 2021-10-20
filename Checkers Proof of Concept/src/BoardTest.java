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
  
  @Test
  void testLegalMove() {
    Piece whitePiece = boardArray[2][0];
    assertTrue(gameBoard.isMoveLegal(1, 3, whitePiece), "A diagonal move should be considered legal and should return true.");
    assertFalse(gameBoard.isMoveLegal(0, 3, whitePiece), "A move forward should not be considered a legal move and should return false.");
    assertFalse(gameBoard.isMoveLegal(1, 1, whitePiece), "A diagonally backwards move, that lands on a piece should return false.");
    assertFalse(gameBoard.isMoveLegal(2, 4, whitePiece), "A diagonal move of more than one place should be considered illegal and should return false.");
    Piece blackPiece = boardArray[5][7];
    assertTrue(gameBoard.isMoveLegal(6, 4, blackPiece), "A diagonal move should be considered legal and should return true.");
    assertFalse(gameBoard.isMoveLegal(5, 6, blackPiece), "A move forward should not be considered a legal move and should return false.");
    assertFalse(gameBoard.isMoveLegal(6, 6, blackPiece), "A diagonally backwards move, that lands on a piece should return false.");
    assertFalse(gameBoard.isMoveLegal(5, 3, blackPiece), "A diagonal move of more than one place should be considered illegal and should return false.");
  }
}
