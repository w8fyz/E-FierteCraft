package gay.efrei.managers.tpa;

import org.bukkit.entity.Player;

public class TPARequest {
	
	private Player sender,target;
	private long timestamp;
	
	public TPARequest(Player sender, Player target, long timestamp) {
		this.sender = sender;
		this.target = target;
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
