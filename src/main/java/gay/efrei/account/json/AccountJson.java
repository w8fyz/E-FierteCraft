package gay.efrei.account.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gay.efrei.account.Account;
import gay.efrei.account.Home;

public class AccountJson {

	private Gson gson;
	
	public AccountJson() {
		this.gson = createGsonInstance();
	}
	
	private Gson createGsonInstance() {
		return new GsonBuilder()
				.serializeNulls()
				.disableHtmlEscaping()
				.registerTypeAdapter(Account.class, new AccountAdapter())
				.registerTypeAdapter(Home.class, new HomeAdapter())
				.create();
	}
	
	
	public String serialize(Account profil) {
		return this.gson.toJson(profil);
		
	}
	
	public Account deserialize(String json) {
		return this.gson.fromJson(json, Account.class);
	}
}