package battleship;


import static battleship.Coordinates.*;
import static battleship.Validator.*;


public class Battleship {
    public static void main(String[] args) {
        Player[] players = {new Player(), new Player()};

        for (Player player : players){
            System.out.printf("%s, place your ships on the game field\n\n", player.getPlayerName());
            player.getBattleFieldWithShips().printBattleField();
            player.getBattleFieldWithShips().fillBattleField(player);
            pressEnter(); // исключить вызов во второй раз
        }
        takeAshoot(players);
    }

    public static void takeAshoot(Player[] players) {
        boolean flag = false;

//        System.out.println("The game starts!\n");

        while (!flag) {
            for (int i = 0, j = 1; i<players.length; i++, j--){
                Player currentPlayer = players[i];
                Player enemy = players[j];

                currentPlayer.getBattleFieldWithFog().printBattleField();
                System.out.println("---------------------");
                currentPlayer.getBattleFieldWithShips().printBattleField();

                System.out.printf("%s, it's your turn:\n", currentPlayer.getPlayerName());

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
                            enemy,
                            getPreliminaryLeftCharCoord(),
                            getPreliminaryLeftIntCoord(),
                            '!',
                            0,
                            0
                        )
                    ) {
                        case "sunk_all_ships":
                            message = "You sank the last ship. You won. Congratulations!\n";
                            flag = true;
                            break;
                        case "sunk":
                            message = "You sank a ship! Specify a new target:\n";
                            break;
                        case "true":
                            message = "You hit a ship!\n";
                            break;
                        case "false":
                            result = " M";
                            message = "You missed!\n";
                            break;
                    }
                    currentPlayer.getBattleFieldWithFog().setBattleField(
                            getPreliminaryLeftCharCoord(),
                            getPreliminaryLeftIntCoord(),
                            result
                    );
                    enemy.getBattleFieldWithShips().setBattleField(
                            getPreliminaryLeftCharCoord(),
                            getPreliminaryLeftIntCoord(),
                            result
                    );
                    System.out.println(message);
                }
                if(flag){
                    break;
                } else {
                    pressEnter();
                }
            }
        }
    }
}
