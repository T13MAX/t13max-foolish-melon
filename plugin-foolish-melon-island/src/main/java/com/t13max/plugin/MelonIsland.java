package com.t13max.plugin;

import com.t13max.plugin.chunk.AirChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MelonIsland extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("瓜岛插件开始加载");


        getLogger().info("瓜岛插件已加载");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        //空岛
        return new AirChunkGenerator();
    }
}
