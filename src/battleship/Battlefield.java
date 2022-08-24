package battleship;

import java.util.HashMap;

import static battleship.Coordinates.*;
import static battleship.Validator.validator;


public class Battlefield {

    private int num = 1;
    private String playerName = "Player " + num;
    private HashMap<Character, String[]> battleFieldWithShips = new HashMap<>();
    private HashMap<Character, String[]> battleFieldWithFog = new HashMap<>();

    public Battlefield() {
        this.battleFieldWithShips = createBattleField(battleFieldWithShips);
        this.battleFieldWithFog = createBattleField(battleFieldWithFog);
        num++;
    }

    public String getPlayerName() {
        return playerName;
    }

    public HashMap<Character, String[]> getBattleFieldWithShips() {
        return battleFieldWithShips;
    }

    public HashMap<Character, String[]> getBattleFieldWithFog() {
        return battleFieldWithFog;
    }

    public void setBattleField(HashMap<Character, String[]> battleFieldName, Character key, int index, String value) {
        battleFieldName.get(key)[index-1] = value;
    }

    public HashMap<Character, String[]> createBattleField(HashMap<Character, String[]> battleFieldName) {
        // create a HashMap "battleField", where 'i' is a character from 'A' to 'J'(inclusively)
        for (int i=(int) 'A'; i<=(int) 'J'; i++) {
            battleFieldName.put((char) i, new String[] {" ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~", " ~"});
        }
        return battleFieldName;
    }


    public static void printBattleField(HashMap<Character, String[]> battleFieldName) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (Character key: battleFieldName.keySet()) {
            System.out.println(key + String.join("", battleFieldName.get(key)));
        }
        System.out.println();
    }


    public static void fillBattleField(HashMap<Character, String[]> battleFieldName) {
        for (Vessels battleShip : Vessels.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells): \n\n", battleShip.getName(), battleShip.getBoatLength());
            while (true) {
                if (!validator(battleShip)) {
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
                            battleFieldName.get((char) i)[j-1] = " O";
                        }
                    }
                    break;
                }
            }
            printBattleField(battleFieldName);
        }
    }
}
