import java.util.ArrayList;

public class AIPlayer extends Game
{   
    private ArrayList<Entity> AIPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Map map = new Map();
    private Entity e = new Entity();
   

    AIPlayer(Map map,MapInfo level,Pieces pieceLists)
    {
        
        this.map = map;
        masterlist = pieceLists.getMasterList();
        AIPieces = pieceLists.getAIParty();
        
    }

    public void getEnemyTurn1()
    {
        // int count = 0;
        // for(Entity e:AIPieces) 
        // {
        //    map.moveState(map.getPieceLocation(count),map.getPieceLocation(count) + 1);
        //    count += 1;
        // }
   

    }
    public void getEnemyTurn2()
    {
        // int count = 0;
        // for(Entity e:AIPieces) 
        // {
        //    map.moveState(map.getPieceLocation(count),map.getPieceLocation(count) + 1);
        //    count += 1;
        // }
      

    }


}