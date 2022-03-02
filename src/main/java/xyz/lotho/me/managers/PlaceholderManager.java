package xyz.lotho.me.managers;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import xyz.lotho.me.SkyChat;

public class PlaceholderManager extends PlaceholderExpansion {

    SkyChat instance;

    public PlaceholderManager(SkyChat instance) {
        this.instance = instance;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "https://github.com/markxhewson)";
    }

    @Override
    public String getIdentifier() {
        return "skychat";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        User user = this.instance.userManager.getUser(player.getUniqueId());

        if (identifier.equals("chatColor")) {
            boolean isStaff = player.hasPermission(this.instance.config.getString("utils.staffPermission"));
            return isStaff ? "&4" : user.getChatColor();
        }

        if (identifier.equals("bio")) {
            return user.getBio();
        }

        return null;
    }
}
