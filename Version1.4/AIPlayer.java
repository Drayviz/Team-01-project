import java.util.ArrayList;

public class AIPlayer extends Game
{   
    private ArrayList<Entity> AIPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Map map = new Map();
    private Entity e = new Entity();
    private int count = 0;
   

    AIPlayer()
    {
        
        map = super.getMap();
        masterlist = super.getPieces().getMasterList();
        AIPieces = super.getPieces().getAIParty();
        count = AIPieces.size();
        
    }

    public void getEnemyTurn1()
    {
        int count = 0;
        for(Entity e:AIPieces) 
        {
           map.moveState(map.getPieceLocation(count),map.getPieceLocation(count) + 1);
           count += 1;
        }
   

    }
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