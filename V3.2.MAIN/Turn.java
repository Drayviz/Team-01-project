import java.util.ArrayList;

public class Turn{
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private int start;
    private Map map = new Map();
    private Entity e = new Entity();
    private Terrain t = new Terrain();
    private boolean viable;

    //CHANGED
    Turn(Map map, PieceLibrary pieceLists)
    {
        this.map = map;
        this.masterlist = pieceLists.getMasterList();
        this.humanPieces = pieceLists.getPlayerParty();
    }
    public Entity getEntity()
    {
        return e;
    }
    public int getStart()
    {
        return start;
    }

    public void unselectPiece() 
    {
        this.e = new Entity();
        start = 0;
    }

    public boolean isValidMove(int end) 
    {
        viable = false;
        if(e.getAP() > 0 && map.getPiece(end) == 0)
        {
            //System.out.println("Check 2");
            if(start == end + 1|| start == end - 1|| end == start + map.getDimensions()|| end == start - map.getDimensions())
            {
                //System.out.println("Check 3");
                if(t.checkMountain(end,this.map) == false)
                {
                    //System.out.println("Check 4");
                    viable = true;
                }
            }
        }
        return viable;
    }
    /* This method considers whether an attack is valid based on where the piece is and where the target is.
    first if-SM: considers if the target is to the left, right, above, or below the attacker(not diagonally, if necessary will implement later), respectively
    second if-SM: the two for-loops are used to obtain the <party> of the pieces. If the pieces are in the same party, the attack is invalid (no friendly-fire)
        NOTE: I have not considered an error if the spot they want to attack is unoccupied
    returns true if attack is valid.  */    
    public boolean isValidAtk(int end) {
        viable = false;
        if(e.getAP() > 0) 
        {
            //System.out.println("Check 6");
            if(start == end + 1|| start == end - 1|| end == start + map.getDimensions() || end == start - map.getDimensions())
            {
                //System.out.println("Check 7");
                if(t.checkRiver(start,this.map) == false && (map.getPiece(end) > 0))
                {
                    //System.out.println("Check 8");
                   viable = true; 
                }
            }
        }
        return viable;
    }

    public boolean isValidSelection(int start) 
    {
        viable = false;
        if(map.getPiece(start) > 0) 
        {
            if(masterlist.get(map.getPiece(start) - 1).getParty() == 1 && masterlist.get(map.getPiece(start) - 1).getState() == 1) 
            {
                viable = true;
                this.start = start;
            }
        }
        return viable;
    }

    public void resetTurn() 
    {
        for(Entity a:humanPieces) 
        {
            a.resetap();
        }
    }

    public boolean checkPieceApAndHealth() 
    {
        boolean test = false;
        if(e.getAP() > 0 && e.getHp() > 0) 
        {
            test = true;
        }
        return test;
    }


    public Entity selectPiece(int start) 
    {
        boolean viable = isValidSelection(start);
        int index = map.getPiece(start);
        if(viable == true) 
        {
            e = masterlist.get(index - 1);
        }
        //System.out.println("AP: " + e.getAP());
        return e;
    }
    public boolean movePiece(int end) 
    {
        viable = false;
        if(map.getPiece(end) == 0) 
        {
            //System.out.println("Check 1");
            viable = isValidMove(end); //first, checking to see if move is valid
            if (viable == true && checkPieceApAndHealth() == true) 
            {
                //System.out.println("Check 5");
                map.moveState(start, end);
                e.ActionTakes(1); //ap is reduced   
                start = end;
                //map.displayMap();
            }
            t.pitfallDeath(end, this.map, this.e);
            e.checkstate();
        }
        //System.out.println("AP: " + e.getAP());
        return viable;
    }
    public boolean attackPiece(int end)
    {
        viable  = isValidAtk(end);
        if (viable == true && checkPieceApAndHealth() == true)
        {
                e.setHp(masterlist.get(map.getPiece(end) - 1));
                e.ActionTakes(2); //ap is reduced
        }
        e.checkstate();
        return viable;
    }

    public boolean healPiece()
    {
        if(e.getHp() < e.getMaxap() && e.getAP() > 0)
        {
            System.out.println("Piece has been healed by 1 hp");
            e.heal();
            e.ActionTakes(2);
            return true;
        }
        else
        {
            System.out.println("Health is already full");
            return false;
        }
    }
}
