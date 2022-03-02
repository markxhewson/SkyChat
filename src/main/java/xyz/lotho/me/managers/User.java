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

    private boolean italic;
    private boolean bold;
    private boolean underline;
    private boolean strikethrough;

    public User(SkyChat instance, UUID uuid, String bio) {
        this.instance = instance;

        this.id = 0;
        this.uuid = uuid;
        this.bio = bio;
        this.chatColor = "&7";

        this.italic = false;
        this.bold = false;
        this.underline = false;
        this.strikethrough = false;
    }

    public void load() {
        String table = this.instance.config.getString("database.table");

        this.instance.sql.select("SELECT id, bio, chatColor, italic, bold, underline, strikethrough FROM " + table + " WHERE uuid=?", res -> {
            try {
                if (res.next()) {
                    this.id = res.getInt("id");
                    this.bio = res.getString("bio");
                    this.chatColor = res.getString("chatColor");

                    this.italic = res.getBoolean("italic");
                    this.bold = res.getBoolean("bold");
                    this.underline = res.getBoolean("underline");
                    this.strikethrough = res.getBoolean("strikethrough");
                } else {
                    this.instance.sql.select("SELECT max(id) FROM " + table, newRes -> {
                        try {
                            if (newRes.next()) {
                                int entries = newRes.getInt(1);
                                this.instance.sql.execute(
                                        "INSERT INTO " + table + "(id, uuid, bio, chatColor, italic, bold, underline, strikethrough) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                        entries + 1, uuid.toString(), this.bio, this.chatColor, this.italic, this.bold, this.underline, this.strikethrough
                                );
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

        this.instance.sql.execute(
                "UPDATE " + table + " SET bio=?, chatColor=?, italic=?, bold=?, underline=?, strikethrough=? WHERE uuid=?",
                this.bio, this.chatColor, this.italic, this.bold, this.underline, this.strikethrough, this.uuid.toString()
        );
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

    public boolean getItalic() {
        return this.italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean getBold() {
        return this.bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean getUnderline() {
        return this.underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public boolean getStrikethrough() {
        return this.strikethrough;
    }

    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }
}
