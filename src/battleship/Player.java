package battleship;

import java.util.HashMap;
import java.util.HashSet;

public class Player {
    private int i = 0;
    private static int num = 1;
    private final String playerName;
    private Battlefield battleFieldWithShips, battleFieldWithFog;
    private HashSet<String> sunkShips;
    private AbstractShip[] allShips = new AbstractShip[5];
    private static final Object[][] battleships = {
        {"Aircraft Carrier", 5},
        {"Battleship", 4},
        {"Submarine", 3},
        {"Cruiser", 3},
        {"Destroyer", 2}
    };

    public Player() {
        this.playerName = "Player " + num++;
        this.battleFieldWithShips = new Battlefield();
        this.battleFieldWithFog = new Battlefield();
        this.sunkShips = new HashSet<String>();
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

    public HashSet<String> getSunkShips() {
        return sunkShips;
    }

    public void setSunkShips(String ship) {
        sunkShips.add(ship);
    }

    public static int getLengthOfAllShips() {
        return battleships.length;
    }

    public boolean checkAllShipsAreSunk() {
        return sunkShips.size() == getLengthOfAllShips();
    }

    public String getPlayerName() {
        return playerName;
    }

    public Battlefield getBattleFieldWithShips() {
        return battleFieldWithShips;
    }

    public Battlefield getBattleFieldWithFog() {
        return battleFieldWithFog;
    }
}
