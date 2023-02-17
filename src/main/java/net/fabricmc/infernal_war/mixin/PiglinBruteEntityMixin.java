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
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;

@Mixin(PiglinBruteEntity.class)
public class PiglinBruteEntityMixin {

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
    
}
