package gay.efrei.account;

public enum Badges {

	DRAGON_SLAYER(0, "Dragon Slayer", 
			"Killing the Dragon"),
	GOT_MY_WINGS_BACK_BITCH(1, "Got my wings back bitch", 
			"Found Elytra"),
	IM_A_FARMER(2, "I'm a farmer"
			,"A double chest of haybells"),
	JUST_SCISSORING(3, "Just Scissoring", 
			"Having a full enchanted shears"),
	HOE_CONNOISSEUR(4, "Hoe Connoisseur", 
			"Having all the hoes enchanted at least once"),
	THE_NOOB_KING(5, "The Noob King", 
			"Actually having a good dirt house"),
	THE_DEVIL(6, "The Devil", 
			"Uses accacia wood unironically"),
	THE_RARE_BREED(7, "The Rare Breed", 
			"Having a Blue Axolotl"),
	SATANIST_SPAWN(8, "Satanist Spawn", 
			"Living under the nether roof"),
	TRAVELER(9, "Traveler", 
			"Go 10000 blocks from spawn and place a home"),
	NO_SHOWER_NEEDED(10, "No shower needed", 
			"Having all of the achievements"),
	GOOD_LIL_HOME(11, "Good Lil Home", 
			"Win the vote for the best home"),
	THE_GAYEST_SHIT_I_SAW(12, "The Gayest shit I saw", 
			"Have a Sheep named jeb_"),
	DEATH_LOVER(13, "Death's lover", 
			"Having 69 deaths or more");
	
	private int id;
	private String name, description;
	
	private Badges(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
}
