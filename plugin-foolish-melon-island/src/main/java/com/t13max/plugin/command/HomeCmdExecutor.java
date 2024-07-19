package com.t13max.plugin.command;

import com.t13max.common.consts.Const;
import com.t13max.common.pos.Position;
import com.t13max.data.model.IslandData;
import com.t13max.data.model.PlayerData;
import com.t13max.plugin.island.IslandManager;
import com.t13max.plugin.memory.MemoryManager;
import com.t13max.util.Log;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * home命令实现
 * tp回自己的主岛 如果没有岛则创建
 *
 * @author: t13max
 * @since: 17:01 2024/7/17
 */
public class HomeCmdExecutor implements IMelonCmdExecutor {

    @Override
    public String getLabel() {
        return "home";
    }

    @Override
    public boolean onCommand(Player player, Command command, String[] args) {
        PlayerData playerData = MemoryManager.inst().getPlayerData(player);
        List<Integer> islandList = playerData.getIslandList();
        IslandData islandData;
        if (islandList.isEmpty()) {
            //创建一个空岛
            islandData = IslandManager.inst().createIslandData(player);
            if (islandData == null) {
                return false;
            }
            islandList.add(islandData.getId());
        } else {
            Integer islandId = islandList.get(0);
            islandData = MemoryManager.inst().getOrLoadIslandData(islandId);
            if (islandData == null) {
                Log.melon.error("岛屿加载失败, uuid={}", player.getUniqueId());
                return false;
            }
        }

        //tp前往岛屿
        Position position = new Position(islandData.getPos());
        //待优化
        World world = player.getWorld();
        player.teleport(new Location(world, position.getX(), Const.SPAWN_Y, position.getZ()));
        return true;
    }
}
