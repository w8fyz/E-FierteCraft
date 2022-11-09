package gay.efrei.managers.discord.listeners;

import java.util.HashMap;
import java.util.Random;

import gay.efrei.Main;
import gay.efrei.managers.discord.Discord;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CustomVoiceListener extends ListenerAdapter {
	
	private static HashMap<String, AudioChannel> custom_channels = new HashMap<>();
	
	private int nb_channels_cache = 0;
	
	private static String VOICE_ID_CREATOR_CHANNEL = Main.getInstance().getConfig().getString("channel_voice_creator");
	
	private String getRandomEmote() {
		String [] list = {"💧", "🧢", "📘", "💎", "🌐", "💦", "🥣", "👔"};
		return list[new Random().nextInt(list.length)];
	}
	
	private VoiceChannel setupVoiceChannelPermissions(Member creator, VoiceChannel channel) {
	//	channel.getPermissionContainer().delete().queue();}
		channel.upsertPermissionOverride(creator).complete().getManager().grant(Permission.ALL_CHANNEL_PERMISSIONS).queue();
		return channel;
	}
	
	private VoiceChannel getTemplateVoiceChannel(Member creator) {
		Guild g = Discord.get().getGuildChannelById(VOICE_ID_CREATOR_CHANNEL).getGuild();
		return setupVoiceChannelPermissions(creator, g.createVoiceChannel(getRandomEmote()+"┃"+"Privé #"+(nb_channels_cache+1 > 9 ? (nb_channels_cache+1) : "0"+(nb_channels_cache+1)), g.getCategoryById("991494771719815218")).complete());
	}
	
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		voiceCreateEventChecking(event.getChannelJoined(), event.getMember());
	}
	
	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
		voiceLeaveEventChecking(event.getChannelLeft(), event.getMember());
		voiceCreateEventChecking(event.getChannelJoined(), event.getMember());
	}
	
	private void voiceCreateEventChecking(AudioChannel channel, Member member) {
		if(channel.getId().equals(VOICE_ID_CREATOR_CHANNEL)) {
			VoiceChannel vc = getTemplateVoiceChannel(member);
			channel.getGuild().moveVoiceMember(member, vc).queue(); 
			custom_channels.put(member.getId(), vc);
			nb_channels_cache++;
			
			/*
			 * TODO :
			 * Replace send panel to DM to send panel to txt in voice channel when the API permit it
			 * 
			 * 
			 * 		PrivateChannel txt = event.getMember().getUser().openPrivateChannel().complete();
			 * 		txt.sendMessageEmbeds(getPanel()).queue();
			 *		txt.sendMessage("⬆️ Bienvenue sur le panel de ton channel privé ! ").queue(); 
			 *
			 *
			 */
		}
	}
	
	private void voiceLeaveEventChecking(AudioChannel channel, Member member) {
		if(custom_channels.values().contains(channel)) {
			if(channel.getMembers().size() == 0) {
				custom_channels.remove(member.getId());
				channel.delete().queue();
			} else {
				if(custom_channels.containsKey(member.getId())) {
					
					Member leader =channel.getMembers().get(0);
					
					channel.getPermissionContainer().upsertPermissionOverride(leader).complete().getManager().grant(Permission.ALL_CHANNEL_PERMISSIONS).queue();
					custom_channels.remove(member.getId());
					custom_channels.put(leader.getId(), channel);
				}
			}
		}
	}
	
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		voiceLeaveEventChecking(event.getChannelLeft(), event.getMember());
	}
	
	/*private MessageEmbed getPanel() {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Panel de configuration");
		eb.addField("Salon ouvert", "❌", true);
		eb.addField("Limite d'utilisateurs", "6", true);
		return eb.build();
	}*/

}