package gay.efrei.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import gay.efrei.managers.ingame.ChestDeath;
import gay.efrei.managers.ingame.DeathChest;

public class DeathChestListener implements Listener {

	@EventHandler
	public void pickupDeathChest(PlayerInteractEvent event) {
		if(event.getAction() !=  Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.DARK_OAK_SIGN) {
			ChestDeath cd = DeathChest.get((TileState) event.getClickedBlock());
			if(cd == null) {
				return;
			}
			if(!cd.getUUID().equals(event.getPlayer().getUniqueId())) {
				event.getPlayer().sendMessage("§cCe n'est pas ta tombe.");
				return;
			}
				event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
				event.getClickedBlock().setType(Material.AIR);
				for(ItemStack ite : cd.getContent()) {
					if(ite != null && ite.getType() != Material.AIR) {
						event.getClickedBlock().getLocation().getWorld().dropItem(event.getClickedBlock().getLocation(), ite);
					}
				}
		}
	}
	
}
