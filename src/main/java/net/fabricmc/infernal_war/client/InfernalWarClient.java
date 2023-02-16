package net.fabricmc.infernal_war.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class InfernalWarClient implements ClientModInitializer{
    
    @Override
    public void onInitializeClient() {
        InfernalWarPredicateProvider.registerModModels();
    }
    
}
