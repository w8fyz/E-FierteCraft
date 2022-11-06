package gay.efrei.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gay.efrei.managers.tpa.TPAQuery;

public class CommandTpcancel implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Player targeter = null;
			if (args.length == 0) {
				targeter = TPAQuery.get(p).getLatestRequested();
			} else {
				targeter = Bukkit.getPlayer(args[0]);
			}
			if (targeter == null) {
				p.sendMessage("§cImpossible de trouver un compte avec ce pseudo connecté.");
				return false;
			}
			if(!targeter.isOnline()) {
				p.sendMessage("§cLa personne n'est pas connectée.");
				return false;
			}
			if (!TPAQuery.get(p).isRequesting(targeter)) {
				p.sendMessage("§cVous n'avez pas fait de demande de téléportation à cette personne.");
				return false;
			}
			TPAQuery.get(p).getRequesting(targeter).delete();
			p.sendMessage("§6Téléportation annulée.");

		}
		return false;
	}

}
