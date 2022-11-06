package gay.efrei.account;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import gay.efrei.Main;
import gay.efrei.account.json.AccountJson;
import gay.efrei.managers.tpa.TPARequest;
import gay.efrei.utils.data.Data;

public class Account {
	

	
	private UUID uuid;
	private String pronouns;
	private List<Home> homes;
	private long timestampLastTP;
	
	public Account(UUID uuid, String pronouns, List<Home> homes, long timestampLastTP) {
		this.uuid = uuid;
		this.pronouns = pronouns;
		this.homes = homes;
		this.timestampLastTP = timestampLastTP;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public long getLastTP() {
		return timestampLastTP;
	}
	
	public boolean canTeleport() {
		return System.currentTimeMillis() - timestampLastTP >= TPARequest.TP_COOLDOWN;
	}
	
	public Account setLastTP(long timestamp) {
		this.timestampLastTP = timestamp;
		return this;
	}
	
	public String getFormatedHomes() {
		StringBuilder sb = new StringBuilder();
		getHomes().forEach( h -> {
			sb.append(", "+h.getName());
		});
		return sb.toString().substring(2);
	}
	
	public List<Home> getHomes() {
		return homes;
	}
	
	public Home getHomeByName(String name) {
		return homes.stream().filter(h -> h.getName().equalsIgnoreCase(name)).findAny().orElse(null);
	}
	
	public String getPronouns() {
		return pronouns;
	}
	
	public void setPronouns(String pronouns) {
		this.pronouns = pronouns;
	}
	
	@SuppressWarnings("deprecation")
	public static Account get(UUID uuid) {
		File f = new File("plugins/"+Main.getInstance().getDescription().getName()+"/data/players/" + uuid.toString());
		if (f.exists()) {
			try {
				return new AccountJson().deserialize(Files.toString(f, Charsets.UTF_8));
			} catch (IOException e) {
				return null;
			}
		}
		return null;
		
	}
	
	public void save() {
		new Data(Account.class).send(uuid.toString(), new AccountJson().serialize(this));
	}

}
