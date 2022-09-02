package battleship;

import static battleship.AbstractShip.checkAllShipsAreSunk;
import static battleship.Battlefield.printBattleField;
import static battleship.Coordinates.*;
import static battleship.Validator.*;


public class Battleship {

    public static void main(String[] args) {
        Battlefield player1 = new Battlefield();
        Battlefield player2 = new Battlefield();
        Battlefield[] players = new Battlefield[]{player1, player2};

        for (Battlefield player : players){
            System.out.printf("%s, place your ships on the game field\n\n", player.getPlayerName());
            printBattleField(player.getBattleFieldWithShips());
            player.fillBattleField(player.getBattleFieldWithShips());
            pressEnter();
        }
        takeAshoot(players);
    }

    public static void takeAshoot(Battlefield[] players) {
//        System.out.println("The game starts!\n");
//        printBattleField(battleFieldWithFog);
//        System.out.println("Take a shot!\n");

        while (!checkAllShipsAreSunk()) {
            for (Battlefield player : players){
                printBattleField(player.getBattleFieldWithFog());
                System.out.println("---------------------");
                printBattleField(player.getBattleFieldWithShips());

                System.out.printf("%s, it's your turn:\n", player.getPlayerName());

                String message = "";
                String result = " X";

                if (takeCoordinates() ||
                    checkTypeOfTheCoordinates(getLengthOfTheCoordinates()) ||
                    checkBattlefieldBoundaries(false)
                ) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                } else {
                    switch (
                        compareCoordinates(
                            player.getAllShips(),
                            getPreliminaryLeftCharCoord(),
                            getPreliminaryLeftIntCoord(),
                            '!',
                            0,
                            0
                        )
                    ) {
                        case "sunk_all_ships":
                            message = "You sank the last ship. You won. Congratulations!\n";
                            break;
                        case "sunk":
                            message = "You sank a ship! Specify a new target:\n";
                            break;
                        case "true":
                            message = "You hit a ship! Try again:\n";
                            break;
                        case "false":
                            result = " M";
                            message = "You missed! Try again:\n";
                            break;
                    }
                    player.setBattleField(player.getBattleFieldWithFog(), getPreliminaryLeftCharCoord(), getPreliminaryLeftIntCoord(), result);
                    player.setBattleField(player.getBattleFieldWithShips(), getPreliminaryLeftCharCoord(), getPreliminaryLeftIntCoord(), result);
                    System.out.println(message);
                }
            }
        }
    }
}
