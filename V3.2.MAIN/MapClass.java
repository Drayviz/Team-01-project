import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.io.File;

public class MapClass
{   
	/**
	 * Instance Variables
	 */
    private int type = 1;
    private int dimensions = 8;
    private ArrayList<Integer> bufferarray = new ArrayList<Integer>(Collections.nCopies(2, 0));
    private ArrayList<ArrayList<Integer>> maparray = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> tilearray = new ArrayList<Integer>(Collections.nCopies(5, 0));
    private ArrayList<Integer> tilearray2 = new ArrayList<Integer>(Collections.nCopies(5, 0));
    private int turns = 4;
    private int numofenemies = 3;
    private int maptype = 1;

    /**
     * Default Constructor for the class Map
     */
    MapClass(){
        
    }
    
    /**
     * Constructor for the class Map
     * Map class initializes the map and controls the board state
     * @param dimension integer
     * @param typ  integer
     */
    
    MapClass(int dimension,int typ)
    {
        if (dimension > 0)
        {
            dimensions = dimension;
            type = typ;
            setMaptype(typ);
            int y = 1;
            int count = 0;
            for(int numberoftiles = 0; numberoftiles < dimensions; numberoftiles ++ ){
                y -= 1;
                tilearray.set(4,y);
                for(int xtiles = 0; xtiles < dimensions; xtiles ++){
                    count += 1;
                    tilearray.set(0,type);
                    tilearray.set(1, 0);
                    tilearray.set(2,count);
                    tilearray.set(3, xtiles);
                    maparray.add(new ArrayList<Integer>(tilearray));
                }
            }
        }
    }
    /**
     * Constructor for map array
     * @param dimension integer
     * @param maparray arraylist
     */
    MapClass(int dimension, ArrayList maparra)
    {   
        setDimensions(dimension);
        maparray = maparra;
    }
    
    /**
     * Copy constructor
     */
    MapClass(MapClass bigone)
    {
        dimensions = bigone.getDimensions();
        turns = bigone.getTurns();
        numofenemies = bigone.getNumofEnemies();
        maptype = bigone.getNumofEnemies();
        maparray = bigone.getMaparray();
    }
  
    

    /**
     * Method that moves piece from location1 to location 2
     * @param location1 is current position of piece
     * @param location2 is desired location
     */
    public void moveState(int location1, int location2)
    {
        {

             tilearray = maparray.get(location1 - 1);
             setState(location2,1,tilearray.get(1));
             setState(location1,1,0);

        }
    /**
     * Method swaps the current state of both tiles selected in parameters
     * @param location1 is current position of piece
     * @param location2 is desired location
     */
    }
    public void switchState(int location1, int location2)
    {
        {   
             tilearray = maparray.get(location1);
             tilearray2 = maparray.get(location2);
             setState(location2, 1, tilearray.get(1));
             setState(location1, 1, tilearray2.get(1));
        }
    }
    
    /**
     * Method takes filename and world name and writes/overwrite into file
     * @param filename passes name of file
	 * @param world...
     */
    public void saveMap(String filename,String world)
    {
        String file = " ";
        
        file = System.getProperty("user.dir") + System.getProperty("file.separator") + world + System.getProperty("file.separator") + filename + ".txt" ;
            try
            {
                FileOutputStream outStr = new FileOutputStream(file,true);
                PrintWriter writer = new PrintWriter(file);
                writer.print(this.getDimensions());
                writer.print(" ");
                writer.print(getTurns());
                writer.print(" ");
                writer.print(getNumofEnemies());
                writer.print(" ");
                writer.print(getMaptype());
                writer.print(" ");
                writer.print("\n");
                for (ArrayList<Integer> ta : maparray)
                {
                    for (int info : ta)
                    {       
                            writer.print(info);
                            writer.print(" ");
                    }
                    writer.print("\n");
                }
                writer.close();
            }
            catch(FileNotFoundException fnfe)
            {    
                System.out.println(fnfe.getMessage());
            }
    }  
    /**
     * Method that reads file in path taken in parameter
     * @param path to filename
     */
    public void loadPath(String a)
    {
        maparray.clear();
        File file = new File(a);

        try{
           Scanner scanner = new Scanner(file);

                this.setDimensions(scanner.nextInt());       
                setTurns(scanner.nextInt()); ; 
                setNumenemies(scanner.nextInt()); ; 
                setMaptype(scanner.nextInt()); ; 
                maparray.clear();

            while(scanner.hasNextInt())
            {

                for(int i = 0;i < 5;i++)
                {
                    tilearray.set(i,(scanner.nextInt()));

                }
                maparray.add(new ArrayList<Integer>(tilearray));
            }
            scanner.close();
            }
            
        catch(FileNotFoundException e)
            {   
                e.printStackTrace();
            }       
    }
    
    
    /**
     * Method displays map in text base game
     */
    public void displayMap()
    {
        int rowcounter = 0;

        for(int d = 0; d < dimensions + 1; d++)
        {
            System.out.print("{" + d + "}");
        }

        for(int i = 0;i < maparray.size();i ++)
        {
            if(i % dimensions == 0)
            {
                rowcounter += 1;
                System.out.print("\n");
                System.out.print("{" + rowcounter  + "}");
            }
            tilearray = maparray.get(i);
            if(tilearray.get(0) == 1 && tilearray.get(1) == 0)
                {
                System.out.print("[+]");
                }
            else if(tilearray.get(0) == 2 && tilearray.get(1) == 0)
                {
                System.out.print("[-]");
                }
            else if(tilearray.get(0) == 3 && tilearray.get(1) == 0)
                {
                System.out.print("[=]");
                }
            else if(tilearray.get(0) == 4 && tilearray.get(1) == 0)
                {
                System.out.print("[x]");
                }
            else if(tilearray.get(0) == 5 && tilearray.get(1) == 0)
                {
                System.out.print("[H]");
                }
            else if(tilearray.get(0) == 6 && tilearray.get(1) == 0)
                {
                System.out.print("[M]");
                }
            else if(tilearray.get(0) == 7 && tilearray.get(1) == 0)
                {
                System.out.print("[R]");
                }
            else if(tilearray.get(0) == 8 && tilearray.get(1) == 0)
                {
                System.out.print("[P]");
                }


            else
                {
                System.out.print("[" + tilearray.get(1) + "]");
                }

            }
            System.out.print("\n");
    }
    
    /**
     * Getter for dimensions
     * @return dimensions given
     */
    public int getDimensions()
    {
        return new Integer(this.dimensions);
    }
    /**
     * Getter for map type
     * @return the maptype
     */
    public int getMaptype() 
    {
         return new Integer(maptype);
    }
    
    /**
     * getter for number of enemies
     * @return the numenemies
     */
    public int getNumofEnemies() 
    {
        return new Integer(numofenemies);
    }
    
    /**
     * getter for number of turns
     * @return the turns
    */
    public int getTurns() 
    {
        return new Integer(turns);
    }
    
    /**
    * Getter for map array 
    *@param maptype the maptype to set
    */
    public ArrayList<ArrayList<Integer>> getMaparray()
    {
        return this.maparray;
    }

    /**
     * Getter for pieces
     * @param location of piece
     * @return piece in arraylist
     */
    public Integer getPiece(int location)
    {
        if(location > 0 && location <= dimensions * dimensions)
        {

            tilearray = maparray.get(location - 1); 
            
        }
        return tilearray.get(1);
            
    }

    
    /**
     * Getter for piece location
     * @param takes in piece in question
     * @return index of piece
     * 
     */
    public Integer getPieceLocation(int piece)
    {
        int decoy = 0;
        for(ArrayList<Integer> e:maparray)
        {
            if(e.get(1) == piece)
            {
                decoy = e.get(2);
            }
        }
        return decoy;
            
    }
    /**
     * Getter for terrain
     * @param takes in tile location
     * @return terrain type
     */
    public int getTerrain(int location)
    {
        if(location > 0)
        {   
            tilearray = maparray.get(location -1 ); 
            
        } 
        else{
            tilearray.set(0, 0);
        }
        return tilearray.get(0);    
    }
    /**
     * Getter for coordinates
     * @param takes in tile array
     * @return coordinates in x,y format
     */
    public ArrayList<Integer> getCoords(int location)
    {
        if(location > 0)
        {   
            tilearray = maparray.get(location);
            bufferarray.set(0, tilearray.get(3));
            bufferarray.set(1, tilearray.get(4));
           
        }
        return bufferarray;    
    }


    /**
     * Setter for dimensions
     * @param dimension of map
     * sets value for instance variable dimensions
     */
    public void setDimensions(int dimension) 
    {
        dimensions = dimension;
    }
    /**
     * Setter for map array
     * @param array list holding all map tiles
     * sets value for instance variable maparray
     */
    public void setMaparray(ArrayList maparra) 
    {
        maparray = maparra;
    }
    
    /**
     * Setter for maptype
     * @param takes in integer for map type
     * sets value for instance variable maptype
     */
    public void setMaptype(int maptype) 
    {
        this.maptype = maptype;
    }
    
    /**
     * Setter for number of enemies
     * @param number of enemies given
     * sets value for instance variable numofenemies
     */
    public void setNumenemies(int numenemies) 
    {
        this.numofenemies = numenemies;
    }
        
    /**
     * Setter for Turns
     * @param number of turns given
     * set values for instance variable turns
     */
    public void setTurns(int turns) 
    {
        this.turns = turns;
    }
     
    /**
     * Setter for state
     * @param location of piece
     * @param arrayslot is the index of piece
     * @param replacer
     * set values for values in map array
     */
    public void setState(int location, int arrayslot, int replacer)
    {
        if(location > 0)
        {
            maparray.get(location - 1).set(arrayslot,replacer);
    
        }
    
    }
    
}
    

  