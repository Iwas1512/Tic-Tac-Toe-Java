//Anlil Iwas 02/24/2025
//Java Tic-Tac-Toe 2 Players


package tic.tac.toe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        game gameInstance = new game();

        System.out.println("Welcome to Anlil's Tic-Tac-Toe! I added documentation to the code so you can track scores! ENJOY");

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
            

            System.out.println("The current log is:");
            System.out.println(gameInstance.getGameLog());
            
        } while (playAgain(gameInstance));


        saveGameLogToFile(gameInstance);
        
        System.out.println("Goodbye, I hope you enjoyed!");
        scanner.close();
    }


    private static void saveGameLogToFile(game gameInstance) {
        System.out.println("Writing the game log to disk. Please see game.txt for the final statistics!");
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write(gameInstance.getGameLogForFile());
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game log: " + e.getMessage());
        }
    }

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

    private static boolean playAgain(game gameInstance) {
        while (true) {
            System.out.print("Would you like to play again (yes/no)? ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                // Inform the user who will go first in the next game
                char nextPlayer = gameInstance.getCurrentPlayer();
                System.out.println("Great! This time " + nextPlayer + " will go first!");
                return true;
            }
            if (input.equals("no")) return false;
            System.out.println("That is not a valid entry!");
        }
    }
}