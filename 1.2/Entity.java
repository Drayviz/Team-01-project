
import java.util.Random;

public class Entity extends MetaGame{
	
	Random r = new Random();

	private double atk;
	private double def;
	private double hp;
	private int party;
	private int state;
	private int ap;
	private int movement;
	private int maxap;
	private int maxhp;
	private int xp = 0;
	private int kills = 0;
	private String Name = "";
	private Weapon weapon = new Weapon();
	private int uniqueid = 0;

	public Entity(String Name, double atk, double def, double hp, int party, int state, int ap, int movement) {
		this.atk = new Double(atk);
		this.def = new Double(def);
		this.hp = new Double(hp);
		this.party = new Integer(party);
		this.state = new Integer(state);
		this.ap = new Integer(ap);
		this.movement = new Integer(movement);
		this.hp = new Double(hp);
		this.maxap = new Integer(ap);
		this.Name = new String(Name);
		this.uniqueid = r.nextInt(999999999);
	}

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
		this.kills = copy.getKills();
	}

	public int getKills()
	{
		return new Integer(kills);
	}
	public int getXp() {
		return new Integer(xp);
	}
	public double getMaxhp()
	{
		return new Double(maxhp);
	}
	public String getName()
	{
		return new String(name);
	}
	public int getUniqueid() {
		return new Integer(uniqueid);
	}
	public int getWeapon(){
		return new Weapon(weapon);
	}
	public double getAtk() {
		return new Double(atk);
	}
	public double getDef() {
		return new Double(def);
	}
	public double getHp() {
		return new Double(hp);
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
