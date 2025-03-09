package tic.tac.toe;


public class game {
    private char[] board;
    private char currentPlayer;
    private boolean gameOver;
    private String resultMessage;

    public game() {
        resetGame();
    }

    public void resetGame() {
        board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        currentPlayer = 'X';
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
            "%n %c | %c | %c %n---+---+---%n %c | %c | %c %n---+---+---%n %c | %c | %c %n", //formating the board neatly
            board[0], board[1], board[2],
            board[3], board[4], board[5],
            board[6], board[7], board[8]
        );
    }

    public String getResultMessage() {
        return resultMessage;
    }

    private void checkWin() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6} //all possible winning combos in simple Tic-Tac-Toe
        };

        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == currentPlayer &&
                board[pattern[1]] == currentPlayer &&
                board[pattern[2]] == currentPlayer) {
                gameOver = true;
                resultMessage = "Player " + currentPlayer + " wins!";
                return;
            }
        }

        for (char c : board) {
            if (c != 'X' && c != 'O') return;
        }

        gameOver = true;
        resultMessage = "It's a draw!"; // draw scenario
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
}
}