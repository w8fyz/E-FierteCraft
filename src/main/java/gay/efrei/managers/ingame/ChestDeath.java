package gay.efrei.managers.ingame;

import java.util.UUID;

import org.bukkit.inventory.ItemStack;

public class ChestDeath {
	
	private UUID uuid;
	private ItemStack[] content;
	
	public ChestDeath(UUID uuid, ItemStack[] content) {
		this.content = content;
		this.uuid = uuid;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public ItemStack[] getContent() {
		return content;
	}

}
