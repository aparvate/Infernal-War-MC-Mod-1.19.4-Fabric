package net.fabricmc.infernal_war.common;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.infernal_war.common.item.RegisterItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class InfernalWarGroup {
    public static final RegistryKey<ItemGroup> INFERNALWAR_INGREDIENTS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("infernalwar", "infernalwar_ingredients"));
	
	public static final RegistryKey<ItemGroup> INFERNALWAR_TOOLS_AND_UTILITIES = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("infernalwar", "infernalwar_tools"));

	public static final RegistryKey<ItemGroup> INFERNALWAR_COMBAT = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("infernalwar", "infernalwar_combat"));

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
