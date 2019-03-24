import java.util.ArrayList;

public class AIPlayer extends Game
{   
    /** Instance variables for the class that are set to default values */
    private ArrayList<Entity> AIPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Map map = new Map();
    private Entity e = new Entity();
    private int count = 0;
   
    /** Constructor */
    AIPlayer()
    {
        
        map = super.getMap();
        masterlist = super.getPieces().getMasterList();
        AIPieces = super.getPieces().getAIParty();
        count = AIPieces.size();
        
    }
    /**Method
    * getting enemy turn*/
    public void getEnemyTurn1()
    {
        int count = 0;
        for(Entity e:AIPieces) 
        {
           map.moveState(map.getPieceLocation(count),map.getPieceLocation(count) + 1);
           count += 1;
        }
   

    }
    /**Method
    * getting enemy turn*/
    public void getEnemyTurn2()
    {
        int count = 0;
        for(Entity e:AIPieces) 
        {
           map.moveState(map.getPieceLocation(count),map.getPieceLocation(count) + 1);
           count += 1;
        }
      

    }
    // public GameState getEnemyTurnExecute()
    // {
    //     for (Entity e:AIPieces) 
    //     {   
    //         e.setHp(pieceList.get(target));
    //         ap = ap - 2;

    //     }

    // }


}
