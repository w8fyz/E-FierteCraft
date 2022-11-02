package gay.efrei;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import gay.efrei.commands.CommandDelHome;
import gay.efrei.commands.CommandHome;
import gay.efrei.commands.CommandSetHome;
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
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
	}

}
