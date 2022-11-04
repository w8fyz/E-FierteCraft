package gay.efrei.managers.tpa;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

public class TPARequest {
	
	protected static ArrayList<TPARequest> requests = new ArrayList<>();
	public static final long TP_COOLDOWN = TimeUnit.MINUTES.toMillis(5);
	public static final long TP_REQUEST_COOLDOWN = TimeUnit.MINUTES.toMillis(2);
	
	
	private Player sender,target;
	private long timestamp;
	
	public TPARequest(Player sender, Player target, long timestamp) {
		this.sender = sender;
		this.target = target;
	}
	
	public static TPARequest make() {
		return new TPARequest(null, null, System.currentTimeMillis());
	}
	
	
	public void execute() {
		
	}
	
	public TPARequest sender(Player p) {
		sender = p;
		return this;
	}
	
	public TPARequest target(Player p) {
		target = p;
		return this;
	}
	
	
	public Player getSender() {
		return sender;
	}
	
	public Player getTarget() {
		return target;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	

}
