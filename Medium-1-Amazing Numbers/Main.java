package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n");
        printWelcomeMessage();

        boolean exit = false;

        while (!exit) {
            System.out.print("\nEnter a request: ");
            String[] input = scanner.nextLine().split(" ");

            if (input.length == 1) {
                if (input[0] == "") {
                    printWelcomeMessage();
                } else {
                    long targetNumber = 0;
                    try {
                        targetNumber = Long.parseLong(input[0]);
                    } catch (Exception e) {
                        targetNumber = -1;
                    }

                    if (targetNumber < 0) {
                        System.out.println("\nThe first parameter should be a natural number or zero.\n");
                    } else if (targetNumber == 0) {
                        exit = true;
                        System.out.println("\nGoodbye!");
                    } else {
                        System.out.println("\nProperties of " + formattedNumber(targetNumber));
                        System.out.println("        even: " + checkEven(targetNumber));
                        System.out.println("         odd: " + checkOdd(targetNumber));
                        System.out.println("        buzz: " + checkBuzzNumber(targetNumber));
                        System.out.println("        duck: " + checkDuckNumber(targetNumber));
                        System.out.println(" palindromic: " + checkPalindromic(targetNumber));
                        System.out.println("      gapful: " + checkGapful(targetNumber));
                        System.out.println("         spy: " + checkSpy(targetNumber));
                        System.out.println("      square: " + checkSquare(targetNumber));
                        System.out.println("       sunny: " + checkSunny(targetNumber));
                        System.out.println("     jumping: " + checkJumping(targetNumber));
                        boolean isHappy = isHappy(targetNumber);
                        System.out.println("       happy: " + isHappy);
                        System.out.println("         sad: " + !isHappy);

                        System.out.println();
                    }
                }
            } else {
                long firstParam = Long.parseLong(input[0]);
                long secondParam = Long.parseLong(input[1]);

                List<String> completeProps = new ArrayList<>();
                List<String> props = new ArrayList<>();
                List<String> propsExclude = new ArrayList<>();

                List<String> notValid = new ArrayList<>();
                List<String> mutuallyExclusive = new ArrayList<>();

                if (input.length > 2) {
                    for (String propName_ : Arrays.copyOfRange(input, 2, input.length)) {
                        String propName = propName_.toUpperCase();

                        //check valid name
                        if (!checkPropertyName(propName)) {
                            notValid.add(propName);
                        }

                        //check mutually exclusive
                        for (String prevProp : completeProps) {
                            if (mutuallyExclusive.size() == 0 && checkMutualExclusive(propName, prevProp)) {
                                mutuallyExclusive.add(prevProp);
                                mutuallyExclusive.add(propName);
                            }

                        }

                        completeProps.add(propName);
                        if (propName.charAt(0) == '-') {
                            propsExclude.add(propName.substring(1));
                        } else if (!props.contains(propName)) {
                            props.add(propName);
                        }

                    }

                }

//                System.out.println("props: "+props);
//                System.out.println("propsExclude: "+propsExclude);
//                System.out.println("notValid: "+notValid);
//                System.out.println("mutuallyExclusive: "+mutuallyExclusive);

                //check
                if (firstParam < 0) {
                    System.out.println("\nThe first parameter should be a natural number or zero.\n");

                } else if (secondParam <= 0) {
                    System.out.println("The second parameter should be a natural number.\n");

                } else if (notValid.size() == 1) {
                    System.out.println("The property " + notValid.toString() + " is wrong.\n" +
                            "Available properties: " + Arrays.stream(Properties.values()).map(item -> item.name()).collect(Collectors.toList())  );

                } else if (notValid.size() > 1) {
                    System.out.println("The properties " + notValid.toString() + " are wrong.\n" +
                            "Available properties: " + Arrays.stream(Properties.values()).map(item -> item.name()).collect(Collectors.toList())  );

                } else if (mutuallyExclusive.size() > 0) {
                    System.out.println("The request contains mutually exclusive properties: " + mutuallyExclusive.toString() + "\n" +
                            "There are no numbers with these properties.");

                } else {
                    System.out.println();
                    long index = firstParam;
                    int counter = 0;
                    boolean fulfilled = false;

                    while (!fulfilled) {
                        List<String> currentProps = new ArrayList<>();

                        if (checkEven(index)) currentProps.add("even");
                        if (checkOdd(index)) currentProps.add("odd");
                        if (checkBuzzNumber(index)) currentProps.add("buzz");
                        if (checkDuckNumber(index)) currentProps.add("duck");
                        if (checkPalindromic(index)) currentProps.add("palindromic");
                        if (checkGapful(index)) currentProps.add("gapful");
                        if (checkSpy(index)) currentProps.add("spy");
                        if (checkSquare(index)) currentProps.add("square");
                        if (checkSunny(index)) currentProps.add("sunny");
                        if (checkJumping(index)) currentProps.add("jumping");
                        currentProps.add((isHappy(index)) ? "happy" : "sad");

                        List<String> currentPropsClone = List.copyOf(currentProps);

                        //Match
                        boolean shouldExcluded = false;

                        //check if it contains unwanted props
                        for (String prop : propsExclude) {
                            if (currentProps.contains(prop.toLowerCase())) {
                                shouldExcluded = true;
                            }
                        }

                        currentProps.retainAll(props.stream().map(item -> item.toLowerCase()).collect(Collectors.toList()));

//                        System.out.println("shouldExclude: "+shouldExcluded);
                        if ((props.size() == 0 || currentProps.size() == props.size()) && !shouldExcluded) {
                            System.out.print("             " + formattedNumber(index) + " is " +
                                    currentPropsClone.toString().substring(1, currentPropsClone.toString().length() - 1));
                            System.out.println();
                            counter++;
                        }

                        index++;
                        if (counter == secondParam) {
                            fulfilled = true;
                        }

//                        break;
                    }
                }

            }
        }

    }

    public static void printWelcomeMessage() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    public static boolean checkPropertyName(String propName) {
        try {
            if (propName.charAt(0) == '-') propName = propName.substring(1);
            Properties.valueOf(propName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String formattedNumber(long targetNumber) {
        String value = String.valueOf(targetNumber);
        int length = value.length();
        String result = "";

        int index = 0;

        for (int pos = length; pos > 0; pos--) {
            result += value.charAt(index);
            if (pos % 3 == 1 && pos != 1) {
                result += ",";
            }
            index++;
        }

        return result;

    }

    public static boolean checkOdd(long targetNumber) {
        return targetNumber % 2 == 1;
    }

    public static boolean checkEven(long targetNumber) {
        return targetNumber % 2 == 0;
    }

    public static boolean checkMutualExclusive(String prop1, String prop2) {
//        System.out.println("checking: "+prop1+", "+prop2);
        if (prop1.equals(prop2)) return false;
        if ((prop1.charAt(0) != '-' && prop2.charAt(0) != '-') ||
                (prop1.charAt(0) == '-' && prop2.charAt(0) == '-')) {

            String prop = prop1 + prop2;
            if (prop1.charAt(0) == '-' && prop2.charAt(0) == '-') {
                prop = prop1.substring(1)+prop2.substring(1);
            }

            if (prop.contains("EVEN") && prop.contains("ODD")) {
                return true;
            } else if (prop.contains("DUCK") && prop.contains("SPY")) {
                return true;
            } else if (prop.contains("SUNNY") && prop.contains("SQUARE")) {
                return true;
            } else if (prop.contains("HAPPY") && prop.contains("SAD")) {
                return true;
            }
        }

        if (prop1.contains(prop2) || prop2.contains(prop1)) {
            return true;
        }

        //check duplicated
        return false;

    }

    public static boolean checkBuzzNumber(long targetNumber) {
        return targetNumber % 10 == 7 || targetNumber % 7 == 0;
    }

    public static boolean checkDuckNumber(long targetNumber) {
        return String.valueOf(targetNumber).contains("0");

    }

    public static boolean checkPalindromic(long targetNumber) {
        String inputToString = String.valueOf(targetNumber);

        for (int i = 0; i < inputToString.length(); i++) {
            if (inputToString.charAt(i) != inputToString.charAt(inputToString.length() - i - 1)) return false;
        }

        return true;


    }

    public static boolean checkSpy(long targetNumber) {
        long sum = 0;
        long product = 1;

        for (char item : String.valueOf(targetNumber).toCharArray()) {
            Long longOfItem = Long.valueOf(String.valueOf(item));
            sum += longOfItem;
            product *= longOfItem;
        }

        return sum == product;
    }

    public static boolean checkGapful(long targetNumber) {
        String targetNumberString = String.valueOf(targetNumber);
        if (targetNumberString.length() < 3) {
            return false;
        }

        long divisor = Long.valueOf(targetNumberString.charAt(0) + "" + targetNumberString.charAt(targetNumberString.length() - 1));
        return ((targetNumber % divisor) == 0);
    }

    public static boolean checkSquare(long targetNumber) {
        double sqrt = Math.sqrt(targetNumber);
        return (sqrt - Math.floor(sqrt)) == 0;
    }

    public static boolean checkSunny(long targetNumber) {
        return checkSquare(targetNumber + 1);
    }

    public static boolean checkJumping(long targetNumber) {
        String lastPosition = "";
        for (String item : String.valueOf(targetNumber).split("")) {
            if (lastPosition != "" && (Math.abs(Long.valueOf(lastPosition) - Long.valueOf(item)) != 1)) {
                return false;
            }

            lastPosition = item;
        }

        return true;
    }

    public static boolean isHappy(long targetNumber) {
        boolean keepChecking = true;
        boolean result = false;

        long prevResult = targetNumber;
        List<Long> resultHistory = new ArrayList<>();

        while (keepChecking) {
            String[] digits = String.valueOf(prevResult).split("");
            long sum = 0;

            for (String digit : digits) {
                Long longValue = Long.valueOf(digit);
                sum += Math.pow(longValue, 2);
            }

            if (sum == 1) {
                keepChecking = false;
                result = true;
            }
            if (sum == targetNumber) {
                keepChecking = false;
            }
            if (resultHistory.contains(sum)) {
                keepChecking = false;
            }

            prevResult = sum;
            resultHistory.add(sum);
        }

        return result;
    }


}
