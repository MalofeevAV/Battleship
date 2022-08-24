package battleship;

import java.util.HashSet;


enum Vessels {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5, new char[2], new int[2], new HashSet<String>()),
    BATTLESHIP("Battleship", 4, new char[2], new int[2], new HashSet<String>()),
    SUBMARINE("Submarine", 3, new char[2], new int[2], new HashSet<String>()),
    CRUISER("Cruiser", 3, new char[2], new int[2], new HashSet<String>()),
    DESTROYER("Destroyer", 2, new char[2], new int[2], new HashSet<String>());

    private final String name;
    private final int boatLength;
    private char[] charCoordinates;
    private int[] intCoordinates;
    private static HashSet<String> SunkShips = new HashSet<String>();
    private HashSet<String> hits;

    Vessels(String name, int boatLength, char[] charCoordinates, int[] intCoordinates, HashSet<String> hits) {
        this.name = name;
        this.boatLength = boatLength;
        this.charCoordinates = charCoordinates;
        this.intCoordinates = intCoordinates;
        this.hits = hits;
    }

    public char getLeftCharCoord() {
        return charCoordinates[0];
    }

    public char getRigthCharCoord() {
        return charCoordinates[1];
    }

    public int getLeftIntCoord() {
        return intCoordinates[0];
    }

    public int getRigthIntCoord() {
        return intCoordinates[1];
    }

    public static void setSunkShips(String ship) {
        SunkShips.add(ship);
    }

    public static boolean checkAllShipsAreSunk() {
        return SunkShips.size() == Vessels.values().length;
    }

    public String getName() {
        return name;
    }

    public int getBoatLength() {
        return boatLength;
    }

    public void setCharCoordinates(char leftCharCoord, char rigthCharCoord) {
        charCoordinates = new char[]{leftCharCoord, rigthCharCoord};
    }

    public void setIntCoordinates(int leftIntCoordinates, int rigthIntCoordinates) {
        intCoordinates = new int[]{leftIntCoordinates, rigthIntCoordinates};
    }

    public void setHits(char leftCharCoord, int leftIntCoord) {
        this.hits.add(String.valueOf(leftCharCoord) + String.valueOf(leftIntCoord));
    }

    public boolean isSunk() {
        return getBoatLength() <= hits.size();
    }
}
