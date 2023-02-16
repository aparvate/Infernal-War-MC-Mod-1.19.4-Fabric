package net.fabricmc.infernal_war.common;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class InfernalWarGroup {
    private static final ItemGroup INFERNALWAR_INGREDIENTS = FabricItemGroup.builder(new Identifier("infernalwar", "infernalwar_ingredients"))
	.icon(() -> new ItemStack(RegisterItems.PIG_IRON))
	.build();
	private static final ItemGroup INFERNALWAR_TOOLS_AND_UTILITIES = FabricItemGroup.builder(new Identifier("infernalwar", "infernalwar_tools"))
	.icon(() -> new ItemStack(RegisterItems.PIG_IRON_PICKAXE))
	.build();
	private static final ItemGroup INFERNALWAR_COMBAT = FabricItemGroup.builder(new Identifier("infernalwar", "infernalwar_combat"))
	.icon(() -> new ItemStack(RegisterItems.PIG_IRON_SCIMITAR))
	.build();

    public static void modifyEntryEvents(){
        ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_INGREDIENTS).register(content -> {
			content.add(RegisterItems.PIG_IRON);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_TOOLS_AND_UTILITIES).register(content -> {
			content.add(RegisterItems.PIG_IRON_PICKAXE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_TOOLS_AND_UTILITIES).register(content -> {
			content.add(RegisterItems.PIG_IRON_SHOVEL);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_TOOLS_AND_UTILITIES).register(content -> {
			content.add(RegisterItems.PIG_IRON_AXE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_TOOLS_AND_UTILITIES).register(content -> {
			content.add(RegisterItems.PIG_IRON_HOE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_SCIMITAR);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_AXE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_CROSSBOW);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_BOW);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_HELMET);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_CHESTPLATE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_LEGGINGS);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_COMBAT).register(content -> {
			content.add(RegisterItems.PIG_IRON_BOOTS);
		});
    }
}
