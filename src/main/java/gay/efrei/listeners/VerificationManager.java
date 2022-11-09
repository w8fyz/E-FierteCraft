package gay.efrei.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import gay.efrei.account.Account;
import gay.efrei.managers.player.Link;

public class VerificationManager implements Listener {

	@EventHandler
	public void onLogin(AsyncPlayerPreLoginEvent event) {
		Account account = Account.get(event.getUniqueId());
		if (account == null) {
			event.disallow(Result.KICK_OTHER,
					"§eBienvenue sur le serveur !\n\n§fPour des raisons de sécurité, vous devez lier votre discord à votre compte minecraft.\n\n§e§l"
							+"§7--------\n§e§l"+ Link.getNewVerificationCode(event.getUniqueId())+"\n§7--------"
							+ "\n\n§fest votre §ecode §fà rentrer dans le §esalon link du Discord.§f Vous pourrez en suite vous reconnecter.");
		}
	}

}
