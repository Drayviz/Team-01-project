import java.util.ArrayList;

public class AIPlayer extends Game
{   
    private Map map = super.getMap();
    private Pieces pieceLists = super.getPieceLists();
    private int count = pieceLists.getMasterList().size() - pieceLists.getAIPieces().size();
    private ArrayList<Entity> AIPieces = pieceLists.getAIPieces();

    AIPlayer()
    {
    }

    
    public void getEnemyTurn()
    {
        int count = 0;
        for(Entity e:AIPieces) 
        {
           map.moveState(map.getPieceLocation(count),map.getPieceLocation(count) + 1);
           count += 1;
        }
        super.update();

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