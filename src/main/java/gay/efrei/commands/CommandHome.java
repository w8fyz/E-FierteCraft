package gay.efrei.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import gay.efrei.account.Account;
import gay.efrei.account.Home;

public class CommandHome implements CommandExecutor, TabExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Account account = Account.get(p.getUniqueId());
			if(args.length == 0) {
				if(account.getHomes().isEmpty()) {
					p.sendMessage("§cTu n'as aucun home.");
					return false;
				}
				p.sendMessage("§6Liste de tes homes : ");
				p.sendMessage("§6"+account.getFormatedHomes());
				return true;
			}
			Home home = account.getHomeByName(args[0]);
			if(home == null) {
				p.sendMessage("§cTu n'as aucun home avec le nom \""+args[0]+"\".");
				return false;
			}
			p.sendMessage("§6Teleporation vers "+home.getName()+" !");
			p.teleport(home.getLocation());
			p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
			
			
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
