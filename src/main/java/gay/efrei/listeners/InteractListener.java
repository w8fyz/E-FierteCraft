package gay.efrei.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Colorable;

import gay.efrei.account.Account;
import gay.efrei.managers.player.PlayerDecoration;
import gay.efrei.utils.ColorsItem;

public class InteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if(event.getItem().getData() instanceof Colorable && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equals("fyz_")) {
			ColorsItem color = ColorsItem.getColorFromItem(((Colorable)event.getItem().getData()).getColor());	
			if(color != null) {
				
				Account.get(event.getPlayer().getUniqueId()).setColor(color.getID()).save();
				PlayerDecoration.update(event.getPlayer());
				event.getPlayer().sendMessage("&eLa couleur de tes pronoms a été changée, comme par magie !");
				ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
				hand.setAmount(hand.getAmount()-1);
				event.getPlayer().getInventory().setItemInMainHand(hand);;
			}
		}
		}
	}

}
