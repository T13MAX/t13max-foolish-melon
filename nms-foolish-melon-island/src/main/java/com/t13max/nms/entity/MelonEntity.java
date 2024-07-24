package com.t13max.nms.entity;

import com.t13max.util.RandomUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author: t13max
 * @Since: 13:51 2024/7/20
 */
@Getter
@Setter
public class MelonEntity extends LivingEntity {

    private Status status = Status.IDLE;

    private LivingEntity target;

    private World world;

    private boolean init;

    private final List<ItemStack> armorItems;
    private final List<ItemStack> handItems;

    protected MelonEntity(World world) {
        super(EntityType.PLAYER, ((CraftWorld) world).getHandle());
        this.world = world;
        this.armorItems = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.armorItems.add(ItemStack.EMPTY);
        }
        this.handItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            this.handItems.add(ItemStack.EMPTY);
        }
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return armorItems;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot.getType()) {
            case HAND -> this.handItems.get(equipmentSlot.getIndex());
            case ARMOR -> this.armorItems.get(equipmentSlot.getIndex());
        };
    }

    @Override
    public void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack) {
        this.setItemSlot(equipmentSlot, itemStack, false);
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack, boolean silent) {
        this.verifyEquippedItem(stack);
        switch (slot.getType()) {
            case HAND:
                this.onEquipItem(slot, this.handItems.set(slot.getIndex(), stack), stack, silent);
                break;
            case ARMOR:
                this.onEquipItem(slot, this.armorItems.set(slot.getIndex(), stack), stack, silent);
        }

    }

    @Override
    public HumanoidArm getMainArm() {
        return this.isLeftHanded() ? HumanoidArm.LEFT : HumanoidArm.RIGHT;
    }

    public boolean isLeftHanded() {
        return true;
        //todo t13max return ((Byte)this.entityData.get(DATA_MOB_FLAGS_ID) & 2) != 0;
    }

    @Override
    public void tick() {
        super.tick();

        if (target == null) return;

        switch (status) {
            case IDLE -> {
                choiceStatus();
            }
            case ARROW -> {

                if (init) {
                    ItemStack itemStack = new ItemStack(Items.BOW);
                    this.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
                    startUsingItem(InteractionHand.MAIN_HAND);
                }

                if (this.isUsingItem()) {
                    int i = this.getUseItemRemainingTicks();
                    if (i >= 20) {
                        this.stopUsingItem();
                        this.shootAt(target, BowItem.getPowerForTime(i));
                    }
                } else {

                }
            }
            case COMMON -> {
            }
            case APPLE -> {
            }
        }

    }

    public void shootAt(LivingEntity livingEntity, float damageModifier) {
        ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW));
        ItemStack itemStack2 = this.getProjectileType(itemStack);
        AbstractArrow arrowEntity = this.getMobArrow(itemStack2, damageModifier);
        double d = target.getX() - this.getX();
        double e = target.getY(0.3333333333333333) - arrowEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        arrowEntity.shoot(d, e + g * 0.2F, f, 1.6F, (float) (14 - this.getWorld().getDifficulty().getValue() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        ((CraftWorld) this.getWorld()).addEntity(arrowEntity, CreatureSpawnEvent.SpawnReason.DEFAULT);
    }

    protected AbstractArrow getMobArrow(ItemStack arrow, float damageModifier) {
        return ProjectileUtil.getMobArrow(this, arrow, damageModifier);
    }

    public ItemStack getProjectileType(ItemStack stack) {
        if (stack.getItem() instanceof ProjectileWeaponItem) {
            Predicate<ItemStack> predicate = ((ProjectileWeaponItem) stack.getItem()).getSupportedHeldProjectiles();
            ItemStack itemStack = ProjectileWeaponItem.getHeldProjectile(this, predicate);
            return itemStack.isEmpty() ? new ItemStack(Items.ARROW) : itemStack;
        } else {
            return ItemStack.EMPTY;
        }
    }


    public void spawn() {
        ((CraftWorld) world).addEntity(this, CreatureSpawnEvent.SpawnReason.DEFAULT);
    }

    private void choiceStatus() {
        Status status = RandomUtil.random(Status.STATUS_LIST);
        init = true;
        this.status = status;
    }

    static enum Status {
        IDLE,
        ARROW,
        COMMON,
        APPLE,

        ;

        private static final List<Status> STATUS_LIST;

        static {
            STATUS_LIST = new ArrayList<>();
            for (Status status : Status.values()) {
                if (status == Status.IDLE) {
                    continue;
                }
                STATUS_LIST.add(status);
            }
        }
    }
}
