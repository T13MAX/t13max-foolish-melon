package com.t13max.nms.entity;


import com.t13max.nms.consts.MelonTextColor;
import io.papermc.paper.adventure.AdventureComponent;
import net.kyori.adventure.text.Component;
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
        setPos(0, 64, 0);
        setCustomNameVisible(true);
        setCustomName(new AdventureComponent(Component.text("汪小奇", MelonTextColor.BLUE)));
    }


}
