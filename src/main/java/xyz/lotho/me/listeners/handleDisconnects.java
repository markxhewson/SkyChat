package xyz.lotho.me.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.managers.User;

public class handleDisconnects implements Listener {

    private SkyChat instance;

    public handleDisconnects(SkyChat instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = this.instance.userManager.getUser(player.getUniqueId());

        this.instance.getServer().getScheduler().runTaskAsynchronously(this.instance, user::save);
    }
}
