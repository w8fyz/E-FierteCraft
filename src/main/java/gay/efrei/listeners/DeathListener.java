package gay.efrei.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import gay.efrei.account.Account;
import gay.efrei.managers.discord.Discord;
import gay.efrei.managers.ingame.DeathChest;
import gay.efrei.managers.player.PlayerDecoration;

public class DeathListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Account account = Account.get(e.getEntity().getUniqueId());
		account.setDeaths(account.getDeaths()+1);
		account.save();
		
		PlayerDecoration.update(e.getEntity());
		
		Discord.sendChatMessage("💀 **"+e.getDeathMessage()+"**");
		e.setDeathMessage("§7"+e.getDeathMessage());
		
		DeathChest.generate(e.getEntity());
	}

}
