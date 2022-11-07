package gay.efrei.account;

public enum Badges {

	TEST(0, "Hello, World!", "Voici un bien beau badge de test, vous ne trouvez pas ? :)");
	
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
