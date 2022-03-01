package xyz.lotho.me.managers;

import xyz.lotho.me.SkyChat;

import java.sql.SQLException;
import java.util.UUID;

public class User {

    private final SkyChat instance;

    private int id;
    private UUID uuid;
    private String bio;
    private String chatColor;

    public User(SkyChat instance, UUID uuid, String bio) {
        this.instance = instance;

        this.id = 0;
        this.uuid = uuid;
        this.bio = bio;
        this.chatColor = "&7";
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getChatColor() {
        return this.chatColor;
    }

    public void setChatColor(String chatColor) {
        this.chatColor = chatColor;
    }

    public void load() {
        String table = this.instance.config.getString("database.table");

        this.instance.sql.select("SELECT id, bio, chatColor FROM " + table + " WHERE uuid=?", res -> {
            try {
                if (res.next()) {
                    this.id = res.getInt("id");
                    this.bio = res.getString("bio");
                    this.chatColor = res.getString("chatColor");
                } else {
                    this.instance.sql.select("SELECT max(id) FROM " + table, newRes -> {
                        try {
                            if (newRes.next()) {
                                int entries = newRes.getInt(1);
                                this.instance.sql.execute("INSERT INTO " + table + "(id, uuid, bio, chatColor) VALUES (?, ?, ?, ?)", entries + 1, uuid.toString(), this.bio, this.chatColor);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }, this.uuid.toString());
    }

    public void save() {
        String table = this.instance.config.getString("database.table");

        this.instance.sql.execute("UPDATE " + table + " SET bio=?, chatColor=? WHERE uuid=?", this.bio, this.chatColor, this.uuid.toString());
    }
}
