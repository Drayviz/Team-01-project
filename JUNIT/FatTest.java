import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FatTest 
{
    @Test
    public void test_MapCreation()
    {
        MapClass test = new MapClass(2,1);
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
    public void test_PieceActionsTerrain()
    {
        MapClass map = new MapClass(8, 1);
        map.setState(11, 0, 6);
        map.setState(10, 0, 7);
        map.setState(9, 0, 8);
        // map.setState(29, 0, 20);
        // map.setState(28, 0, 30);
        // map.setState(27, 0, 40);
        // map.setState(26, 0, 50);
        // map.setState(25, 0, 60);

        PieceLibrary x = new PieceLibrary();
        x.addHumanPieces(1);
        x.addtoPlayerParty(0);
        x.addAIPieces(10);
        x.addtoEntityParty(0);
        x.compileMasterList();

        x.getMasterList().get(0).setName("literal ape");
        Game game = new Game(map,x);
        Turn turn = new Turn(map,x);
        

        //TESTS AI PLACEMENT
        game.placeAIPiece(4,2);
        assertEquals("Expected Entity at 4 to be an enemy","enemytitan",  x.getMasterList().get((map.getPiece(4) - 1)).getName());

        //TESTS HUMAN PLACEMENT
        game.placeHumanPieces(4);
        assertEquals("Expected Entity at 4 to be an enemy","enemytitan",  x.getMasterList().get((map.getPiece(4) - 1)).getName());

        game.placeHumanPieces(1);
        assertEquals("Expected Entity at 1 to contain a human piece named 'literal ape'","literal ape", x.getMasterList().get((map.getPiece(1) - 1)).getName());
        
        
        //TESTS SELECTIONS
        assertEquals("Expected to be invalid selection (Enemy Piece)",false, turn.isValidSelection(4));
        
        assertEquals("Expected to be invalid selection (Blank Space)",false, turn.isValidSelection(2));

        turn.selectPiece(1);
       
        
        turn.movePiece(2);
        


        //TESTS MOVEMENT
      

        assertEquals("Expected Literal Ape to not be on old space",-1, map.getPiece(1) - 1);

        assertEquals("Expected Literal Ape to be on tile 2","literal ape", x.getMasterList().get((map.getPiece(2) - 1)).getName());

        assertEquals("Expected Literal Ape to have an ap of 1",1, x.getMasterList().get((map.getPiece(2) - 1)).getAP());

        turn.movePiece(3);
      

        assertEquals("Expected Literal Ape to not be on old space",-1, map.getPiece(2) - 1);

        assertEquals("Expected Literal Ape to be on tile 3","literal ape", x.getMasterList().get((map.getPiece(3) - 1)).getName());

        //TESTS AP USEAGE

        
        assertEquals("Expected Literal Ape to have an ap of 0",0, x.getMasterList().get(map.getPiece(3) - 1).getAP());

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

        assertEquals("Expected Enemy to have 1 hp",1, x.getMasterList().get((map.getPiece(4) - 1)).getHp());

        turn.resetTurn();
        

        assertEquals("Expected Literal Ape to have 4 hp",4, x.getMasterList().get((map.getPiece(3) - 1)).getHp());

        //TESTS HEAL

        x.getMasterList().get((map.getPiece(3) - 1)).heal();

        assertEquals("Expected Literal Ape to have an hp of 5",5, x.getMasterList().get(0).getHp());

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