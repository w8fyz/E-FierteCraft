package gay.efrei.managers.player;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class Link {

	private static HashMap<String, UUID> verification_code = new HashMap<>();
	
	public static String getNewVerificationCode(UUID uuid) {
		String code = RandomStringUtils.random(8, true, false);
		verification_code.put(code, uuid);
		return code;
	}
	
	public static UUID isValidCode(String code) {
		if(!verification_code.containsKey(code)) {
			return null;
		}
		return verification_code.get(code);
	}

	public static void remove(String code) {
		verification_code.remove(code);
	}
	
}
