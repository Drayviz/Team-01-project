
import java.util.Random;

public class Entity extends MetaGame{
	/** Instance variables for the class that are set to default values */
	Random r = new Random();

	private int atk;
	private int def;
	private int hp;
	private int party;
	private int state;
	private int ap;
	private int movement;
	private int maxap;
	private int maxhp;
	private int xp = 0;
	private int kills = 0;
	private String name = "";
	private Weapon weapon = new Weapon();
	private int uniqueid = 0;
	private int prefabidentifyer = 0;
	
	/** Constructor */
	Entity()
	{

	}
	
	/** Constructor.
     	* @param name Takes name as a parameter from instance variable. 
     	* @param atk Takes atk as a parameter from instance variable.
     	* @param def Takes def as a parameter from instance variable.
	* @param hp Takes hp as a parameter from instance variable. 
     	* @param party Takes party as a parameter from instance variable.
     	* @param state Takes state as a parameter from instance variable
	* @param ap Takes ap as a paramete from instance variable
     	* @param movement Takes movement as a parameter from instance variable
	* @param prefabidentifyer Takes prefabidentifyer as a parameter from instance variable
	*/
	public Entity(String name, int atk, int def, int hp, int party, int state, int ap, int movement,int prefabidentifyer) {
		this.atk = new Integer(atk);
		this.def = new Integer(def);
		this.hp = new Integer(hp);
		this.party = new Integer(party);
		this.state = new Integer(state);
		this.ap = new Integer(ap);
		this.movement = new Integer(movement);
		this.hp = new Integer(hp);
		this.maxap = new Integer(ap);
		this.name = name;
		this.uniqueid = r.nextInt(999999999);
		this.prefabidentifyer = prefabidentifyer;
		//weapon = new Weapon(prefabidentifyer);
	}
	/** Copy constructor
	* @param copy Takes an Entity object as an argument and creates a copy of it*/
	public Entity(Entity copy) {
		this.atk = copy.getAtk();
		this.def = copy.getDef();
		this.hp = copy.getHp();
		this.party = copy.getParty();
		this.state = copy.getState();
		this.ap = copy.getAP();
		this.movement = copy.getMovement();
		this.maxhp = copy.getMaxhp();
		this.maxap = copy.getMaxap();
		this.weapon = copy.getWeapon();
		this.uniqueid = copy.getUniqueId();
		this.xp = copy.getXp();
		this.name = copy.getName();
		this.kills = copy.getKills();
		this.prefabidentifyer = copy.getPrefabIdentifyer();
	}

	/** Getter. 
     	* @return prefabidentifyer. Used to return the instance variable prefabidentifyer.*/
	public int getPrefabIdentifyer()
	{
		return new Integer(prefabidentifyer);
	}
	
	/** Getter. 
     	* @return kills. Used to return the instance variable kills.*/
	public int getKills()
	{
		return new Integer(kills);
	}
	
	/** Getter. 
     	* @return xp. Used to return the instance variable xp.*/
	public int getXp() {
		return new Integer(xp);
	}
	
	/** Getter. 
     	* @return maxhp. Used to return the instance variable maxhp.*/
	public int getMaxhp()
	{
		return new Integer(maxhp);
	}
	
	/** Getter. 
     	* @return name. Used to return the instance variable name.*/
	public String getName()
	{
		return name;
	}
	
	/** Getter. 
     	* @return uniqueid. Used to return the instance variable uniqueid.*/
	public int getUniqueId() {
		return new Integer(uniqueid);
	}
	
	/** Getter. 
     	* @return weapon. Used to return the instance variable weapon.*/
	public Weapon getWeapon(){
		return new Weapon(weapon);
	}
	
	/** Getter. 
     	* @return atk. Used to return the instance variable atk.*/
	public int getAtk() {
		return new Integer(atk);
	}
	
	/** Getter. 
     	* @return def. Used to return the instance variable def.*/
	public int getDef() {
		return new Integer(def);
	}
	
	/** Getter. 
     	* @return hp. Used to return the instance variable hp.*/
	public int getHp() {
		return new Integer(hp);
	}
	
	/** Getter. 
     	* @return party. Used to return the instance variable party.*/
	public int getParty() {
		return new Integer(party);
	}
	
	/** Getter. 
     	* @return state. Used to return the instance variable state.*/
	public int getState() {
		return new Integer(state);
	}
	
	/** Getter. 
     	* @return ap. Used to return the instance variable ap.*/
	public int getAP() {
		return new Integer(ap);
	}
	
	/** Getter. 
     	* @return movement. Used to return the instance variable movement.*/
	public int getMovement()
	{
		return new Integer(movement);
	}
	
	/** Getter. 
     	* @return maxap. Used to return the instance variable maxap.*/
	public int getMaxap() 
	{
		return new Integer(maxap);
	}
	
	/**
	*Function used to check state of pieces 
	*@returns test. Used to return the state of the piece*/
	public int checkstate()
	{
		int test = 0;
		if(this.hp <= 0)
		{
			this.state = 0;
			test = 1;
		}
		return test;
	}
	
	/**
	*Keeps track of action points which allows pieces to attack */
	public void ActionTakes(int points)
	{
		this.ap = this.ap - points;
	}
	
	/**Setter.
    	* Used to set the value for the instance variable <hp>.
    	* @param target This is new target they'd like to set.*/
	public void setHp(Entity target)
	{
		target.hp = target.hp - (atk - target.def);
		if(target.checkstate() == 1)
		{
			this.addXp(3);
			this.addKill();
		}
	}
	/**
	*@param ammount Takes ammount as a parameter 
	*Adds xp gained to the variable xp */
	public void addXp(int ammount)
	{
		xp += ammount;
	}
	
	/**
	*Every kill increments kills by one, used to keep track of total kills */
	public void addKill()
	{
		kills ++;
	}

	/**Setter.
     	* Used to set the value for the instance variable <name>.
     	* @param name This is new name they'd like to set.*/
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	*Each heal increments by one */
	public void heal()
	{
		this.hp ++;
	}
	
	/**
	*Function used to restore maxphp and maxap */
	public void rejuvinate()
	{
		this.hp = this.maxhp;
		this.ap = this.maxap;
	}
	
	/**
	*Function used to reset maxap back to its original */
	public void resetap()
	{
		ap = getMaxap();
	}

	
	
}
