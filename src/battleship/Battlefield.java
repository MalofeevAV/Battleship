package battleship;


import java.util.HashMap;
import java.util.HashSet;

import static battleship.Coordinates.*;
import static battleship.Validator.validator;

public class Battlefield {
//    private static int num = 1;
//    private AbstractShip[] allShips = new AbstractShip[5];
//    private String playerName;
    private HashMap<Character, String[]> battleField = new HashMap<>();
//    private HashMap<Character, String[]> battleFieldWithFog = new HashMap<>();
//    private HashSet<String> SunkShips = new HashSet<String>();
//    private static final Object[][] battleships = {
//        {"Aircraft Carrier", 5},
//        {"Battleship", 4},
//        {"Submarine", 3},
//        {"Cruiser", 3},
//        {"Destroyer", 2}
//    };

    public Battlefield() {
        this.battleField = createBattleField();
//        int i = 0;
//        this.playerName = "Player " + num++;
//        this.battleFieldWithShips = createBattleField(battleFieldWithShips);
//        this.battleFieldWithFog = createBattleField(battleFieldWithFog);
//        for (Object[] battleship : battleships) {
//            this.allShips[i++] = new AbstractShip(
//                String.valueOf(battleship[0]),
//                (int) battleship[1],
//                new char[2],
//                new int[2],
//                new HashSet<String>()
//            );
//        }
    }

//    public AbstractShip[] getAllShips() {
//        return allShips;
//    }

//    public HashSet<String> getSunkShips() {
//        return SunkShips;
//    }

//    public void setSunkShips(String ship) {
//        SunkShips.add(ship);
//    }

//    public boolean checkAllShipsAreSunk() {
//        return SunkShips.size() == getLengthOfAllShips();
//    }

//    public static int getLengthOfAllShips() {
//        return battleships.length;
//    }

//    public String getPlayerName() {
//        return playerName;
//    }

//    public HashMap<Character, String[]> getBattleFieldWithShips() {
//        return battleFieldWithShips;
//    }

//    public HashMap<Character, String[]> getBattleFieldWithFog() {
//        return battleFieldWithFog;
//    }

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
