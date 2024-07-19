package com.t13max.nms.entity;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;

/**
 * 无敌的羊羊!
 *
 * @author: t13max
 * @since: 16:44 2024/7/18
 */
public class BigSheepEntity extends Sheep {

    public BigSheepEntity(World world) {
        super(EntityType.SHEEP, ((CraftWorld) world).getHandle());
        setPos(0,64,0);
    }


    @Override
    protected void registerGoals() {

    }


}
