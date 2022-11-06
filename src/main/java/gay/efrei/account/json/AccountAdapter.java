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

		return new Account(uuid, pronouns, homes, timestampLastTP);
	}

	@Override
	public JsonElement serialize(Account account, Type type, JsonSerializationContext context) {
		JsonObject result = new JsonObject();

		UUID uuid = account.getUUID();
		String pronouns = account.getPronouns();
		List<Home> homes = account.getHomes();
		long timestampLastTP = account.getLastTP();

		result.add("uuid", context.serialize(uuid));
		result.add("pronouns", context.serialize(pronouns));
		result.add("homes", context.serialize(homes));
		result.add("timestampLastTP", context.serialize(timestampLastTP));

		return result;
	}

}
