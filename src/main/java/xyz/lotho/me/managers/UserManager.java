package xyz.lotho.me.managers;

import xyz.lotho.me.SkyChat;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class UserManager {

    private final SkyChat instance;
    private final HashMap<UUID, User> usersMap = new HashMap<UUID, User>();

    public UserManager(SkyChat instance) {
        this.instance = instance;
    }

    public void handleUserCreation(UUID uuid) {
        if (!this.usersMap.containsKey(uuid)) usersMap.put(uuid, new User(instance, uuid, this.instance.config.getString("chat.defaultBio")));
    }

    public User getUser(UUID uuid) {
        if (!this.usersMap.containsKey(uuid)) return null;
        return this.usersMap.get(uuid);
    }
}
