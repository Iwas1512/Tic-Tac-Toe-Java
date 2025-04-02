package tic.tac.toe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

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
        
        testGame.makeMove(0);
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
    
    @Test
    public void testGameStatisticsTracking() {
        game testGame = new game();
        
        testGame.makeMove(0);
        testGame.makeMove(3);
        testGame.makeMove(1);
        testGame.makeMove(4);
        testGame.makeMove(2);
        
        assertEquals(true, testGame.isGameOver());
        assertEquals("Player X wins!", testGame.getResultMessage());
        
        String gameLog = testGame.getGameLog();
        assertTrue(gameLog.contains("Player X Wins   1"));
        assertTrue(gameLog.contains("Player O Wins   0"));
        assertTrue(gameLog.contains("Ties            0"));
    }
    
    @Test
    public void testLoserGoesFirst() {
        game testGame = new game();
        
        testGame.makeMove(0);
        testGame.makeMove(3);
        testGame.makeMove(1);
        testGame.makeMove(4);
        testGame.makeMove(2);
        
        testGame.resetGame();
        
        assertEquals('O', testGame.getCurrentPlayer());
        
        testGame.makeMove(0);
        testGame.makeMove(3);
        testGame.makeMove(1);
        testGame.makeMove(4);
        testGame.makeMove(2);
        
        testGame.resetGame();
        assertEquals('X', testGame.getCurrentPlayer());
    }
    
    @Test
    public void testDrawGameStatistics() {
        game testGame = new game();
        
        testGame.makeMove(0);
        testGame.makeMove(1);
        testGame.makeMove(2);
        testGame.makeMove(4);
        testGame.makeMove(7);
        testGame.makeMove(6);
        testGame.makeMove(3);
        testGame.makeMove(5);
        testGame.makeMove(8);
        
        assertEquals(true, testGame.isGameOver());
        assertEquals("It's a draw!", testGame.getResultMessage());
        
        String gameLog = testGame.getGameLog();
        assertTrue(gameLog.contains("Player X Wins   0"));
        assertTrue(gameLog.contains("Player O Wins   0"));
        assertTrue(gameLog.contains("Ties            1"));
        
        testGame.resetGame();
        assertEquals('X', testGame.getCurrentPlayer());
    }
    
    @Test
    public void testGameLogFormat() {
        game testGame = new game();
        
        String gameLog = testGame.getGameLog();
        assertTrue(gameLog.contains("Player X Wins   0"));
        assertTrue(gameLog.contains("Player O Wins   0"));
        assertTrue(gameLog.contains("Ties            0"));
        
        String fileLog = testGame.getGameLogForFile();
        assertTrue(fileLog.contains("Tic-Tac-Toe Game Statistics"));
        assertTrue(fileLog.contains("Total Games:   0"));
    }
}