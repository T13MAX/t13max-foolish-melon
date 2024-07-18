package com.t13max.nms;

import com.t13max.common.manager.ManagerBase;
import com.t13max.nms.entity.BigSheepEntity;
import net.minecraft.world.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;

import java.util.HashMap;
import java.util.Map;

/**
 * nms管理器
 *
 * @author: t13max
 * @since: 17:51 2024/7/18
 */
public class NmsManager extends ManagerBase {

    private final Map<String, Entity> entityMap = new HashMap<>();

    @Override

    protected void onShutdown() {
        super.onShutdown();
    }

    @Override
    public void init() {
        World world = Bukkit.getWorld("overload");
        BigSheepEntity bigSheepEntity = new BigSheepEntity(((CraftWorld) world).getHandle());

        super.init();
    }
}
