package gay.efrei.managers.scoreboard;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import gay.efrei.account.Account;
import gay.efrei.managers.tpa.TPARequest;

public class Board {

	private static JPerPlayerScoreboard scoreboard;

	private static int color = -1;
	private static String[] colors = {"&c", "&6", "&e", "&a", "&b", "&9", "&f", "&f", "&f", "&f", "&f", "&f", "&f", "&f", "&f", "&f"};
	
	public static void init() {
		scoreboard = new JPerPlayerScoreboard((player) -> {
			return "&7 - "+ color() + "Efierte&fCraft &7-";
		}, (player) -> {
			
			Account account = Account.get(player.getUniqueId());
			
			return Arrays.asList(
					"§a",
					"➤ §e"+player.getName(),
					"Badge"+(account.getBadges().size() > 1 ? "s" : "")+": §b"+account.getBadges().size(),
					"§b",
					"TPA : "+canTP(account),
					"Home"+(account.getHomes().size() > 1 ? "s" : "")+": §b"+account.getHomes().size(),
					"§c",
					"En ligne"+(Bukkit.getOnlinePlayers().size() > 1 ? "s" : "")+": §a"+Bukkit.getOnlinePlayers().size(),
					"§e",
					"§7efrei.gay");
		});
	}
	
	private static String canTP(Account account) {
		return account.canTeleport() ? "§a✔" : formated(account.getLastTP());
	}

	private static String formated(long lastTP) {
        long milliseconds = TPARequest.TP_COOLDOWN - (System.currentTimeMillis() - lastTP);
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
		return "§e"+(minutes > 9 ? minutes : "0"+minutes) + ":" + (seconds > 9 ? seconds : "0"+seconds);
	}

	private static String color() {
		color++;
		if (color ==  colors.length) {
			color = 0;
		}

		return colors[color];
	}

	public static void set(Player p) {
		scoreboard.addPlayer(p);
	}

	public static void update() {
		scoreboard.updateScoreboard();
	}

}
