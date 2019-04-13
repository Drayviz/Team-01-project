/*DONE*/

import java.util.ArrayList;

/*package text;*/
/**
 * TURN
 * 
 * Methods and scripts needed for the player and ai to attack, move etc
 * The bridge between entity and map, uses privacy leaked entities to
 * manipulate the stats of individual entities.
 * Is also the core logic for whether or not the ai/player can do something based on the terrain 
 * around the map.
 * Edits the map, and entity states.
 * 
 */

public class Turn{
	
	/**
	 * Instance variables 
	 */
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private ArrayList<Entity> aiList = new ArrayList<Entity>();
    private ArrayList<Entity> buildinglist = new ArrayList<Entity>();
    private int start;
    private MapClass map = new MapClass();
    private Entity e = new Entity();
    private Terrain t = new Terrain();
    private boolean viable;
    

    /**
     * Constructor for the class Turn
     * Turn class controls the pieces movement on the map and the interactions it has with objects. 
     * @param map passing in class map 
     * @param pieceLists passing in class pieceLists 
     */
    Turn(MapClass map, PieceLibrary pieceLists)
    {
        this.map = new MapClass(map);
        this.masterlist = new ArrayList<Entity>(pieceLists.getMasterList());
        this.humanPieces = new ArrayList<Entity>(pieceLists.getPlayerParty());
        this.aiList = new ArrayList<Entity>(pieceLists.getAIParty());
        this.buildinglist= new ArrayList<Entity>(pieceLists.getBuildingList());
    }
    
    /**
     * getter for entity 
     * @return the stats of pieces
     */
    public Entity getEntity()
    {
        return new Entity(e);
    }
    
    /**
     * getter for start
     * @return the place of the piece taking the turn
     */
    public int getStart()
    {
        return new Integer(start);
    }
    
    /**
     * Method allows user to reselect another piece 
     */
    public void unSelectPiece() 
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
            if(start == end + 1|| start == end - 1|| end == start + map.getDimensions()|| end == start - map.getDimensions())
            {
                if(t.checkMountain(end,this.map) == false)
                {
                    viable = true;
                }
            }
        }
        return new Boolean(viable);
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
            if(start == end + 1|| start == end - 1|| end == start + map.getDimensions() || end == start - map.getDimensions())
            {
                if(t.checkRiver(start,this.map) == false && (map.getPiece(end) > 0))
                {
                   viable = true; 
                }
            }
        }
        return new Boolean(viable);
    }
    
    /**
     * Method checks for object on map being selected is valid for selection 
     * @param start passes a check on the selected location of piece on map
     * @return If object can be interacted with 
     */
    public boolean isValidSelection(int start) 
    {
        viable = false;
        if(map.getPiece(start) > 0 && map.getPiece(start) < masterlist.size() - buildinglist.size()) 
        {
            if(masterlist.get(map.getPiece(start) - 1).getParty() == 1 && masterlist.get(map.getPiece(start) - 1).getState() == 1) 
            {
                viable = true;
                this.start = start;
            }
        }
        return new Boolean(viable);
    }
    
    /**
     * Method resets the users amount of ap for a new turn 
     */
    public void resetTurn() 
    {
        for(Entity a:humanPieces) 
        {
            a.resetAp();
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
        return new Boolean(test);
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
        return new Entity(e);
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
            viable = isValidMove(end);
            if (viable == true && checkPieceApAndHealth() == true) 
            {
                map.moveState(start, end);
                e.actionTakes(1);
                start = end;
            }
            t.pitfallDeath(end, this.map, this.e);
            e.checkState();
        }
        return new Boolean(viable);
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
                e.actionTakes(2);
        }
        e.checkState();
        return new Boolean(viable);
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
            e.actionTakes(2);
            return true;
        }
        else
        {
            System.out.println("Health is already full");
            return false;
        }
    }
    
    /**
     * Method that searches for nearest human piece to move to and project attack
     */
    public void aiMoveAndProjectAttack()
    {
        int possibleTarget1 = 0;
        int possibleTarget2 = 0;
        int definiteTarget = 0;
        int targetfound = 0;
        
        //Scans up
        System.out.println(this.start);
        for(int x = new Integer(this.start); x > 1; x--)
        {

            if(map.getPiece(x) != 0 && masterlist.get(map.getPiece(x)-1).getState() != 0)
            {
                if(masterlist.get(map.getPiece(x)-1).getParty() != 2)
                {
                    possibleTarget1 = x;
                    System.out.println(possibleTarget1);
                    targetfound = 1;
                    break;
    		    }

            }
            
        }
        //Scans down
        for(int y = new Integer(this.start); y < map.getDimensions() * map.getDimensions();y++)
        {
            if(map.getPiece(y) != 0 && masterlist.get(map.getPiece(y)-1).getState() != 0)
            {
                if(masterlist.get(map.getPiece(y)-1).getParty() != 2)
                {
                    possibleTarget2 = y;
                    targetfound = 1;
                    System.out.println(possibleTarget2);
                    break;
                }
            }
    	}
                
        if(targetfound == 1)
        {

        
            if((start - possibleTarget1) <= (possibleTarget2 - start))
            {
    			definiteTarget = possibleTarget1;
            }
            else
            {
                definiteTarget = possibleTarget2;
            }
            if(map.getPiece(definiteTarget+1) == 0 && map.getTerrain(definiteTarget+1) != 6 && map.getTerrain(definiteTarget+1) != 8 && map.getTerrain(definiteTarget+1) != 20 
            && map.getTerrain(definiteTarget+1) != 30 && map.getTerrain(definiteTarget+1) != 40 && map.getTerrain(definiteTarget+1) != 50 && map.getTerrain(definiteTarget+1) != 60)
            {
    			map.moveState(start,definiteTarget+1);
    			e.setAttackMemory(definiteTarget);
    		}
            else if(map.getPiece(definiteTarget-1) == 0 && map.getTerrain(definiteTarget-1) != 6 && map.getTerrain(definiteTarget-1) != 8 && map.getTerrain(definiteTarget-1) != 20 
            && map.getTerrain(definiteTarget-1) != 30 && map.getTerrain(definiteTarget-1) != 40 && map.getTerrain(definiteTarget-1) != 50 && map.getTerrain(definiteTarget-1) != 60) 
            {
    			map.moveState(start,definiteTarget-1);
    			e.setAttackMemory(definiteTarget);
    		}
            else if(map.getPiece(definiteTarget+map.getDimensions()) == 0 && map.getTerrain(definiteTarget + map.getDimensions()) != 6 && map.getTerrain(definiteTarget + map.getDimensions()) != 8 && map.getTerrain(definiteTarget + map.getDimensions()) != 20 
            && map.getTerrain(definiteTarget + map.getDimensions()) != 30 && map.getTerrain(definiteTarget + map.getDimensions()) != 40 && map.getTerrain(definiteTarget + map.getDimensions()) != 50 && map.getTerrain(definiteTarget + map.getDimensions()) != 60)
            {
    			map.moveState(start,definiteTarget+map.getDimensions());
    			e.setAttackMemory(definiteTarget);
    		}
            else if(map.getPiece(definiteTarget-map.getDimensions()) == 0 && map.getTerrain(definiteTarget - map.getDimensions()) != 6 && map.getTerrain(definiteTarget - map.getDimensions()) != 8 && map.getTerrain(definiteTarget - map.getDimensions()) != 20 
            && map.getTerrain(definiteTarget - map.getDimensions()) != 30 && map.getTerrain(definiteTarget - map.getDimensions()) != 40 && map.getTerrain(definiteTarget - map.getDimensions()) != 50 && map.getTerrain(definiteTarget - map.getDimensions()) != 60)
            {
    			map.moveState(start,definiteTarget-map.getDimensions());
    			e.setAttackMemory(definiteTarget);
    		}	
        }
    }
    
    /**
     * Method to attack on projected tile
     */
    public void aiAttack(){
    	for(Entity entity:aiList){
    		if(entity.getAttackMemory() != -1){
    			entity.setHp(masterlist.get(map.getPiece(entity.getAttackMemory()-1)));
    			entity.setAttackMemory(-1);
    		}
    	}
    }
    
    /**
     * Method that checks if it's a valid slection that is an AI piece
     * @param start is location of selected 
     * @return boolean statement whether or not it's a valid selection
     */
    public boolean validAISelection(int start){
    	viable = false;
        if(map.getPiece(start) > 0) 
        {
            if(masterlist.get(map.getPiece(start) - 1).getParty() == 2 && masterlist.get(map.getPiece(start) - 1).getState() == 1){
                viable = true;
                this.start = start;
            }
        }
        return new Boolean(viable);
    }
    
    /**
     * Method that goes through all enemy Ai to do their turn
     */
    public void aiTurn(){
        aiAttack();
        for(int x = 1;x <= map.getDimensions() * map.getDimensions() - 1;x++)
        {
            boolean viable = validAISelection(x);
            int index = map.getPiece(x);
            
            if(viable == true)
            {
                
                e = masterlist.get(index - 1);
                System.out.println(e.getName());

                aiMoveAndProjectAttack();
            }
        }
    
}
}
