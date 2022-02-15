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
  
  @Test
  void testSize() {
    gameBoard = new Board(GameType.CHECKERS);
    assertEquals(gameBoard.getSize(), 8, "Size of a checkers board should be set to 8.");
    gameBoard = new Board (GameType.CHESS);
    assertEquals(gameBoard.getSize(), 8, "Size of a chess board should be set to 8.");
    gameBoard = new Board (GameType.DRAUGHTS);
    assertEquals(gameBoard.getSize(), 10, "Size of a draughts board should be set to 10.");
    gameBoard = new Board (GameType.GOMOKU);
    assertEquals(gameBoard.getSize(), 15, "Size of a gomoku board should be set to 15.");
    gameBoard = new Board (GameType.DAMA);
    assertEquals(gameBoard.getSize(), 8, "Size of a dama board should be set to 8.");
  }
  
  @Test
  void testDiagonal() {
    setupCheckers();
    Piece testPiece = new Piece(Type.CHECKERS_MAN, 0, 0, Colour.WHITE);
    assertTrue(gameBoard.isMoveDiagonal(1, 1, testPiece), "This test should return true as the position (1, 1) is diagonal to (0, 0)");
    assertFalse(gameBoard.isMoveDiagonal(1, 0, testPiece), "This test should return false as the position (1, 0) is not diagonal to (0, 0)");
    assertTrue(gameBoard.isMoveDiagonal(7, 7, testPiece), "This test should return true as the position (7, 7) is diagonal to (0, 0)");
  }
  
  @Test
  void testDistance() {
    setupCheckers();
    Piece testPiece = new Piece(Type.CHECKERS_MAN, 0, 0, Colour.WHITE);
    assertEquals(gameBoard.moveDistance(2, 3, testPiece), 3, "The distance in this case should return three.");
  }
  
  @Test
  void testCount() {
    setupCheckers();
    int whiteCount = gameBoard.getCount(Colour.WHITE);
    int blackCount = gameBoard.getCount(Colour.BLACK);
    assertEquals(whiteCount, 12, "The number of white pieces on a default checkers board should be 12.");
    assertEquals(blackCount, 12, "The number of black pieces on a default checkers board should be 12.");
  }
  
  @Test
  void testTakesPiece() {
    setupCheckers();
    boardArray[2][3] = new Piece(Type.CHECKERS_MAN, 2, 3, Colour.BLACK);
    // Manually adding piece in take-able position
    Piece testPiece = boardArray[1][2];
    assertTrue(gameBoard.takesPiece(3, 4, testPiece), "This move should take the black piece.");
    testPiece = boardArray[7][2];
    assertFalse(gameBoard.takesPiece(6, 3, testPiece), "This move should not take any piece.");
  }
}
