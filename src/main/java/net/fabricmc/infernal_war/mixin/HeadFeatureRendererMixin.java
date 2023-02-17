package net.fabricmc.infernal_war.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.infernal_war.client.rendering.InfernalWarPiglinHelmet;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.item.ItemRenderer;

@Environment(EnvType.CLIENT)
@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererMixin {
    @Shadow
    @Final
    private float scaleX;
    @Shadow
    @Final
    private float scaleY;
    @Shadow
    @Final
    private float scaleZ;

    private final InfernalWarPiglinHelmet pigIronHelmet = new InfernalWarPiglinHelmet(InfernalWarPiglinHelmet.getTexturedModelData().createModel());

    @SuppressWarnings("rawtypes")
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, LivingEntity livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo info) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
        if (!(itemStack.isEmpty()) && (itemStack.getItem() == RegisterItems.PIG_IRON_HELMET)) {
            matrixStack.push();
            matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);
            ((ModelWithHead) ((HeadFeatureRenderer) (Object) this).getContextModel()).getHead().rotate(matrixStack);
            matrixStack.translate(0.0D, -1.75D, 0.0D);
            matrixStack.scale(1.19F, 1.19F, 1.19F);
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, this.pigIronHelmet.getLayer(new Identifier("infernalwar", "textures/entity/pig_iron_helmet.png")), false, itemStack.hasGlint());
            this.pigIronHelmet.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();
            info.cancel();
        }
    }
}
