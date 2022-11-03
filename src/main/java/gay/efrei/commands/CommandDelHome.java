package gay.efrei.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import gay.efrei.account.Account;
import gay.efrei.account.Home;

public class CommandDelHome implements CommandExecutor, TabExecutor {

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

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<String> result = new ArrayList<>();
		if(sender instanceof Player && args.length == 1) {
			Player p = ((Player) sender);
			Account account = Account.get(p.getUniqueId());
			for(Home home : account.getHomes()) {
				if(home.getName().startsWith(args[0])) {
					result.add(home.getName());
				}
			}
		}
		return result;
	}
	
}
