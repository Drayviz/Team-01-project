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


    private OSValidator a = new OSValidator();    
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
  
    public void setState(int location, int arrayslot, int replacer)
    {
        if(location > 0)
        {
             maparray.get(location - 1).set(arrayslot,replacer);

        }

    }

    public void moveState(int location1, int location2)
    {
        {

             tilearray = maparray.get(location1 - 1);
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
        String file = " ";
        if(a.isWindows())
        {
            file = System.getProperty("user.dir") + "\\" + world + "\\" + filename + ".txt" ;
        }
        else if(a.isUnix())
        {
            file = System.getProperty("user.dir") + "/" + world + "/" + filename + ".txt" ;
        }
        else if(a.isMac())
        {
            file = System.getProperty("user.dir") + "/" + world + "/" + filename + ".txt" ;
        }
        else if(a.isSolaris())
        {
            file = System.getProperty("user.dir") + "/" + world + "/" + filename + ".txt" ;
        }
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

    public void loadMap(String world,String filename)
    {
        maparray.clear();
        String test = "";
        if(a.isWindows())
        {
            test = System.getProperty("user.dir") + "\\" + world + "\\" + filename + ".txt" ;
        }
        else if(a.isUnix())
        {
            test = System.getProperty("user.dir") + "/" + world + "/" + filename + ".txt" ;
        }
        else if(a.isMac())
        {
            test = System.getProperty("user.dir") + "/" + world + "/" + filename + ".txt" ;
        }
        else if(a.isSolaris())
        {
            test = System.getProperty("user.dir") + "/" + world + "/" + filename + ".txt" ;
        }
        this.loadPath(test);
    }
        
    public void loadPath(String a)
    {
        maparray.clear();
        File file = new File(a);
        try{
           Scanner scanner = new Scanner(file);

                this.setDimensions(scanner.nextInt());       
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

    

    //NON-BASIC METHODS END

    //GETTERS
    //AAAAAAAAA
    //AAAAAAAA
    public int getDimensions()
    {
        return new Map(dimensions,mapinfo,maparray).dimensions;
    }
   
    public ArrayList<ArrayList<Integer>> getMaparray() 
    {
        return new Map(dimensions,mapinfo,maparray).maparray;
    }

    public MapInfo getMapinfo() 
    {
        return new Map(dimensions,mapinfo,maparray).mapinfo;
    }

    public Integer getPiece(int location)
    {
        if(location > 0 && location <= dimensions * dimensions)
        {

            tilearray = maparray.get(location - 1); 
            
        }
        return tilearray.get(1);
            
    }

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

    public MapInfo getMapInfo() {
        return new MapInfo(mapinfo);
    }

     //SETTERS
    //AAAAAAAAA
    //AAAAAAAA
    public void setDimensions(int dimension) 
    {
        dimensions = dimension;
    }
  
    public void setMaparray(ArrayList maparra) 
    {
        maparray = maparra;
    }
 
    public void setMapinfo(MapInfo mapinf) 
    {
        mapinfo = mapinf;
    }

}
    

  