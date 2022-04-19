package battleship;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;


public class Battleship {

    private static char leftCharCoord, rigthCharCoord;
    private static int leftIntCoord, rigthIntCoord;

    private static HashMap<Character, String[]> battleFieldWithShips = new HashMap<>();
    private static HashMap<Character, String[]> battleFieldWithFog = new HashMap<>();
    private static ArrayList<Object []> createdShipsCoordinates = new ArrayList<>();

    enum Vessels {
        AIRCRAFT_CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        SUBMARINE("Submarine", 3),
        CRUISER("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        final String name;
        final int boatLength;

        Vessels(String name, int boatLength) {
            this.name = name;
            this.boatLength = boatLength;
        }

        public String getName() {
            return name;
        }

        public int getBoatLength() {
            return boatLength;
        }
    }


    public static void main(String[] args) {
        createBattleField(battleFieldWithShips);
        createBattleField(battleFieldWithFog);
        printBattleField(battleFieldWithShips);
        fillBattleField(battleFieldWithShips);
        takeAshoot(battleFieldWithFog, battleFieldWithShips);
    }


    public static void createBattleField(HashMap<Character, String[]> battleFieldName) {
        // create a HashMap "battleField", where 'i' is a character from 'A' to 'J'(inclusively)
        for (int i=(int) 'A'; i<=(int) 'J'; i++) {
            battleFieldName.put((char) i, new String[] {" ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~"});
        }
    }


    public static void printBattleField(HashMap<Character, String[]> battleFieldName) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (Character key: battleFieldName.keySet()) {
            System.out.println(key + String.join("", battleFieldName.get(key)));
        }
        System.out.println();
    }


    public static boolean takeCoordinates() {
        // input data validation
        String[] coordinates;
        boolean flag = false;

        Scanner sc = new Scanner(System.in);

        try {
            coordinates = sc.nextLine().toUpperCase().split(" ");
            System.out.println();
            if (coordinates.length == 1) {
                try {
                    leftCharCoord = coordinates[0].charAt(0);
                    leftIntCoord = Integer.parseInt(coordinates[0].substring(1));
                } catch (IllegalArgumentException e) {
                    flag = true;
                }
            } else {
                try {
                    leftCharCoord = coordinates[0].charAt(0);
                    leftIntCoord = Integer.parseInt(coordinates[0].substring(1));
                    rigthCharCoord = coordinates[1].charAt(0);
                    rigthIntCoord = Integer.parseInt(coordinates[1].substring(1));
                } catch (IllegalArgumentException e) {
                    flag = true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            flag = true;
        }
        return flag;
    }


    public static boolean checkLengthOfTheShip(Vessels battleShip) {
        int lengthOfTheShip = (
            (int) leftCharCoord == (int) rigthCharCoord ?
                    Math.abs(leftIntCoord - rigthIntCoord) + 1 : Math.abs((int) leftCharCoord - (int) rigthCharCoord) + 1
        );
        return lengthOfTheShip != battleShip.getBoatLength();
    }


    public static boolean checkShapeOfTheShip() {
        // validation if the ship is not in one line
        return ((int) leftCharCoord != (int) rigthCharCoord) && (leftIntCoord != rigthIntCoord);
    }


    public static boolean checkBattlefieldBoundaries(boolean twoCoordnates) {
        // validation of the battlefield boundaries
        boolean result;
        if (twoCoordnates) {
            result = (int) leftCharCoord * (int) rigthCharCoord < (int) 'A' * (int) 'A' ||
                    (int) leftCharCoord * (int) rigthCharCoord > (int) 'J' * (int) 'J' ||
                    leftIntCoord * rigthIntCoord < 1 ||
                    leftIntCoord * rigthIntCoord > 100;
        } else {
            result = (int) leftCharCoord < (int) 'A' ||
                    (int) leftCharCoord > (int) 'J' ||
                    leftIntCoord < 1 ||
                    leftIntCoord > 10;
        }
        return result;
    }


    public static boolean compareCoordinates(
        char leftCharCoord,
        int leftIntCoord,
        char rigthCharCoord,
        int rigthIntCoord,
        int additionalBoundary
    ) {
        boolean answer = false;
        boolean compareIntCoord, compareCharCoord;
        int maxIntBoarder, minIntBoarder;
        int maxCharBoarder, minCharBoarder;

        for (Object[] shipsCoordinate : createdShipsCoordinates) {
            maxIntBoarder = Math.max((Integer) shipsCoordinate[1], (Integer) shipsCoordinate[3]) + additionalBoundary;
            minIntBoarder = Math.min((Integer) shipsCoordinate[1], (Integer) shipsCoordinate[3]) - additionalBoundary;
            compareIntCoord = (
                    leftIntCoord >= minIntBoarder && leftIntCoord <= maxIntBoarder
            ) || (
                    rigthIntCoord >= minIntBoarder && rigthIntCoord <= maxIntBoarder
            );

            maxCharBoarder = Math.max((int) (char) shipsCoordinate[0], (int) (char) shipsCoordinate[2]) + additionalBoundary;
            minCharBoarder = Math.min((int) (char) shipsCoordinate[0], (int) (char) shipsCoordinate[2]) - additionalBoundary;
            compareCharCoord = (
                    (int) leftCharCoord >= minCharBoarder && (int) leftCharCoord <= maxCharBoarder
            ) || (
                    (int) rigthCharCoord >= minCharBoarder && (int) rigthCharCoord <= maxCharBoarder
            );

            if (compareIntCoord && compareCharCoord) {
                answer = true;
                break;
            }
        }
        return answer;
    }


    public static boolean validator(Vessels battleShip) {
        boolean flag = false;

        if (takeCoordinates()) {
            flag = true;
            System.out.println("Error! Wrong ship coordinates! Try again:\n");
        } else if (checkLengthOfTheShip(battleShip)) {
            flag = true;
            System.out.printf("Error! Wrong length of the %s! Try again: \n\n", battleShip.getName());
        } else if (
            checkShapeOfTheShip() || checkBattlefieldBoundaries(true)
        ) {
            flag = true;
            System.out.println("Error! Wrong ship location! Try again:\n");
        } else if (compareCoordinates(leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord, 1)) {
            flag = true;
            System.out.println("Error! You placed it too close to another one. Try again:\n");
        }

        return flag;
    }


    public static void fillBattleField(HashMap<Character, String[]> battleFieldName) {
        for (Vessels battleShip : Vessels.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells): \n\n", battleShip.getName(), battleShip.getBoatLength());
            while (true) {
                if (!validator(battleShip)) {
                    for (int i=Math.min((int) leftCharCoord, (int) rigthCharCoord); i<=Math.max((int) leftCharCoord, (int) rigthCharCoord); i++) {
                        for (int j=Math.min(leftIntCoord, rigthIntCoord); j<=Math.max(leftIntCoord, rigthIntCoord); j++) {
                            battleFieldName.get((char) i)[j-1] = " O";
                        }
                    }
                    createdShipsCoordinates.add(new Object[] {leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord});
                    break;
                }
            }
            printBattleField(battleFieldName);
        }
    }


    public static void takeAshoot(HashMap<Character, String[]> battleFieldName1, HashMap<Character, String[]> battleFieldName2) {
        String message, result;
        System.out.println("The game starts!\n");
        printBattleField(battleFieldName1);
        System.out.println("Take a shot!\n");
        while (true) {
            if (takeCoordinates() || checkBattlefieldBoundaries(false))
            {
                System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                continue;
            }
            if (compareCoordinates(leftCharCoord, leftIntCoord, '!', 0, 0)) {
                result = " X";
                message = "You hit a ship!\n";
            } else {
                result = " M";
                message = "You missed!\n";
            }
            battleFieldName1.get(leftCharCoord)[leftIntCoord-1] = result;
            battleFieldName2.get(leftCharCoord)[leftIntCoord-1] = result;
            printBattleField(battleFieldName1);
            System.out.println(message);
            printBattleField(battleFieldName2);
            break;
        }
    }
}
