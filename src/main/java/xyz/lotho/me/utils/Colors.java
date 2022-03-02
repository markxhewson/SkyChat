package xyz.lotho.me.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Colors {
    RED(ChatColor.RED, Material.STAINED_GLASS, 14, "Red", "skychat.red", false),
    DARKAQUA(ChatColor.DARK_AQUA, Material.STAINED_GLASS, 9, "Dark Aqua", "skychat.darkAqua", false),
    LIGHTPURPLE(ChatColor.LIGHT_PURPLE, Material.STAINED_GLASS, 6, "Light Purple", "skychat.lightPurple", false),
    GOLD(ChatColor.GOLD, Material.STAINED_GLASS, 1, "Gold", "skychat.gold", false),
    LIME(ChatColor.GREEN, Material.STAINED_GLASS, 5, "Lime", "skychat.lime", false),
    AQUA(ChatColor.AQUA, Material.STAINED_GLASS, 3, "Aqua", "skychat.aqua", false),
    GRAY(ChatColor.GRAY, Material.STAINED_GLASS, 8, "Gray", "skychat.gray", false),
    DARKGREEN(ChatColor.DARK_GREEN, Material.STAINED_GLASS, 13, "Dark Green", "skychat.darkGreen", false),
    BLUE(ChatColor.BLUE, Material.STAINED_GLASS, 11, "Blue", "skychat.blue", false),
    DARKPURPLE(ChatColor.DARK_PURPLE, Material.STAINED_GLASS, 2, "Dark Purple", "skychat.darkPurple", false),
    BLACK(ChatColor.BLACK, Material.STAINED_GLASS, 15, "Black", "skychat.black", false),
    YELLOW(ChatColor.YELLOW, Material.STAINED_GLASS, 4, "Yellow", "skychat.yellow", false),
    DARKGRAY(ChatColor.DARK_GRAY, Material.STAINED_GLASS, 7, "Dark Gray", "skychat.darkGray", false),
    WHITE(ChatColor.WHITE, Material.STAINED_GLASS, 0, "White", "skychat.white", false),

    ITALIC(ChatColor.ITALIC, Material.SUGAR, 0, "Italic", "skychat.italic", true),
    BOLD(ChatColor.BOLD, Material.BEDROCK, 0, "Bold", "skychat.bold", true),
    UNDERLINE(ChatColor.UNDERLINE, Material.RAILS, 0, "Underline", "skychat.underline", true),
    STRIKETHROUGH(ChatColor.STRIKETHROUGH, Material.REDSTONE_TORCH_ON, 0, "Strikethrough", "skychat.strikethrough", true);

    private final ChatColor color;
    private final Material material;
    private final int code;
    private final String displayName;
    private final String permission;
    private final boolean setting;

    private Colors(ChatColor color, Material material, int code, String displayName, String permission, boolean setting) {
        this.color = color;
        this.material = material;
        this.code = code;
        this.displayName = displayName;
        this.permission = permission;
        this.setting = setting;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public Material getMaterial() {
        return this.material;
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

    public boolean getSetting() {
        return this.setting;
    }
}
