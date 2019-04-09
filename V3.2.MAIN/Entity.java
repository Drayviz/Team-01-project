
import java.util.Random;
/**
 * ENTITY
 * 
 * Creates different entities based on constructor parameters. 
 * Contains all methods needed when manipulating entities
 * The core of entity communication
 */

public class Entity{
	
	/**
	 * Instance Variables
	 */
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
	private int weapon = 0;
	private int uniqueid = 0;
	private int prefabidentifyer = 0;
	private int flight = 0;
	private int attackMemory = -1;
	/** 
	 * Default Constructor for the class Entity 
	 */
	Entity()
	{

	}
	
	/**
	 * Constructor for the class Entity 
	 * Entity is a class that relays the stats of both player pieces and enemy pieces to various classes 
	 * @param name passing in name as an argument 
	 * @param atk passing in atk as an argument 
	 * @param def passing in def as an argument 
	 * @param hp passing in hp as an argument 
	 * @param party passing in party as an argument 
	 * @param state passing in state as an argument 
	 * @param ap passing in ap as an argument 
	 * @param movement passing in movement as an argument 
	 * @param prefabidentifyer passing in prefabidentifyerp as an argument 
	 * @param flight passing in flight as an arugment 
	 */
	public Entity(String name, int atk, int def, int hp, int party, int state, int ap, int movement,int prefabidentifyer,int flight) {
		this.atk = new Integer(atk);
		this.def = new Integer(def);
		this.hp = new Integer(hp);
		this.party = new Integer(party);
		this.state = new Integer(state);
		this.ap = new Integer(ap);
		this.movement = new Integer(movement);
		this.maxhp = new Integer(hp);
		this.maxap = new Integer(ap);
		this.name = name;
		this.uniqueid = r.nextInt(999999999);
		this.prefabidentifyer = prefabidentifyer;
		this.flight = flight;
		//weapon = new Weapon(prefabidentifyer);
	}

	/**
	 *  Copy constructor for the class entity 
	 * @param copy provide a copy of the specified object
	 */
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
		this.flight = copy.getFlight();
	}

	/** 
	 * getter for prefabIdentifyer
	 * @return the instance variable prefabidentifyer
	 */
	public int getPrefabIdentifyer()
	{
		return new Integer(prefabidentifyer);
	}
	
	/**
	 * getter for flight
	 * @return the instance variable flight
	 */
	public int getFlight() 
	{
		return new Integer(flight);
	}
	
	/**
	 * getter for kills
	 * @return the instance variable kills
	 */
	public int getKills()
	{
		return new Integer(kills);
	}
	
	/**
	 * getter for xp
	 * @return the instance variable xp
	 */
	public int getXp() {
		return new Integer(xp);
	}
	
	/**
	 * getter for maxhp
	 * @return the instance variable maxhp
	 */
	
	public int getMaxhp()
	{
		return new Integer(maxhp);
	}
	
	/**
	 * getter for name
	 * @return the instance variable name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * getter for uniqueid
	 * @return the instance variable uniqueid
	 */
	public int getUniqueId() {
		return new Integer(uniqueid);
	}
	
	/**
	 * getter for weapon
	 * @return the instance variable weapon
	 */
	public int getWeapon(){
		return new Integer(weapon);
	}
	
	/**
	 * getter for atk
	 * @return the instance variable atk
	 */
	public int getAtk() {
		return new Integer(atk);
	}
	
	/**
	 * getter for def
	 * @return the instance variable def
	 */
	public int getDef() {
		return new Integer(def);
	}
	
	/**
	 * getter for hp
	 * @return the instance variable hp
	 */
	public int getHp() {
		return new Integer(hp);
	}
	
	/**
	 * getter for party
	 * @return the instance variable party
	 */
	public int getParty() {
		return new Integer(party);
	}
	
	/**
	 * getter for state
	 * @return the instance variable state
	 */
	public int getState() {
		return new Integer(state);
	}
	
	/**
	 * getter for ap
	 * @return the instance variable ap
	 */
	public int getAP() {
		return new Integer(ap);
	}
	
	/**
	 * getter for movement
	 * @return the instance variable movement
	 */
	public int getMovement()
	{
		return new Integer(movement);
	}
	
	/**
	 * getter for maxap
	 * @return the instance variable maxap
	 */
	public int getMaxap() 
	{
		return new Integer(maxap);
	}
	
	/**
	 * Method checks the state of pieces 
	 * @return the stats of hp and state 
	 */
	public int checkState()
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
	 * Method reduces the pieces total amount of ap if a move or attack is used 
	 * @param points 
	 */
	public void actionTakes(int points)
	{
		
		this.ap = this.ap - points;
	}
	
	/**
	 * setter for hp 
	 * @param target passes target from the class entity as an arugment to change the hp 
	 */
	public void setHp(Entity target)
	{
		if(target.getHp() != 0)
		{
			if(target.getParty() == 1 || target.getParty() == 2)
			{
				target.hp = target.hp - (atk - target.def);

				if(target.checkState() == 0)
				{
					this.addXp(3);
					this.addKill();
				}
				checkState();
			}
			else if(target.getParty() == 3)
			{
				if(this.atk > target.getHp())
				{
					target.hp = 0;
				}
				else
				{
					target.hp -= this.atk;
				}
				checkState();
			}	
		}	
	}
	/**
	 * Method changes the hp and ap to zero if piece lands on a pitfall terrain 
	 */
	public void murder() {
		this.hp= 0;
		this.ap = 0;
	}
	
	/**
	 * Method adds xp to the piece 
	 * @param ammount passes amount of xp gained as an argument 
	 */
	public void addXp(int ammount)
	{
		xp += ammount;
	}
	
	/**
	 * Method stores amount of pieces eliminated 
	 */
	public void addKill()
	{
		kills ++;
	}

	/**
	 * setter for name
	 * @param name passes the inputted name as an argument 
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Method adds hp 
	 */
	public void heal()
	{
		this.hp ++;
	}
	
	/**
	 * Method restores hp and ap back to the max 
	 */
	public void rejuvinate()
	{
		this.hp = this.maxhp;
		this.ap = this.maxap;
	}
	
	/**
	 * Method resets ap back to the max
	 */
	public void resetAp()
	{
		ap = getMaxap();
	}
	/**
	 * Getter for attack memory
	 * @return integer that stores predicted attack
	 */
	public int getAttackMemory()
	{
		return this.attackMemory;
	}
	
	/**
	 * This is for AI, it is for projected attacks, executes it on space in turn
	 * @param takes in the location of an attack
	 * 
	 */
	public void setAttackMemory(int attackMemory){
		this.attackMemory = attackMemory;
	}
	
	
}
