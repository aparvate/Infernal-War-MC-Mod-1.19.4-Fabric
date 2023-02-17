package net.fabricmc.infernal_war.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin{

    @Shadow
    private void equipAtChance(EquipmentSlot slot, ItemStack stack, Random random){}

    @Inject(method = "initEquipment", at = @At("HEAD"))
    protected void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo info){
        if (random.nextInt(1) == 0){
            if (((PiglinEntity)(Object)this).isAdult()) {
                this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(RegisterItems.PIG_IRON_HELMET), random);
                this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(RegisterItems.PIG_IRON_CHESTPLATE), random);
                this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(RegisterItems.PIG_IRON_LEGGINGS), random);
                this.equipAtChance(EquipmentSlot.FEET, new ItemStack(RegisterItems.PIG_IRON_BOOTS), random);
            }
        }
    }

    /*java.util.Random random = new java.util.Random();

    @Shadow
    @Inject(method = "makeInitialWeapon", at = @At("HEAD"))
    private ItemStack makeInitialWeapon(CallbackInfo info){
        if ((double)this.random.nextFloat() < 0.5){
            if ((double)this.random.nextFloat() < 0.5){
                return new ItemStack(RegisterItems.PIG_IRON_SCIMITAR);
            }
            else{
                return new ItemStack(RegisterItems.PIG_IRON_AXE);
            }
        }
        else{
            if ((double)this.random.nextFloat() < 0.5) {
                return new ItemStack(Items.CROSSBOW);
            }
            return new ItemStack(Items.GOLDEN_SWORD);
        }
    }*/
}
