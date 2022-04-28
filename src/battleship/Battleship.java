package battleship;


public class Battleship {

    private static int i = 0;
    protected static Ship[] battleShips = new Ship[5];

    private static Object[][] vessels = new Object[][]{
            {"Aircraft Carrier", 5},
            {"Battleship", 4},
            {"Submarine", 3},
            {"Cruiser", 3},
            {"Destroyer", 2}
    };

    public static void main(String[] args) {

        for (Object[] vessel : vessels) {
            battleShips[i++] = new Ship((String) vessel[0], (int) vessel[1]);
        }

        Battlefield battleFieldWithShips = new Battlefield();
        Battlefield battleFieldWithFog = new Battlefield();

        battleFieldWithShips.printBattleField();
        battleFieldWithShips.fillBattleField(battleShips);
        takeAshoot(battleFieldWithFog, battleFieldWithShips);
    }


    public static void takeAshoot(Battlefield battleFieldWithFog, Battlefield battleFieldWithShips) {
        System.out.println("The game starts!\n");
        battleFieldWithFog.printBattleField();
        System.out.println("Take a shot!\n");

        while (true) {
            setHitsTakens();
            battleFieldWithFog.printBattleField();
            System.out.println(message);
        }
    }
}
