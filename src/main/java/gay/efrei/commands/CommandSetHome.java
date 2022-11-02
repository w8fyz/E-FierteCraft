package gay.efrei.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gay.efrei.account.Account;
import gay.efrei.account.Home;

public class CommandSetHome implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Account account = Account.get(p.getUniqueId());
			if(args.length < 1) {
				p.sendMessage("§cFormat de la commande : /sethome [nom du home].");
				return false;
			}
			if(account.getHomes().size() >= 3) {
				p.sendMessage("§cLe nombre maximal de homes est de 3.");
				return false;
			}
			if(account.getHomeByName(args[1]) != null) {
				p.sendMessage("§cTu possèdes déjà un home avec ce nom.");
				return false;
			}
			account.getHomes().add(new Home(args[1], p.getLocation()));
			account.save();
			p.sendMessage("§aTon home a été créé avec succès !");
		}
		return false;
	}

}
