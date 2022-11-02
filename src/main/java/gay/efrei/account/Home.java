package gay.efrei.account;

import org.bukkit.Location;

public class Home {

	private String name;
	private Location loc;
	
	public Home(String name, Location loc) {
		this.name = name;
		this.loc = loc;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return loc;
	}
	
}
