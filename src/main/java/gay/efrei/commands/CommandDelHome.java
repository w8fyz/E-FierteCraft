package gay.efrei.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gay.efrei.account.Account;
import gay.efrei.account.Home;

public class CommandDelHome implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Account account = Account.get(p.getUniqueId());
			if(args.length < 1) {
				p.sendMessage("§cFormat de la commande : /delhome [nom du home].");
				return false;
			}
			Home home = account.getHomeByName(args[1]);
			if(home == null) {
				p.sendMessage("§cTu ne possèdes pas de homes avec ce nom.");
				return false;
			}
			account.getHomes().remove(home);
			account.save();
			p.sendMessage("§aTon home a été supprimé avec succès !");
		}
		return true;
	}

}
