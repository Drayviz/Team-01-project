package Project;

public class Entity {
  private double atk;
	private double def;
	private double hp;
	private int party;
	private int state;
	private int ap;

	public Entity(double atk, double def, double hp, int party, int state, int ap) {
		this.atk = new Double(atk);
		this.def = new Double(def);
		this.hp = new Double(hp);
		this.party = new Integer(party);
		this.state = new Integer(state);
		this.ap = new Integer(ap);
	}
	//copy constructor
	public Entity(Entity copy) {
		this.atk = new Double(copy.atk);
		this.def = new Double(copy.def);
		this.hp = new Double(copy.hp);
		this.party = new Integer(copy.party);
		this.state = new Integer(copy.state);
		this.ap = new Integer(copy.ap);
	}
	
	//getters
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
	public int getAp() {
		return new Integer(ap);
	}
	
	// setters
	public void setHp(Entity target, double dmgDealt) {
		target.hp = target.hp - (dmgDealt)*(target.def/10.0);
	}
	
}

