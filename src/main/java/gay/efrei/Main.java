package gay.efrei;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import gay.efrei.commands.CommandDelHome;
import gay.efrei.commands.CommandHome;
import gay.efrei.commands.CommandPronouns;
import gay.efrei.commands.CommandSetHome;
import gay.efrei.commands.CommandTpa;
import gay.efrei.commands.CommandTpano;
import gay.efrei.commands.CommandTpayes;
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
		getCommand("tpaccept").setExecutor(new CommandTpayes());
		getCommand("tpayes").setExecutor(new CommandTpayes());
		getCommand("tpadeny").setExecutor(new CommandTpano());
		getCommand("tpano").setExecutor(new CommandTpano());
		
		getCommand("home").setTabCompleter(new CommandHome());
		getCommand("delhome").setTabCompleter(new CommandDelHome());
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
	}

}
