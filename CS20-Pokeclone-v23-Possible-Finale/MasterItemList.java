/* this class is already done 
* There is no need to edit/update this class
* unless you are wanting to add more Items or adding extra features as per the assignment
*/

import java.util.ArrayList;

public class MasterItemList {
  //ArrayList to hold all the possible Items
	ArrayList<Item> allItemsList = new ArrayList<>();

  //Constructor that contains the allItemsList ArrayList filled with all possible 
  //Item objects
  //Format -> Name, Description, Effect Type, Effect, Amount,
	public MasterItemList() {
		allItemsList.add(new Item("Potion", "Heals for 25% HP", "Restoration", 0.25, 3));
    allItemsList.add(new Item("Super Potion", "Heals for 50% HP", "Restoration", 0.5, 2));
    allItemsList.add(new Item("Full Restore", "Fully Restores HP", "Restoration", 1, 1));
    allItemsList.add(new Item("Revive", "Revives a Pokemon to Half HP", "Revive", 0.5, 1));
    allItemsList.add(new Item("Full Revive", "Revives a Pokemon to Full HP", "Revive", 1, 1));
    allItemsList.add(new Item("X Attack", "Raises ATK by 10%", "AtkBuff", 0.1, 2));
    allItemsList.add(new Item("X Attack II", "Raises ATK by 25%", "AtkBuff", 0.25, 1));
    allItemsList.add(new Item("X Attack III", "Raises ATK by 50%", "AtkBuff", 0.5, 1));
    allItemsList.add(new Item("X Accuracy", "Raises hit chance by 10%", "AccBuff", 0.25, 2));
    allItemsList.add(new Item("X Accuracy II", "Raises hit chance by 25%", "AccBuff", 0.25, 1));
    allItemsList.add(new Item("X Accuracy III", "Raises hit chance by 50%", "AccBuff", 0.5, 1));
    allItemsList.add(new Item("X Weaken", "Lowers ATK by 10%", "AtkDebuff", 0.1, 3));
    allItemsList.add(new Item("X Weaken II", "Lowers ATK by 25%", "AtkDebuff", 0.25, 2));
    allItemsList.add(new Item("X Weaken III", "Lowers ATK by 50%", "AtkDebuff", 0.5, 1));
    allItemsList.add(new Item("Mist in a Bottle", "Lowers hit chance by 10%", "AccDebuff", 0.1, 1));
    allItemsList.add(new Item("Cloud in a Bottle", "Lowers hit chance by 25%", "AccDebuff", 0.25, 1));
    allItemsList.add(new Item("Fog in a bottle", "Lowers hit chance by 50%", "AccDebuff", 0.5, 1));
    allItemsList.add(new Item("Hotfix", "Defeats the anomaly among the code", "Hotfix", 0, 1));
    allItemsList.add(new Item("Joe Biden", "Defeats the 2016 President of The United States", "Democrat", 0, 1));
    allItemsList.add(new Item("Norfleet", "BLOWS UP EVERYTHING!!!", "Nuke", 0, 100));
	}

	/**
	 * @return the allItemsList
	 */
	public ArrayList<Item> getAllItemsList() {
		return allItemsList;
	}
	
}//End class
//A Clash of history...