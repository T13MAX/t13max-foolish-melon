package com.t13max.plugin.command;

import com.t13max.nms.entity.BigSheepEntity;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * 测试用命令
 *
 * @Author: t13max
 * @Since: 21:41 2024/7/18
 */
public class TestCmdExecutor implements IMelonCmdExecutor {

    @Override
    public String getLabel() {
        return "xxx";
    }

    @Override
    public boolean onCommand(Player player, Command command, String[] args) {
        if (!player.isOp()) return false;

        World world = player.getWorld();
        new BigSheepEntity(world);
        return true;
    }
}
