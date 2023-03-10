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

import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents.Register;
import net.fabricmc.infernal_war.common.InfernalWarTrackedData;
import net.fabricmc.infernal_war.common.access.PiglinTypeVariantInterface;
import net.fabricmc.infernal_war.common.access.PiglinVariantInterface;
import net.fabricmc.infernal_war.common.access.PiglinData;
import net.fabricmc.infernal_war.common.entity.PiglinHeadFeature;
import net.fabricmc.infernal_war.common.entity.PiglinType;
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
import net.minecraft.entity.mob.PiglinActivity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.RabbitEntity.RabbitType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.random.*;
import net.minecraft.util.Util;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity implements VariantHolder<PiglinType>{
    private static final TrackedData<Integer> PIGLIN_SKIN_COLOR = DataTracker.registerData(PiglinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> PIGLIN_TYPE = DataTracker.registerData(PiglinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> PIGLIN_HEAD_FEATURE = DataTracker.registerData(PiglinEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "canUseRangedWeapon", at = @At("TAIL"))
    public boolean canUsePigIronCrossbow(RangedWeaponItem weapon, CallbackInfoReturnable info) {
        return weapon == RegisterItems.PIG_IRON_CROSSBOW || weapon == Items.CROSSBOW;
    }

    @Inject(method = "getActivity", at = @At("TAIL"), cancellable = true)
    public PiglinActivity getPigIronCrossbowActivity(CallbackInfoReturnable info) {
        if (this.isAttacking() && this.isHolding(RegisterItems.PIG_IRON_CROSSBOW)) {
            return PiglinActivity.CROSSBOW_HOLD;
        }
        return PiglinActivity.DEFAULT;
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initVarTracker(CallbackInfo info){
        ((PiglinEntity)(Object)this).getDataTracker().startTracking(PIGLIN_SKIN_COLOR, 0);
        ((PiglinEntity)(Object)this).getDataTracker().startTracking(PIGLIN_TYPE, 0);
        ((PiglinEntity)(Object)this).getDataTracker().startTracking(PIGLIN_HEAD_FEATURE, 0);
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    private void initializeVariants(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt, CallbackInfoReturnable cir){
        PiglinType piglinType;
        int randomSkinColor;
        Random random = world.getRandom();
        if (entityData instanceof PiglinData) {
            piglinType = ((PiglinData)entityData).type;
            randomSkinColor = ((PiglinData)entityData).type.getSkinColor();
        } else {
            piglinType = Util.getRandom(PiglinType.values(), random);
            randomSkinColor = piglinType.getSkinColor();
            entityData = new PiglinData(piglinType);
        }
        this.setSkinColor(randomSkinColor);
        this.setVariant(piglinType);
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

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeVariantToNbt(NbtCompound nbt, CallbackInfo info){
        nbt.putInt("piglinSkinColor", this.getVariant().getSkinColor());
        nbt.putInt("piglinSkin", this.getVariant().getId());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readVariantFromNbt(NbtCompound nbt, CallbackInfo info){
        this.setVariant(PiglinType.byId(nbt.getInt("piglinSkin"),nbt.getInt("piglinSkinColor")));
    }

    LocalRandom random = new LocalRandom(RandomSeed.getSeed());

    private ItemStack makeInitialWeapon(){
        double randVal = (double)this.random.nextFloat();
        if (randVal <= 0.6) {
            randVal = (double)this.random.nextFloat();
            if (randVal <= 0.6){
                return new ItemStack(Items.CROSSBOW);}
            else{
                return new ItemStack(RegisterItems.PIG_IRON_CROSSBOW);
            }
        }
        else if (randVal <= 0.7) {
            return new ItemStack(RegisterItems.PIG_IRON_AXE);
        }
        else if (randVal <= 0.8) {
            return new ItemStack(RegisterItems.PIG_IRON_SCIMITAR);
        }
        return new ItemStack(Items.GOLDEN_SWORD);
    }

    private void setPiglinType(int variant) {
        this.dataTracker.set(PIGLIN_TYPE, variant);
    }

    private int getPiglinType() {
        return this.dataTracker.get(PIGLIN_TYPE);
    }

    private void setPiglinSkinColor(int variant) {
        this.dataTracker.set(PIGLIN_SKIN_COLOR, variant);
    }

    private int getPiglinSkinColor() {
        return this.dataTracker.get(PIGLIN_SKIN_COLOR);
    }

    private void setPiglinHeadFeature(int variant) {
        this.dataTracker.set(PIGLIN_HEAD_FEATURE, variant);
    }

    private int getPiglinHeadFeature() {
        return this.dataTracker.get(PIGLIN_HEAD_FEATURE);
    }

    public PiglinType getVariant() {
        return PiglinType.byId(this.getPiglinType(), this.getPiglinSkinColor());
    }

    public PiglinHeadFeature getHeadFeatureVariant() {
        return PiglinHeadFeature.byIndex(this.getPiglinHeadFeature());
    }

    public void setVariant(PiglinType piglinType) {
        this.setPiglinType(piglinType.getId()| this.getPiglinType());
        this.setSkinColor(piglinType.getSkinColor()| this.getPiglinSkinColor());
    }

    public void setSkinColor(int piglinSkinColor) {
        this.setPiglinSkinColor(piglinSkinColor);
    }
}
