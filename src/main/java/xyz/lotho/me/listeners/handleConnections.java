package xyz.lotho.me.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.managers.User;

public class handleConnections implements Listener {

    private SkyChat instance;

    public handleConnections(SkyChat instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        this.instance.userManager.handleUserCreation(event.getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = this.instance.userManager.getUser(player.getUniqueId());

        this.instance.getServer().getScheduler().runTaskAsynchronously(this.instance, user::load);
    }
}
