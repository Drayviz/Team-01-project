import java.util.Scanner;

public class Game {
    private HumanPlayer human;
    private AIPlayer ai;
    private GameState gameState;
    private Map map;
    private MapInfo level;
    private Pieces pieceList;

    public void setup() {
        System.out.println("Welcome to <insert game name here>!");
        if (level.getTurns() = 1) {
            Map map1 = new Map(map); //need a copy constructor?


            //USED TO PLACE PIECES (this shouldn't run unless this is the first iteration...)
            for (int a = 0; a < 3; ai++) {
                Entity hPiece = new Entity(10, 10, 50, 1, 1, 5);
                pieceList.addHumanPieces();
            }
        /*  Entity h1 = new Entity(10, 10, 50, 1, 1, 5);
            Entity h2 = new Entity(10, 10, 50, 1, 1, 5);
            Entity h3 = new Entity(10, 10, 50, 1, 1, 5); */

            for (int b = 0; b < level.getNumOfEnemies(); b++) {
                Entity ePiece = new Entity(10, 10, 50, 0, 1, 5);
                pieceList.addAIPieces();
            }

            //below is temporary, only used to place AI pieces in simple positions
            for (int c=0; c < map1.length(); c++) {
                for (Entity e:pieceList.getAIPieces()) {
                    int proxy = 1; //just so we can keep moving the AI backwards...
                    if (c == map1.length() - proxy) {
                        map1[c][1] = 0; //indicating that on that piece of the map, there is an enemy piece
                        proxy = proxy + 1;
                    }
                }
            }


            Scanner input = new Scanner(System.in);
            for (Entity h:getHumanPieces()) {
                System.out.println("Where would you like to place your pieces? ");
                map1.toString() //so they can see the board
                int place = input.nextInt();
                map1[d-1][0] = h;
            }
            GameState game = new gameState(map);
        }
    }

    public void play() {
        int level = level.getTurns();
        while (level > 0) {
            System.out.println("AI TURN");
            //ai.getTurn();
            //GameState gameState = new GameState(???);
            System.out.println("HUMAN TURN");
            human.getTurn();
            GameState gameState = new GameState(???);
            level = level - 1;
        }
    }
}