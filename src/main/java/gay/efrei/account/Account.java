package gay.efrei.account;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import gay.efrei.utils.data.Data;

public class Account {
	
	private UUID uuid;
	private String pronouns;
	private List<Home> homes;
	
	public Account(UUID uuid, String pronouns, List<Home> homes) {
		this.uuid = uuid;
		this.pronouns = pronouns;
		this.homes = homes;
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
		return Arrays.stream(homes.toArray(new Home[0])).filter(h -> h.getName().equalsIgnoreCase(name)).findAny().orElse(null);
	}
	
	public String getPronouns() {
		return pronouns;
	}
	
	public void setPronouns(String pronouns) {
		this.pronouns = pronouns;
	}
	
	public static Account get(UUID uuid) {
		return (Account) new Data(Account.class).get(uuid.toString());
	}
	
	public void save() {
		new Data(Account.class).send(uuid.toString(), new Gson().toJson(this));
	}

}
