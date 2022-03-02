package xyz.lotho.me.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.managers.User;
import xyz.lotho.me.utils.Chat;

import java.util.Arrays;
import java.util.Iterator;

public class handleChat implements Listener {

    private final SkyChat instance;

    public handleChat(SkyChat instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player sender = event.getPlayer();
        User user = this.instance.userManager.getUser(sender.getUniqueId());

        String format = this.instance.config.getString("chat.format");
        StringBuilder message = new StringBuilder();

        if (user.getBold()) message.append("&l");
        if (user.getItalic()) message.append("&o");
        if (user.getUnderline()) message.append("&n");
        if (user.getStrikethrough()) message.append("&m");

        message.append(event.getMessage());

        String staffColor = this.instance.config.getString("utils.staffColor");

        String formatted = PlaceholderAPI.setPlaceholders(
                sender, format.replace("{playerName}", sender.getName())
                        .replace("{content}", (!user.isStaff() ? user.getChatColor() : staffColor) + message)
        );

        BaseComponent[] component = TextComponent.fromLegacyText(formatted);

        Arrays.stream(component).iterator().forEachRemaining((baseComponent) -> {
            baseComponent.setClickEvent(
                    new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + sender.getName() + " ")
            );
            baseComponent.setHoverEvent(
                    new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                            PlaceholderAPI.setPlaceholders(sender, Chat.colorize(this.instance.getConfig().getString("chat.hover").replace("{playerBio}", user.getBio())))
                    ).create())
            );
        });

        event.getRecipients().forEach((player) -> player.spigot().sendMessage(component));
    }
}
