package battleship;


import java.util.HashSet;


public class AbstractShip{
    private final String name;
    private final int boatLength;
    private char[] charCoordinates;
    private int[] intCoordinates;
    private HashSet<String> hits;

    public AbstractShip(String name, int boatLength, char[] charCoordinates, int[] intCoordinates, HashSet<String> hits) {
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
