package battleship;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;


public class Battleship {

    private static char leftCharCoord, rigthCharCoord;
    private static int leftIntCoord, rigthIntCoord;

    private static HashMap<Character, String[]> battleField = new HashMap<>();
    private static ArrayList<Object []> createdShipsCoordinates = new ArrayList<>();
    private static Object[][] battleShips = new Object[][]{
            {"Aircraft Carrier", 5},
            {"Battleship", 4},
            {"Submarine", 3},
            {"Cruiser", 3},
            {"Destroyer", 2}
    };

    public static void main(String[] args) {
        createBattleField(battleField);
        printBattleField(battleField);
        fillBattleField(createdShipsCoordinates, battleShips);
    }


    public static void createBattleField(HashMap<Character, String[]> battleField) {
        // create a HashMap "battleField", where 'i' is a character from 'A' to 'J'(inclusively)
        for (int i=(int) 'A'; i<=(int) 'J'; i++) {
            battleField.put((char) i, new String[] {" ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~"});
        }
    }


    public static void printBattleField(HashMap<Character, String[]> battleField) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (Character key: battleField.keySet()) {
            System.out.println(key + String.join("", battleField.get(key)));
        }
    }


    public static boolean takeCoordinates() {
        String[] coordinates;
        boolean flag = false;

        Scanner sc = new Scanner(System.in);

        try {
            coordinates = sc.nextLine().toUpperCase().split(" ");
            try {
                leftCharCoord = coordinates[0].charAt(0);
                leftIntCoord = Integer.parseInt(coordinates[0].substring(1));
                rigthCharCoord = coordinates[1].charAt(0);
                rigthIntCoord = Integer.parseInt(coordinates[1].substring(1));
            } catch (IllegalArgumentException e) {
                flag = true;
            }
        } catch (IndexOutOfBoundsException e) {
            flag = true;
        }
        return flag;
    }


    public static boolean compareCoordinates(
            ArrayList<Object []> createdShipsCoordinates,
            char leftCharCoord,
            int leftIntCoord,
            char rigthCharCoord,
            int rigthIntCoord
    ) {
        boolean answer = false;
        boolean compareIntCoord, compareCharCoord;
        int maxIntBoarder, minIntBoarder;
        int maxCharBoarder, minCharBoarder;

        for (Object[] shipsCoordinate : createdShipsCoordinates) {
            maxIntBoarder = Math.max((Integer) shipsCoordinate[1], (Integer) shipsCoordinate[3]) + 1;
            minIntBoarder = Math.min((Integer) shipsCoordinate[1], (Integer) shipsCoordinate[3]) - 1;
            compareIntCoord = (
                    leftIntCoord >= minIntBoarder && leftIntCoord <= maxIntBoarder
            ) || (
                    rigthIntCoord >= minIntBoarder && rigthIntCoord <= maxIntBoarder
            );

            maxCharBoarder = Math.max((int) (char) shipsCoordinate[0], (int) (char) shipsCoordinate[2]) + 1;
            minCharBoarder = Math.min((int) (char) shipsCoordinate[0], (int) (char) shipsCoordinate[2]) - 1;
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


    public static void fillBattleField(ArrayList<Object []> createdShipsCoordinates, Object[][] battleShips) {

        for (Object[] battleShip : battleShips) {
            while (true) {
                System.out.printf("Enter the coordinates of the %s (%d cells): \n\n", battleShip[0], battleShip[1]);

                // проверка входящих данных
                if (takeCoordinates()) {
                    System.out.println("Error! Wrong ship coordinates! Try again:");
                    continue;
                }

                int lengthOfTheShip = Math.abs(leftIntCoord - rigthIntCoord) + 1;

                if (lengthOfTheShip > (int) battleShip[1] || lengthOfTheShip < 1) {
                    System.out.printf("Error! Wrong length of the %s! Try again: \n\n", battleShip[0]);
                } else if (
                    // проверка - если корабль не в одну линию
                    (((int) leftCharCoord != (int) rigthCharCoord) && (leftIntCoord != rigthIntCoord)) ||
                    // проверка границы поля боя
                    ((int) leftCharCoord + (int) rigthCharCoord) < (int) 'A' + (int) 'A' ||
                    ((int) leftCharCoord + (int) rigthCharCoord) > (int) 'J' + (int) 'J' ||
                    (leftIntCoord + rigthIntCoord) < 2 ||
                    (leftIntCoord + rigthIntCoord) > 20
                ) {
                    System.out.println("Error! Wrong ship location! Try again:");
                } else if (compareCoordinates(createdShipsCoordinates, leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord)) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                } else {
                    for (int i=Math.min((int) leftCharCoord, (int) rigthCharCoord); i<=Math.max((int) leftCharCoord, (int) rigthCharCoord); i++) {
                        for (int j=Math.min(leftIntCoord, rigthIntCoord); j<=Math.max(leftIntCoord, rigthIntCoord); j++) {
                            battleField.get((char) i)[j-1] = " O";
                        }
                    }
                    createdShipsCoordinates.add(new Object[] {leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord});
                    break;
                }
            }
            printBattleField(battleField);
        }
    }
}
