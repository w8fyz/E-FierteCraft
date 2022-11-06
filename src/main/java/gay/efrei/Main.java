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
import gay.efrei.listeners.JoinQuitListener;

public class Main extends JavaPlugin{
	
	private static Main INSTANCE;
	
	public static Main getInstance() {
		return INSTANCE;
	}
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		registerListeners();
		registerCommands();
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
		
		getCommand("home").setTabCompleter(new CommandHome());
		getCommand("delhome").setTabCompleter(new CommandDelHome());
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
	}

}
