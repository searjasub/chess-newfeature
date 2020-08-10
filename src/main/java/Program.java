import Chess.ChessGame;
import Chess.Tuple;
import Console.InputHandler;
import Console.BoardDisplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Program {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        boolean isValid = false;
        System.out.println("\nWelcome to PRO250");

        while (!isValid) {
            System.out.println("Let's get started by selecting which game you want to play.");
            System.out.println("[0] - Regular Chess Game");
            System.out.println("[1] - CHESS 960");

            String option = reader.readLine();
            switch (option) {
                case "0":
                    startChessGame();
                    isValid = true;
                    break;
                case "1":
                    startChess960Game();
                    isValid = true;
                    break;
                default:
                    System.out.println("That's not a valid option");
                    isValid = false;
                    break;
            }
        }
        main(args);
    }

    private static void startChess960Game() {

    }

    public static void startChessGame() throws IOException {
        InputHandler handler = new InputHandler();

        ChessGame game = new ChessGame();

        BoardDisplay.clearConsole();
        BoardDisplay.printBoard(game.getBoard());

        while (!game.isFinished()) {
            System.out.println("Enter move (ex. A2-A3): ");
            String input = reader.readLine();

            if (input.equals("EXIT")){
                break;
            }

            if (!handler.isValid(input)) {
                System.out.println("Invalid input!");
                System.out.println("Valid input is in form: A2-A3");
            } else {
                Tuple from = handler.getFrom(input);
                Tuple to = handler.getTo(input);

                boolean movePlayed = game.playMove(from, to);
                if (!movePlayed)
                    System.out.println("Illegal move!");
                else {
                    BoardDisplay.clearConsole();
                    BoardDisplay.printBoard(game.getBoard());
                }
            }
        }

        System.out.println("Game has finished. Thanks for playing.");
    }
}
