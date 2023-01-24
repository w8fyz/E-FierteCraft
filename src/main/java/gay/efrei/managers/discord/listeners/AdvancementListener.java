package gay.efrei.managers.discord.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import gay.efrei.managers.discord.Discord;

public class AdvancementListener implements Listener {
	
	@EventHandler
	public void onAdvancement(PlayerAdvancementDoneEvent event) {
		Discord.sendChatMessage(":minidisc: **"+event.getPlayer().getName()+" a obtenu l'avancement \""+event.getAdvancement().getDisplay().getTitle()+"\" !**");
	}

}
