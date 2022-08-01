/* this class is already done 
* There is no need to edit/update this class
* unless you are wanting to add more cards or adding extra features as per the assignment
*/

import java.util.ArrayList;

public class MasterCardList {
  //ArrayList to hold all the possible cards
	ArrayList<PokemonCard> allCardsList = new ArrayList<>();

  //Constructor that contains the allCardsList ArrayList filled with all possible 
  //PokemonCard objects
  //Format -> Name, Type, HP, Damage, HitChance, isOP
	public MasterCardList() {
		allCardsList.add(new PokemonCard("Bulbasaur", "Grass", 40, 20, 0.6));
		allCardsList.add(new PokemonCard("Charmander", "Fire", 50, 10, 0.6));
		allCardsList.add(new PokemonCard("Diglett", "Ground", 30, 10, 0.4));
		allCardsList.add(new PokemonCard("Drowzee", "Psychic", 50, 10, 0.6));
		allCardsList.add(new PokemonCard("Machop", "Fighting",  50, 20, 0.7));
		allCardsList.add(new PokemonCard("Magnemite", "Steel", 40, 10, 0.5));
		allCardsList.add(new PokemonCard("Onix", "Rock",  90, 10, 0.8));
		allCardsList.add(new PokemonCard("Pidgey", "Flying", 40, 10, 0.5));
		allCardsList.add(new PokemonCard("Pikachu", "Electric", 40, 10, 0.5));
		allCardsList.add(new PokemonCard("Poliwag", "Water", 40, 10, 0.5));
		allCardsList.add(new PokemonCard("Ponyta", "Fire",  40, 10, 0.5));
    allCardsList.add(new PokemonCard("MissingNo.", "Normal", 2147483647, 2147483647, 0.9));
    allCardsList.add(new PokemonCard ("Arceus", "Normal", 100, 100, 0.9));
    allCardsList.add(new PokemonCard ("Donald J. Trump", "Normal", 200, 20, 0.5));
    allCardsList.add(new PokemonCard ("Donald J. Trump", "Normal", 200, 20, 0.5));
    allCardsList.add(new PokemonCard ("Sepulcher", "Normal", 500, 30, 0.2));
    allCardsList.add(new PokemonCard ("Supreme Catastrophe", "Normal", 75, 40, 0.3));
    allCardsList.add(new PokemonCard ("Supreme Cataclysm", "Normal", 150, 10, 0.7));
    allCardsList.add(new PokemonCard ("Supreme Soul Seeker", "Normal", 30, 20, 0.9));
    allCardsList.add(new PokemonCard ("Sepulcher", "Normal", 500, 30, 0.5));
    allCardsList.add(new PokemonCard (" ", "Normal", 1, 0, 0.0));
	}

	/**
	 * @return the allCardsList
	 */
	public ArrayList<PokemonCard> getAllCardsList() {
		return allCardsList;
	}
	
}//End class
//As the blood melts into the rain...