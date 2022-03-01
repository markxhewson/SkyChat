package xyz.lotho.me.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.interfaces.utils.Menu;
import xyz.lotho.me.managers.User;
import xyz.lotho.me.utils.Colors;

public class ChatSettingsMenu extends Menu {

    protected final SkyChat instance;

    public ChatSettingsMenu(SkyChat instance) {
        super("Your Chat Settings", 27);

        this.instance = instance;
        setItems();
    }

    @Override
    public void setItems() {
        super.fillRemainingSlots();


    }

    public void handleClick(Player clicker, ItemStack clickedItem, int slot) {
        if (clickedItem.equals(super.FILLER_ITEM)) return;

        User user = this.instance.userManager.getUser(clicker.getUniqueId());
        String color = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

        Colors chatColor = Colors.valueOf(color.replaceAll(" ", "").toUpperCase());


        if (clicker.hasPermission(chatColor.getPermission())) {
            user.setChatColor("&" + chatColor.getColor().getChar());
            clicker.closeInventory();
        }
    }
}
