import java.util.ArrayList;

public class GameState extends Game
{
    private Map map = super.getMap();
    private MapInfo level = this.map.getMapinfo();
    private Pieces pieceLists = super.getPieceLists();
    private int turncounter = level.getTurns();

    public GameState() 
    {

    }

    /* If all is good, updates changes to the map (obv by creating a new one, because privacy!!!) */
    
    /* This method considers whether a move is valid based on where the piece is and where the piece wants to go.
    first if-SM: considers if the piece wants to move to the same spot; invalid
    second if-SM: only allows piece to move left, right, up, down (not diagonally, if necessary will implement later), respectively
    third if-SM: doesn't allow one to move to a space if it's already occupied.
    returns true if move is valid.  */
    public void update()
    {
        this.map = new Map(this.map);
    }

    public Map getMap()
    {   
        return new Map(map);
    }
   
    public Pieces getPieceLists() 
    {
        return new Pieces(pieceLists);
    }
    
    public boolean isValidMove(int start, int end, Entity piece) 
    {
        boolean viable = false;
    
        if(start == end + piece.getMovement() || start == end - piece.getMovement() || start == end + map.getDimensions() || start == end - map.getDimensions())
        {
            viable = true;
        }

        return viable;

    }
    /* This method considers whether an attack is valid based on where the piece is and where the target is.
    first if-SM: considers if the target is to the left, right, above, or below the attacker(not diagonally, if necessary will implement later), respectively
    second if-SM: the two for-loops are used to obtain the <party> of the pieces. If the pieces are in the same party, the attack is invalid (no friendly-fire)
        NOTE: I have not considered an error if the spot they want to attack is unoccupied
    returns true if attack is valid.  */    
    public boolean isValidAtk(int start, int end,Entity piece) 
    {
        boolean viable = true;
        //int indicator1;
        //int indicator2;

        if(start == end + piece.getMovement() || start == end - piece.getMovement() || start == end + map.getDimensions() || start == end - map.getDimensions())
        {
            viable = true;
        }

        return viable;
    }

    public int hasWon() 
    {
        int won = 1;
        int friendlycount = 0;
        int enemyCount = 0;
        int count = -1;

        for (ArrayList<Integer> a:map.getMaparray()) 
        {  
            count += 1;
            if (a.get(1) != 0) 
            {
                if (pieceLists.getMasterList().get(count).getParty() == 2) 
                {
                    enemyCount = enemyCount + 1;
                }
                else if (pieceLists.getMasterList().get(count).getParty() == 1) 
                {
                    friendlycount = friendlycount + 1;
                }
            }
        
            if (enemyCount == 0) {
                won = 2;
            }
            if (friendlycount == 0 || turncounter == 0) 
            {
                won = 3;
            }
        }
        turncounter -= 1;
        return won;

    }

}