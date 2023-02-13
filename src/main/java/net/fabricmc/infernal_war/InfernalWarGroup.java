package net.fabricmc.infernal_war;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.infernal_war.item.RegisterItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class InfernalWarGroup {
    private static final ItemGroup INFERNALWAR_GROUP = FabricItemGroup.builder(new Identifier("infernal_war", "infernal_war_group"))
	.icon(() -> new ItemStack(RegisterItems.PIG_IRON))
	.build();

    public static void modifyEntryEvents(){
        ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_GROUP).register(content -> {
			content.add(RegisterItems.PIG_IRON);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_GROUP).register(content -> {
			content.add(RegisterItems.PIG_IRON_PICKAXE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_GROUP).register(content -> {
			content.add(RegisterItems.PIG_IRON_SHOVEL);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_GROUP).register(content -> {
			content.add(RegisterItems.PIG_IRON_AXE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_GROUP).register(content -> {
			content.add(RegisterItems.PIG_IRON_HOE);
		});

		ItemGroupEvents.modifyEntriesEvent(INFERNALWAR_GROUP).register(content -> {
			content.add(RegisterItems.PIG_IRON_SCIMITAR);
		});
    }
}
