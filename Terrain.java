
public class Terrain extends Turn{
	
	/* getTerrain returns tileArray(0), which is the the type ?? */

	
	Terrain() {
		
	}

	
	public boolean checkMountain(int location,Map m) {
	 	//mountain (can't place piece)
	    if (m.getTerrain(location) == 6) {
	    	return true;
	    }else{
	    	return false;
	    }
	    
	}
	public boolean checkRiver(int location,Map m){
		if(m.getTerrain(location) == 7){
			return true;
		}else{
			return false;
		}
	}
	//Don't know where to put in other classes.
	public void pitfallDeath(int location,Map m,Entity e){
		if(m.getTerrain(location) == 8){
			e.murder();
			m.setState(location,1,0);
		}
	}
}
