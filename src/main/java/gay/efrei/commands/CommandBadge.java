package gay.efrei.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import gay.efrei.account.Account;
import gay.efrei.account.Badges;
import gay.efrei.managers.discord.Discord;

public class CommandBadge implements CommandExecutor, TabExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Player target = null;
			if (args.length < 1) {
				target = p;
			} else {
				target = Bukkit.getPlayer(args[0]);
			}
			if (target == null || !target.isOnline()) {
				p.sendMessage("§cLe joueur est invalide ou n'est pas connecté.");
				return false;
			}
			Account target_acc = Account.get(target.getUniqueId());
			if (args.length == 2) {
				if (!p.isOp()) {
					p.sendMessage("§cErreur : Tu n'as pas la permission requise.");
					return false;
				}
				try {
					Badges badge = Badges.valueOf(args[1]);
					if(target_acc.getBadges().contains(badge)) {
						target_acc.getBadges().remove(badge);
						target_acc.save();
						p.sendMessage("§aLe badge §e" + badge.getName() + " §aa bien été retiré à §e" + target.getName()
								+ "§a !");
						return true;
					}
					target_acc.getBadges().add(badge);
					target_acc.save();
					p.sendMessage("§aLe badge §e" + badge.getName() + " §aa bien été attribué à §e" + target.getName()
							+ "§a !");
					Discord.sendChatMessage(":star: "+target.getName()+" vient d'obtenir le badge **"+badge.getName()+"** ! :star:");
					return true;
				} catch (Exception e) {
					p.sendMessage("§cErreur : Le badge est invalide.");
				}
			} else {
				if (target_acc.getBadges().isEmpty()) {
					p.sendMessage("§e" + target.getName() + " §7ne possède aucun badge.");
					return true;
				}
				p.sendMessage("§7Badges de §e" + target.getName() + "§7 :");
				for (Badges b : target_acc.getBadges()) {
					p.sendMessage("§7- §e" + b.getName() + "§7 : §f" + b.getDescription());
				}
				return true;
			}

		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> matches = new ArrayList<String>();

		if (args.length == 1) {
			final String search = args[0].toLowerCase(Locale.ROOT);

			for (final Player pp : Bukkit.getOnlinePlayers()) {
				if (pp.getName().toLowerCase(Locale.ROOT).startsWith(search)) {
					matches.add(pp.getName());
				}
			}
		} else if (args.length == 2) {
			final String search = args[1].toLowerCase(Locale.ROOT);

			for (Badges badge : Badges.values()) {
				if (badge.toString().toLowerCase(Locale.ROOT).startsWith(search)) {
					matches.add(badge.toString());
				}
			}
		}

		return matches;
	}

}
