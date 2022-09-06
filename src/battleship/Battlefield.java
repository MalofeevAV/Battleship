package battleship;


import java.util.HashMap;
import java.util.HashSet;

import static battleship.Coordinates.*;
import static battleship.Validator.validator;

public class Battlefield {
    private static int num = 1;
    private AbstractShip[] allShips = new AbstractShip[5];
    private String playerName;
    private HashMap<Character, String[]> battleFieldWithShips = new HashMap<>();
    private HashMap<Character, String[]> battleFieldWithFog = new HashMap<>();
    private static final Object[][] battleships = {
            {"Aircraft Carrier", 5},
            {"Battleship", 4},
            {"Submarine", 3},
            {"Cruiser", 3},
            {"Destroyer", 2}
    };

    public Battlefield() {
        int i = 0;
        this.playerName = "Player " + num++;
        this.battleFieldWithShips = createBattleField(battleFieldWithShips);
        this.battleFieldWithFog = createBattleField(battleFieldWithFog);
        for (Object[] battleship : battleships) {
            this.allShips[i++] = new AbstractShip(
                String.valueOf(battleship[0]),
                (int) battleship[1],
                new char[2],
                new int[2],
                new HashSet<String>()
            );
        }
    }

    public AbstractShip[] getAllShips() {
        return allShips;
    }

    public static int getLengthOfAllShips() {
        return battleships.length;
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


    public void fillBattleField(HashMap<Character, String[]> battleFieldName) {
        for (AbstractShip battleShip : allShips) {
            System.out.printf("Enter the coordinates of the %s (%d cells): \n\n", battleShip.getName(), battleShip.getBoatLength());
            while (true) {
                if (!validator(battleShip, allShips)) {
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
