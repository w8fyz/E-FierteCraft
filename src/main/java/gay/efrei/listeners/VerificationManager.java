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
		if (!account.isLinked()) {
			event.disallow(Result.KICK_OTHER,
					"§eBienvenue sur le serveur !\n§6Pour des raisons de sécurité, vous devez lier votre discord à votre compte minecraft.\n\n§e§l"
							+ Link.getNewVerificationCode(event.getUniqueId())
							+ "\n\n§6est votre code à rentrer dans le salon link du Discord  Vous pourrez en suite vous reconnecter.");
		}
	}

}
