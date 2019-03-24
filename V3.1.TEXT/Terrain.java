
public class Terrain {
	
	/* getTerrain returns tileArray(0), which is the the type ?? */
	
	Terrain() {
		
	}

	
	public boolean checkMountain(int location,Map m) 
	{
	 	//mountain (can't place piece)
	 	boolean mountainCheck = false;
	    if (m.getTerrain(location) == 6) 
	    {
	    	
	    	mountainCheck = true;
	    }
	    return mountainCheck;
	}
	public boolean checkRiver(int location,Map m)
	{
		boolean riverCheck = false;
		if(m.getTerrain(location) == 7)
		{
			
			riverCheck= true;
			
		}
	return riverCheck;
	}
	
	public void pitfallDeath(int location,Map m,Entity e){
		if(m.getTerrain(location) == 8){
			e.murder();
			System.out.println(e.getAP());
			System.out.println(e.getHp());
			m.setState(location,1,0);

		}
	}
}
