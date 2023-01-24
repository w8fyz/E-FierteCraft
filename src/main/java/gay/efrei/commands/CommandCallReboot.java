package gay.efrei.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gay.efrei.managers.discord.Discord;

public class CommandCallReboot implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player))  {
			Discord.sendChatMessage("⚠️ Redémarrage journalier du serveur en cours ⚠️");
		}
		return false;
	}

}
