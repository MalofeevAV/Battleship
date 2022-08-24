package battleship;

import java.util.Scanner;

import static battleship.Coordinates.*;
import static battleship.Vessels.checkAllShipsAreSunk;
import static battleship.Vessels.setSunkShips;


public class Validator {

    public static boolean validator(Vessels battleShip) {
        boolean flag = false;

        if (takeCoordinates() || checkTypeOfTheCoordinates(getLengthOfTheCoordinates())) {
            flag = true;
            System.out.println("Error! Wrong ship coordinates! Try again:\n");
        } else if (checkLengthOfTheShip(battleShip)) {
            flag = true;
            System.out.printf("Error! Wrong length of the %s! Try again: \n\n", battleShip.getName());
        } else if (checkShapeOfTheShip() || checkBattlefieldBoundaries(true)) {
            flag = true;
            System.out.println("Error! Wrong ship location! Try again:\n");
        } else if (compareCoordinates(getPreliminaryLeftCharCoord(),
            getPreliminaryLeftIntCoord(),
            getPreliminaryRigthCharCoord(),
            getPreliminaryRigthIntCoord(),
            1).equals("true"))
             {
                flag = true;
                System.out.println("Error! You placed it too close to another one. Try again:\n");
             }
        return flag;
    }


    public static boolean checkLengthOfTheShip(Vessels battleShip) {
        int lengthOfTheShip = (
            (int) getPreliminaryLeftCharCoord() == (int) getPreliminaryRigthCharCoord() ?
            Math.abs(getPreliminaryLeftIntCoord() - getPreliminaryRigthIntCoord()) + 1 :
            Math.abs((int) getPreliminaryLeftCharCoord() - (int) getPreliminaryRigthCharCoord()) + 1
        );
        return lengthOfTheShip != battleShip.getBoatLength();
    }


    public static boolean checkShapeOfTheShip() {
        // validation if the ship is not in one line
        return ((int) getPreliminaryLeftCharCoord() != (int) getPreliminaryRigthCharCoord() &&
                getPreliminaryLeftIntCoord() != getPreliminaryRigthIntCoord()
        );
    }


    public static boolean checkBattlefieldBoundaries(boolean twoCoordnates) {
        int PreliminaryRigthCharCoord, LeftCharCoordBoundary, RigthCharCoordBoundary;
        boolean result;

        // validation of the battlefield boundaries
        if (twoCoordnates) {
            PreliminaryRigthCharCoord = getPreliminaryRigthCharCoord();
            LeftCharCoordBoundary = 'A' * 'A';
            RigthCharCoordBoundary = 'J' * 'J';
        } else {
            PreliminaryRigthCharCoord = 1;
            LeftCharCoordBoundary = 'A';
            RigthCharCoordBoundary = 'J';
        }

        result = (int) getPreliminaryLeftCharCoord() * PreliminaryRigthCharCoord < LeftCharCoordBoundary ||
                (int) getPreliminaryLeftCharCoord() * PreliminaryRigthCharCoord > RigthCharCoordBoundary ||
                getPreliminaryLeftIntCoord() * getPreliminaryRigthIntCoord() < 1 ||
                getPreliminaryLeftIntCoord() * getPreliminaryLeftIntCoord() > 100;

        return result;
    }


    public static String compareCoordinates(
        char leftCharCoord,
        int leftIntCoord,
        char rigthCharCoord,
        int rigthIntCoord,
        int additionalBoundary
    ) {
        String answer = "false";
        boolean compareIntCoord, compareCharCoord;

        for (Vessels battleShip : Vessels.values()) {
            if (battleShip.getRigthIntCoord() != 0 &&
                battleShip.getLeftIntCoord() != 0 &&
                battleShip.getRigthCharCoord() != '\u0000' &&
                battleShip.getLeftCharCoord() != '\u0000') {

                compareIntCoord = checkBoarder(battleShip.getLeftIntCoord(), battleShip.getRigthIntCoord(), leftIntCoord,
                        rigthIntCoord, additionalBoundary);
                compareCharCoord = checkBoarder(battleShip.getLeftCharCoord(), battleShip.getRigthCharCoord(), leftCharCoord,
                        rigthCharCoord, additionalBoundary);

                if (compareIntCoord && compareCharCoord) {
                    answer = "true";
                    if (additionalBoundary == 0) {
                        battleShip.setHits(leftCharCoord, leftIntCoord);
                        if (checkIfSunck(battleShip)){
                            answer = "sunk";
                            setSunkShips(battleShip.name());
                            if(checkAllShipsAreSunk()){
                                answer = "sunk_all_ships";
                            }
                        }
                    }
                    break;
                }
            }
        }
        return answer;
    }


    private static boolean checkBoarder(
        int battleshipLeftCoord,
        int battleshipRigthCoord,
        int leftCoord,
        int rigthCoord,
        int additionalBoundary
    ) {
        boolean compareCoord;
        int maxBoarder, minBoarder;

        maxBoarder = Math.max(battleshipLeftCoord, battleshipRigthCoord) + additionalBoundary;
        minBoarder = Math.min(battleshipLeftCoord, battleshipRigthCoord) - additionalBoundary;

        if (additionalBoundary == 0) {
            compareCoord = (minBoarder <= leftCoord && leftCoord <= maxBoarder);
        } else {
            compareCoord = (
                minBoarder <= leftCoord && leftCoord <= maxBoarder
            ) || (
                minBoarder <= rigthCoord && rigthCoord <= maxBoarder
            );
        }
        return compareCoord;
    }


    public static boolean checkIfSunck(Vessels battleShip) {
        return battleShip.isSunk();
    }

    public static void pressEnter(){
        Scanner sc = new Scanner(System.in);
        String coordinates = " ";
        while (!coordinates.equals("")){
            System.out.println("Press Enter and pass the move to another player\n...");
            coordinates = sc.nextLine();
        }
//        sc.close();
    }
}
