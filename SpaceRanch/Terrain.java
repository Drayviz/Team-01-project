
public class Terrain {
	
	/**
	 * TERRAIN
	 * 
	 * Various terrain checkers for reference by turn
	 * checks different terrain for player and ai logic
	 */
	
	Terrain() 
	{
		
	}
	/**
	 * Method checks if tile terrain is pitfall while entity does not have flight mechanic
	 * @param a is location of entity
	 * @param e is entity in question
	 * @param m is map information
	 */
	public void checkForDeaths(int a,Entity e,MapClass m) 
	{
		 for(int i = 1; i <= m.getDimensions() * m.getDimensions(); i++)
		 {
			if(m.getTerrain(i) == 8 && e.getFlight() == 0)
			{
				pitfallDeath(a, m, e);
			}
		 }
	}
	
	/**
	 * Method checks for terrain type mountain where mountain does not allow movement on that tile
	 * @param location is tile location
	 * @param m is map information
	 * @return boolean statement if tile is terrain type mountain
	 */
	public boolean checkMountain(int location,MapClass m) 
	{
	 	//mountain (can't place piece)
	 	boolean mountainCheck = false;
	    if (m.getTerrain(location) == 6) 
	    {
	    	mountainCheck = true;
	    }
	    return mountainCheck;
	}
	/**
	 * Method checks for terrain type river where river does not allow an entity to attack while on that tile
	 * @param location is tile location
	 * @param m is map information
	 * @return boolean statetement if tile is terrain type river
	 */
	public boolean checkRiver(int location,MapClass m)
	{
		boolean riverCheck = false;
		if(m.getTerrain(location) == 7)
		{
			riverCheck= true;
		}
		return riverCheck;
	}
	
	/**
	 * Method checks for terrain type pitfall where any entity placed on the same tile automatically dies if they are grounded
	 * @param location is tile location
	 * @param m is map information
	 * @param e is entity in question
	 */
	public void pitfallDeath(int location,MapClass m,Entity e)
	{
		if(m.getTerrain(location) == 8)
		{
			e.murder();
			System.out.println(e.getAP());
			System.out.println(e.getHp());
			m.setState(location,1,0);
		}
	}
	
	
}
