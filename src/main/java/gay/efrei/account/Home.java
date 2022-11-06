package gay.efrei.account;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Home {

	private String name;
	private HomeLocation loc;
	
	public Home(String name, HomeLocation loc) {
		this.name = name;
		this.loc = loc;
	}
	
	public String getName() {
		return name;
	}
	
	public HomeLocation getLocation() {
		return loc;
	}
	
	public Location getBukkitLocation() {
		return new Location(Bukkit.getWorld(loc.getWorldName()), loc.x(), loc.y(), loc.z(), loc.yaw(), loc.pitch());
	}
	
}
