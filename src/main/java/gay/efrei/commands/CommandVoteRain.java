package gay.efrei.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gay.efrei.managers.ingame.RainVote;

public class CommandVoteRain implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!RainVote.isVoteStarted()) {
				p.sendMessage("§cAucun vote n'est en cours.");
				return false;
			}
			if(!RainVote.vote(p)) {
				p.sendMessage("§cTon vote a déjà été pris en compte.");
				return false;
			}
			p.sendMessage("§bTon vote a bien été pris en compte pour couper la pluie !");
		}
		return true;
	}

}
