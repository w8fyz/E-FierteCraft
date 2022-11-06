package gay.efrei.account;

public class HomeLocation {
	
	private String worldName;
	private double x,y,z;
	private float yaw, pitch;
	
	public HomeLocation(String worldName, double x, double y, double z, float yaw, float pitch) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.worldName = worldName;
	}
	
	public String getWorldName() {
		return worldName;
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}	
	public double z() {
		return z;
	}	
	public float yaw() {
		return yaw;
	}	
	public float pitch() {
		return pitch;
	}
}
