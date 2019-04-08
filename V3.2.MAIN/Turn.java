import java.util.ArrayList;

public class Turn{
	
	/**
	 * Instance variables 
	 */
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private int start;
    private Map map = new Map();
    private Entity e = new Entity();
    private Terrain t = new Terrain();
    private boolean viable;

    /**
     * Constructor for the class Turn
     * Turn class controls the pieces movement on the map and the interactions it has with objects. 
     * @param map passing in class map 
     * @param pieceLists passing in class pieceLists 
     */
    Turn(Map map, PieceLibrary pieceLists)
    {
        this.map = map;
        this.masterlist = pieceLists.getMasterList();
        this.humanPieces = pieceLists.getPlayerParty();
    }
    
    /**
     * getter for entity 
     * @return the stats of pieces
     */
    public Entity getEntity()
    {
        return e;
    }
    
    /**
     * getter for start
     * @return the place of the piece taking the turn
     */
    public int getStart()
    {
        return start;
    }
    
    /**
     * Method allows user to reselect another piece 
     */
    public void unselectPiece() 
    {
        this.e = new Entity();
        start = 0;
    }
    
    /**
     * Method checks the surrounding of the map for valid movement of pieces 
     * @param end passes a check on the selected location of piece on map 
     * @return If the piece can be moved onto selected part of map
     */
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
  
    /**
     * Method checks to see if selected enemy can be attacked 
     * Checks for sufficient amount of ap to perform attack 
     * @param end passes a check on the selected location of piece on map
     * @return If the piece can attack selected enemy 
     */
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
    
    /**
     * Method checks for object on map being selected is valid for selection 
     * @param start passes a check on the selected location of piece on map
     * @return If object can be interacted with 
     */
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
    
    /**
     * Method resets the users amount of ap for a new turn 
     */
    public void resetTurn() 
    {
        for(Entity a:humanPieces) 
        {
            a.resetap();
        }
    }
    
    /**
     * Method checks for the health and ap of pieces 
     * @return boolean based on whether a piece is capable of an action
     */
    public boolean checkPieceApAndHealth() 
    {
        boolean test = false;
        if(e.getAP() > 0 && e.getHp() > 0) 
        {
            test = true;
        }
        return test;
    }

    /**
     * Method checks to see if piece can be picked 
     * @param start passes a check on the selected location of piece on map
     * @return If the piece can be selected 
     */
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
    
    /**
     * Method moves piece to selected position of the map
     * @param end passes a check on the selected location of piece on map
     * @return If piece can be moved 
     */
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
    
    /**
     * Method attacks pieces on map 
     * Checks if valid there is sufficient ap to attack piece 
     * @param end passes a check on the selected location of piece on map
     * @return If piece can be attacked 
     */
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
    
    /**
     * Method increases the health of piece 
     * Checks for total health and ap for valid actions 
     * @return If health can be increased
     */
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
