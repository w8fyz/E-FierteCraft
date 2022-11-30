package gay.efrei.managers.scoreboard;

import org.bukkit.scheduler.BukkitRunnable;

import gay.efrei.managers.ingame.DeathTracker;

public class BoardTask extends BukkitRunnable{

	@Override
	public void run() {
		Board.updateAll();
		DeathTracker.track();
	}

}
