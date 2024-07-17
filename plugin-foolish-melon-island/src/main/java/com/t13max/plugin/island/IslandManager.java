package com.t13max.plugin.island;

import com.t13max.common.consts.Const;
import com.t13max.common.manager.ManagerBase;
import com.t13max.common.pos.Position;
import com.t13max.data.PersistManager;
import com.t13max.data.model.IslandData;
import com.t13max.data.model.PluginData;
import com.t13max.plugin.memory.MemoryManager;
import com.t13max.util.Log;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * 岛屿管理器
 *
 * @author: t13max
 * @since: 17:09 2024/7/17
 */
public class IslandManager extends ManagerBase {

    public static IslandManager inst() {
        return inst(IslandManager.class);
    }

    @Override
    public List<Class<? extends ManagerBase>> getDependents() {
        return Collections.singletonList(PersistManager.class);
    }

    public IslandData createIslandData(Player player) {

        IslandData islandData = new IslandData();

        PluginData pluginData = MemoryManager.inst().getPluginData();

        int index = 0;
        //有点重 先暂时这样吧
        synchronized (pluginData) {
            index = pluginData.getIndex() + 1;
            pluginData.setIndex(index);
        }

        if (index == 0) {
            return null;
        }

        //计算出空岛位置
        Position position = calcPosition(index);
        if (position == null) {
            return null;
        }

        //初始化
        islandData.setId(index);
        islandData.setPos(position.calcPos());
        islandData.setUuid(player.getUniqueId().toString());
        //islandData.getAuthorityMap().put(player.getUniqueId(), );

        //保存
        PersistManager.inst().saveIslandData(islandData);
        //放入内存
        MemoryManager.inst().putIslandData(islandData);

        //返回
        return islandData;
    }

    /**
     * 计算岛屿位置
     *
     * @Author t13max
     * @Date 17:33 2024/7/17
     */
    private Position calcPosition(int index) {
        if (index > Const.ISLAND_NUM * Const.ISLAND_NUM) {
            Log.melon.error("岛屿数量太多了!, index={}", index);
            //超过十万了 该走新逻辑了
            return null;
        }
        //index从一万开始递增 则x就是从((10000/10000)+10)*8192开始 原点8192格内为主城
        int x = index / 1000 + 10;
        //z则从0开始
        int z = index % 1000;
        //我的世界坐标用int存 最大为21亿
        return new Position(x * Const.ISLAND_SIDE, z * Const.ISLAND_SIDE);
    }


}
