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
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.util.math.random.*;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin{

    @Shadow
    private void equipAtChance(EquipmentSlot slot, ItemStack stack, Random random){}

    @Inject(method = "initEquipment", at = @At("HEAD"))
    protected void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo info){
        if (random.nextInt(2) == 0){
            if (((PiglinEntity)(Object)this).isAdult()) {
                this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(RegisterItems.PIG_IRON_HELMET), random);
                this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(RegisterItems.PIG_IRON_CHESTPLATE), random);
                this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(RegisterItems.PIG_IRON_LEGGINGS), random);
                this.equipAtChance(EquipmentSlot.FEET, new ItemStack(RegisterItems.PIG_IRON_BOOTS), random);
            }
        }
    }
}
