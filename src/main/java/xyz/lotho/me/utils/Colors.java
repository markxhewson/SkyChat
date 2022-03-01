package xyz.lotho.me.utils;

import org.bukkit.ChatColor;

public enum Colors {
    RED(ChatColor.RED, 14, "Red", "skychat.red"),
    DARKAQUA(ChatColor.DARK_AQUA, 9, "Dark Aqua", "skychat.darkAqua"),
    LIGHTPURPLE(ChatColor.LIGHT_PURPLE, 6, "Light Purple", "skychat.lightPurple"),
    GOLD(ChatColor.GOLD, 1, "Gold", "skychat.gold"),
    LIME(ChatColor.GREEN, 5, "Lime", "skychat.lime"),
    AQUA(ChatColor.AQUA, 3, "Aqua", "skychat.aqua"),
    GRAY(ChatColor.GRAY, 8, "Gray", "skychat.gray"),
    DARKGREEN(ChatColor.DARK_GREEN, 13, "Dark Green", "skychat.darkGreen"),
    BLUE(ChatColor.BLUE, 11, "Blue", "skychat.blue"),
    DARK_PURPLE(ChatColor.DARK_PURPLE, 2, "Dark Purple", "skychat.darkPurple"),
    BLACK(ChatColor.BLACK, 15, "Black", "skychat.black"),
    YELLOW(ChatColor.YELLOW, 4, "Yellow", "skychat.yellow"),
    DARKGRAY(ChatColor.DARK_GRAY, 7, "Dark Gray", "skychat.darkGray"),
    WHITE(ChatColor.WHITE, 0, "White", "skychat.white"),

    ITALICS(ChatColor.ITALIC, 0, "Italic", "skychat.italic"),
    BOLD(ChatColor.BOLD, 0, "Bold", "skychat.bold"),
    UNDERLINE(ChatColor.UNDERLINE, 0, "Underline", "skychat.underline"),
    STRIKETHROUGH(ChatColor.STRIKETHROUGH, 0, "Strikethrough", "skychat.strikethrough");

    private final ChatColor color;
    private final int code;
    private final String displayName;
    private final String permission;

    private Colors(ChatColor color, int code, String displayName, String permission) {
        this.color = color;
        this.code = code;
        this.displayName = displayName;
        this.permission = permission;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public int getCode() {
        return this.code;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getPermission() {
        return this.permission;
    }
}
