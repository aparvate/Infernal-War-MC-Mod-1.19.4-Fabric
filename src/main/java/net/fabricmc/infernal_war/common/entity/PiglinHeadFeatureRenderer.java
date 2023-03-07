package net.fabricmc.infernal_war.common.entity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.infernal_war.mixin.PiglinEntityMixin;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.HorseMarking;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

@Environment(value=EnvType.CLIENT)
public class PiglinHeadFeatureRenderer
extends FeatureRenderer<PiglinEntity, PiglinEntityModel<PiglinEntity>> {
    private static final Map<PiglinHeadFeature, Identifier> TEXTURES = Util.make(Maps.newEnumMap(PiglinHeadFeature.class), textures -> {
        textures.put(PiglinHeadFeature.NONE, null);
        textures.put(PiglinHeadFeature.RIGHT_EYEPATCH, new Identifier("infernalwar", "textures/entity/piglin/head_features/1.png"));
    });

    public PiglinHeadFeatureRenderer(FeatureRendererContext<PiglinEntity, PiglinEntityModel<PiglinEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack var1, VertexConsumerProvider var2, int var3, PiglinEntity var4, float var5,
            float var6, float var7, float var8, float var9, float var10) {
                Identifier identifier = TEXTURES.get(((PiglinEntityMixin)(Object)var4).getHeadFeatureVariant());
                if (identifier == null || var4.isInvisible()) {
                    return;
                }
                VertexConsumer vertexConsumer = var2.getBuffer(RenderLayer.getEntityTranslucent(identifier));
                ((PiglinEntityModel)this.getContextModel()).render(var1, vertexConsumer, var3, LivingEntityRenderer.getOverlay(var4, 0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
    }
}


