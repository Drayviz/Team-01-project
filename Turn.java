public class Turn {
/*     private GameState gameState;
    private HumanPlayer humanP;
    private AIPlayer aiP; unnecessary?*/

    private Map map;
    private int startLocation;
    private int end;
    private Entity player;

    /* Constructor for moves */
    public Turn(int startLocation, int end, Entity player) {
        this.startLocation = new Integer(startLocation);
        this.end = new Integer(end);
        this.player = new Entity(player);
    }

    /* Constructor for attacks */
    public Turn(int startLocation, int end) {
        this.startLocation = new Integer(startLocation);
        this.end = new Integer(end);
    }

    /* temp is just to create a duplicate of the map. This is to execute moves on. However, this execution will be passed into GameState to ensure
    whatever was done was valid, THEN the map will be updated (via GameState) */
    public Map move(int startLocation, int end, Entity player) { 
        Map temp = new Map(map); //need Map copy constructor
        for (Array a:temp) {
            if (a[2] == startLocation) { //finds the tile to start on...
                a[1] = 0; //removes the entity on that tile, whatever it may be
            }
        }
        for (Array b:temp) {
            if (b[2] == end) { //finds the tile to move to...
                b[1] = player; //places the APPROPRIATE PIECE
            }
        }
        return temp;
    }

    /* Same principle as move() */
    public Map attack(int startLocation, int end) { //can't keep track of who can get attacked; put this method on hold... 
        Map temp = new Map(map); //need Map copy constructor
        for (Array a:temp) {
            if (a[2] == startLocation) { //finds the tile attacker...
                int dmgDealt = a[1].getAtk(); //attacker's attack stat will be taken to consider how much dmg he'll do
            }
        }
        for (Array b:temp) {
            if (b[2] == end) { //finds the tile of target...
                b[1].setHP(b[1], dmgDealt); //target's HP will be set (refer to Entity.java)
            }
        }
        return temp;
    }
}
