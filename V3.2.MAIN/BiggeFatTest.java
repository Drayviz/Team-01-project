import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BiggeFatTest 
{
    @Test
    public void test_MapCreation()
    {
        Map test = new Map(2,1);
        ArrayList<ArrayList<Integer>> maparray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> buffer = new ArrayList<Integer>();
       
        buffer.add(1);
        buffer.add(0);
        buffer.add(1);
        buffer.add(0);
        buffer.add(0);
        maparray.add(buffer);
        assertEquals("Expected First Tile To Be [1,0,1,0,0]",maparray.get(0), test.getMaparray().get(0));
        buffer.set(3,1);
        buffer.set(2,2);
        maparray.add(buffer);
        assertEquals("Expected Second Tile To Be [1,0,2,1,0]",maparray.get(1), test.getMaparray().get(1));
        buffer.set(3,0);
        buffer.set(2,3);
        buffer.set(4,-1);
        maparray.add(buffer);
        assertEquals("Expected Third Tile To Be [1,0,3,0,1]",maparray.get(2), test.getMaparray().get(2));
        buffer.set(3,1);
        buffer.set(2,4);
        maparray.add(buffer);
        assertEquals("Expected Fourth Tile To Be [1,0,4,1,1]",maparray.get(3), test.getMaparray().get(3));
       

    }
    @Test
    public void test_SaveMapAndLoad() 
    {
        Map test2 = new Map(1,1);
        MapInfo a = new MapInfo();
        ArrayList<ArrayList<Integer>> maparray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> buffer = new ArrayList<Integer>();
       
        buffer.add(1);
        buffer.add(0);
        buffer.add(1);
        buffer.add(0);
        buffer.add(0);
        maparray.add(buffer);
        a.setMaptype(2);
        a.setNumenemies(10);
        a.setTurns(6);
        Map test = new Map(test2.getDimensions(),a,test2.getMaparray());
        test.setDimensions(4);
        test.saveMap("test", "one");
        test.loadMap("one", "test");
        assertEquals("Expected Map to To contain one tile [1,0,1,0,0]",maparray, test.getMaparray());

        assertEquals("Expected Map Type to be 2",2, test.getMapInfo().getMaptype());
        assertEquals("Expected Numenemies to be 10",10, test.getMapInfo().getNumofEnemies());
        assertEquals("Expected Turns to be 6",6, test.getMapInfo().getTurns());
        assertEquals("Expected Dimensions to be 4",4, test.getDimensions());
    }
  
    @Test
    public void test_PieceActionsTerrain()
    {
        MapInfo a = new MapInfo();
        Map map = new Map(8, 1);
        map.setState(11, 0, 6);
        map.setState(10, 0, 7);
        map.setState(9, 0, 8);
        Pieces x = new Pieces();
        x.addHumanPieces(1);
        x.addtoPlayerParty(0);
        x.addAIPieces(10);
        x.addtoEntityParty(0);
        x.compileMasterList();
        x.getMasterList().get(0).setName("literal ape");
        Game game = new Game(map,a,x);
        Turn turn = new Turn(map, x);

        //TESTS AI PLACEMENT
        game.placeAIPiece(4);
        assertEquals("Expected Entity at 4 to be an enemy","enemytitan",  x.getMasterList().get((map.getPiece(4) - 1)).getName());

        //TESTS HUMAN PLACEMENT
        game.placeHumanPieces(4);
        assertEquals("Expected Entity at 4 to be an enemy","enemytitan",  x.getMasterList().get((map.getPiece(4) - 1)).getName());

        game.placeAllHumanPieces(1);
        assertEquals("Expected Entity at 1 to contain a human piece named 'literal ape'","literal ape", x.getMasterList().get((map.getPiece(1) - 1)).getName());
        
        
        //TESTS SELECTIONS
        assertEquals("Expected to be invalid selection (Enemy Piece)",false, turn.isValidSelection(4));
        
        assertEquals("Expected to be invalid selection (Blank Space)",false, turn.isValidSelection(2));

        turn.selectpiece(1);
        turn.movePiece(2);


        //TESTS MOVEMENT
        assertEquals("Expected Literal Ape to not be on old space",-1, map.getPiece(1) - 1);

        assertEquals("Expected Literal Ape to be on tile 2","literal ape", x.getMasterList().get((map.getPiece(2) - 1)).getName());

        assertEquals("Expected Literal Ape to have an ap of 1",1, x.getMasterList().get((map.getPiece(2) - 1)).getAP());

        turn.movePiece(3);

        assertEquals("Expected Literal Ape to not be on old space",-1, map.getPiece(2) - 1);

        assertEquals("Expected Literal Ape to be on tile 3","literal ape", x.getMasterList().get((map.getPiece(3) - 1)).getName());

        //TESTS AP USEAGE

        assertEquals("Expected Literal Ape to have an ap of 0",0, x.getMasterList().get((map.getPiece(3) - 1)).getAP());

        assertEquals("Expected to be invalid attack (out of ap)",false, turn.isValidAtk(4));

        assertEquals("Expected to be invalid move (out of ap)",false, turn.isValidMove(3));

        turn.resetTurn();

        //TESTING EDGE MOVE CASE

        assertEquals("Expected to be invalid move (tried to move on enemy)",false, turn.isValidMove(4));

        //TESTING RESET TURN

        assertEquals("Expected Literal Ape to have an ap of 2",2, x.getMasterList().get((map.getPiece(3) - 1)).getAP());

        turn.attackPiece(4);

        //TESTS ATTACKS

        assertEquals("Expected Literal Ape to have an ap of 0",0, x.getMasterList().get((map.getPiece(3) - 1)).getAP());

        assertEquals("Expected Enemy to have 0 hp",0, x.getMasterList().get((map.getPiece(4) - 1)).getHp());

        assertEquals("Expected Literal Ape to have 4 hp",4, x.getMasterList().get((map.getPiece(4) - 1)).getHp());

        //TESTS HEAL

        x.getMasterList().get((map.getPiece(3) - 1)).heal();

        assertEquals("Expected Literal Ape to have an hp of 0",0, x.getMasterList().get(0).getHp());

        //TESTS MOUNTAIN

        assertEquals("Expected to be invalid move (tried to move onto mountain)",false, turn.isValidMove(11));

        turn.movePiece(2);

        turn.movePiece(10);
        turn.resetTurn();

        //TESTS WATER
        assertEquals("Expected to be invalid attack (tried to attack in water)",false, turn.isValidAtk(2));

        turn.movePiece(9);
        //TESTING PITFALL

        assertEquals("Expected Literal Ape to have an hp of 0",0, x.getMasterList().get(0).getHp());

        assertEquals("Expected Literal Ape to have a state of 0 (dead)",0, x.getMasterList().get(0).getState());

        //assertEquals("Expected Literal Ape to have been yeeted off the map",0, map.getPiece(9));
    }
    
}