import java.util.*;

import com.sun.tools.javac.code.Attribute.Array;

public class MapClass
{
    private int dimensions;
    private MapInfo mapinfo;
    private int tilearray[] = {0,0,0,0,0};
    private int tilearray2[] = {0,0,0,0,0};
    private int bufferarray[] = {0,0};
    private MapClass bigone = new MapClass();
    private ArrayList<int[]> maparray = new ArrayList();
    
    //CONSTRUCTORS
    MapClass()
    {

    }
    MapClass(int dimension,int type){
        if (dimension > 0)
        {
        dimensions = dimension;
        int y = 1;
        int count = 0;
        for(int numberoftiles = 0; numberoftiles < dimensions; numberoftiles ++ ){
            int x = 0;
            y -= 1;
            tilearray[4] = y;
            for(int xtiles = 0; xtiles < dimensions; xtiles ++){
                count += 1;
                tilearray[0] = type;
                tilearray[2] = count;
                tilearray[3] = x;
                maparray.add(tilearray);
            }
        }
        }
    }
    MapClass(int dimension, MapInfo ma, ArrayList maparra)
    {   

        dimensions = dimension;
        mapinfo = ma;
        maparray = maparra;
        
    }
    
    MapClass(MapClass biggemap)
    {
        bigone = new ClassMap(biggemap.getDimensions(),biggemap.getMapinfo(),biggemap.getMaparray());
        dimensions = bigone.getDimensions();
        mapinfo = bigone.getMapinfo();
        maparray = bigone.getMaparray();

    }
    //END OF CONSTRUCTORS
    

    //NON-BASIC METHODS
    public Integer getPiece(int location)
    {
        if(location > 0)
        {
            tilearray = maparray.get(location); 
            return new parseInteger(tilearray[1]);
        }
            
    }

    public Integer getTerrain(int location)
    {
        if(location > 0)
        {   
            tilearray = maparray.get(location); 
            return new parseInteger(tilearray[0]);
        }
            
    }

    public int[] getCoords(int location)
    {
        if(location > 0)
        {   

            tilearray = maparray.get(location);
            bufferarray[0] = tilearray[3];
            bufferarray[1] = tilearray[4];
            return bufferarray;
        }


            
    }



    public void setState(int location, int arrayslot, int replacer)
    {
        if(location > 0)
        {
             tilearray = maparray.get(location);
             tilearray[arrayslot] = replacer;
             maparray.set(location,tilearray);

        }

    }

    public void moveState(int location1, int location2)
    {
        if(location1 > 0 && location2 > 0)
        {
             tilearray = maparray.get(location1);
             maparray.setState(location2,1,tilearray[1]);
             maparray.setState(location1,1,0);

        }

    }
    public void switchState(int location1, int location2)
    {
        if(location1 > 0 && location2 > 0)
        {   
             tilearray = maparray.get(location1);
             tilearray2 = maparray.get(location2);
             maparray.setState(location2, 1, tilearray[1]);
             maparray.setState(location1, 1, tilearray2[1]);

        }

    }
    public void saveMap(String filename)
    {
        PrintWriter writer = new PrintWriter("C:/Users/Computer-PC/Desktop/maps/" + filename + ".txt", "UTF-8");
        //for(int i; i < maparray.size(); i ++)
        for (int[] ta : maparray)
        {
           for (int info : ta)
           {
                writer.print(info);
                writer.print(",");
           }
           write.print("\n");

        }

        writer.close();

    }
    private String[] lineToStringArray(String line){
        return line.split(",");
    }

    private int[] stringArrayToIntArray(String[] numsString){
        int intArray[] = new int[numsString.size];
        for (int i = 0; i < lineArray.size(); i++) intArray[i] = Integer.parseInt(numsString[0]);
        return intArray;
    }

    private ArrayList<int[]> streamToArrayList(ArrayList<int[]> acc, int[] line){
        acc.add(line);
    }

    public void loadMap(String filename)
    {
        try(BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Computer-PC/Desktop/maps/" + filename + ".txt"))){
            maparray = reader.lines()
            //lamda functions
                /*.map(line -> {
                    return line.split(",");
                })*/
                .map(lineToStringArray)
                /*.map(lineArray -> {
                    int intArray = new int[lineArray.size()];
                    for (int i = 0; i < lineArray.size(); i++) intArray[i] = Integer.parseInt(lineArray[0]);
                    return intArray;
                })*/
                .map(stringArrayToIntArray)
                //.reduce(new ArrayList<int[]>(), (acc, line)->{acc.add(line);});
                .reduce(new ArryaList<int[]>(), streamToArrayList);
        }

    }

    //NON-BASIC METHODS END



    //GETTERS
    //AAAAAAAAA
    //AAAAAAAA
    public int getDimensions()
    {
        return new MapClass(dimensions,mapinfo,maparray).getDimensions();
    }
   
    public ArrayList getMaparray() 
    {
        return new MapClass(dimensions,mapinfo,maparray).getMaparray();
    }

    public MapInfo getMapinfo() 
    {
        return new MapClass(dimensions,mapinfo,maparray).getMapinfo();
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
    

  