package gay.efrei.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import gay.efrei.managers.player.PlayerDecoration;
import gay.efrei.utils.ColorsItem;

public class InteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if(event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equals("fyz_")) {
			@SuppressWarnings("deprecation")
			ColorsItem color = ColorsItem.getColorFromItem(event.getItem().getData().getData());	
			if(color != null) {
				account.setColor(color.getID()).save();
				PlayerDecoration.update(event.getPlayer());
				event.getPlayer().sendMessage("&eLa couleur de tes pronoms a été changée, comme par magie !");
			}
		}
		}
	}

}
