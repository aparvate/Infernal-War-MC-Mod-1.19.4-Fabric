package net.fabricmc.infernal_war.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterItems {

    public static final PigIron PIG_IRON = new PigIron(new FabricItemSettings().fireproof());
    public static final ToolItem PIG_IRON_SCIMITAR = new SwordItem(PigIronToolMaterial.INSTANCE, 2, -2.4F, new FabricItemSettings().fireproof());
    public static final ToolItem PIG_IRON_SHOVEL = new ShovelItem(PigIronToolMaterial.INSTANCE, 1.5F, -3.0F, new FabricItemSettings().fireproof());
    public static final ToolItem PIG_IRON_PICKAXE = new InfernalWarPickaxe(PigIronToolMaterial.INSTANCE, 1, -2.8F, new FabricItemSettings().fireproof());
    public static final ToolItem PIG_IRON_AXE = new InfernalWarAxe(PigIronToolMaterial.INSTANCE, 4.5F, -3.1F, new FabricItemSettings().fireproof());
    public static final ToolItem PIG_IRON_HOE = new InfernalWarHoe(PigIronToolMaterial.INSTANCE, 0, -2.4F, new FabricItemSettings().fireproof());
    public static final InfernalWarCrossbow PIG_IRON_CROSSBOW = new InfernalWarCrossbow(new FabricItemSettings().fireproof().maxDamage(750), 40, 6, 4.5F);

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron"), PIG_IRON);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_scimitar"), PIG_IRON_SCIMITAR);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_pickaxe"), PIG_IRON_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_shovel"), PIG_IRON_SHOVEL);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_axe"), PIG_IRON_AXE);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_hoe"), PIG_IRON_HOE);
        Registry.register(Registries.ITEM, new Identifier("infernalwar", "pig_iron_crossbow"), PIG_IRON_CROSSBOW);
    }
}
