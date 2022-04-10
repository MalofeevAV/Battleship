package battleship;

import java.util.Scanner;
import java.util.HashMap;


public class Battleship {

    private static HashMap<Character, String[]> battleField = new HashMap<Character, String[]>();

    public static void main(String[] args) {

        // create HashMap battleField, where 'i' is a character from 'A' to 'J'(inclusively)
        for (int i=65; i<75; i++) {
            battleField.put((char) i, new String[] {" ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~"});
        }

        printBattelField(battleField);
        takeCoordinates();

    }


    public static void printBattelField(HashMap<Character, String[]> battleField) {

        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (Character key: battleField.keySet()) {
            System.out.println(key + String.join("", battleField.get(key)));
        }
    }


    public static boolean compareCoordinates(Object[][] createdShipsCoordinates, char leftCharCoord, int leftIntCoord, char rigthCharCoord, int rigthIntCoord) {

        boolean answer = false;
        boolean compareIntCoord, compareCharCoord;
        int maxIntBoarder, minIntBoarder;
        int maxCharBoarder, minCharBoarder;

        for (Object[] shipsCoordinate : createdShipsCoordinates) {
            if (createdShipsCoordinates[0] == null) {
                break;
            }

//            Object a = shipsCoordinate[1];
//            Object b = shipsCoordinate[3];

            maxIntBoarder = Math.max((Integer) shipsCoordinate[1], (Integer) shipsCoordinate[3]) + 1;
            minIntBoarder = Math.min((Integer) shipsCoordinate[1], (Integer) shipsCoordinate[3]) - 1;
            compareIntCoord = (leftIntCoord > minIntBoarder && leftIntCoord < maxIntBoarder) || (rigthIntCoord > minIntBoarder && rigthIntCoord < maxIntBoarder);

            maxCharBoarder = Math.max((int) shipsCoordinate[0], (int) shipsCoordinate[2]) + 1;
            minCharBoarder = Math.min((int) shipsCoordinate[0], (int) shipsCoordinate[2]) - 1;
            compareCharCoord = ((int) leftCharCoord > minCharBoarder && (int) leftCharCoord < maxCharBoarder) || ((int) rigthCharCoord > minCharBoarder && (int) rigthCharCoord < maxCharBoarder);

            if (compareIntCoord || compareCharCoord) {
                answer = true;
                break;
            }
        }
        return answer;
    }


    public static void takeCoordinates() {

        Scanner sc = new Scanner(System.in);

        char leftCharCoord, rigthCharCoord;
        int leftIntCoord, rigthIntCoord;
        int counter = 0;

        String[] coordinates;

        Object[][] createdShipsCoordinates = new Object[5][];

        Object[][] battleShips = new Object[][]{
                {"Aircraft Carrier", 5},
                {"Battleship", 4},
                {"Submarine", 3},
                {"Cruiser", 3},
                {"Destroyer", 2}
        };

        for (Object[] battleShip : battleShips) {
            while (true) {
                System.out.printf("Enter the coordinates of the %s (%d cells): \n\n", battleShip[0], battleShip[1]);

                coordinates = sc.nextLine().split(" ");

                leftCharCoord = coordinates[0].charAt(0);
                leftIntCoord = Integer.parseInt(coordinates[0].substring(1));
                rigthCharCoord = coordinates[1].charAt(0);
                rigthIntCoord = Integer.parseInt(coordinates[1].substring(1));

                if (leftCharCoord == rigthCharCoord || leftIntCoord == rigthIntCoord) {
                    int lengthOfTheShip = Math.abs(leftIntCoord - rigthIntCoord) + 1;

                    // обработать случай, когда числа равны, а буквы - нет
                    if (lengthOfTheShip > (int) battleShip[1] || lengthOfTheShip < 0) {
                        System.out.printf("Error! Wrong length of the %s! Try again: \n\n", battleShip[0]);
                        // Error! Wrong length of the Submarine! Try again: -- check lenght of the ship

                        // проверить срабатывание условия
                    } else if (((int) leftCharCoord + (int) rigthCharCoord) < (int) 'A' + (int) 'A' || ((int) leftCharCoord + (int) rigthCharCoord) > (int) 'J' + (int) 'J' || (leftIntCoord + rigthIntCoord) < 2 || (leftIntCoord + rigthIntCoord) > 20) {
                        System.out.println("Error! Wrong ship location! Try again:");
                        // Error! Wrong ship location! Try again: -- check boarders of battlefield

                        // проверить работу функции
                    } else if (compareCoordinates(createdShipsCoordinates, leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord)) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        // Error! You placed it too close to another one. Try again: -- check the position of the ship, before write down the " O"
                    } else {
                        for (int i=(int) leftCharCoord; i<=(int) rigthCharCoord; i++) {
                            for (int j=Math.min(leftIntCoord, rigthIntCoord); j<=Math.max(leftIntCoord, rigthIntCoord); j++) {
                                battleField.get((char) i)[j-1] = " O";
                            }
                        }
                    }
                    createdShipsCoordinates[counter++] = new Object[] {leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord};
                    break;
                }
            }
            printBattelField(battleField);
        }
    }

}

/*
F3 F7
A1 D1
J10 J8
*/
