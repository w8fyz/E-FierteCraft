package gay.efrei.managers.tpa;

import org.bukkit.entity.Player;

public class TPAQuery {

	private Player query;	
	
	public TPAQuery(Player query) {
		this.query = query;
	}
	
	public static TPAQuery get(Player p) {
		return new TPAQuery(p);
	}
	
	public Player getLatestTargeter() {
		TPARequest result = null;
		for(TPARequest requests : TPARequest.requests) {
			if(requests.getTarget() == query) {
				if(result == null || result.getTimestamp() < requests.getTimestamp()) {
					result = requests;
				}
			}
		}
		return result == null ? null : result.getSender();
	}
	
	public TPARequest getRequested(Player p) {
		for(TPARequest requests : TPARequest.requests) {
			if(requests.getTarget() == query && requests.getSender() == p) return requests;
		}
		return null;
	}
	
	public boolean isRequested(Player p) {
		for(TPARequest requests : TPARequest.requests) {
			if(requests.getTarget() == query && requests.getSender() == p) return true;
		}
		return false;
	}
	
	public boolean isRequesting(Player target) {
		for(TPARequest requests : TPARequest.requests) {
			if(requests.getTarget() == target && requests.getSender() == query) return true;
		}
		return false;
	}
	
	public boolean isOnRequestCooldown(Player target) {
		for(TPARequest requests : TPARequest.requests) {
			if(requests.getTarget() == target && requests.getSender() == query) {
				if(System.currentTimeMillis() - requests.getTimestamp() < TPARequest.TP_REQUEST_COOLDOWN) {
					return true;
				}
			}
		}
		return false;
	}

	public TPARequest getRequesting(Player p) {
		for(TPARequest requests : TPARequest.requests) {
			if(requests.getSender() == query && requests.getTarget() == p) return requests;
		}
		return null;
	}

	public Player getLatestRequested() {
		TPARequest result = null;
		for(TPARequest requests : TPARequest.requests) {
			if(requests.getSender() == query) {
				if(result == null || result.getTimestamp() < requests.getTimestamp()) {
					result = requests;
				}
			}
		}
		return result == null ? null : result.getSender();
	}
	
}
