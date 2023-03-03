package net.fabricmc.infernal_war.mixin;

import java.io.File;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.infernal_war.common.access.PiglinVariantInterface;
import net.fabricmc.infernal_war.common.entity.PiglinType;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.entity.VariantHolder;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.model.PiglinEntityModel;

import com.google.common.collect.ImmutableMap;

@Mixin(PiglinEntityRenderer.class)
public abstract class PiglinEntityRendererMixin{

    public Identifier getTexture(MobEntity mobEntity) {
        Identifier zombiePiglin = new Identifier("textures/entity/piglin/zombified_piglin.png");
        Identifier piglinBrute = new Identifier("textures/entity/piglin/piglin_brute.png");
        Identifier piglin = new Identifier("infernalwar", "textures/entity/piglin/piglin_scavenger/0.png");
        String pigScavRandString = "textures/entity/piglin/piglin_scavenger/";
        if (mobEntity.getType().equals(EntityType.PIGLIN_BRUTE)) {
            return piglinBrute;
        }
        else if (mobEntity.getType().equals(EntityType.ZOMBIFIED_PIGLIN)){
            return zombiePiglin;
        }
        else if (mobEntity.getType().equals(EntityType.PIGLIN)){
            PiglinType piglinType = (PiglinType)((VariantHolder)(Object) mobEntity).getVariant();
            return switch (piglinType.getId()) {
                default -> throw new IncompatibleClassChangeError();
                case 0 -> piglin;
                case 1 -> new Identifier("infernalwar", pigScavRandString + "1.png");
                case 2 -> new Identifier("infernalwar", pigScavRandString + "2.png");
            };
        }
        return piglin;
    }
}
