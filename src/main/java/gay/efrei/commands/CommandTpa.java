package gay.efrei.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gay.efrei.account.Account;
import gay.efrei.managers.tpa.TPAQuery;
import gay.efrei.managers.tpa.TPARequest;

public class CommandTpa implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Account sender_account = Account.get(p.getUniqueId());
			if (args.length == 0) {
				p.sendMessage("§cLe format de la commande est /tpa [pseudo]");
				return false;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage("§cImpossible de trouver un compte avec ce pseudo connecté.");
				return false;
			}
			if (p == target) {
				p.sendMessage("§cVous ne pouvez pas vous téléporter à vous même !");
				return false;
			}
			if (TPAQuery.get(p).isOnRequestCooldown(target)) {
				p.sendMessage("§cUne requête a déjà été envoyée à cette personne il y a moins de 2 minutes.");
				return false;
			}
			if (!sender_account.canTeleport()) {
				p.sendMessage("§cVotre dernière téléportation date d'il y a moins de 5 minutes.");
				return false;
			}
			TPARequest.make().sender(p).target(target).execute();
			p.sendMessage("§6Requête de téléportation envoyée à §e" + target.getName() + " §6!");
			return true;
		}
		return false;
	}

}
