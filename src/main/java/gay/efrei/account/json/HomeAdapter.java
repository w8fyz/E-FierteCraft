package gay.efrei.account.json;

import java.lang.reflect.Type;

import org.bukkit.Location;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import gay.efrei.account.Home;
import gay.efrei.account.HomeLocation;

public class HomeAdapter implements JsonSerializer<Home>, JsonDeserializer<Home> {

	@Override
	public Home deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();

		String name = jsonObject.get("name").getAsString();
		@SuppressWarnings("serial")
		Location loc = new Gson().fromJson(jsonObject.get("loc"), new TypeToken<Location>() {
		}.getType());

		return new Home(name, new HomeLocation(loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ(),
				loc.getYaw(), loc.getPitch()));
	}

	@Override
	public JsonElement serialize(Home home, Type type, JsonSerializationContext context) {
		JsonObject result = new JsonObject();

		String name = home.getName();
		HomeLocation loc = home.getLocation();

		result.add("name", context.serialize(name));
		result.add("loc", context.serialize(loc));

		return result;
	}

}
