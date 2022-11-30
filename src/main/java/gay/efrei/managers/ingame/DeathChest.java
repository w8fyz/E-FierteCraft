package gay.efrei.managers.ingame;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import gay.efrei.Main;

public class DeathChest {

	public static void generate(Player p) {
		Block loc = p.getWorld().getBlockAt(p.getLocation());
		Location loc_below = p.getLocation().clone();
		loc_below.setY(loc.getY() - 1);
		Block below = p.getWorld().getBlockAt(loc_below);
		if (below == null || below.getType() == Material.AIR) {
			below.setType(Material.DIRT);
		}
		loc.setType(Material.DARK_OAK_SIGN);
		Sign sign = (Sign) loc;
		sign.setLine(0, "§8 - Tombe de -");
		sign.setLine(1, "§c" + p.getName());
		updateBlock(sign, p);

	}

	private static void updateBlock(Sign b, Player p) {
		PersistentDataContainer container = b.getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey(Main.getInstance(), "inventory");
		container.set(key, PersistentDataType.STRING,
				InventorySerializer.to(new ChestDeath(p.getUniqueId(), p.getInventory().getContents())));
		b.update();
	}

	public static ChestDeath get(TileState b) {
		PersistentDataContainer container = b.getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey(Main.getInstance(), "inventory");
		if(!container.has(key, PersistentDataType.STRING)) return null;
		return InventorySerializer.from(container.get(key, PersistentDataType.STRING));
	}

}
