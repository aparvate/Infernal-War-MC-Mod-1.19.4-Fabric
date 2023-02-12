package net.fabricmc.infernal_war.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterItems {

    public static final PigIron PIG_IRON = new PigIron(new FabricItemSettings());
    public static final ToolItem PIG_IRON_SCIMITAR = new SwordItem(PigIronToolMaterial.INSTANCE, 2, -2.4F, new Item.Settings());
    public static final ToolItem PIG_IRON_SHOVEL = new ShovelItem(PigIronToolMaterial.INSTANCE, 0, -3.0F, new Item.Settings());
    public static final ToolItem PIG_IRON_PICKAXE = new InfernalWarPickaxe(PigIronToolMaterial.INSTANCE, 1, -2.8F, new Item.Settings());

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron"), PIG_IRON);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_scimitar"), PIG_IRON_SCIMITAR);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_pickaxe"), PIG_IRON_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_shovel"), PIG_IRON_SHOVEL);
    }
}
