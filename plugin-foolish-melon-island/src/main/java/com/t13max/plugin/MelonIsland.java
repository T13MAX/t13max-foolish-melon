package com.t13max.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MelonIsland extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("瓜岛插件已加载");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
