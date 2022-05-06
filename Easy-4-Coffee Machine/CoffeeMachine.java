package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static int INVENTORY_WATER = 400;
    public static int INVENTORY_MILK = 540;
    public static int INVENTORY_BEANS = 120;
    public static int INVENTORY_CUPS = 9;
    public static int INVENTORY_MONEY = 550;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        do {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String option = scanner.nextLine();
            switch (option) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printStatusOfMachine();
                    break;
                case "exit":
                    exit = true;
            }
        } while (!exit);


//        System.out.println("Starting to make a coffee");
//        System.out.println("Grinding coffee beans");
//        System.out.println("Boiling water");
//        System.out.println("Mixing boiled water with crushed coffee beans");
//        System.out.println("Pouring coffee into the cup");
//        System.out.println("Pouring some milk into the cup");
//        System.out.println("Coffee is ready!");

//        System.out.println("Write how many cups of coffee you will need:");
//        int cups = scanner.nextInt();
//
//        calculateCups(machineInventory, cups);


//
//        System.out.printf("For %d cups of coffee you will need:\n", cups);
//        System.out.printf("%d ml of water\n", cups * cup_water);
//        System.out.printf("%d ml of milk\n", cups * cup_milk);
//        System.out.printf("%d g of coffee beans\n", cups * cup_beans);

    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water the coffee machine has: ");
        INVENTORY_WATER = scanner.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        INVENTORY_MILK = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        INVENTORY_BEANS = scanner.nextInt();

    }

    public static void calculateCups(int[] inventory, int cups) {
        //0 = water, 1 = milk, 2 = beans
        int[] requiredPerCup = {200, 50, 15};

        int cupsAvailable = 0;
        cupsAvailable = Math.min((inventory[0] / requiredPerCup[0]), (inventory[1] / requiredPerCup[1]));
        cupsAvailable = Math.min(cupsAvailable, (inventory[2] / requiredPerCup[2]));

        if (cups == cupsAvailable) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (cups < cupsAvailable) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)\n", cupsAvailable - cups);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee\n", cupsAvailable);
        }
    }

    public static void printStatusOfMachine() {
        System.out.println("\nThe coffee machine has:");

        System.out.printf("%d ml of water\n", INVENTORY_WATER);
        System.out.printf("%d ml of milk\n", INVENTORY_MILK);
        System.out.printf("%d g of coffee beans\n", INVENTORY_BEANS);
        System.out.printf("%d disposable cups\n", INVENTORY_CUPS);
        System.out.printf("$%d of money\n", INVENTORY_MONEY);

        System.out.println();
    }

    public static void buy() {
        Scanner scanner = new Scanner(System.in);
        int[] req_espresso = {250, 0, 16, 4};
        int[] req_latte = {350, 75, 20, 7};
        int[] req_cappuccino = {200, 100, 12, 6};

        int[] chosenOption = {};
        boolean back = false;

        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                chosenOption = req_espresso;
                break;
            case "2":
                chosenOption = req_latte;
                break;
            case "3":
                chosenOption = req_cappuccino;
                break;
            case "back":
                back = true;
                break;
        }

        if (!back) {

            if (INVENTORY_WATER - chosenOption[0] < 0) {
                System.out.println("Sorry, not enough water!");
            } else if (INVENTORY_MILK - chosenOption[1] < 0) {
                System.out.println("Sorry, not enough milk!");
            } else if (INVENTORY_BEANS - chosenOption[2] < 0) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (INVENTORY_CUPS == 0) {
                System.out.println("Sorry, not enough cups!");
            } else {

                //Have enough resources
                System.out.println("I have enough resources, making you a coffee!");
                INVENTORY_WATER -= chosenOption[0];
                INVENTORY_MILK -= chosenOption[1];
                INVENTORY_BEANS -= chosenOption[2];
                INVENTORY_CUPS--;
                INVENTORY_MONEY += chosenOption[3];

            }
        }

        System.out.println();

    }

    public static void fill() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWrite how many ml of water you want to add: ");
        INVENTORY_WATER += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        INVENTORY_MILK += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        INVENTORY_BEANS += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        INVENTORY_CUPS += scanner.nextInt();

        System.out.println();
    }

    public static void take() {

        System.out.println("I gave you $" + INVENTORY_MONEY);

        INVENTORY_MONEY = 0;

        System.out.println();
    }

}
