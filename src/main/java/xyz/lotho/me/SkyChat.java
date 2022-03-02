package xyz.lotho.me;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.lotho.me.commands.ChatCommand;
import xyz.lotho.me.config.Config;
import xyz.lotho.me.interfaces.ChatManagerMenu;
import xyz.lotho.me.interfaces.ChatColorsMenu;
import xyz.lotho.me.listeners.handleChat;
import xyz.lotho.me.listeners.handleConnections;
import xyz.lotho.me.listeners.handleDisconnects;
import xyz.lotho.me.listeners.handleInventory;
import xyz.lotho.me.managers.PlaceholderManager;
import xyz.lotho.me.managers.User;
import xyz.lotho.me.managers.UserManager;
import xyz.lotho.me.storage.MySQL;
import xyz.lotho.me.storage.utils.SQL;

public final class SkyChat extends JavaPlugin {

    public Config configManager = new Config(this, "config.yml");
    public YamlConfiguration config = configManager.getConfig();

    public UserManager userManager = new UserManager(this);

    public ChatManagerMenu chatManagerMenu = new ChatManagerMenu(this);

    public MySQL mySQL;
    public SQL sql;

    PluginManager pluginManager;

    @Override
    public void onEnable() {
        pluginManager = this.getServer().getPluginManager();

        mySQL = new MySQL(this);
        sql = new SQL(this);

        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderManager(this).register();
        }

        sql.createTable(
                this.config.getString("database.table"),
                "id INTEGER NOT NULL PRIMARY KEY," +
                        "uuid VARCHAR(36) NOT NULL," +
                        "bio TEXT NOT NULL," +
                        "chatColor VARCHAR(3) NOT NULL," +
                        "italic BOOLEAN NOT NULL," +
                        "bold BOOLEAN NOT NULL," +
                        "underline BOOLEAN NOT NULL," +
                        "strikethrough BOOLEAN NOT NULL"
        );

        this.getServer().getOnlinePlayers().forEach((player) -> {
            this.userManager.handleUserCreation(player.getUniqueId());
            User user = this.userManager.getUser(player.getUniqueId());

            this.getServer().getScheduler().runTaskAsynchronously(this, user::load);
        });

        loadListeners();
        loadCommands();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void loadListeners() {
        pluginManager.registerEvents(new handleChat(this), this);
        pluginManager.registerEvents(new handleInventory(this), this);
        pluginManager.registerEvents(new handleConnections(this), this);
        pluginManager.registerEvents(new handleDisconnects(this), this);
    }

    public void loadCommands() {
        this.getCommand("chat").setExecutor(new ChatCommand(this));
    }
}
