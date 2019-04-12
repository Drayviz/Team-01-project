public class MapInfo
{
    private int turns = 4;
    private int numofenemies = 3;
    private int maptype = 1;

    MapInfo()
    {

    }
    
    MapInfo(MapInfo big)
    {
        turns = big.getTurns();
        numofenemies = big.getNumofEnemies();
        maptype = big.getMaptype();

    }
    /**
     * @return the maptype
     */
    public int getMaptype() 
    {
        return new Integer(maptype);
    }

    /**
     * @return the numenemies
     */
    public int getNumofEnemies() 
    {
        return new Integer(numofenemies);
    }

    /**
     * @return the turns
     */
    public int getTurns() 
    {
        return new Integer(turns);
    }

    /**
     * @param maptype the maptype to set
     */
    public void setMaptype(int maptype) 
    {
        this.maptype = maptype;
    }

    /**
     * @param numenemies the numenemies to set
     */
    public void setNumenemies(int numenemies) 
    {
        this.numofenemies = numofenemies;
    }
    
    /**
     * @param turns the turns to set
     */
    public void setTurns(int turns) 
    {
        this.turns = turns;
    }


























}