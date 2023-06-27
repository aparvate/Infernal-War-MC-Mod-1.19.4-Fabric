package net.fabricmc.infernal_war.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfernalWar implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("infernalwar");

	@Override
	public void onInitialize() {
		RegisterItems.register();
		InfernalWarGroup.modifyEntryEvents();
		
		Registry.register(Registries.ITEM_GROUP, InfernalWarGroup.INFERNALWAR_COMBAT, FabricItemGroup.builder()
       	.icon(() -> new ItemStack(RegisterItems.PIG_IRON_AXE))
       	.displayName(Text.translatable("itemGroup.infernalwar.infernalwar_combat"))
       	.build()); // build() no longer registers by itself

		Registry.register(Registries.ITEM_GROUP, InfernalWarGroup.INFERNALWAR_TOOLS_AND_UTILITIES, FabricItemGroup.builder()
       	.icon(() -> new ItemStack(RegisterItems.PIG_IRON_PICKAXE))
       	.displayName(Text.translatable("itemGroup.infernalwar.infernalwar_tools"))
       	.build()); // build() no longer registers by itself

		Registry.register(Registries.ITEM_GROUP, InfernalWarGroup.INFERNALWAR_INGREDIENTS, FabricItemGroup.builder()
       	.icon(() -> new ItemStack(RegisterItems.PIG_IRON))
       	.displayName(Text.translatable("itemGroup.infernalwar.infernalwar_ingredients"))
       	.build()); // build() no longer registers by itself

		LOGGER.info("The Piglins will rise...");
	}
}
