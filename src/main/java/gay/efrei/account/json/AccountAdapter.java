package gay.efrei.account.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import gay.efrei.account.Account;
import gay.efrei.account.Badges;
import gay.efrei.account.Home;

public class AccountAdapter implements JsonSerializer<Account>, JsonDeserializer<Account> {

	@SuppressWarnings("serial")
	@Override
	public Account deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();

		UUID uuid = UUID.fromString(jsonObject.get("uuid").getAsString());

		String pronouns;
		if (jsonObject.get("pronouns") == null) {
			pronouns = "";
		} else {
			pronouns = jsonObject.get("pronouns").getAsString();
		}

		List<Home> homes;
		if (jsonObject.get("homes") == null) {
			homes = new ArrayList<>();
		} else {
			homes = new Gson().fromJson(jsonObject.get("homes"), new TypeToken<List<Home>>() {
			}.getType());
		}

		long timestampLastTP = jsonObject.get("timestampLastTP").getAsLong();
		
		List<Badges> badges = new Gson().fromJson(jsonObject.get("badges"), new TypeToken<List<Badges>>() {
		}.getType());

		int deaths = jsonObject.get("deaths").getAsInt();
		int color = jsonObject.get("color").getAsInt();
		String discordID = jsonObject.get("discordID").getAsString();
		int playCount = jsonObject.get("playCount").getAsInt();
		
		return new Account(uuid, pronouns, homes, timestampLastTP, badges, deaths, color, discordID, playCount);
	}

	@Override
	public JsonElement serialize(Account account, Type type, JsonSerializationContext context) {
		JsonObject result = new JsonObject();

		UUID uuid = account.getUUID();
		String pronouns = account.getPronouns();
		List<Home> homes = account.getHomes();
		long timestampLastTP = account.getLastTP();
		List<Badges> badges = account.getBadges();
		int deaths = account.getDeaths();
		int color = account.getColor();
		String discordID = account.getDiscordID();
		int playCount = account.getPlayCount();
		
		result.add("uuid", context.serialize(uuid));
		result.add("pronouns", context.serialize(pronouns));
		result.add("homes", context.serialize(homes));
		result.add("timestampLastTP", context.serialize(timestampLastTP));
		result.add("badges", context.serialize(badges));
		result.add("deaths", context.serialize(deaths));
		result.add("color", context.serialize(color));
		result.add("discordID", context.serialize(discordID));
		result.add("playCount", context.serialize(playCount));
		
		return result;
	}

}
