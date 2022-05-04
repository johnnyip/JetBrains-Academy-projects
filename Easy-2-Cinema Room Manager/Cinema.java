package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);

    static private int rows;
    static private int cols;
    static private char[][] seats;

    static private int ticketIncome = 0;

    static private int ticketsSold = 0;

    public static void main(String[] args) {
        // Write your code here

        init(); //get inputs
        boolean exit = false;

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    printSeats();  //print cinema seats
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("\nWrong input!\n");
                    break;
            }
        } while (!exit);
    }

    static void init() {
        //Reading inputs - seats col and row
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = scanner.nextInt();

        //init 2d array
        seats = new char[rows][cols];
        for (char[] seats_row : seats) {
            Arrays.fill(seats_row, 0, cols, 'S');
        }

        System.out.println();
    }

    static void printSeats() {
        System.out.println("\nCinema:");
        //First line
        for (int i = 0; i <= cols; i++) {
            if (i != 0) {
                System.out.print(i + " ");
            } else {
                System.out.print("  ");
            }
        }
        System.out.println("");

        //Remaining
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");

            //Seats in a row
            for (int j = 0; j < cols; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void buyTicket() {

        boolean success = false;
        do{
            System.out.println("\nEnter a row number:");
            int buy_rows = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int buy_cols = scanner.nextInt();

            if (buy_rows > rows || buy_cols > cols){
                System.out.println("\nWrong input!");
            }else if (seats[buy_rows-1][buy_cols-1] == 'B'){
                System.out.println("\nThat ticket has already been purchased!");
            }else{
                //Calculate price
                calculatePrice(buy_rows, buy_cols);

                //Mark as reserved and print
                seats[buy_rows - 1][buy_cols - 1] = 'B';
                ticketsSold++;
                success = true;
            }
        }while (!success);


    }

    static int getTotalIncome() {
        int total_amount = 0;

        if ((rows * cols) < 60) {
            total_amount = (rows * cols * 10);
        } else {
            //First half
            total_amount = (rows / 2) * cols * 10;
            //Second half
            total_amount += (rows - (rows / 2)) * cols * 8;
        }

        return total_amount;
    }

    static void calculatePrice(int buy_rows, int buy_col) {
        int priceOfTicket = 0;

        if ((rows * cols) < 60) {
            priceOfTicket = 10;
        } else {
            if (buy_rows <= (rows / 2)) {
                //First half
                priceOfTicket = 10;
            } else {
                //Second half
                priceOfTicket += 8;
            }
        }

        System.out.println("Ticket price: $" + priceOfTicket);
        System.out.println();

        ticketIncome += priceOfTicket;
    }

    static void statistics() {
        System.out.println("\nNumber of purchased tickets: " + ticketsSold);
        double percentage = (double) ticketsSold / (rows * cols) * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + ticketIncome);
        System.out.println("Total income: $" + getTotalIncome());
        System.out.println();
    }
}