package tic.tac.toe;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class gametest {
    
    @Test
    public void testNewGameInitialization() {
        game testGame = new game();
        assertEquals('X', testGame.getCurrentPlayer());
        assertEquals(false, testGame.isGameOver());
        assertEquals("", testGame.getResultMessage());
    }
    
    @Test
    public void testIsValidMove() {
        game testGame = new game();
        assertEquals(true, testGame.isValidMove(0));
        assertEquals(true, testGame.isValidMove(8));
        
        testGame.makeMove(0); //for position 1
        assertEquals(false, testGame.isValidMove(0));
        assertEquals(true, testGame.isValidMove(1));
    }
    
    @Test
    public void testMakeMove() {
        game testGame = new game();
        testGame.makeMove(0); 
        assertEquals('O', testGame.getCurrentPlayer());
        
        testGame.makeMove(1); 
        assertEquals('X', testGame.getCurrentPlayer());
    }
    
    @Test
    public void testHorizontalWinFirstRow() {
        game testGame = new game();
        // X plays positions 1, 2,and 3 
        testGame.makeMove(0); 
        assertEquals(false, testGame.isGameOver());
        
        testGame.makeMove(3); 
        assertEquals(false, testGame.isGameOver());
        
        testGame.makeMove(1); 
        assertEquals(false, testGame.isGameOver());
        
        testGame.makeMove(4); 
        assertEquals(false, testGame.isGameOver());
        
        testGame.makeMove(2); 
        assertEquals(true, testGame.isGameOver());
        assertEquals("Player X wins!", testGame.getResultMessage());
    }
    
    @Test
    public void testVerticalWinFirstColumn() {
        game testGame = new game();
        // O plays positions 1, 4, 7 (first column)
        testGame.makeMove(1); 
        testGame.makeMove(0); 
        testGame.makeMove(2);
        testGame.makeMove(3); 
        testGame.makeMove(8); 
        testGame.makeMove(6);
        
        assertEquals(true, testGame.isGameOver());
        assertEquals("Player O wins!", testGame.getResultMessage());
    }
    
    @Test
    public void testDiagonalWinTopLeftToBottomRight() {
        game testGame = new game();
        // X plays positions 1, 5, 9 
        testGame.makeMove(0); 
        testGame.makeMove(1); 
        testGame.makeMove(4); 
        testGame.makeMove(2); 
        testGame.makeMove(8); 
        
        assertEquals(true, testGame.isGameOver());
        assertEquals("Player X wins!", testGame.getResultMessage());
    }
    
    @Test
    public void testDiagonalWinTopRightToBottomLeft() {
        game testGame = new game();
        // O plays positions 3, 5, 7 (another diagnol test)
        testGame.makeMove(0); 
        testGame.makeMove(2); 
        testGame.makeMove(1); 
        testGame.makeMove(4); 
        testGame.makeMove(8); 
        testGame.makeMove(6); 
        
        assertEquals(true, testGame.isGameOver());
        assertEquals("Player O wins!", testGame.getResultMessage());
    }
    
 
    
    @Test
    public void testResetGame() {
        game testGame = new game();
       
        testGame.makeMove(0);
        testGame.makeMove(1);
        
   
        testGame.resetGame();
        
        // make sure the game is rest here, checking statuses
        assertEquals('X', testGame.getCurrentPlayer());
        assertEquals(false, testGame.isGameOver());
        assertEquals("", testGame.getResultMessage());
        assertEquals(true, testGame.isValidMove(0));
        assertEquals(true, testGame.isValidMove(1));
    }
    
    @Test
    public void testGetBoardDisplay() {
        game testGame = new game();
        String display = testGame.getBoardDisplay();
        
       //does the board work correctly?
        assertEquals(true, display.contains("1"));
        assertEquals(true, display.contains("9"));
        assertEquals(true, display.contains("|"));
        assertEquals(true, display.contains("---+---+---"));
    }
    
    @Test
    public void testBoardStateAfterMoves() {
        game testGame = new game();
        
        testGame.makeMove(0); 
        String display = testGame.getBoardDisplay();
        assertEquals(true, display.contains("X"));
        assertEquals(false, display.contains("1"));
        
        testGame.makeMove(1);
        display = testGame.getBoardDisplay();
        assertEquals(true, display.contains("O"));
        assertEquals(false, display.contains("2"));
    }
}