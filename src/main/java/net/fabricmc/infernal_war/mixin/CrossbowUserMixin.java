package net.fabricmc.infernal_war.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.infernal_war.common.item.PigIronCrossbow;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;

@Mixin(CrossbowUser.class)
public abstract interface CrossbowUserMixin extends RangedAttackMob {

    @Overwrite
    default public void shoot(LivingEntity entity, float speed) {
        Hand hand;
        ItemStack itemStack;
        if (entity.isHolding(Items.CROSSBOW)) {
            hand = ProjectileUtil.getHandPossiblyHolding(entity, Items.CROSSBOW);
            itemStack = entity.getStackInHand(hand);
            CrossbowItem.shootAll(entity.world, entity, hand, itemStack, speed, 14 - entity.world.getDifficulty().getId() * 4);
        } 
        if (entity.isHolding(RegisterItems.PIG_IRON_CROSSBOW)) {
            hand = ProjectileUtil.getHandPossiblyHolding(entity, RegisterItems.PIG_IRON_CROSSBOW);
            itemStack = entity.getStackInHand(hand);
            PigIronCrossbow.shootAll(entity.world, entity, hand, itemStack, speed + 2F, 14 - entity.world.getDifficulty().getId() * 4);
        }
        ((CrossbowUser)(Object)this).postShoot();
    }
}
