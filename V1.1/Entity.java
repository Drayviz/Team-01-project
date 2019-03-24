public class Entity extends MetaGame{
	
	private double atk;
	private double def;
	private double hp;
	private int party;
	private int state;
	private int ap;
	private int movement;
	private int maxap;
	private String Name = "";
	private Weapon weapon = new Weapon();

	public Entity(String Name, double atk, double def, double hp, int party, int state, int ap, int movement) {
		this.atk = new Double(atk);
		this.def = new Double(def);
		this.hp = new Double(hp);
		this.party = new Integer(party);
		this.state = new Integer(state);
		this.ap = new Integer(ap);
		this.movement = new Integer(movement);
		this.maxap = new Integer(ap);
		this.Name = new String(Name);
	}

	public Entity(Entity copy) {
		this.atk = copy.getAtk();
		this.def = copy.getDef();
		this.hp = copy.getHp();
		this.party = copy.getParty();
		this.state = copy.getState();
		this.ap = copy.getAP();
		this.movement = copy.getMovement();
		this.maxap = copy.getMaxap();
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


	public void ActionTakes(int points)
	{
		this.ap = this.ap - points;
	}
	
	public void setHp(Entity target)
	{
		target.hp = target.hp - (atk - target.def);
	}

	public void heal()
	{
		this.hp ++;
	}

	public void resetap()
	{
		ap = getMaxap();
	}

	public void checkstate()
	{
		if(this.hp <= 0)
		{
			this.state = 0;
		}


	}
	
}
