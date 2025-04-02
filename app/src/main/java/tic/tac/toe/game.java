package tic.tac.toe;

public class game {
    private char[] board;
    private char currentPlayer;
    private boolean gameOver;
    private String resultMessage;
    
    private int playerXWins;
    private int playerOWins;
    private int ties;
    private char previousLoser = ' ';

    public game() {
        resetGame();
        
        playerXWins = 0;
        playerOWins = 0;
        ties = 0;
    }

    public void resetGame() {
        board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        
        if (previousLoser == ' ') {
            currentPlayer = 'X';
        } else {
            currentPlayer = previousLoser;
        }
        
        gameOver = false;
        resultMessage = "";
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isValidMove(int move) {
        return board[move] != 'X' && board[move] != 'O'; 
    }

    public void makeMove(int move) {
        board[move] = currentPlayer;
        checkWin();
        if (!gameOver) {
            switchPlayer();
        }
    }

    public String getBoardDisplay() {
        return String.format(
            "%n %c | %c | %c %n---+---+---%n %c | %c | %c %n---+---+---%n %c | %c | %c %n",
            board[0], board[1], board[2],
            board[3], board[4], board[5],
            board[6], board[7], board[8]
        );
    }

    public String getResultMessage() {
        return resultMessage;
    }
    
    public String getGameLog() {
        return String.format(
            "Player X Wins   %d%nPlayer O Wins   %d%nTies            %d",
            playerXWins, playerOWins, ties
        );
    }
    
    public String getGameLogForFile() {
        return String.format(
            "Tic-Tac-Toe Game Statistics\n\n" +
            "Player X Wins: %d\n" +
            "Player O Wins: %d\n" +
            "Ties:          %d\n\n" +
            "Total Games:   %d",
            playerXWins, playerOWins, ties, playerXWins + playerOWins + ties
        );
    }

    private void checkWin() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == currentPlayer &&
                board[pattern[1]] == currentPlayer &&
                board[pattern[2]] == currentPlayer) {
                gameOver = true;
                resultMessage = "Player " + currentPlayer + " wins!";
                
                if (currentPlayer == 'X') {
                    playerXWins++;
                    previousLoser = 'O';
                } else {
                    playerOWins++;
                    previousLoser = 'X';
                }
                return;
            }
        }

        // Check for draw
        boolean boardFull = true;
        for (char c : board) {
            if (c != 'X' && c != 'O') {
                boardFull = false;
                break;
            }
        }

        if (boardFull) {
            gameOver = true;
            resultMessage = "It's a draw!";
            ties++;
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}