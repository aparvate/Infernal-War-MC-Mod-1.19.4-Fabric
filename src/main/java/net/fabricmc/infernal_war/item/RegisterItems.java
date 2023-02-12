package net.fabricmc.infernal_war.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterItems {

    public static PigIron PIG_IRON = new PigIron(new FabricItemSettings());
    public static ToolItem PIG_IRON_SCIMITAR = new SwordItem(PigIronToolMaterial.INSTANCE, 4, -2.4F, new Item.Settings());

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_scimitar"), PIG_IRON_SCIMITAR);
    }
}
