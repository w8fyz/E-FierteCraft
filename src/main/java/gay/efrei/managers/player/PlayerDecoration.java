package gay.efrei.managers.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import gay.efrei.account.Account;

public class PlayerDecoration {

	@SuppressWarnings("deprecation")
	public static void update(Player p) {
		final Scoreboard scoreBoard = Bukkit.getScoreboardManager().getMainScoreboard();
		final String id = getTeamID(p);

		Account account = Account.get(p.getUniqueId());
		
		if (scoreBoard.getTeam(id) == null) {
			scoreBoard.registerNewTeam(id);
		}

		final Team team = scoreBoard.getTeam(id);

		team.setPrefix(account.getFormatedColor()+account.getPronouns()+ (account.getPronouns().length() == 0 ? "" : " "));
		team.setSuffix(" �8[�7"+account.getDeaths()+"]�8");
		team.addPlayer(p);

		p.setScoreboard(scoreBoard);
	}
	
	private static String getTeamID(Player p) {
		String uuid = p.getUniqueId().toString().substring(0, 13);
		return uuid;
	}
}
