package gay.efrei.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import gay.efrei.managers.ingame.RainVote;

public class RainListener implements Listener {
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if(event.toWeatherState()) {
			int goal = RainVote.start();
			Bukkit.broadcastMessage("§bIl commence à pleuvoir !");
			Bukkit.broadcastMessage("§b> Si §e"+goal+" §bpersonne"+(goal > 1 ? "s font" : " fait")+" la commande §e/voterain§b, le soleil reviendra.");
		}
	}

}
