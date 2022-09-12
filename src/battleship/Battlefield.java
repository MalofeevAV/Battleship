package battleship;


import java.util.HashMap;

import static battleship.Coordinates.*;
import static battleship.Validator.validator;

public class Battlefield {
    private HashMap<Character, String[]> battleField = new HashMap<>();


    public Battlefield() {
        this.battleField = createBattleField();
    }

    public void setBattleField(Character key, int index, String value) {
        battleField.get(key)[index-1] = value;
    }

    public HashMap<Character, String[]> createBattleField() {
        // create a HashMap "battleField", where 'i' is a character from 'A' to 'J'(inclusively)
        for (int i=(int) 'A'; i<=(int) 'J'; i++) {
            battleField.put((char) i, new String[] {" ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~"});
        }
        return battleField;
    }


    public void printBattleField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (Character key: battleField.keySet()) {
            System.out.println(key + String.join("", battleField.get(key)));
        }
        System.out.println();
    }


    public void fillBattleField(Player currentPlayer) {
        for (AbstractShip battleShip : currentPlayer.getAllShips()) {
            System.out.printf("Enter the coordinates of the %s (%d cells): \n\n", battleShip.getName(), battleShip.getBoatLength());
            while (true) {
                if (!validator(battleShip, currentPlayer)) {
                    battleShip.setCharCoordinates(getPreliminaryLeftCharCoord(), getPreliminaryRigthCharCoord());
                    battleShip.setIntCoordinates(getPreliminaryLeftIntCoord(), getPreliminaryRigthIntCoord());
                    for (int i=Math.min(battleShip.getLeftCharCoord(), battleShip.getRigthCharCoord());
                         i<=Math.max(battleShip.getLeftCharCoord(), battleShip.getRigthCharCoord());
                         i++
                    ) {
                        for (int j=Math.min(battleShip.getLeftIntCoord(), battleShip.getRigthIntCoord());
                             j<=Math.max(battleShip.getLeftIntCoord(), battleShip.getRigthIntCoord());
                             j++
                        ) {
                            battleField.get((char) i)[j-1] = " O";
                        }
                    }
                    break;
                }
            }
            printBattleField();
        }
    }
}
