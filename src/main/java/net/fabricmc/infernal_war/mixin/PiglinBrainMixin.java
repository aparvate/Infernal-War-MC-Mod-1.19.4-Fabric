package net.fabricmc.infernal_war.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.fabricmc.infernal_war.common.material.InfernalWarArmorMaterials;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {
    
    private static boolean isHoldingCrossbow(LivingEntity piglin) {
        return piglin.isHolding(Items.CROSSBOW) || piglin.isHolding(RegisterItems.PIG_IRON_CROSSBOW);
    }

    @Inject(method = "wearsGoldArmor", at = @At("TAIL"), cancellable = true)
    private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> info) {
        Iterable<ItemStack> iterable = entity.getArmorItems();
        for (ItemStack itemStack : iterable) {
            Item item = itemStack.getItem();
            if (!(item instanceof ArmorItem) || ((ArmorItem) item).getMaterial() != InfernalWarArmorMaterials.PIG_IRON) continue;
            info.setReturnValue(true);
        }
    }
}
