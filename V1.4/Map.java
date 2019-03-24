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


public class Map
{   
    /** Instance variables for the class that are set to default values */
    private int type = 1;
    private int dimensions = 8;
    private MapInfo mapinfo = new MapInfo();
    private ArrayList<Integer> bufferarray = new ArrayList<Integer>(Collections.nCopies(2, 0));
    private ArrayList<ArrayList<Integer>> maparray = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> tilearray = new ArrayList<Integer>(Collections.nCopies(5, 0));
    private ArrayList<Integer> tilearray2 = new ArrayList<Integer>(Collections.nCopies(5, 0));
    
    
    //CONSTRUCTORS
    Map()
    {

    }
    /** Constructor.
     	* @param name Takes dimension as a parameter. 
     	* @param atk Takes typ as a parameter.
     .
        */
    Map(int dimension,int typ){
        if (dimension > 0)
        {
        dimensions = dimension;
        type = typ;
        mapinfo.setMaptype(type);
        int y = 1;
        int count = -1;
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
    /** Copy constructor
	* @param  Takes an dimensions, mapinfo object and array list maparra as arguments and creates a copies*/
    Map(int dimension, MapInfo ma, ArrayList maparra)
    {   

        dimensions = dimension;
        mapinfo = ma;
        maparray = maparra;
        
    }
    
    
    Map(Map biggemap)
    {
        Map bigone = new Map(biggemap.getDimensions(),biggemap.getMapinfo(),biggemap.getMaparray());
        dimensions = bigone.getDimensions();
        mapinfo = bigone.getMapinfo();
        maparray = bigone.getMaparray();

    }
    //END OF CONSTRUCTORS
    

    //NON-BASIC METHODS
  
    /**Setter.
    	* Used to set the state of map
    	* @param location, arrayslot and replacer.*/
    public void setState(int location, int arrayslot, int replacer)
    {
        if(location > 0)
        {
             maparray.get(location - 1).set(arrayslot,replacer);

        }

    }
    //* Method that uses setState to determine the map
    public void moveState(int location1, int location2)
    {
        {

             tilearray = maparray.get(location1);
             setState(location2,1,tilearray.get(1));
             setState(location1,1,0);

        }

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

    public void saveMap(String filename,String world)
    {
        String file = System.getProperty("user.dir") + "\\" + world + "\\" + filename + ".txt";
            try{
                FileOutputStream outStr = new FileOutputStream(file,true);
                PrintWriter writer = new PrintWriter(file);
                writer.print(this.getDimensions());
                writer.print(" ");
                writer.print(mapinfo.getTurns());
                writer.print(" ");
                writer.print(mapinfo.getNumofEnemies());
                writer.print(" ");
                writer.print(mapinfo.getMaptype());
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

    public void loadMap(String filename)
    {
        maparray.clear();
        File file = new File(filename + ".txt");
        try{
           Scanner scanner = new Scanner(file);

            
                this.setDimensions(scanner.nextInt()); 
                System.out.println(dimensions);
                mapinfo.setTurns(scanner.nextInt()); ; 
                mapinfo.setNumenemies(scanner.nextInt()); ; 
                mapinfo.setMaptype(scanner.nextInt()); ; 
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

    
    //* Method that displays map text base version
    public void displayMap()
    {
    int rowcounter = 0;

    for(int d = 0; d < dimensions + 1; d++)
    {
        System.out.print(d);

    }

    for(int i = 0;i < maparray.size();i ++)
    {
        if(i % dimensions == 0)
        {
            rowcounter += 1;
            System.out.print("\n");
            System.out.print(rowcounter);
        }
        tilearray = maparray.get(i);
        if(tilearray.get(0) == 1 && tilearray.get(1) == 0)
            {
            System.out.print("+");
            }
        else if(tilearray.get(0) == 2 && tilearray.get(1) == 0)
            {
            System.out.print("-");
            }
        else if(tilearray.get(0) == 3 && tilearray.get(1) == 0)
            {
            System.out.print("=");
            }
        else if(tilearray.get(0) == 4 && tilearray.get(1) == 0)
            {
            System.out.print("x");
            }
        else if(tilearray.get(0) == 5 && tilearray.get(1) == 0)
            {
            System.out.print("H");
            }
        else
            {
            System.out.print(tilearray.get(1));
            }

        }
        System.out.print("\n");
    }

    

    //NON-BASIC METHODS END

    /*GETTER
    *return Map object dimensions.*/ 
    public int getDimensions()
    {
        return new Map(dimensions,mapinfo,maparray).dimensions;
    }
   
    /*GETTER
    @return Map objectarray lists.*/
    public ArrayList<ArrayList<Integer>> getMaparray() 
    {
        return new Map(dimensions,mapinfo,maparray).maparray;
    }
    
    
    /*GETTER
    @return map object map info.*/
    public MapInfo getMapinfo() 
    {
        return new Map(dimensions,mapinfo,maparray).mapinfo;
    }
    
    
    /*GETTER
    @return piece on arraylist*/
    public Integer getPiece(int location)
    {
        if(location > 0 && location < dimensions * dimensions)
        {
            tilearray = maparray.get(location); 
            
        }
        return tilearray.get(1);
            
    }
    
    /*GETTER
    @param piece as an integer
    @return decoy to get piece location*/
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

    
    /*GETTER
    @return get terrain*/
    public int getTerrain(int location)
    {
        if(location > 0)
        {   
            tilearray = maparray.get(location); 
            
        } 
        else{
            tilearray.set(0, 0);
        }
        return tilearray.get(0);    
    }

    /*GETTER
    @return array list to get coordinates*/
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
    
    /*GETTER
    @return map info object */
    public MapInfo getMapInfo() {
        return new MapInfo(mapinfo);
    }

    
    /**Setter.
    * Used to set the value for the instance variable dimension.
    * @param target This is new dimension they'd like to set.*/
    public void setDimensions(int dimension) 
    {
        dimensions = dimension;
    }
    /**Setter.
    * Used to set the value for the instance variable maparray.
    * @param target This is new map array they'd like to set.*/
    public void setMaparray(ArrayList maparra) 
    {
        maparray = maparra;
    }
    /**Setter.
    * Used to set the value for the instance variable mapinfon.
    * @param target This is new mapinfo they'd like to set.*/
    public void setMapinfo(MapInfo mapinf) 
    {
        mapinfo = mapinf;
    }

}
    

  
