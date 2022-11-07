package gay.efrei.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import gay.efrei.utils.ColorsItem;

public class InteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			@SuppressWarnings("deprecation")
			ColorsItem color = ColorsItem.getColorFromItem(event.getItem().getData().getData());
			
			if(color != null) {
				
			}
		}
	}

}
