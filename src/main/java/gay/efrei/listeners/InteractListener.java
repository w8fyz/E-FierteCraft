package gay.efrei.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import gay.efrei.account.Account;
import gay.efrei.managers.player.PlayerDecoration;
import gay.efrei.utils.ColorsItem;

public class InteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
		if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_AIR
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (hand != null && hand.hasItemMeta() && hand.getItemMeta().hasDisplayName()
					&& hand.getItemMeta().getDisplayName().equals("su&toh<3")) {
				ColorsItem color = ColorsItem.getColorFromItem(hand.getType());
				if (color != null) {
					Account.get(event.getPlayer().getUniqueId()).setColor(color.getID()).save();
					PlayerDecoration.update(event.getPlayer());
					event.getPlayer().sendMessage("§eLa couleur de tes pronoms a été changée, comme par magie !");
					hand.setAmount(hand.getAmount() - 1);
					event.getPlayer().getInventory().setItemInMainHand(hand);
					;
				}
			}
		}
	}

}
