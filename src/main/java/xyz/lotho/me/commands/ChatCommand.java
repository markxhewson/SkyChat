package xyz.lotho.me.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.lotho.me.SkyChat;

public class ChatCommand implements CommandExecutor {

    private final SkyChat instance;

    public ChatCommand(SkyChat instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        this.instance.chatManagerMenu.open(((Player) sender));
        return true;
    }
}
