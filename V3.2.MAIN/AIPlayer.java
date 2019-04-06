import java.util.ArrayList;

public class AIPlayer extends Turn
{   
    private ArrayList<Entity> AIPieces = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Map map = new Map();
    private Entity e = new Entity();
   

     /** Constructor */ //CHANGED
    AIPlayer(Map map, PieceLibrary pieceLists)
    {
        super(map, pieceLists);
        this.map = map;
        masterlist = pieceLists.getMasterList();
        AIPieces = pieceLists.getAIParty();
        
    }
    /**Method
    * getting enemy Move Phase*/
    public void getEnemyTurn1()
    {
        // int count = 0;
        // for(Entity e:AIPieces) 
        // {
        //    map.moveState(map.getPieceLocation(count),map.getPieceLocation(count) + 1);
        //    count += 1;
        // }
   

    }
       /**Method
    * getting enemy Attack Phase*/
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