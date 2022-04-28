package battleship;

/*
Класс Корабль
- название
- длинна
- координаты начала
- координаты конца
- проверка потоплен или нет
- получил попадание
 */
import static battleship.Battleship.battleShips;
import static battleship.Validation.*;


public class Ship {
    private final String name;
    private final int boatLength;
    private char leftCharCoord;
    private int leftIntCoord;
    private char rigthCharCoord;
    private int rigthIntCoord;
    private int hitsTaken = 0;
    private boolean isSunk;

    public Ship(String name, int boatLength) {
        this.name = name;
        this.boatLength = boatLength;
    }

    public String getName() {
        return name;
    }

    public int getBoatLength() {
        return boatLength;
    }


    public char getLeftCharCoord() {
        return leftCharCoord;
    }

    public int getLeftIntCoord() {
        return leftIntCoord;
    }

    public char getRigthCharCoord() {
        return rigthCharCoord;
    }

    public int getRigthIntCoord() {
        return rigthIntCoord;
    }

    public void setHitsTaken(int hitsTaken) {
        this.hitsTaken = hitsTaken;
    }

    public void setHitsTakens(char hitCharCoord, int hitIntCoord) {
        String message, result;

        if (takeCoordinates(battleShip) || checkBattlefieldBoundaries(false)) {
            System.out.println("Error! You entered the wrong coordinates! Try again:\n");
//                continue;
        } else {
            if (compareCoordinates(battleShips, hitCharCoord, hitIntCoord, '!', 0, 0)) {
                if (!isSunk() || checkDuplicateHits()) {
                    ++this.hitsTaken;
                }
                result = " X";
                message = "You hit a ship! Try again:\n";
            } else {
                result = " M";
                message = "You missed. Try again:\n";
            }
            battleFieldName1.get(hitCharCoord)[hitIntCoord-1] = result;
            battleFieldName2.get(hitCharCoord)[hitIntCoord-1] = result;
        }
    }

    private boolean checkDuplicateHits() {
        return false;
    }

    public int getHitsTaken() {
        return hitsTaken;
    }

    public boolean isSunk() {
        return getBoatLength() == getHitsTaken();
    }

    public void setCoord(char leftCharCoord, int leftIntCoord, char rigthCharCoord, int rigthIntCoord) {
        this.leftCharCoord = leftCharCoord;
        this.leftIntCoord = leftIntCoord;
        this.rigthCharCoord = rigthCharCoord;
        this.rigthIntCoord = rigthIntCoord;
    }
}
