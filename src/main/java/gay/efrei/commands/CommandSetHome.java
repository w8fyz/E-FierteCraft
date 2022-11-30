package gay.efrei.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gay.efrei.account.Account;
import gay.efrei.account.Home;
import gay.efrei.account.HomeLocation;

public class CommandSetHome implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Account account = Account.get(p.getUniqueId());
			if (args.length < 1) {
				p.sendMessage("§cFormat de la commande : /sethome [nom du home].");
				return false;
			}
			if (account.getHomeByName(args[0]) != null) {
				account.getHomes().remove(account.getHomeByName(args[0]));
			} else {
				if (account.getHomes().size() >= 3) {
					p.sendMessage("§cLe nombre maximal de homes est de 3.");
					return false;
				}
			}
			
			account.getHomes()
					.add(new Home(args[0],
							new HomeLocation(p.getLocation().getWorld().getName(), p.getLocation().getX(),
									p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(),
									p.getLocation().getPitch())));
			account.save();
			p.sendMessage("§aTon home a été créé avec succès !");
		}
		return true;
	}

}
