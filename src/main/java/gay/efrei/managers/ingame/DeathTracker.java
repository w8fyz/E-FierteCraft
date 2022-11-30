package gay.efrei.managers.ingame;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class DeathTracker {

	public static HashMap<Player, Location> deaths = new HashMap<Player, Location>();

	public static void track() {
		for (Iterator<Player> deathsIT = deaths.keySet().iterator(); deathsIT.hasNext();) {
			Player pls = deathsIT.next();
			pls.spigot().sendMessage(ChatMessageType.ACTION_BAR,
					TextComponent.fromLegacyText(getDirectionFormated(pls)));
			if (canStopTrack(pls) && !pls.isDead())
				deathsIT.remove();
		}
	}

	private static boolean canStopTrack(Player player) {
		return player.getLocation().distance(deaths.get(player)) < 5;
	}

	private static String getDirectionFormated(final Player player) {

		Location spawn = deaths.get(player);

		if (player.getWorld().equals(spawn.getWorld())) {

			spawn.setY(player.getLocation().getY());
			final double distanceArrow = getDirectionTo(player, spawn.clone());
			final int distanceM = (int) player.getLocation().distance(spawn);

			if (distanceArrow <= 45.0D) {
				return "§7" + "Mort" + "§f§l ⬆ " + "§7(" + distanceM + "m)";
			} else if (distanceArrow <= 90.0D) {
				return "§7" + "Mort" + "§f§l ⬈ " + "§7(" + distanceM + "m)";
			} else if (distanceArrow <= 135.0D) {
				return "§7" + "Mort" + "§f§l ➡ " + "§7(" + distanceM + "m)";
			} else if (distanceArrow <= 180.0D) {
				return "§7" + "Mort" + "§f§l ⬊ " + "§7(" + distanceM + "m)";
			} else if (distanceArrow <= 225.0D) {
				return "§7" + "Mort" + "§f§l ⬇ " + "§7(" + distanceM + "m)";
			} else if (distanceArrow <= 270.0D) {
				return "§7" + "Mort" + "§f§l ⬋ " + "§7(" + distanceM + "m)";
			} else {
				return distanceArrow <= 315.0D ? "§7" + "Mort" + "§f ⬅ " + "§7(" + distanceM + "m)"
						: "§7" + "Mort" + "§f ⬉ " + "§7(" + distanceM + "m)";
			}
		} else {
			return "§7" + "Mort" + "§7 Introuvable... ";
		}
	}

	public static double getDirectionTo(final Player paramPlayer, final Location paramLocation) {

		final Location localLocation = paramPlayer.getLocation().clone();
		localLocation.setY(0.0D);
		paramLocation.setY(0.0D);
		final Vector localVector1 = localLocation.getDirection();
		final Vector localVector2 = paramLocation.subtract(localLocation).toVector().normalize();
		double d1 = Math.toDegrees(Math.atan2(localVector1.getX(), localVector1.getZ()));
		d1 -= Math.toDegrees(Math.atan2(localVector2.getX(), localVector2.getZ()));
		d1 = (int) (d1 + 22.5D) % 360;
		if (d1 < 0.0D) {
			d1 += 360.0D;
		}

		return d1;

	}

}
