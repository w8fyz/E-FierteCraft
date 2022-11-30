package gay.efrei.managers.ingame;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class InventorySerializer {

	public static String to(ChestDeath inventory) {
		return new Gson().toJson(inventory);
	}

	@SuppressWarnings("serial")
	public static ChestDeath from(String string) {
		return new Gson().fromJson(string, new TypeToken<ChestDeath>() {}.getType());
	}

}
