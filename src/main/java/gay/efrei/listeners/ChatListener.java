package gay.efrei.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import gay.efrei.account.Account;
import gay.efrei.managers.discord.Discord;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Account account = Account.get(e.getPlayer().getUniqueId());
		e.setFormat(account.getFormatedColor()+e.getPlayer().getName()+"§7: "+e.getMessage());
	
		Discord.sendChatMessage("**"+e.getPlayer().getName()+"**: "+e.getMessage());
	}
	
}
