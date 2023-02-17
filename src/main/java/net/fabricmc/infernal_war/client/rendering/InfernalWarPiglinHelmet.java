package net.fabricmc.infernal_war.client.rendering;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class InfernalWarPiglinHelmet extends Model {

	private final ModelPart root;
	private final ModelPart snout;

	public InfernalWarPiglinHelmet(ModelPart root) {
		super(RenderLayer::getArmorCutoutNoCull);
		this.root = root.getChild("root");
		this.snout = this.root.getChild("snout");
	}
	
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData modelPartData1 = modelPartData.addChild("root", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8F, -4F, 8.0F, 8.0F, 8.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		modelPartData1.addChild("snout", ModelPartBuilder.create().uv(24, 0).cuboid(-2.0F, -28F, -4.5F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}