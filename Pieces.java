import java.util.Scanner;
import java.util.ArrayList;

public class Pieces {
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> aiPieces = new ArrayList<Entity>();

    public ArrayList<Entity> getHumanPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity h:humanPieces) {
            decoy.add(new Entity(h));
        }
        return decoy;
    }
    
    public ArrayList<Entity> getAIPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity e:aiPieces) {
            decoy.add(new Entity(e));
        }
        return decoy;
    }   

    public void addHumanPieces(){
        int count = 0; //intended to individualize each piece; each piece will have a different token as their last parameter)
        for (int a = 0; a < 3; a++) {
            count = count + 1;
            Entity hPiece = new Entity(10, 10, 50, 1, 1, 5);
            humanPieces.add(new Entity(hPiece));
        }
    }

    public void addAIPieces(){
        int count = 0; //intended to individualize each piece; each piece will have a different token as their last parameter)
        for (int b = 0; b < level.getNumOfEnemies(); b++) {
            count = count + 1;
            Entity ePiece = new Entity(10, 10, 50, 2, 1, 5);
            aiPieces.add(new Entity(ePiece));
        }
    }   
}