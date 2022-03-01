package xyz.lotho.me.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import xyz.lotho.me.SkyChat;
import xyz.lotho.me.interfaces.ChatColorsMenu;

public class handleInventory implements Listener {

    private final SkyChat instance;

    public handleInventory(SkyChat instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onInventoryMove(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory.getName().equals(this.instance.chatManagerMenu.getName()) && event.getSlotType() != InventoryType.SlotType.OUTSIDE) {
            this.instance.chatManagerMenu.handleClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getSlot());
            event.setCancelled(true);
            return;
        }

        ChatColorsMenu chatColorsMenu = new ChatColorsMenu(this.instance, (Player) event.getWhoClicked());

        if (inventory.getName().equals(chatColorsMenu.getName()) && event.getSlotType() != InventoryType.SlotType.OUTSIDE) {
            chatColorsMenu.handleClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getSlot());
            event.setCancelled(true);
            return;
        }

    }
}
