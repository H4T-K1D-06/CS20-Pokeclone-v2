import java.util.ArrayList; //Arrays are cool
import java.util.Random; //RNG is cooler

public class Player {
	private ArrayList<PokemonCard> allCardsList = new MasterCardList().getAllCardsList(); //Grabs a copy of the Master Card List
	private PokemonCard[] hand = new PokemonCard[6]; //Represents the player's hand
  private ArrayList<Item> allItemsList = new MasterItemList().getAllItemsList(); //Grabs a copy of the Master Item List
	private Item[] bag = new Item[5]; //Represents the player's item bag
  private Random r = new Random(); //Random number generator
  private int activeCardIndex = 0; //The index of the current active card
  private PokemonCard activeCard; //The Active card
  private String playerName; //The player's name
  private Item activeItem;//The Item Choice
  private PokemonCard itemTarget;//The item's target Pokemon
	
  //Constructor that allows a player to enter a name
  public Player(String playerName) {
    this.playerName = playerName;
    if (!(playerName.equals("Supreme Calamitas"))) {
      allCardsList.remove(20);
      allCardsList.remove(19);
      allCardsList.remove(18);
      allCardsList.remove(17);
      allCardsList.remove(16);
      allCardsList.remove(15);
      for(int i = 0; i < 6; i++) {//randomly choose 5 cards
        hand[i]=allCardsList.remove(r.nextInt(allCardsList.size()));
      }
    } else {
      hand[0] = allCardsList.get(15);
      hand[1] = allCardsList.get(16);
      hand[2] = allCardsList.get(17);
      hand[3] = allCardsList.get(18);
      hand[4] = allCardsList.get(19);
      hand[5] = allCardsList.get(20);
    }

    //Prevents Overpowered cards from appearing first in the hand and reduces their chance to appear overall.
    if(hand[0].getName().equals("MissingNo.")) {
      hand[0] = allCardsList.remove(r.nextInt(allCardsList.size()));
    }
    if(hand[0].getName().equals("Arceus")) {
      hand[0] = allCardsList.remove(r.nextInt(allCardsList.size()));
    }
    if(hand[0].getName().equals("Donald J. Trump")) {
      hand[0] = allCardsList.remove(r.nextInt(allCardsList.size()));
    }

    for(int i = 0; i < 5; i++) {//randomly choose 5 items
			bag[i]=allItemsList.remove(r.nextInt(allItemsList.size()));
    }
	}
	
  //Returns the Active Card
	public PokemonCard getActiveCard() {
		return activeCard;
	}

  //Returns the Active Card's index
	public int getActiveCardIndex() {
    return activeCardIndex;
	}

  //Returns the player's hand
	public PokemonCard[] getHand() {
		return hand;
	}

  //Returns the player's name
  public String getName() {
    return playerName;
  }

  //Sets the Active Card
	public void activateCard(int cardIndex) {
		 hand[cardIndex].setActive(true); //Sets the card to the active state
     activeCardIndex = cardIndex; //Updates activeCardIndex
     activeCard = hand[cardIndex]; //Updates the new card
	}

  //Sets the Active Item 
  public void activateItem(int itemIndex) {
    activeItem = bag[itemIndex];
  }

  //Returns the activeItem variable
  public Item getActiveItem() {
    return activeItem;
  }

  //Sets the target Pokemon for item use
  public void setItemTarget(int cardIndex) {
    itemTarget = hand[cardIndex];
  }

  //Returns the itemTarget variable
  public PokemonCard getItemTarget() {
    return itemTarget;
  }

  //Prints the Player's Hand
	public void showHand() {
    for (int i = 0; i < 6; i++) {
      if (hand[i].getHp() != 0) {
        System.out.println("[" + i + "] " + hand[i]); //Standard
      } else {
        System.out.println("[FAINTED] " + hand[i]); //If Pokemon has 0 HP.
      }
     }
	}

  //Prints the Player's Bag
	public void showBag() {
    for (int i = 0; i < 5; i++) {
      if (bag[i].getAmount() != 0) {
        System.out.println("[" + i + "] " + bag[i]); //Standard
      } else {
        System.out.println("[DEPLETED] " + bag[i]); //If item runs out of uses
      }
     }
	}
	
  //Attack method
	public boolean attack (PokemonCard p2Card) {
		if (Math.random() <= activeCard.getHitChance()) {//Rolls the hit
      if (Math.random() <= 0.05) {//Rolls a critical hit
        System.out.println(activeCard.getName() + " scores a CRITICAL HIT!");
        System.out.println(activeCard.getName() + " deals " +  (activeCard.getDamage() * 2) + " damage!");
        p2Card.setHp(p2Card.getHp() - (activeCard.getDamage() * 2)); //Damage Calculation
      } else {//Normal hit
      System.out.println(activeCard.getName() + " deals " +  activeCard.getDamage() + " damage!");
      p2Card.setHp(p2Card.getHp() - activeCard.getDamage()); //Damage calculation
      }//End if else
      if (p2Card.getHp() < 0) {//Sets card HP to 0 if it goes under
        p2Card.setHp(0); 
      }
      System.out.println(p2Card.getName() + " now has " + p2Card.getHp() + "/" + p2Card.getTotalHp() + " HP remaining!"); //Displays remaining HP
      return true;
    } else {//Miss
      System.out.println(activeCard.getName() + " Missed it's attack!");
      return false;
    }
	}//End method

  //Use Item Method
  public void useItem (PokemonCard targetCard) {
    System.out.println("\n" + playerName + " used " + activeItem.getName() +" on " + targetCard.getName() + "...");
    if (activeItem.getEffectType().equals("Restoration") && targetCard.getHp() < targetCard.getTotalHp() && targetCard.getHp() != 0) {//If healing item
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      targetCard.setHp(targetCard.getHp() + (int)(targetCard.getTotalHp() * activeItem.getEffect()));
      if (targetCard.getHp() > targetCard.getTotalHp()) {//Prevents Overhealing
        targetCard.setHp(targetCard.getTotalHp());
      }
      System.out.println(targetCard.getName() + " now has " + targetCard.getHp() + " HP!");
    } else if (activeItem.getEffectType().equals("Revive") && targetCard.getHp() == 0) {//If revive item
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      targetCard.setHp(targetCard.getHp() + (int)(targetCard.getTotalHp() * activeItem.getEffect()));
      System.out.println(targetCard.getName() + " was revived and now has " + targetCard.getHp() + " HP!");
    } else if (activeItem.getEffectType().equals("AtkBuff")) {//If attack buff
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      targetCard.setDamage(targetCard.getDamage() + (int)(activeItem.getEffect() * targetCard.getDamage()));
      System.out.println(targetCard.getName() + "'s DAMAGE rose to " + + targetCard.getDamage() + "!");
    } else if (activeItem.getEffectType().equals("AccBuff")) {//If Hit Chance buff
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      targetCard.setHitChance(targetCard.getHitChance() + activeItem.getEffect());
      if (targetCard.getHitChance() > 0.9) {//Prevents a hit chance over 90%
        targetCard.setHitChance(0.9);
      }
      System.out.println(targetCard.getName() + "'s HIT CHANCE rose to " + ((int) (targetCard.getHitChance() * 100 )) + "%!");
    } else if (activeItem.getEffectType().equals("AtkDebuff") && targetCard.getDamage() != 10) {//If Attack Debuff
      activeItem.setAmount(activeItem.getAmount() - 1);
      targetCard.setDamage(targetCard.getDamage() - (int)(activeItem.getEffect() * targetCard.getDamage()));
      if (targetCard.getDamage() < 10) {//Prevents an attack value less than 10
        targetCard.setDamage(10);
      }
      System.out.println(targetCard.getName() + "'s DAMAGE fell to " + targetCard.getDamage() + "!");
    } else if (activeItem.getEffectType().equals("AccDebuff")) {//If Hit Chance debuff
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      targetCard.setHitChance(targetCard.getHitChance() - activeItem.getEffect());
      if (targetCard.getHitChance() < 0.1) {//Prevents a hit chance under 10%
        targetCard.setHitChance(0.1);
      }
      System.out.println(targetCard.getName() + "'s HIT CHANCE fell to " + ((int) (targetCard.getHitChance() * 100 )) + "%!");
    } else if (activeItem.getEffectType().equals("Hotfix") && targetCard.getName().equals("MissingNo.")) {//If Hotfix item and target is MissingNo.
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      targetCard.setHp(0);
      System.out.println("HOTFIXES APPLIED!");
    } else if (activeItem.getEffectType().equals("Democrat") && targetCard.getName().equals("Donald J. Trump")) {//If Joe Biden item and target is Donald J. Trump
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      targetCard.setHp(0);
      System.out.println("2021 is now.");
    } else if (activeItem.getEffectType().equals("Nuke")) {//If Norfleet
      activeItem.setAmount(activeItem.getAmount() - 1);//Consumes an item
      if (activeItem.getAmount() < 0) {//Band-aid code to prevent Norfleet bug
        activeItem.setAmount(0);
      }
      targetCard.setHp(0);
      double rand = Math.random();
      if (rand < 0.25) { //Random message generator for flavour
        System.out.println(targetCard.getName() + " disappeared in a Cataclysmic Blast...");
      } else if (rand >= 0.25 && rand < 0.50) {
        System.out.println(targetCard.getName() + " burst into sinless ash...");
      } else if (rand >= 0.50 && rand < 0.75) {
        System.out.println(targetCard.getName() + "'s soul was extinguished...");
      }else if (rand >= 0.75) {
        System.out.println(targetCard.getName() + " wasn't worthy...");
      }
    } else {//If no other criteria is filled.
      System.out.println("But it failed!");
    }
  }
}//end player class
//In blood, sweat and tears...