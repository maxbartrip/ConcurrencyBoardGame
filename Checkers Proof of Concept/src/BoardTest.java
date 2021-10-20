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
    assertFalse(gameBoard.isMoveLegal(7, 4, blackPiece), "A move forward should not be considered a legal move and should return false.");
    assertFalse(gameBoard.isMoveLegal(6, 6, blackPiece), "A diagonally backwards move, that lands on a piece should return false.");
    assertFalse(gameBoard.isMoveLegal(5, 3, blackPiece), "A diagonal move of more than one place should be considered illegal and should return false.");
  }
  
  @Test
  void testMove() {
    Piece whitePiece = boardArray[2][0];
    gameBoard.makeMove(1, 3, whitePiece);
    int newX = whitePiece.getxPos();
    int newY = whitePiece.getyPos();
    assertEquals(newX, 1, "The new X position of the white piece should be changed to the input position.");
    assertEquals(newY, 3, "The new Y position of the white piece should be changed to the input position.");
    Piece blackPiece = boardArray[5][7];
    gameBoard.makeMove(6, 4, blackPiece);
    newX = blackPiece.getxPos();
    newY = blackPiece.getyPos();
    assertEquals(newX, 6, "The new X position of the black piece should be changed to the input position.");
    assertEquals(newY, 4, "The new Y position of the black piece should be changed to the input position.");
    
    assertEquals(boardArray[3][1], whitePiece, "Check to see if the position of the white piece in the array has been updated.");
    assertEquals(boardArray[4][6], blackPiece, "Check to see if the position of the black piece in the array has been updated.");
  }
  
  @Test
  void testLegalMoveKing() {
    Piece whitePiece = boardArray[2][0];
    whitePiece.setType(Type.CHECKERS_KING);
    assertTrue(gameBoard.isMoveLegal(1, 3, whitePiece), "A single diagonal move should be legal for a king.");
    assertTrue(gameBoard.isMoveLegal(2, 4, whitePiece), "A double diagonal move should also be legal for a king.");
    assertFalse(gameBoard.isMoveLegal(0, 3, whitePiece), "A move forward should not be considered a legal move and should return false.");
    Piece blackPiece = boardArray[5][7];
    blackPiece.setType(Type.CHECKERS_KING);
    assertTrue(gameBoard.isMoveLegal(6, 4, blackPiece), "Check to see if a single move is legal for a black king.");
    assertTrue(gameBoard.isMoveLegal(5, 3, blackPiece), "Check to see if a double move is legal for a black king.");
    assertFalse(gameBoard.isMoveLegal(7, 4, blackPiece), "Check to see if a forward move is illegal for a black king.");
  }
}
