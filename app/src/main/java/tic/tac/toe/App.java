//Anlil Iwas
//Java Tic-Tac-Toe 2 Players and AI

package tic.tac.toe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        game gameInstance = new game();

        System.out.println("Welcome to Anlil's Tic-Tac-Toe! I added documentation to the code so you can track scores! ENJOY");
        
   
        selectGameMode(gameInstance);

        do {
            gameInstance.resetGame();
            while (!gameInstance.isGameOver()) {
                System.out.println(gameInstance.getBoardDisplay());
                
                if (gameInstance.isComputerGame() && gameInstance.isComputerTurn()) {
                    System.out.println("Computer (" + gameInstance.getComputerMark() + ") is making a move...");
                    int computerMove = gameInstance.getComputerMove();
                    System.out.println("Computer chose position " + (computerMove + 1));
                    gameInstance.makeMove(computerMove);
                } else {
                    System.out.printf("Player " + gameInstance.getCurrentPlayer() + " enter your move (1-9): ");
                    int move = getValidMove(gameInstance);
                    gameInstance.makeMove(move);
                }
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
    
    private static void selectGameMode(game gameInstance) {
        System.out.println("What kind of game would you like to play?");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");
        
        int choice = 0;
        boolean validChoice = false;
        
        while (!validChoice) {
            System.out.print("What is your selection? ");
            String input = scanner.nextLine().trim();
            
            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 3) {
                    validChoice = true;
                } else {
                    System.out.println("Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        switch (choice) {
            case 1:
               
                gameInstance.setComputerGame(false, ' ');
                System.out.println("Great! Human vs. Human game selected.");
                break;
            case 2:
                
                gameInstance.setComputerGame(true, 'O');
                System.out.println("Great! You will go first as X.");
                break;
            case 3:
                
                gameInstance.setComputerGame(true, 'X');
                System.out.println("Great! The computer will go first as X.");
                break;
        }
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
                
                char nextPlayer = gameInstance.getCurrentPlayer();
                System.out.println("Great! This time " + nextPlayer + " will go first!");
                return true;
            }
            if (input.equals("no")) return false;
            System.out.println("That is not a valid entry!");
        }
    }
}