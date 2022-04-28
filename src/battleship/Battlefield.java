package battleship;

import java.util.HashMap;

import static battleship.Validation.validator;


public class Battlefield {

    private HashMap<Character, String[]> battleField = new HashMap<>();


    public Battlefield() {
        // create a HashMap "battleField", where 'i' is a character from 'A' to 'J'(inclusively)
        for (int i=(int) 'A'; i<=(int) 'J'; i++) {
            this.battleField.put((char) i, new String[] {" ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~"});
        }
    }


    public void printBattleField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (Character key: this.battleField.keySet()) {
            System.out.println(key + String.join("", battleField.get(key)));
        }
        System.out.println();
    }


    public void fillBattleField(Ship[] battleShips) {
        int minCharCoord, maxCharCoord, minIntCoord, maxIntCoord;

        for (Ship battleShip : battleShips) {
            System.out.printf("Enter the coordinates of the %s (%d cells): \n\n", battleShip.getName(), battleShip.getBoatLength());
            while (true) {
                if (validator(battleShip)) {
                    minCharCoord = Math.min(battleShip.getLeftCharCoord(), battleShip.getRigthCharCoord());
                    maxCharCoord = Math.max(battleShip.getLeftCharCoord(), battleShip.getRigthCharCoord());
                    for (int i = minCharCoord; i <= maxCharCoord; i++) {
                        for (int j=Math.min(battleShip.getLeftIntCoord(), battleShip.getRigthIntCoord()); j<=Math.max(battleShip.getLeftIntCoord(), battleShip.getRigthIntCoord()); j++) {
                            battleField.get((char) i)[j-1] = " O";
                        }
                    }
//                    battleShip.setCoord();
//                    createdShipsCoordinates.add(new Object[] {leftCharCoord, leftIntCoord, rigthCharCoord, rigthIntCoord});
                    break;
                }
            }
            printBattleField();
        }
    }

}
