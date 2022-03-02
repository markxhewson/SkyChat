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
import java.util.List;

public class ChatSettingsMenu extends Menu {

    protected final SkyChat instance;

    protected final Player player;
    protected final List<Integer> slots;

    public ChatSettingsMenu(SkyChat instance, Player player) {
        super("Your Chat Settings", 27);

        this.instance = instance;
        this.player = player;

        this.slots = Arrays.asList(10, 12, 14, 16);

        setItems();
    }

    @Override
    public void setItems() {
        int[] index = new int[1];
        Arrays.stream(Colors.values()).filter(Colors::getSetting).forEach((color) -> {
            super.getInventory().setItem(this.slots.get(index[0]), Item.createItem(
                    color.getMaterial(),
                    color.getColor() + color.getDisplayName(),
                    Chat.colorize(player.hasPermission(color.getPermission()) ? "&7Click to activate the " + color.getDisplayName() + " chat setting." : "&cThis chat setting is locked, you cannot access it.")
            ));
            index[0]++;
        });

        super.getInventory().setItem(18, Item.createItem(Material.ARROW, "&7&lBack"));
        super.fillRemainingSlots();
    }

    public void handleClick(Player clicker, ItemStack clickedItem, int slot) {
        if (clickedItem.equals(super.FILLER_ITEM)) return;
        if (clickedItem.getType() == Material.ARROW) {
            new ChatManagerMenu(this.instance).open(clicker);
            return;
        }

        User user = this.instance.userManager.getUser(clicker.getUniqueId());
        String settingName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

        Colors setting = Colors.valueOf(settingName.replaceAll(" ", "").toUpperCase());

        boolean mode = false;
        if (clicker.hasPermission(setting.getPermission())) {
            if (settingName.toLowerCase().contains("italic")) {
                mode = !user.getItalic();
                if (mode && user.getSettingsEnabled() >= 2) {
                    this.player.sendMessage(Chat.colorize("&cYou can not enable more than two chat settings at a time!"));
                    return;
                }

                user.setItalic(!user.getItalic());
            }
            if (settingName.toLowerCase().contains("bold")) {
                mode = !user.getBold();
                if (mode && user.getSettingsEnabled() >= 2) {
                    this.player.sendMessage(Chat.colorize("&cYou can not enable more than two chat settings at a time!"));
                    return;
                }

                user.setBold(!user.getBold());
            }
            if (settingName.toLowerCase().contains("underline")) {
                mode = !user.getUnderline();
                if (mode && user.getSettingsEnabled() >= 2) {
                    this.player.sendMessage(Chat.colorize("&cYou can not enable more than two chat settings at a time!"));
                    return;
                }

                user.setUnderline(!user.getUnderline());
            }
            if (settingName.toLowerCase().contains("strikethrough")) {
                mode = !user.getStrikethrough();
                if (mode && user.getSettingsEnabled() >= 2) {
                    this.player.sendMessage(Chat.colorize("&cYou can not enable more than two chat settings at a time!"));
                    return;
                }

                user.setStrikethrough(!user.getStrikethrough());
            }

            this.player.sendMessage(Chat.colorize("&aYou have successfully &e" + (mode ? "enabled" : "disabled") + " &athe &e" + setting.getDisplayName() + " &asetting!"));
        }
    }
}
