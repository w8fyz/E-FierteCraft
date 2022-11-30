package gay.efrei.managers.discord;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import gay.efrei.Main;
import gay.efrei.managers.discord.listeners.CustomVoiceListener;
import gay.efrei.managers.discord.listeners.MessageListener;
import gay.efrei.managers.discord.listeners.ReadyListener;
import gay.efrei.managers.discord.listeners.VerificationChannelListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Discord {
	
	private static JDA jda;
	
	public static JDA get() {
		return jda;
	}
	
	public static void init() {
		try {
			jda = JDABuilder.createDefault(Main.getInstance().getConfig().getString("discord_token"))
					.setActivity(Activity.playing("efrei.gay"))
					.addEventListeners(new ReadyListener(), new CustomVoiceListener(), new VerificationChannelListener(), new MessageListener())
					.enableIntents(EnumSet.allOf(GatewayIntent.class))
					.setMemberCachePolicy(MemberCachePolicy.ALL).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendChatMessage(String message) {
		TextChannel channel = jda.getTextChannelById(Main.getInstance().getConfig().getString("channel_link"));
		channel.sendMessage(protect(message)).queue();
	}

	private static String protect(String message) {
		return message.replaceAll("@", "ⓐ");
		
	}
	
	

}
