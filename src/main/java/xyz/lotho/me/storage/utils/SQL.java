package xyz.lotho.me.storage.utils;

import org.bukkit.Bukkit;
import xyz.lotho.me.SkyChat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {
    protected final SkyChat instance;
    protected final Connection connection;

    public SQL(SkyChat instance) {
        this.instance = instance;
        this.connection = this.instance.mySQL.getConnection();
    }

    public void createTable(String name, String info) {
        this.instance.getServer().getScheduler().runTaskAsynchronously(this.instance, () -> {
            try {
                this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + name + "(" + info + ");").execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void execute(String query, Object... values) {
        this.instance.getServer().getScheduler().runTaskAsynchronously(this.instance, () -> {
            try {
                PreparedStatement statement = this.connection.prepareStatement(query);
                for (int i = 0; i < values.length; i++) {
                    statement.setObject((i +1), values[i]);
                }
                statement.execute();
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("An error occurred while executing an update on the database.");
                Bukkit.getConsoleSender().sendMessage("MySQL#execute : " + query);
                e.printStackTrace();
            }
        });
    }

    public void select(String query, SelectCall callback, Object... values) {
        this.instance.getServer().getScheduler().runTaskAsynchronously(this.instance, () -> {
            try {
                PreparedStatement statement = this.connection.prepareStatement(query);
                for (int i = 0; i < values.length; i++) {
                    statement.setObject((i +1), values[i]);
                }
                callback.call(statement.executeQuery());
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("An error occurred while executing a query on the database.");
                Bukkit.getConsoleSender().sendMessage("MySQL#select : " + query);
                e.printStackTrace();
            }
        });
    }
}
