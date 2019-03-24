import java.util.Scanner;
import java.util.ArrayList;


public class Pieces 
{
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> aiPieces = new ArrayList<Entity>();
    private ArrayList<Entity> entityParty = new ArrayList<Entity>();
    private ArrayList<Entity> playerParty = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    Pieces()
    {
    
    }

    Pieces(Pieces copy)
    {
        humanPieces = copy.getHumanPieces();
        aiPieces = copy.getAIPieces();
        entityParty = copy.getAIParty();
        playerParty = copy.getPlayerParty();
        masterlist = copy.getMasterList();

    }

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

    public ArrayList<Entity> getAIParty() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity e:entityParty) {
            decoy.add(new Entity(e));
        }
        return decoy;
    }

    public ArrayList<Entity> getPlayerParty() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity e:playerParty) {
            decoy.add(new Entity(e));
        }
        return decoy;
    }

    public ArrayList<Entity> getMasterList() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity e:masterlist) {
            decoy.add(new Entity(e));
        }
        return decoy;
    }



    public void addHumanPieces(String name)
    {
        Entity hPiece = new Entity(name,1, 0, 4, 1, 1, 5, 2);
        humanPieces.add(new Entity(hPiece));
    }
    

    public void addAIPieces(String name)
    {
         
        Entity ePiece = new Entity(name, 1, 0, 2, 2, 1, 5, 2);
        aiPieces.add(new Entity(ePiece));

    }

    
    public void addtoMasterList()
    {   
        {
            masterlist.add(new Entity(aiPieces.get(aiPieces.size() - 1)));
        }
    }

    public void addtoPlayerParty(int index)
    {
        playerParty.add(new Entity(humanPieces.get(index)));
    }

    public void addtoEntityParty()
    {
        for (int b = 0; b < aiPieces.size(); b++) 
        {
            entityParty.add(new Entity(aiPieces.get(b)));

        }
    }

    public void compileMasterList()
    {
        for (int b = 0; b < playerParty.size(); b++) 
        {
            masterlist.add(new Entity(playerParty.get(b)));
        }
        
        for (int b = 0; b < aiPieces.size(); b++) 
        {
            masterlist.add(new Entity(aiPieces.get(b)));
        }


    }

    public int getPieceCount(Entity list)
    {
        int count = 0;
        for (int b = 0; b < list.size(); b++) 
        {
            count++;
        }
        return count;
    }



    
}