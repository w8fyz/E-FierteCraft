package gay.efrei.utils;

import java.util.Arrays;

import org.bukkit.Material;

public enum ColorsItem {
	
	BLACK(0, Material.BLACK_DYE, "§0"),
	BLUE(1, Material.BLUE_DYE, "§1"),
	CYAN(2, Material.CYAN_DYE, "§3"),
	GRAY(3, Material.GRAY_DYE, "§8"),
	GREEN(4, Material.GREEN_DYE, "§2"),
	LIGHT_BLUE(5, Material.LIGHT_BLUE_DYE, "§9"),
	LIGHT_GRAY(6, Material.GRAY_DYE, "§7"),
	LIME(7, Material.LIME_DYE, "§a"),
	ORANGE(9, Material.ORANGE_DYE, "§6"),
	PINK(10, Material.PINK_DYE, "§d"),
	PURPLE(11, Material.PURPLE_DYE, "§5"),
	RED(12, Material.RED_DYE, "§c"),
	WHITE(13, Material.WHITE_DYE, "§f"),
	YELLOW(14, Material.YELLOW_DYE, "§e");

	private int id;
	private Material dye;
	private String chatColor; 

	ColorsItem(int id, Material dye, String chatColor) {
		this.dye = dye;
		this.id = id;
		this.chatColor = chatColor;
	}

	public int getID() {
		return id;
	}

	public Material getDye() {
		return dye;
	}

	public String getChatColor() {
		return chatColor;
	}

	public static ColorsItem getColorFromItem(Material color) {
		return Arrays.stream(values()).filter(c -> c.getDye() == color).findAny().orElse(null);
	}

	public static ColorsItem getColorFromID(int color) {
		return Arrays.stream(values()).filter(c -> c.getID() == color).findAny().orElse(null);
	}

}
