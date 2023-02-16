package net.fabricmc.infernal_war.client;

import net.fabricmc.infernal_war.common.item.InfernalWarBow;
import net.fabricmc.infernal_war.common.item.InfernalWarCrossbow;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class InfernalWarPredicateProvider {
    public static void registerModModels(){
        registerInfernalWarCrossbow(RegisterItems.PIG_IRON_CROSSBOW);
        registerInfernalWarBow(RegisterItems.PIG_IRON_BOW);
    }

    private static void registerInfernalWarBow(InfernalWarBow bow){
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"), (ItemStack, ClientWorld, LivingEntity, seed) -> {
            if (LivingEntity == null) {
                return 0.0F;
            }
            return LivingEntity.getActiveItem() != ItemStack ? 0.0F : (ItemStack.getMaxUseTime() - LivingEntity.getItemUseTimeLeft()) / 20.0F;
        });
         
        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"), (ItemStack, ClientWorld, LivingEntity, seed) -> {
            if (LivingEntity == null) {
                return 0.0F;
            }
            return LivingEntity.isUsingItem() && LivingEntity.getActiveItem() == ItemStack ? 1.0F : 0.0F;
        });
    }

    private static void registerInfernalWarCrossbow(InfernalWarCrossbow crossbow){
        ModelPredicateProviderRegistry.register(crossbow, new Identifier("pull"), (ItemStack, ClientWorld, LivingEntity, seed) -> {
            if (LivingEntity == null) {
                return 0.0f;
            }
            if (InfernalWarCrossbow.isCharged(ItemStack)) {
                return 0.0f;
            }
            return (float)(ItemStack.getMaxUseTime() - LivingEntity.getItemUseTimeLeft()) / (float)crossbow.getPullTime(ItemStack);
        });
        ModelPredicateProviderRegistry.register(crossbow, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !InfernalWarCrossbow.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(crossbow, new Identifier("charged"), (stack, world, entity, seed) -> entity != null && InfernalWarCrossbow.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(RegisterItems.PIG_IRON_CROSSBOW, new Identifier("firework"), (stack, world, entity, seed) -> entity != null && InfernalWarCrossbow.isCharged(stack) && InfernalWarCrossbow.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0f : 0.0f);
    }
}
