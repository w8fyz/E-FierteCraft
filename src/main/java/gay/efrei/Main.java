package gay.efrei;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import gay.efrei.commands.CommandDelHome;
import gay.efrei.commands.CommandHome;
import gay.efrei.commands.CommandPronouns;
import gay.efrei.commands.CommandSetHome;
import gay.efrei.commands.CommandTpa;
import gay.efrei.commands.CommandTpcancel;
import gay.efrei.commands.CommandTpno;
import gay.efrei.commands.CommandTpyes;
import gay.efrei.commands.CommandVoteRain;
import gay.efrei.listeners.ChatListener;
import gay.efrei.listeners.DeathChestListener;
import gay.efrei.listeners.DeathListener;
import gay.efrei.listeners.InteractListener;
import gay.efrei.listeners.JoinQuitListener;
import gay.efrei.listeners.RainListener;
import gay.efrei.listeners.VerificationManager;
import gay.efrei.managers.discord.Discord;
import gay.efrei.managers.scoreboard.BoardTask;

public class Main extends JavaPlugin{
	
	private static Main INSTANCE;
	
	public static Main getInstance() {
		return INSTANCE;
	}
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		saveDefaultConfig();
		
		registerListeners();
		registerCommands();
		
		Discord.init();
		new BoardTask().runTaskTimer(this, 10, 10);	
	}
	
	@Override
	public void onDisable() {
		Discord.sendChatMessage(":red_circle: **Serveur éteint**");
	}

	private void registerCommands() {
		getCommand("home").setExecutor(new CommandHome());
		getCommand("sethome").setExecutor(new CommandSetHome());
		getCommand("delhome").setExecutor(new CommandDelHome());
		getCommand("pronouns").setExecutor(new CommandPronouns());
		getCommand("tpa").setExecutor(new CommandTpa());
		getCommand("tpaccept").setExecutor(new CommandTpyes());
		getCommand("tpyes").setExecutor(new CommandTpyes());
		getCommand("tpdeny").setExecutor(new CommandTpno());
		getCommand("tpno").setExecutor(new CommandTpno());
		getCommand("tpcancel").setExecutor(new CommandTpcancel());
		getCommand("tpundo").setExecutor(new CommandTpcancel());
		getCommand("voterain").setExecutor(new CommandVoteRain());
		
		getCommand("home").setTabCompleter(new CommandHome());
		getCommand("delhome").setTabCompleter(new CommandDelHome());
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new RainListener(), this);
		Bukkit.getPluginManager().registerEvents(new VerificationManager(), this);
		Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathChestListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
	}

}
