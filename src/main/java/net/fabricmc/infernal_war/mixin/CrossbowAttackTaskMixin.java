package net.fabricmc.infernal_war.mixin;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.infernal_war.common.item.PigIron;
import net.fabricmc.infernal_war.common.item.PigIronCrossbow;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.CrossbowAttackTask;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.goal.CrossbowAttackGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;

@Mixin(CrossbowAttackTask.class)
public abstract class CrossbowAttackTaskMixin <E extends MobEntity, T extends LivingEntity>
extends MultiTickTask<E>{

    @Shadow
    private CrossbowAttackTask.CrossbowState state;

    @Shadow
    private int chargingCooldown;

    public CrossbowAttackTaskMixin(ImmutableMap<MemoryModuleType<?>, MemoryModuleState> requiredMemoryState) {
        super(requiredMemoryState);
        //TODO Auto-generated constructor stub
    }

    @Inject (method = "shouldRun", at = @At("HEAD"), cancellable = true)
    private void shouldRunWhileHoldingPigIronCrossbow(ServerWorld serverWorld, E mobEntity, CallbackInfoReturnable<Boolean> info) {
        LivingEntity livingEntity = CrossbowAttackTaskInvokerMixin.invokeGetAttackTarget(mobEntity);
        if (((LivingEntity)mobEntity).isHolding(RegisterItems.PIG_IRON_CROSSBOW) && LookTargetUtil.isVisibleInMemory(mobEntity, livingEntity) && LookTargetUtil.isTargetWithinAttackRange(mobEntity, livingEntity, 0)){
            info.setReturnValue(true);
        }
    }

    @Inject (method = "finishRunning", at = @At("HEAD"), cancellable = true)
    private void finishRunningPigIronCrossbow(ServerWorld serverWorld, E mobEntity, long l, CallbackInfo info) {
        if (((LivingEntity)mobEntity).isUsingItem()) {
            ((LivingEntity)mobEntity).clearActiveItem();
        }
        if (((LivingEntity)mobEntity).isHolding(RegisterItems.PIG_IRON_CROSSBOW)) {
            ((CrossbowUser)mobEntity).setCharging(false);
            PigIronCrossbow.setCharged(((LivingEntity)mobEntity).getActiveItem(), false);
        }
    }

    @Inject (method = "tickState", at = @At("HEAD"), cancellable = true)
    private void tickStatePigIronCrossbow(E entity, LivingEntity target, CallbackInfo info) {
        if (entity.isHolding(RegisterItems.PIG_IRON_CROSSBOW)){
            if (this.state == CrossbowAttackTask.CrossbowState.UNCHARGED) {
                ((LivingEntity)entity).setCurrentHand(ProjectileUtil.getHandPossiblyHolding(entity, RegisterItems.PIG_IRON_CROSSBOW));
                this.state = CrossbowAttackTask.CrossbowState.CHARGING;
                ((CrossbowUser)entity).setCharging(true);
            } else if (this.state == CrossbowAttackTask.CrossbowState.CHARGING) {
                ItemStack itemStack;
                int i;
                if (!((LivingEntity)entity).isUsingItem()) {
                    this.state = CrossbowAttackTask.CrossbowState.UNCHARGED;
                }
                if ((i = ((LivingEntity)entity).getItemUseTime()) >= PigIronCrossbow.getPullTime(itemStack = ((LivingEntity)entity).getActiveItem())) {
                    ((LivingEntity)entity).stopUsingItem();
                    this.state = CrossbowAttackTask.CrossbowState.CHARGED;
                    this.chargingCooldown = 40 + ((LivingEntity)entity).getRandom().nextInt(20);
                    ((CrossbowUser)entity).setCharging(false);
                }
            } else if (this.state == CrossbowAttackTask.CrossbowState.CHARGED) {
                --this.chargingCooldown;
                if (this.chargingCooldown == 0) {
                    this.state = CrossbowAttackTask.CrossbowState.READY_TO_ATTACK;
                }
            } else if (this.state == CrossbowAttackTask.CrossbowState.READY_TO_ATTACK) {
                ((RangedAttackMob)entity).attack(target, 1.0f);
                ItemStack itemStack2 = ((LivingEntity)entity).getStackInHand(ProjectileUtil.getHandPossiblyHolding(entity, RegisterItems.PIG_IRON_CROSSBOW));
                PigIronCrossbow.setCharged(itemStack2, false);
                this.state = CrossbowAttackTask.CrossbowState.UNCHARGED;
            }
            info.cancel();
        }
    }
}