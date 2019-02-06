import java.util.*;

public class mapClass{
    private int dimensions;
    private int tilearray[] = {0,0,0,0,0};
    private ArrayList maparray = new ArrayList();
    
    mapClass(int dimension){
        dimensions = dimension;
        int x = 0;
        int y = 1;
        int count = 0;
        for(int numberoftiles = 0; numberoftiles < dimensions; numberoftiles ++ ){
            y -= 1;
            tilearray[4] = y;
            for(int xtiles = 0; xtiles < dimensions; xtiles ++){
                count += 1;
                tilearray[2] = count;
                tilearray[3] = x;
                maparray.add(tilearray);
            }
        }
    }

   // mapClass(int dimension, int terrain){
        //dimensions = dimension;
    
}

    }