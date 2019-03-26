
import java.util.Random;

public class Entity{
	
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
		this.maxhp = new Integer(hp);
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

	
	public int getKills()
	{
		return new Integer(kills);
	}
	public int getXp() {
		return new Integer(xp);
	}
	public int getMaxhp()
	{
		return new Integer(maxhp);
	}
	public String getName()
	{
		return name;
	}
	public int getUniqueId() {
		return new Integer(uniqueid);
	}
	public Weapon getWeapon(){
		return new Weapon(weapon);
	}
	public int getAtk() {
		return new Integer(atk);
	}
	
	public int getDef() {
		return new Integer(def);
	}
	public int getHp() {
		return new Integer(hp);
	}
	public int getParty() {
		return new Integer(party);
	}
	public int getState() {
		return new Integer(state);
	}
	public int getAP() {
		return new Integer(ap);
	}
	public int getMovement()
	{
		return new Integer(movement);
	}
	public int getMaxap() 
	{
		return new Integer(maxap);
	}

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
	public void ActionTakes(int points)
	{
		this.ap = this.ap - points;
	}
	
	public void setHp(Entity target)
	{
		target.hp = target.hp - (atk - target.def);
		if(target.checkstate() == 1)
		{
			this.addXp(3);
			this.addKill();
		}
		checkstate();
	}
	
	public void murder() {
		this.hp= 0;
		this.ap = 0;
	}

	public void addXp(int ammount)
	{
		xp += ammount;
	}

	public void addKill()
	{
		kills ++;
	}


	public void setName(String name)
	{
		this.name = name;
	}

	public void heal()
	{
		this.hp ++;
	}

	public void rejuvinate()
	{
		this.hp = this.maxhp;
		this.ap = this.maxap;
	}

	public void resetap()
	{
		ap = getMaxap();
	}

	
	
}
