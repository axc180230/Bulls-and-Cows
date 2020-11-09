package bullscows;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        mainBodyFinal();
    }

    static void mainBodyFinal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        int length = getIntFromUser();

        System.out.println("Input the number of possible symbols in the code:");
        int symbolAmount = getIntFromUser();

        String secretNum;
        boolean secretNumFound = false;
        int turnCounter = 0;
        int[] bullsAndCows;

        if (length < 1 || length > 36) {
            System.out.println("Error: Invalid length. Must be >= 1 and <= 36.");
        } else if (length > symbolAmount) {
            System.out.print("Error: it's not possible to generate a code with a " +
                    "length of " + length + " with " + symbolAmount + " unique symbols.");
        } else if (symbolAmount < 1 || symbolAmount > 36) {
            System.out.println(
                    "Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else {
            secretNum = createSecretString(length, symbolAmount);
            System.out.println(secretNum);
            System.out.print("The secret is prepared: ");
            System.out.print(getLengthAsAsterisks(length) + " ");
            System.out.println(getValueRangeString(symbolAmount) + ").");
            System.out.println("Okay, let's start a game!");

            while (!secretNumFound) {
                System.out.println("Turn " + turnCounter); //+ ". Answer:");

                try {
                    String userInput = scanner.next();
                    // Todo: validate user input
                    turnCounter++;

                    if (length != userInput.length()) {
                        System.out.println("Please enter a number of length " + length);
                    } else {
                        bullsAndCows = countBullsAndCows(userInput, secretNum, length);

                        // index 0 = bulls; index 1 = cows
                        int bulls = bullsAndCows[0];
                        int cows = bullsAndCows[1];

                        printResults(bulls, cows, length);
                        if (bulls == length) {
                            System.out.println("Congratulations! You guessed the secret code.");
                            secretNumFound = true;
                        }
                        System.out.println();
                    }

                } catch (Exception e) {
                    System.out.println("Something went wrong. Exception: " + e.toString());
                }
            }
        }
    }

    static String createSecretString(int length, int possibleValuesAmount) {
        // Using hashmap to efficiently prevent duplicate characters in string
        HashMap<Character, Character> distinctValues = new HashMap<>();

        // initialzing first char
        char[] finalValue = new char[length];
        char temp = getRandomChar(possibleValuesAmount);
        finalValue[0] = temp;
        distinctValues.put(temp, temp);

        // filling rest of array and tracking using hashmap
        // duplicate values cause loop to repeat until unused
        // char is generated. Not sure if improvement can
        // be made to avoid this.
        for (int i = 1; i < length; i++) {
            temp = getRandomChar(possibleValuesAmount);

            if (!distinctValues.containsKey(temp)) {
                finalValue[i] = temp;
                distinctValues.put(temp, temp);
            }else {
                i--;
            }
        }

        String secretNum = new String(finalValue);
        return secretNum;
    }

    static int getIntFromUser() {
        Scanner scanner = new Scanner(System.in);
        String input = null;
        int integer = -1;

        try {
            input = scanner.nextLine();
            integer = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Error: " + input + " isn't a valid number.");
            System.exit(0);
        }

        return integer;
    }

    static char getRandomChar(int maxPossibleValue) {
        int randomChar = 48;
        Random randNum = new Random();

        if (maxPossibleValue <= 10) {
            // Bounding between 48 and 57 since those chars
            // equal 0 - 9 characters
            randomChar = randNum.nextInt(57 - 48 + 1)+ 48;
        } else if (maxPossibleValue <= 36) {
            // 50/50 toss up if letter or number generated
            if (Math.random() < .5) {
                randomChar = randNum.nextInt(10) + 48;
            } else {
                // binding between 97 and 122 for a-z chars
                randomChar = randNum.nextInt(maxPossibleValue - 10) + 97;
            }
        }

        return (char) randomChar;
    }

    static String getValueRangeString(int maxPossibleValue) {
        String range = "";

        if (maxPossibleValue == 1) {
            range = "(0)";
        } else if (maxPossibleValue <= 10) {
            range = "(0-" + (maxPossibleValue - 1) + ")";
        } else if (maxPossibleValue == 11) {
            range = ("0-9, a");
        }
        else if (maxPossibleValue <= 36) {
            range = "(0-9, a-" + (char) (maxPossibleValue + 86) + ")";
        }

        return range;
    }

    static String getLengthAsAsterisks(int length) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < length; i++) {
            s.append("*");
        }

        return s.toString();
    }

    static int[] countBullsAndCows(String userInput, String secretNumber, int length) {
        // using int array to return two values, first ([0]) bulls, second ([1]) cows
        int[] bnc = new int[2];
        int bulls = 0;
        int cows = 0;

        for (int digitPlace = 0; digitPlace < length; digitPlace++) {
            if (userInput.charAt(digitPlace) == secretNumber.charAt(digitPlace)) {
                bulls++;
            } else if (secretNumber.contains(String.valueOf(userInput.charAt(digitPlace)))) {
                cows++;
            }
        }

        bnc[0] = bulls;
        bnc[1] = cows;
        return bnc.clone();
    }

    static void printResults(int bulls, int cows, int length) {
        if (bulls == 0) {
            if (cows == 0) {
                System.out.println("Grade: None.");
            } else {
                System.out.println("Grade: " + cows + " cow(s).");
            }
        } else if (bulls == length) {
            System.out.println("Grade: " + length + " bull(s).");
        } else {
            if (cows == 0) {
                System.out.println("Grade: " + bulls + " bull(s).");
            } else {
                System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");
            }
        }
    }

}


