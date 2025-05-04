package tic.tac.toe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ComputerPlayerTest {
    
    @Test
    public void testFirstMoveIsCorner() {
        ComputerPlayer computer = new ComputerPlayer();
        char[] emptyBoard = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        
        int move = computer.makeMove(emptyBoard, 'X');
        assertTrue(move == 0 || move == 2 || move == 6 || move == 8);
    }
    
    @Test
    public void testSecondMoveIsCenter() {
        ComputerPlayer computer = new ComputerPlayer();
        char[] boardWithOneMove = {'X', '2', '3', '4', '5', '6', '7', '8', '9'};
        
        int move = computer.makeMove(boardWithOneMove, 'O');
        assertEquals(4, move);
    }
    
    @Test
    public void testWinningMove() {
        ComputerPlayer computer = new ComputerPlayer();
        
        char[] rowWin = {'X', 'X', '3', '4', '5', '6', '7', '8', '9'};
        assertEquals(2, computer.makeMove(rowWin, 'X'));
        
        char[] colWin = {'X', '2', '3', 'X', '5', '6', '7', '8', '9'};
        assertEquals(6, computer.makeMove(colWin, 'X'));
        
        char[] diagWin = {'X', '2', '3', '4', 'X', '6', '7', '8', '9'};
        assertEquals(8, computer.makeMove(diagWin, 'X'));
    }
    
    
    @Test
    public void testBlockingMove() {
        ComputerPlayer computer = new ComputerPlayer();
        
        char[] blockRow = {'O', 'O', '3', '4', '5', '6', '7', '8', '9'};
        assertEquals(2, computer.makeMove(blockRow, 'X'));
        
        char[] blockCol = {'O', '2', '3', '4', '5', '6', 'O', '8', '9'};
        assertEquals(3, computer.makeMove(blockCol, 'X'));
        
        char[] blockDiag = {'O', '2', '3', '4', 'O', '6', '7', '8', '9'};
        assertEquals(8, computer.makeMove(blockDiag, 'X'));
    }
    
    @Test
    public void testPriorityOrder() {
        ComputerPlayer computer = new ComputerPlayer();
        
        char[] winOrBlock = {
            'X', 'X', '3',
            'O', 'O', '6',
            '7', '8', '9'
        };
        assertEquals(2, computer.makeMove(winOrBlock, 'X'));
    }
    
    @Test
    public void testRandomMove() {
        ComputerPlayer computer = new ComputerPlayer();
        
        char[] randomBoard = {
            'X', 'O', 'X',
            'O', 'X', 'O',
            '7', '8', '9'
        };
        
        int move = computer.makeMove(randomBoard, 'X');
        assertTrue(move == 6 || move == 7 || move == 8);
    }
}