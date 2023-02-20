package net.fabricmc.infernal_war.mixin;

import java.util.Arrays;
import java.util.function.IntFunction;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.graph.ElementOrder.Type;

import net.fabricmc.infernal_war.common.InfernalWarTrackedData;
import net.fabricmc.infernal_war.common.access.PiglinTypeVariantInterface;
import net.fabricmc.infernal_war.common.access.PiglinVariantInterface;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.RabbitEntity.RabbitType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.random.*;
import net.minecraft.util.Util;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity{
    private static final TrackedData<Integer> PIGLIN_TYPE = DataTracker.registerData(PiglinEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    private void equipAtChance(EquipmentSlot slot, ItemStack stack, Random random){}

    @Inject(method = "initEquipment", at = @At("HEAD"))
    protected void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo info){
        if ((double)random.nextFloat() <= 0.25){
            if (((PiglinEntity)(Object)this).isAdult()) {
                this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(RegisterItems.PIG_IRON_HELMET), random);
                this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(RegisterItems.PIG_IRON_CHESTPLATE), random);
                this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(RegisterItems.PIG_IRON_LEGGINGS), random);
                this.equipAtChance(EquipmentSlot.FEET, new ItemStack(RegisterItems.PIG_IRON_BOOTS), random);
            }
        }
    }

    LocalRandom random = new LocalRandom(RandomSeed.getSeed());

    private ItemStack makeInitialWeapon(){
        double randVal = (double)this.random.nextFloat();
        if (randVal <= 0.6) {
            return new ItemStack(Items.CROSSBOW);
        }
        else if (randVal <= 0.7) {
            return new ItemStack(RegisterItems.PIG_IRON_AXE);
        }
        else if (randVal <= 0.8) {
            return new ItemStack(RegisterItems.PIG_IRON_SCIMITAR);
        }
        return new ItemStack(Items.GOLDEN_SWORD);
    }
}
