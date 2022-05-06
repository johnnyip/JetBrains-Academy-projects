package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here

//        //Read cell input
//        System.out.print("Enter cells: ");
//        String cells = scanner.nextLine();

        //init and print game board
        char[][] game = new char[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(game[i],' ');
        }
        printBoard(game);

        //prepare for game rounds
        boolean xTurn = true;
        boolean gameFinish = false;

        do {
            boolean error = true;

            do {
                error = false;
                System.out.print("Enter the coordinates: ");
                String coordinateInput = scanner.nextLine();

                if (coordinateInput.length() != 3) {
                    error = true;
                    System.out.println("You should enter numbers!");
                }

                for (int i = 0; i < coordinateInput.length() && !error; i++) {
                    char word = coordinateInput.charAt(i);
                    if ((i == 0 || i == 2) && !(word >= 49 && word <= 51)) {
                        error = true;
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                    if (i == 1 && word != 32) {
                        error = true;
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                }

                if (!error) {
                    //check and place on board
                    int vertical = coordinateInput.charAt(0) - 49;
                    int horizontal = coordinateInput.charAt(2) - 49;
                    if (game[vertical][horizontal] != ' ') {
                        error = true;
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        game[vertical][horizontal] = xTurn ? 'X' : 'O';
                    }
                }
            } while (error);

            printBoard(game);

            xTurn = !xTurn;
            gameFinish = analyseGame(game);
        } while (!gameFinish);

    }

    public static char[][] convertInto2DArray(String cells) {
        char[][] game = new char[3][3];

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = cells.charAt(counter);
                counter++;
            }
        }

        return game;
    }

    public static void printBoard(char[][] game) {
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", game[0][0], game[0][1], game[0][2]);
        System.out.printf("| %s %s %s |\n", game[1][0], game[1][1], game[1][2]);
        System.out.printf("| %s %s %s |\n", game[2][0], game[2][1], game[2][2]);
        System.out.println("---------");
    }

    public static boolean analyseGame(char[][] game) {
        boolean endGame = false;

        //Checking for impossible moves
        int numX = 0;
        int numO = 0;
        int num_ = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char pos = game[i][j];
                if (pos == 'X') {
                    numX++;
                } else if (pos == 'O') {
                    numO++;
                } else if (pos == ' ') {
                    num_++;
                }
            }
        }

        if (Math.abs(numX - numO) > 1) {
            System.out.println("Impossible");
        }

        //Checking winner
        int resultCount = 0;
        char winner = 'n';

        //Checking win, for horizontal
        if (game[0][0] == game[0][1] && game[0][0] == game[0][2] && game[0][0] != ' ') {
            resultCount++;
            winner = game[0][0];
        }
        if (game[1][0] == game[1][1] && game[1][0] == game[1][2] && game[1][0] != ' ') {
            resultCount++;
            winner = game[1][0];
        }
        if (game[2][0] == game[2][1] && game[2][0] == game[2][2] && game[2][0] != ' ') {
            resultCount++;
            winner = game[2][0];
        }

        //Checking win, for vertical
        if (game[0][0] == game[1][0] && game[0][0] == game[2][0] && game[0][0] != ' ') {
            resultCount++;
            winner = game[0][0];
        }
        if (game[0][1] == game[1][1] && game[0][1] == game[2][1] && game[0][1] != ' ') {
            resultCount++;
            winner = game[0][1];

        }
        if (game[0][2] == game[1][2] && game[0][2] == game[2][2] && game[0][2] != ' ') {
            resultCount++;
            winner = game[0][2];
        }

        //Checking win, for cross
        if (game[0][2] == game[1][1] && game[0][2] == game[2][0] && game[0][2] != ' ') {
            resultCount++;
            winner = game[0][2];
        }
        if (game[0][0] == game[1][1] && game[0][0] == game[2][2] && game[2][2] != ' ') {
            resultCount++;
            winner = game[0][0];

        }

        if (resultCount == 1) {
            System.out.println(winner + " wins");
            return true;
        } else if (resultCount > 1) {
            System.out.println("Impossible");
            return true;
        }

        //Checking for Draw
        if (num_ != 0) {
            //game not finished
            return false;
        } else {
            System.out.println("Draw");
            return true;
        }

    }
}
