package tic.tac.toe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    private Random random = new Random();
    
    public int makeMove(char[] board, char computerMark) {
        char opponentMark = (computerMark == 'X') ? 'O' : 'X';
        
        
        if (isBoardEmpty(board)) {
            return chooseCorner();
        }
        
      
        if (isSecondMove(board) && board[4] != 'X' && board[4] != 'O') {
            return 4; 
        }
        
      
        int winningMove = findWinningMove(board, computerMark);
        if (winningMove != -1) {
            return winningMove;
        }
        
       
        int blockingMove = findWinningMove(board, opponentMark);
        if (blockingMove != -1) {
            return blockingMove;
        }
        

        return chooseRandomMove(board);
    }
    
    private boolean isBoardEmpty(char[] board) {
        for (char c : board) {
            if (c == 'X' || c == 'O') {
                return false;
            }
        }
        return true;
    }
    
    private boolean isSecondMove(char[] board) {
        int markedPositions = 0;
        for (char c : board) {
            if (c == 'X' || c == 'O') {
                markedPositions++;
            }
        }
        return markedPositions == 1;
    }
    
    private int chooseCorner() {
        int[] corners = {0, 2, 6, 8};
        return corners[random.nextInt(corners.length)];
    }
    
    private int findWinningMove(char[] board, char mark) {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
            {0, 4, 8}, {2, 4, 6}             
        };
        
        for (int[] pattern : winPatterns) {
            int markCount = 0;
            int emptyPos = -1;
            
            for (int pos : pattern) {
                if (board[pos] == mark) {
                    markCount++;
                } else if (board[pos] != 'X' && board[pos] != 'O') {
                    emptyPos = pos;
                }
            }
            
            if (markCount == 2 && emptyPos != -1) {
                return emptyPos;
            }
        }
        
        return -1;
    }
    
    private int chooseRandomMove(char[] board) {
        List<Integer> availableMoves = new ArrayList<>();
        
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                availableMoves.add(i);
            }
        }
        
        if (availableMoves.isEmpty()) {
            return -1; // No moves available (shouldn't happen if game logic is correct)
        }
        
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }
}