package com.t13max.plugin;

import com.t13max.common.manager.ManagerBase;
import com.t13max.common.plugin.PluginContext;
import com.t13max.plugin.chunk.AirChunkGenerator;
import com.t13max.plugin.command.CommandManager;
import com.t13max.plugin.menu.MenuManager;
import com.t13max.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public final class MelonIsland extends JavaPlugin implements Listener {

    public static JavaPlugin PLUGIN;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

        try {

            Log.melon.info("瓜岛插件开始加载");

            //缓存自己到静态字段 方便获取
            PLUGIN = this;

            Log.melon.info("瓜岛插件, 初始化插件上下文...");
            PluginContext.onEnable();

            Log.melon.info("瓜岛插件, 初始化所有Manager...");
            ManagerBase.initAllManagers();

            Log.melon.info("瓜岛插件已加载");

        } catch (Exception e) {
            e.printStackTrace();
            Log.melon.error("瓜岛插件加载出错!");
            //后续补充异常处理
        }
    }

    @Override
    public void onDisable() {
        Log.melon.info("瓜岛插件开始卸载");
        try {
            Log.melon.info("瓜岛插件, Bukkit.getScheduler().cancelTasks...");
            Bukkit.getScheduler().cancelTasks(this);
            Log.melon.info("瓜岛插件, ManagerBase.shutdown()...");
            ManagerBase.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            Log.melon.error("瓜岛插件已卸载出错!");
            //异常处理
        }
        Log.melon.info("瓜岛插件已卸载");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //监听命令 命令管理器分发命令
        return CommandManager.inst().onCommand(sender, command, label, args);
    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        //空岛生成器
        return new AirChunkGenerator();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        MenuManager.inst().onInventoryClick(event);
    }

}
