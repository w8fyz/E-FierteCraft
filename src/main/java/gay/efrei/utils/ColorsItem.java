package gay.efrei.utils;

import java.util.Arrays;

import org.bukkit.DyeColor;

public enum ColorsItem {
	
	BLACK(0, DyeColor.BLACK, "§0"),
	BLUE(1, DyeColor.BLUE, "§1"),
	CYAN(2, DyeColor.CYAN, "§3"),
	GRAY(3, DyeColor.GRAY, "§8"),
	GREEN(4, DyeColor.GREEN, "§2"),
	LIGHT_BLUE(5, DyeColor.LIGHT_BLUE, "§9"),
	LIGHT_GRAY(6, DyeColor.LIGHT_GRAY, "§7"),
	LIME(7, DyeColor.LIME, "§a"),
	ORANGE(9, DyeColor.ORANGE, "§6"),
	PINK(10, DyeColor.PINK, "§d"),
	PURPLE(11, DyeColor.PURPLE, "§5"),
	RED(12, DyeColor.RED, "§c"),
	WHITE(13, DyeColor.WHITE, "§f"),
	YELLOW(14, DyeColor.YELLOW, "&e");

	private int id;
	private DyeColor color;
	private String chatColor; 

	ColorsItem(int id, DyeColor color, String chatColor) {
		this.color = color;
		this.id = id;
		this.chatColor = chatColor;
	}

	public int getID() {
		return id;
	}

	public DyeColor getColor() {
		return color;
	}

	public String getChatColor() {
		return chatColor;
	}

	public static ColorsItem getColorFromItem(DyeColor color) {
		return Arrays.stream(values()).filter(c -> c.getColor() == color).findAny().orElse(null);
	}

	public static ColorsItem getColorFromID(int color) {
		return Arrays.stream(values()).filter(c -> c.getID() == color).findAny().orElse(null);
	}

}
