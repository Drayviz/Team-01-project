import java.util.Scanner;
import java.util.ArrayList;
import java.util.Array;

public class HumanPlayer {
    private ArrayList<Entity> humanPieces = getHumanPieces();
    private Entity player; //???
    private GameState gameState;
    private Map map;

    /* Uses current version of <map> and scours it for an Entity of interest
    (basically, this method is given a picture of a person, then it looks through the map to see if they can see a someone who looks exactly like that person. if so, they copy the location of that person)  */
    private int obtainPieceLocation(Entity e) {
        int location;
        for (Array m:map) {
            if (m[1].equals(e)) {
                location = m[2];
            }   
        }
        return location;
    }

    /* This is a big boy. I'm gonna comment throughout... */
    public GameState getTurn() {
        int count = 1; //For aesthetic, just to keep track of which piece is being used
        Scanner input = new Scanner(System.in);
        for (Entity e:humanPieces) {
            int ap = e.getAP(); //getting ap
            while (ap > 0 && gameState.hasWon() == false && gameState.hasLost() == false) { //ensures that game doesn't continue when it's already been won/lost
                System.out.print("For piece " + count + " would you like to move or attack? Type \"M\" for the former or \"A\" for the latter. ");
                char move = input.nextChar();
                while (move != 'M' && move != 'A') { //Error catching
                    System.out.println("Invalid answer.");
                    System.out.print("For piece " + count + " would you like to move or attack? Type \"M\" for the former or \"A\" for the latter. ");
                    char action = input.nextChar();
                }
                if (action == 'M') {
                    boolean viable = false; //doesn't become true until a turn has been made
                    while (viable == false) { //thus, as long as a turn hasn't been made, this keeps on going
                        Scanner input2 = new Scanner(System.in);
                        map.toString();
                        System.out.print("Where would you like to move? "); 
                        int end = input2.nextInt();
                        int startLocation = obtainPieceLocation(e); //just to obtain where the piece is
                        Turn moveTurn = new Turn(startLocation, end, e); //creating new Turn object to allow for move to be made
                        viable = gameState.isValidMove(startLocation, end); //first, checking to see if move is valid
                        if (viable) { 
                            return gameState.update(moveTurn.move(startLocation, end, e)); //when all is g, the move is made and a map is returned, which is updated as the new map
                            ap = ap - 1; //ap is reduced
                        }
                    }
                }
                /* Same concept as (action == 'M') */
                else {
                    boolean viable2 = false;
                    while (viable2 == false) {
                        Scanner input2 = new Scanner(System.in);
                        map.toString();
                        System.out.print("Which space is your target standing on? ");
                        int target = input2.nextInt();
                        int startLocation = obtainPieceLocation(e);
                        Turn moveTurn = new Turn(startLocation, end);
                        viable2 = gameState.isValidAtk(startLocation, target);
                        gameState.removePieceFromLists(); //these clean the lists and maps of dead pieces
                        gameState.removePieceFromMap();
                        if (viable2) {
                            return gameState.update(moveTurn.attack(startLocation, target));
                            ap = ap - 1;
                        } 
                    }
                }
            }
        }
    }
}