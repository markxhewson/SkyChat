package xyz.lotho.me.interfaces;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.interfaces.utils.Menu;
import xyz.lotho.me.managers.User;
import xyz.lotho.me.utils.Chat;
import xyz.lotho.me.utils.Item;

public class ChatManagerMenu extends Menu {

    protected final SkyChat instance;

    public ChatManagerMenu(SkyChat instance) {
        super("Your Chat Management", 27);

        this.instance = instance;
        setItems();
    }

    @Override
    public void setItems() {
        super.fillRemainingSlots();

        super.getInventory().setItem(11, Item.createItem(
                Material.ANVIL,
                "&c&lYour Bio",
                Chat.colorize("&7Click to modify your bio, that displays"),
                Chat.colorize("&7when users hover over your name in chat!")
        ));

        super.getInventory().setItem(13, Item.createItem(
                Material.INK_SACK,
                "&a&lChat Colors",
                Chat.colorize("&7Click to modify your chat color!")
        ));

        super.getInventory().setItem(15, Item.createItem(
                Material.ITEM_FRAME,
                "&a&lChat Settings",
                Chat.colorize("&7Click to modify the way your chat"),
                Chat.colorize("&7appears to other online users!")
        ));
    }

    public void handleClick(Player clicker, ItemStack clickedItem, int slot) {
        switch (clickedItem.getType()) {
            case ANVIL:
                User user = this.instance.userManager.getUser(clicker.getUniqueId());

                new AnvilGUI.Builder()
                        .onComplete((player, text) -> {
                            user.setBio(ChatColor.stripColor(text).trim());

                            clicker.sendMessage(Chat.colorize("&aYou have successfully set &e" + user.getBio() + " &aas your new bio!"));
                            return AnvilGUI.Response.close();
                        })
                        .text(user.getBio())
                        .plugin(this.instance)
                        .open(clicker);
                break;

            case INK_SACK:
                new ChatColorsMenu(this.instance, clicker).open(clicker);
                break;

            case ITEM_FRAME:
                new ChatSettingsMenu(this.instance, clicker).open(clicker);
                break;

            default:
        }
    }


}
