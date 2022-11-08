package gay.efrei.utils;

import java.util.Arrays;

public enum ColorsItem {
	
	YELLOW(0, 0, "&e");

	private int id;
	private byte data;
	private String chatColor; 

	ColorsItem(int id, byte data, String chatColor) {
		this.data = data;
		this.id = id;
		this.chatColor = chatColor;
	}

	public int getID() {
		return id;
	}

	public byte getData() {
		return data;
	}

	public String getChatColor() {
		return chatColor;
	}

	public static ColorsItem getColorFromItem(byte data) {
		return Arrays.stream(values()).filter(c -> c.getData() == data).findAny().orElse(null);
	}

}
