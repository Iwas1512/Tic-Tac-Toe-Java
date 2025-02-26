//Anlil Iwas 02/24/2025
//Java Tic-Tac-Toe 2 Players


package tic.tac.toe;



import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        game gameInstance = new game();

        System.out.println("Welcome to Tic-Tac-Toe!, This is a 2 player HumanvsHuman Implementation");

        do {
            gameInstance.resetGame();
            while (!gameInstance.isGameOver()) {
                System.out.println(gameInstance.getBoardDisplay());
                System.out.printf("Player " + gameInstance.getCurrentPlayer() + " enter your move (1-9): ");
                int move = getValidMove(gameInstance);
                gameInstance.makeMove(move);
            }
            System.out.println(gameInstance.getBoardDisplay());
            System.out.println(gameInstance.getResultMessage());
        } while (playAgain());

        System.out.println("Goodbye!");
        scanner.close();
    }




    //  functions for some game logic below, Consider making a possible third class to seperate Moving Logic Vs Game Logic, For exmple, a class that has getmove, move, etc.. and a class that has the winning conditions.

    private static int getValidMove(game gameInstance) { 
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.matches("[1-9]")) {
                int move = Integer.parseInt(input) - 1;
                if (gameInstance.isValidMove(move)) {
                    return move;
                }
            }
            System.out.println("Invalid move! Try again.");
        }
    }

    private static boolean playAgain() {
        while (true) {
            System.out.print("Would you like to play again (yes/no)? ");
            String input = scanner.nextLine().trim().toLowerCase(); //important here to convert to lower case to account for errors
            if (input.equals("yes")) return true;
            if (input.equals("no")) return false;
            System.out.println("That is not a valid entry!");
        }
    }
}
