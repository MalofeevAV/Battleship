package battleship;

import java.util.Scanner;

import static battleship.Battleship.battleShips;


public class Validation {

    private static char leftCharCoord, rigthCharCoord;
    private static int leftIntCoord, rigthIntCoord;

    public static boolean takeCoordinates(Ship battleShip) {
        // input data validation
        String[] coordinates;
        boolean flag = false;

        Scanner sc = new Scanner(System.in);

        try {
            coordinates = sc.nextLine().toUpperCase().split(" ");
            System.out.println();
            if (coordinates.length == 1) {
                try {
                    shotCharCoord = coordinates[0].charAt(0);
                    shotIntCoord = Integer.parseInt(coordinates[0].substring(1));
                    battleShip.setCoord(shotCharCoord, shotIntCoord);
                } catch (IllegalArgumentException e) {
                    flag = true;
                }
            } else {
                try {
                    leftCharCoord = coordinates[0].charAt(0);
                    leftIntCoord = Integer.parseInt(coordinates[0].substring(1));
                    rigthCharCoord = coordinates[1].charAt(0);
                    rigthIntCoord = Integer.parseInt(coordinates[1].substring(1));
                    battleShip.setCoord(leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord);
                } catch (IllegalArgumentException e) {
                    flag = true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            flag = true;
        }
        return flag;
    }


    public static boolean checkLengthOfTheShip(Ship battleShip) {
        int lengthOfTheShip = (
                (int) battleShip.getLeftCharCoord() == (int) battleShip.getRigthCharCoord() ?
                        Math.abs(battleShip.getLeftIntCoord() - battleShip.getRigthIntCoord()) + 1 :
                        Math.abs((int) battleShip.getLeftCharCoord() - (int) battleShip.getRigthCharCoord()) + 1
        );
        return lengthOfTheShip != battleShip.getBoatLength();
    }


    public static boolean checkShapeOfTheShip(Ship battleShip) {
        // validation if the ship is not in one line
        return (
                (int) battleShip.getLeftCharCoord() != (int) battleShip.getRigthCharCoord()) &&
                (battleShip.getLeftIntCoord() != battleShip.getRigthIntCoord()
        );
    }


    public static boolean checkBattlefieldBoundaries(Ship battleShip, boolean twoCoordnates) {
        // validation of the battlefield boundaries
        boolean result;
        if (twoCoordnates) {
            result = (int) battleShip.getLeftCharCoord() * (int) battleShip.getRigthCharCoord() < (int) 'A' * (int) 'A' ||
                    (int) battleShip.getLeftCharCoord() * (int) battleShip.getRigthCharCoord() > (int) 'J' * (int) 'J' ||
                    battleShip.getLeftIntCoord() * battleShip.getRigthIntCoord() < 1 ||
                    battleShip.getLeftIntCoord() * battleShip.getRigthIntCoord() > 100;
        } else {
            result = (int) battleShip.getLeftCharCoord() < (int) 'A' ||
                    (int) battleShip.getLeftCharCoord() > (int) 'J' ||
                    battleShip.getLeftIntCoord() < 1 ||
                    battleShip.getLeftIntCoord() > 10;
        }
        return result;
    }


    public static boolean compareCoordinates(
            Ship[] battleShips,
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

        for (Ship battleShip : battleShips) {
            maxIntBoarder = Math.max(battleShip.getLeftIntCoord(), battleShip.getRigthIntCoord()) + additionalBoundary;
            minIntBoarder = Math.min(battleShip.getLeftIntCoord(), battleShip.getRigthIntCoord()) - additionalBoundary;
            compareIntCoord = (
                    leftIntCoord >= minIntBoarder && leftIntCoord <= maxIntBoarder
            ) || (
                    rigthIntCoord >= minIntBoarder && rigthIntCoord <= maxIntBoarder
            );

            maxCharBoarder = Math.max(battleShip.getLeftCharCoord(), battleShip.getRigthCharCoord()) + additionalBoundary;
            minCharBoarder = Math.min(battleShip.getLeftCharCoord(), battleShip.getRigthCharCoord()) - additionalBoundary;
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


    public static boolean validator(Ship battleShip) {
        boolean flag = true;

        if (takeCoordinates(battleShip)) {
            flag = false;
            System.out.println("Error! Wrong ship coordinates! Try again:\n");
        } else if (checkLengthOfTheShip(battleShip)) {
            flag = false;
            System.out.printf("Error! Wrong length of the %s! Try again: \n\n", battleShip.getName());
        } else if (
                checkShapeOfTheShip(battleShip) || checkBattlefieldBoundaries(battleShip,true)
        ) {
            flag = false;
            System.out.println("Error! Wrong ship location! Try again:\n");
        } else if (
                compareCoordinates(battleShips,
                                   battleShip.getLeftCharCoord(),
                                   battleShip.getLeftIntCoord(),
                                   battleShip.getRigthCharCoord(),
                                   battleShip.getRigthIntCoord(),
                                    1)
        ) {
            flag = false;
            System.out.println("Error! You placed it too close to another one. Try again:\n");
        }

        return flag;
    }
}
