package com.t13max.nms.entity;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;

/**
 * 无敌的羊羊!
 *
 * @author: t13max
 * @since: 16:44 2024/7/18
 */
public class BigSheepEntity extends Sheep {

    public BigSheepEntity(Level world) {
        super(EntityType.SHEEP, world);
    }


    @Override
    public float getHeadEatPositionScale(float delta) {


        return super.getHeadEatPositionScale(delta);
    }
}
