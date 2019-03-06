import java.util.Scanner;
import java.util.ArrayList;
import java.util.Array;

public class HumanPlayer {
    private ArrayList<Entity> humanPieces = getHumanPieces();
    private Entity player; //???
    private GameState gameState;
    private Map map;

    public int obtainPieceLocation(int count) {
        int location;
        for (Array m:map) {
            for (Array x:m) {
                if (x[1][3] == 1 && x[1][6] == count) {
                    location = m[0];
                }
            }
        }
        return location;
    }

    public GameState getTurn() {
        int count = 1;
        int ap;
        Scanner input = new Scanner(System.in);
        for (Entity e:humanPieces) {
            ap = e[count][5];
            while (ap > 0 && gameState.hasWon() == false) {
                System.out.print("For piece " + count + " would you like to move or attack? Type \"M\" for the former or \"A\" for the latter. ");
                char move = input.nextChar();
                while (move != 'M' && move != 'A') {
                    System.out.println("Invalid answer.");
                    System.out.print("For piece " + count + " would you like to move or attack? Type \"M\" for the former or \"A\" for the latter. ");
                    char action = input.nextChar();
                }
                if (action == 'M') {
                    boolean viable = false;
                    while (viable == false) {
                        Scanner input2 = new Scanner(System.in);
                        map.toString();
                        System.out.print("Where would you like to move? ");
                        int end = input2.nextInt();
                        int startLocation = obtainPieceLocation(count);
                        viable = gameState.isValidMove(Turn.move(startLocation, end));
                        if (viable) {
                            return gameState.update(Turn.move(startLocation, end));
                            /* ap = ap - (end - startLocation); */ //number of spaces moved = 1 ap NOT VALID LINE
                        }
                    }
                }
                else {
                    boolean viable2 = false;
                    while (viable2 == false) {
                        Scanner input2 = new Scanner(System.in);
                        map.toString();
                        System.out.print("Which space is your target standing on? ");
                        int target = input2.nextInt();
                        int startLocation = obtainPieceLocation(count);
                        viable2 = gameState.isValidMove(Turn.attack(startLocation, target)); //using these as arguments rather than gameState
                        if (viable2) {
                            return gameState.update(Turn.attack(startLocation, target));
                            /* ap = ap - (end - startLocation); */ //number of spaces moved = 1 ap NOT VALID LINE
                        } 
                    }
                }
            }
        }
    }
}