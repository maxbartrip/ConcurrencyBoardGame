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
  
  @Test
  void testIsLegal() {
    setupCheckers();
    Piece blackPiece = boardArray[0][5];
    Piece whitePiece = boardArray[1][2];
    //Legal move
    assertTrue(gameBoard.isMoveLegal(1, 4, blackPiece), "A diagonal move forward of a black piece should be considered a legal move.");
    assertTrue(gameBoard.isMoveLegal(0, 3, whitePiece), "A diagonal move forward of a white piece should be considered a legal move.");
    //Illegal forward move
    assertFalse(gameBoard.isMoveLegal(0, 4, blackPiece), "A move forward of a black piece should not be considered a legal move and should return false.");
    assertFalse(gameBoard.isMoveLegal(1, 3, whitePiece), "A move forward of a white piece should not be considered a legal move and should return false.");
    //Illegal backwards move
    assertFalse(gameBoard.isMoveLegal(0, 5, blackPiece), "A move backwards of a black piece should not be considered a legal move and should return false.");
    assertFalse(gameBoard.isMoveLegal(1, 1, whitePiece), "A move backwards of a white piece should not be considered a legal move and should return false.");
    //Illegal backwards move onto piece
    assertFalse(gameBoard.isMoveLegal(1, 6, blackPiece), "A diagonally backwards move of a black piece, that lands on a piece should return false.");
    assertFalse(gameBoard.isMoveLegal(0, 1, whitePiece), "A diagonally backwards move of a white piece, that lands on a piece should return false.");
    //Illegal forwards diagonal move
    assertFalse(gameBoard.isMoveLegal(2, 3, blackPiece), "A diagonal move forwards of a black piece of more than one place should return false.");
    assertFalse(gameBoard.isMoveLegal(3, 4, whitePiece), "A diagonal move forwards of a white piece of more than one place should return false.");
  }
  
  @Test
  void testMove() {
    setupCheckers();
    Piece blackPiece = boardArray[0][5];
    Piece whitePiece = boardArray[1][2];
    
    gameBoard.makeMove(1, 4, blackPiece);
    int newX = blackPiece.getxPos();
    int newY = blackPiece.getyPos();
    assertEquals(newX, 1, "The new X position of the black piece should be changed to the input position.");
    assertEquals(newY, 4, "The new Y position of the black piece should be changed to the input position.");
    assertEquals(boardArray[1][4], blackPiece, "Check to see if the position of the black piece in the array has been updated.");
    
    gameBoard.makeMove(0, 3, whitePiece);
    newX = whitePiece.getxPos();
    newY = whitePiece.getyPos();
    assertEquals(newX, 0, "The new X position of the white piece should be changed to the input position.");
    assertEquals(newY, 3, "The new Y position of the white piece should be changed to the input position.");
    assertEquals(boardArray[0][3], whitePiece, "Check to see if the position of the white piece in the array has been updated.");
    
    Piece newPiece = new Piece(Type.CHECKERS_MAN, 3, 4, Colour.WHITE);
    boardArray[3][4] = newPiece;
    int whiteCount = gameBoard.getCount(Colour.WHITE);
    //Manually adding piece in take-able position.
    blackPiece = boardArray[2][5];
    gameBoard.makeMove(4, 3, blackPiece);
    newX = blackPiece.getxPos();
    newY = blackPiece.getyPos();
    assertEquals(newX, 4, "The new X position of the black piece should be changed to the input position.");
    assertEquals(newY, 3, "The new Y position of the black piece should be changed to the input position.");
    assertEquals(boardArray[4][3], blackPiece, "Check to see if the position of the black piece in the array has been updated.");
    assertEquals(boardArray[3][4], null, "Check that the taken piece has been removed from the board array.");
    assertEquals(gameBoard.getCount(Colour.WHITE), whiteCount-1, "Check that the count of white pieces has been decreased by one when a piece is taken.");
  }
  
  @Test
  void testTurns() {
    setupCheckers();
    Piece blackPiece = boardArray[0][5];
    Piece whitePiece = boardArray[1][2];
    
    gameBoard.makeMove(0, 3, whitePiece);
    int newX = whitePiece.getxPos();
    int newY = whitePiece.getyPos();
    assertEquals(newX, 1, "The new X position of the white piece should not be changed as black should have the first turn.");
    assertEquals(newY, 2, "The new Y position of the white piece should not be changed as black should have the first turn.");
    assertEquals(boardArray[1][2], whitePiece, "Check to ensure the position of the white piece in the array has not been changed.");
    
    gameBoard.makeMove(1, 4, blackPiece);
    newX = blackPiece.getxPos();
    newY = blackPiece.getyPos();
    assertEquals(newX, 1, "The new X position of the black piece should be changed to the input position.");
    assertEquals(newY, 4, "The new Y position of the black piece should be changed to the input position.");
    assertEquals(boardArray[1][4], blackPiece, "Check to see if the position of the black piece in the array has been updated.");
    
    gameBoard.makeMove(2, 5, blackPiece);
    newX = blackPiece.getxPos();
    newY = blackPiece.getyPos();
    assertEquals(newX, 1, "The new X position of the black piece should not be changed as it should be white's turn.");
    assertEquals(newY, 4, "The new Y position of the black piece should not be changed as it should be white's turn.");
    assertEquals(boardArray[1][4], blackPiece, "Check to enure the position of the black piece in the array has not been changed.");
    
    gameBoard.makeMove(0, 3, whitePiece);
    newX = whitePiece.getxPos();
    newY = whitePiece.getyPos();
    assertEquals(newX, 0, "The new X position of the white piece should be changed to the input position.");
    assertEquals(newY, 3, "The new Y position of the white piece should be changed to the input position.");
    assertEquals(boardArray[0][3], whitePiece, "Check to see if the position of the white piece in the array has been updated.");
  }
}
