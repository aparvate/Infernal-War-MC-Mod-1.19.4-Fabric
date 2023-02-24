package net.fabricmc.infernal_war.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;

@Mixin(PiglinBruteEntity.class)
public abstract class PiglinBruteEntityMixin extends AbstractPiglinEntity {

    public PiglinBruteEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
        //TODO Auto-generated constructor stub
    }

    @Inject(method = "createPiglinBruteAttributes", at = @At("TAIL"))
    private static DefaultAttributeContainer.Builder createPiglinBruteAttributes(CallbackInfoReturnable info) {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0);
    }

    private void equipAtChance(EquipmentSlot slot, ItemStack stack, Random random) {
        if (random.nextFloat() < 0.1f) {
            ((PiglinBruteEntity)(Object)this).equipStack(slot, stack);
        }
    }

    @Inject(method = "initEquipment", at = @At("TAIL"))
    protected void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo info){
        double randVal = (double)random.nextFloat();
        if (randVal <= 0.15){
            ((PiglinBruteEntity)(Object)this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(RegisterItems.PIG_IRON_AXE));
        }
        else if (randVal <= 0.3){
            ((PiglinBruteEntity)(Object)this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(RegisterItems.PIG_IRON_SCIMITAR));
        }
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    public void initializeArmor(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt, CallbackInfoReturnable info) {
        this.initArmor(world.getRandom(), difficulty);
    }

    protected void initArmor(Random random, LocalDifficulty localDifficulty) {
        if ((double)random.nextFloat() <= 0.5){
            this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(RegisterItems.PIG_IRON_HELMET), random);
        }
        else{
            this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET), random);
        }
        if ((double)random.nextFloat() <= 0.5){
            this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(RegisterItems.PIG_IRON_CHESTPLATE), random);
        }
        else{
            this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE), random);
        }
        if ((double)random.nextFloat() <= 0.5){
            this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(RegisterItems.PIG_IRON_LEGGINGS), random);
        }
        else{
            this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS), random);
        }
        if ((double)random.nextFloat() <= 0.5){
            this.equipAtChance(EquipmentSlot.FEET, new ItemStack(RegisterItems.PIG_IRON_BOOTS), random);
        }
        else{
            this.equipAtChance(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS), random);
        }
    }
    
}
