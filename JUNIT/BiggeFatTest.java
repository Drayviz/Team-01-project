import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BiggeFatTest 
{
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
        maparray.add(buffer);
        assertEquals("Expected Second Tile To Be [1,0,1,1,0]",maparray.get(1), test.getMaparray().get(1));
        buffer.set(3,0);
        buffer.set(4,1);
        maparray.add(buffer);
        assertEquals("Expected Third Tile To Be [1,0,1,0,1]",maparray.get(2), test.getMaparray().get(2));
        buffer.set(3,1);
        maparray.add(buffer);
        assertEquals("Expected Fourth Tile To Be [1,0,1,1,1]",maparray.get(3), test.getMaparray().get(3));
       

    }
    public void test_SaveMapAndLoad() 
    {
        Map test = new Map(1,1);
        MapInfo a = new MapInfo();
        ArrayList<ArrayList<Integer>> maparray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> buffer = new ArrayList<Integer>();
       
        buffer.add(1);
        buffer.add(0);
        buffer.add(1);
        buffer.add(0);
        buffer.add(0);
        maparray.add(buffer);
        test.setMaptype(2);
        test.setNumenemies(10);
        test.setTurns(6);
        test.setTurns(6);
        test.setDimensions(4);
        test.saveMap("test", "one");
        test.loadMap("one", "test");
        assertEquals("Expected Map to To contain one tile [1,0,1,0,0]",maparray, test.getMaparray());
        assertEquals("Expected Map Type to be 2",2, test.getMaptype());
        assertEquals("Expected Numenemies to be 10",10, test.getNumofEnemies());
        assertEquals("Expected Turns to be 6",6, test.getTurns());
        assertEquals("Expected Dimensions to be 4",4, test.getDimensions());
    }
  
    public void test_Select()
    {

    }
    public void test_Attack() 
    {
      

    }
    public void test_Heal() 
    {
      

    }
    public void test_StandardMove() 
    {
      

    }
    public void test_EdgeCaseMoves() 
    {
      

    }
    public void test_MoveOntoPit() 
    {
      

    }
    public void test_MoveOntoEnemy() 
    {
      

    }
    public void test_MoveOntoMountain() 
    {
      

    }
    public void test_MoveOntoFire()
    {
      

    }
    public void test_ApUsedThenMove() 
    {
      

    }
    public void test_ApUsedThenAttack() 
    {
      

    }
  

   





}