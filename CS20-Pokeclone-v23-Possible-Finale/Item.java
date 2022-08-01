public class Item {

  private String name;//Item name
  private String desc;//Item Description
  private String effectType;//What the item does
  private double effect;//How much of an effect an item has, usually a multiplier. Set to 0 for most uniques
  private int amount;//Amount of times an item can be used before depletion

  //Item Constructor
  public Item(String name, String desc, String effectType, double effect, int amount) {
    this.name = name;
    this.desc = desc;
    this.effectType = effectType;
    this.effect = effect;
    this.amount = amount;
  }

  //Return name variable
  public String getName() {
    return name;
  }

  //Return name variable
  public String getDesc() {
    return desc;
  }

  //Returns the effectType variable
  public String getEffectType() {
    return effectType;
  }

  //Returns the effect variable
  public double getEffect() {
    return effect;
  }

  //Returns the amount variable
  public int getAmount() {
    return amount;
  }

  //Changes the amount variable
  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String toString() {
    return amount + "x " + name + " - " + desc;
  }

  
}//End Class
//A feral sense of dominance...