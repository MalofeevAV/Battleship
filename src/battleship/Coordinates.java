package battleship;

import java.util.Scanner;


public class Coordinates {

    private static char leftCharCoord, rigthCharCoord;
    private static int leftIntCoord, rigthIntCoord;

    private static String[] coordinates;


    public static char getPreliminaryLeftCharCoord() {
        return leftCharCoord;
    }

    public static char getPreliminaryRigthCharCoord() {
        return rigthCharCoord;
    }

    public static int getPreliminaryLeftIntCoord() {
        return leftIntCoord;
    }

    public static int getPreliminaryRigthIntCoord() {
        return rigthIntCoord;
    }

    public static int getLengthOfTheCoordinates() {
        return coordinates.length;
    }


    public static boolean takeCoordinates() {
        // input data validation
        boolean flag = false;
        Scanner sc = new Scanner(System.in);

        try {
            coordinates = sc.nextLine().trim().toUpperCase().split(" ");
            System.out.println();
        } catch (IndexOutOfBoundsException e) {
                flag = true;
            }
        return flag;
    }


    public static boolean checkTypeOfTheCoordinates(int lengthOfTheCoordinates) {
        boolean answer = false;

        try {
            if (lengthOfTheCoordinates == 1) {
                leftCharCoord = coordinates[0].charAt(0);
                leftIntCoord = Integer.parseInt(coordinates[0].substring(1));
            } else {
                leftCharCoord = coordinates[0].charAt(0);
                leftIntCoord = Integer.parseInt(coordinates[0].substring(1));
                rigthCharCoord = coordinates[1].charAt(0);
                rigthIntCoord = Integer.parseInt(coordinates[1].substring(1));
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            answer = true;
        }
        return answer;
    }
}
