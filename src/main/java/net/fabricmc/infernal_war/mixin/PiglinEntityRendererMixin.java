package net.fabricmc.infernal_war.mixin;

import java.io.File;
import java.util.Map;

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
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import com.google.common.collect.ImmutableMap;

@Mixin(PiglinEntityRenderer.class)
public class PiglinEntityRendererMixin {
    
    public Identifier getTexture(MobEntity mobEntity) {
        java.util.Random rand = new java.util.Random(0);
        Identifier piglinBruteID = new Identifier("textures/entity/piglin/piglin_brute.png");
        Identifier zombiPigID = new Identifier("textures/entity/piglin/zombified_piglin.png");
        if (mobEntity.getType().equals(EntityType.PIGLIN)){
            String piglinScav = "textures/entity/piglin/piglin_scavenger/" + rand.nextInt(2) + ".png";
            Identifier piglinScavID = new Identifier("infernalwar",piglinScav);
            return piglinScavID;
        }
        else if (mobEntity.getType().equals(EntityType.PIGLIN_BRUTE)){
            return piglinBruteID;
        }
        else if (mobEntity.getType().equals(EntityType.ZOMBIFIED_PIGLIN)){
            return zombiPigID;
        }
        return null;
    }
}
