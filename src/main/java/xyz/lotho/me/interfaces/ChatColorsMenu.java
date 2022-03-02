package xyz.lotho.me.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.interfaces.utils.Menu;
import xyz.lotho.me.managers.User;
import xyz.lotho.me.utils.Chat;
import xyz.lotho.me.utils.Colors;
import xyz.lotho.me.utils.Item;

import java.util.Arrays;

public class ChatColorsMenu extends Menu {

    protected final SkyChat instance;

    protected Player player;

    public ChatColorsMenu(SkyChat instance, Player player) {
        super("Chat Color Options", 36);

        this.instance = instance;
        this.player = player;

        setItems();
    }

    @Override
    public void setItems() {
        for (int i = 0; i < 9; i ++) {
            super.getInventory().setItem(i, super.FILLER_ITEM);
        }
        super.getInventory().setItem(9, super.FILLER_ITEM);
        super.getInventory().setItem(17, super.FILLER_ITEM);
        super.getInventory().setItem(18, super.FILLER_ITEM);
        super.getInventory().setItem(26, super.FILLER_ITEM);

        Arrays.stream(Colors.values()).filter((color) -> !color.getSetting()).forEach((color) -> {
            super.getInventory().addItem(
                    Item.createItemShort(
                            Material.STAINED_GLASS_PANE,
                            color.getCode(),
                            color.getColor() + color.getDisplayName(),
                            Chat.colorize(player.hasPermission(color.getPermission()) ? "&7Click to set your chat color as " + color.getDisplayName() + "." : "&cThis color is locked, you cannot access it.")
                    )
            );
        });
        super.getInventory().setItem(27, Item.createItem(Material.ARROW, "&7&lBack"));

        super.fillRemainingSlots();
    }

    public void handleClick(Player clicker, ItemStack clickedItem, int slot) {
        if (clickedItem.equals(super.FILLER_ITEM)) return;
        if (clickedItem.getType() == Material.ARROW) {
            new ChatManagerMenu(this.instance).open(clicker);
            return;
        }

        User user = this.instance.userManager.getUser(clicker.getUniqueId());
        String color = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

        Colors chatColor = Colors.valueOf(color.replaceAll(" ", "").toUpperCase());


        if (clicker.hasPermission(chatColor.getPermission())) {
            user.setChatColor("&" + chatColor.getColor().getChar());
            clicker.closeInventory();
        }
    }

}
