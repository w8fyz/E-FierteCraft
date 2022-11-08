package gay.efrei.managers.discord;

import gay.efrei.Main;
import gay.efrei.managers.discord.listeners.MessageListener;
import gay.efrei.managers.discord.listeners.VerificationChannelListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Discord {
	
	private static JDA jda;
	
	public static JDA get() {
		return jda;
	}
	
	public static void init() {
		jda = JDABuilder.createDefault(Main.getInstance().getConfig().getString("discord_token"))
				.addEventListeners(new VerificationChannelListener(), new MessageListener())
				.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
				.setMemberCachePolicy(MemberCachePolicy.ALL).build();
	}
	
	public static void sendChatMessage(String message) {
		TextChannel channel = jda.getTextChannelById(Main.getInstance().getConfig().getLong("channel_link"));
		channel.sendMessage(protect(message)).queue();
	}

	private static String protect(String message) {
		return message.replaceAll("@", "*(at)*");
		
	}
	
	

}
