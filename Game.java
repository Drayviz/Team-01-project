import java.util.Scanner;
import java.util.ArrayList;
import java.util.Array;

public class Game {
    private HumanPlayer human;
    private AIPlayer ai;
    private GameState gameState;
    private Map map;
    private MapInfo level;
    private Pieces pieceLists;

    public ArrayList<Pieces> placeHumanPieces() {
        Scanner input = new Scanner(System.in);
        for (Entity h:pieceLists.getHumanPieces()) {
            System.out.println("Where would you like to place your pieces? ");
            map.toString(); //so they can see the board
            int place = input.nextInt();
            map[place-1][1] = h; //Placing the entity itself in the <piece> index of the map
        }
    }

    //THIS IS TEMPORARY. THIS IS JUST THE EASIEST WAY TO PLACE AI PIECES; WE WILL PLACE THEM STRATEGICALLY IN THE FUTURE
    public ArrayList<Pieces> placeAIPieces() {
        int proxy = 1; //just so we can keep moving the AI backwards...
        for (int c=0; c < map.length(); c++) {
            for (Entity e:pieceLists.getAIPieces()) {
                map[map.length()-proxy][1] = 0; //indicating that on that piece of the map, there is an enemy piece
                proxy = proxy + 1;
            }
        }
    }

    public void setup() {
        System.out.println("Welcome to <insert game name here>!");
        if (level.getTurns() == 1) { //only runs when it's the first turn
            Map map = new Map(map); //need a copy constructor?
            ArrayList<Entity> humanPieces = pieceLists.getHumanPieces();
            ArrayList<Entity> aiPieces = pieceLists.getAIPieces();
            GameState game = new gameState(map);
        }
        
    }

    public void play() {
        int turnCount = level.getTurns();
        while (turnCount > 0) {
            System.out.println("AI TURN");
            ai.getTurn();
            //GameState gameState = new GameState(map);
            System.out.println("HUMAN TURN");
            human.getTurn();
            //return gameState = new GameState(map);
            turnCount = turnCount - 1;
        }
        if (gameState.hasWon()) {
            System.out.println("CONGRATULATIONS!");
        }
        else {
            System.out.println("GAME OVER");
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.setup();
        game.play();
    }

}



/* Note: this enitre code was built on the assumption that each Array within each coordinate
in the ArrayList map assumes that the parameters are (simpleReference, typeOfPiece, ...)  */