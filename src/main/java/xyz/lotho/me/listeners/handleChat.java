package xyz.lotho.me.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.managers.User;
import xyz.lotho.me.utils.Chat;

public class handleChat implements Listener {

    private final SkyChat instance;

    public handleChat(SkyChat instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player sender = event.getPlayer();
        String message = event.getMessage();

        User user = this.instance.userManager.getUser(sender.getUniqueId());

        String format = this.instance.config.getString("chat.format");
        TextComponent formatted = new TextComponent(PlaceholderAPI.setPlaceholders(sender, format.replace("{playerName}", sender.getName()).replace("{content}", user.getChatColor() + message)));

        BaseComponent[] msg = new BaseComponent[]{new TextComponent(PlaceholderAPI.setPlaceholders(sender, Chat.colorize(this.instance.getConfig().getString("chat.hover").replace("{playerBio}", user.getBio()))))};
        formatted.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, msg));
        formatted.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + sender.getName() + " "));

        event.getRecipients().forEach((player) -> player.spigot().sendMessage(formatted));
    }
}
