package com.t13max.plugin.memory;

import com.t13max.common.manager.ManagerBase;
import com.t13max.common.plugin.PluginContext;
import com.t13max.data.PersistManager;
import com.t13max.data.model.IslandData;
import com.t13max.data.model.PlayerData;
import com.t13max.data.model.PluginData;
import com.t13max.plugin.MelonIsland;
import com.t13max.plugin.persist.type.PlayerType;
import com.t13max.common.util.Log;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存管理类
 * 包含玩家数据 以及在线玩家的空岛数据 定时入库 离线保存
 * 目前可能存在线程安全问题 可能数据不一致
 *
 * @author: t13max
 * @since: 13:18 2024/7/17
 */
public class MemoryManager extends ManagerBase {

    //插件持久化数据数据
    @Getter
    private PluginData pluginData;

    private final Map<Integer, IslandData> islandDataMap = new ConcurrentHashMap<>();

    public static MemoryManager inst() {
        return inst(MemoryManager.class);
    }

    @Override
    public List<Class<? extends ManagerBase>> getDependents() {
        return Collections.singletonList(PersistManager.class);
    }

    @Override
    protected void onShutdown() {
        Log.melon.info("内存管理器开始onShutdown!");

        Log.melon.info("保存插件数据!");
        PersistManager.inst().savePluginData(this.pluginData);

        Log.melon.info("保存岛屿数据!");
        for (IslandData islandData : this.islandDataMap.values()) {
            PersistManager.inst().saveIslandData(islandData);
        }
    }

    @Override
    public void init() {
        //插件数据id
        int id = PluginContext.CONFIG.getId();

        PluginData pluginData = PersistManager.inst().getPluginData(id);
        if (pluginData == null) {
            pluginData = new PluginData(id);
            PersistManager.inst().savePluginData(pluginData);
        }
        //缓存
        this.pluginData = pluginData;
    }

    /**
     * 获取空岛数据 优先走内存 没有则自动加载
     *
     * @Author t13max
     * @Date 16:38 2024/7/17
     */
    public IslandData getOrLoadIslandData(int id) {
        IslandData islandData = this.getIslandData(id);
        if (islandData == null) {
            islandData = this.loadIslandData(id);
        }
        return islandData;
    }

    /**
     * 获取空岛数据
     *
     * @Author t13max
     * @Date 16:36 2024/7/17
     */
    public IslandData getIslandData(int id) {
        return this.islandDataMap.get(id);
    }

    /**
     * 从数据库加载空岛数据
     *
     * @Author t13max
     * @Date 16:37 2024/7/17
     */
    public IslandData loadIslandData(int id) {
        IslandData islandData = PersistManager.inst().getIslandData(id);
        if (islandData == null) {
            return null;
        }
        this.putIslandData(islandData);
        return islandData;
    }

    /**
     * 空岛数据存入内存
     *
     * @Author t13max
     * @Date 16:36 2024/7/17
     */
    public void putIslandData(IslandData islandData) {
        this.islandDataMap.put(islandData.getId(), islandData);
    }

    /**
     * 获取玩家数据
     *
     * @Author t13max
     * @Date 16:47 2024/7/17
     */
    public <T> T getPersistentData(Player player, PersistentDataType<byte[], T> type) {
        return player.getPersistentDataContainer().get(new NamespacedKey(MelonIsland.PLUGIN, type.getClass().getSimpleName()), type);
    }

    /**
     * 设置玩家数据
     *
     * @Author t13max
     * @Date 16:47 2024/7/17
     */
    public <T> void setPersistentData(Player player, PersistentDataType<byte[], T> type, T data) {
        player.getPersistentDataContainer().set(new NamespacedKey(MelonIsland.PLUGIN, type.getClass().getSimpleName()), type, data);
    }

    /**
     * 直接获取PlayerData对象
     *
     * @Author t13max
     * @Date 17:07 2024/7/17
     */
    public PlayerData getPlayerData(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(MelonIsland.PLUGIN, PlayerType.class.getSimpleName()), PlayerType.INS);
    }

}
