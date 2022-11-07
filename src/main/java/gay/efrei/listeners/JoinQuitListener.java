package gay.efrei.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import gay.efrei.account.Account;
import gay.efrei.managers.player.PlayerDecoration;
import gay.efrei.managers.scoreboard.Board;

public class JoinQuitListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Account account = Account.get(p.getUniqueId());
		e.setJoinMessage("§a+ §7"+p.getName());
		if(account == null) {
			new Account(p.getUniqueId(), "", new ArrayList<>(), 0, new ArrayList<>(), 0, 0).save();
			Bukkit.getOnlinePlayers().forEach(pls ->  {
				pls.playSound(pls.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			});
			Bukkit.broadcastMessage("§eBienvenue à §6"+p.getName()+" §esur le serveur !");
		}
		Board.set(p);
		PlayerDecoration.update(p);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage("§c- §7"+p.getName());
	}

}
