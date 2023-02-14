package net.fabricmc.infernal_war.client;

import net.fabricmc.infernal_war.common.item.InfernalWarCrossbow;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class InfernalWarPredicateProvider {
    public static void registerModModels(){
        registerCrossbow(RegisterItems.PIG_IRON_CROSSBOW);
    }

    private static void registerCrossbow(InfernalWarCrossbow crossbow){
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
