package gay.efrei.managers.scoreboard;

import org.bukkit.scheduler.BukkitRunnable;

public class BoardTask extends BukkitRunnable{

	@Override
	public void run() {
		Board.updateAll();
	}

}
