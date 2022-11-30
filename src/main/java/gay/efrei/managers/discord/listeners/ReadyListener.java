package gay.efrei.managers.discord.listeners;

import gay.efrei.managers.discord.Discord;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter{
	
	@Override
	public void onReady(ReadyEvent event ) {
		Discord.sendChatMessage(":green_circle: **Serveur lancé**");
	}

}
