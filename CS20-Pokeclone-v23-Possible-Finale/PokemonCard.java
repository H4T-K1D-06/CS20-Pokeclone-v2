public class PokemonCard {

/* missing code - please refer to google doc for required instance variables */
private String name; //Name of Pokemon
private String type; //Pokemon Type
private int hp; //Current HP of Pokemon
private int totalHp; //Max HP of Pokemon. Doesn't change
private int damage; //Damage Stat of Pokemon 
private double hitChance; //Hit Chance of Pokemon
private boolean isActive; //Is the card active?
	
	/**
	 * @param name
	 * @param hp
	 * @param damage
	 * @param hitChance
	 */
	public PokemonCard(String name, String type, int hp, int damage, double hitChance) {
    this.name = name;
    this.type = type;
    this.hp = hp;
    totalHp = hp;
    this.damage = damage;
    this.hitChance = hitChance;
  }
	/**
	 * @return the name variable
	 */
	public String getName() {
    return name;
	}

  //Returns the type variable
  public String getType() {
    return type;
  }
  
	/**
	 * @return the hp variable
	 */
	public int getHp() {
		return hp;
	}

  //Returns the totalHp variable
  public int getTotalHp() {
    return totalHp;
  }
	
	/**
	 * @param hp set the hp to the value of the parameter
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	/**
	 * @return the damage variable
	 */
	public int getDamage() {
		return damage;
	}

  public void setDamage(int damage) {
    this.damage = damage;
  }

	/**
	 * @return the hitChance variable
	 */
	public double getHitChance() {
		return hitChance;
	}

  public void setHitChance(double hitChance) {
    this.hitChance = hitChance;
  }
	
	/**
	 * @return the isActive variable
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * @param isActive sets the value of isActive to parameter
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 *@return the String representation of this object
	 */
	@Override
	public String toString() {
    return "Name = " + name + ", HP = " + hp + "/" + totalHp + ", Damage = " + damage + ", Hit Chance = " + (int)(hitChance * 100) + "%";
	}
	
}//end PokemonCard class
//This war will cause our world to change...