package floffy.toffys_hooks.register;

import floffy.toffys_hooks.ToffysHooks;
import floffy.toffys_hooks.item.HookClawItem;
import floffy.toffys_hooks.item.HookItem;
import floffy.toffys_hooks.item.IceSkateItem;
import floffy.toffys_hooks.item.MultiToolItem;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ToffysHooks.MOD_ID, name), item);
    }
    public static void register() {

        ToffysHooks.LOGGER.debug("Registering items for " + ToffysHooks.MOD_ID);
    }
    public static final Item GRAPPLE_HOOK = registerItem("grappling_hook", new HookItem(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item HOOK = registerItem("hook", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item HOOK_AXE= registerItem("hook_axe", new MultiToolItem(ToolMaterials.DIAMOND, new Item.Settings().
            attributeModifiers(MultiToolItem.createAttributeModifiers(ToolMaterials.DIAMOND, 4, -2.4F)).rarity(Rarity.RARE)));
    public static final Item CLIMBING_HOOK = registerItem("climbing_hook", new HookClawItem(
            ModArmourMaterials.CLIMBING_HOOK, EquipmentType.BOOTS, (new Item.Settings()).rarity(Rarity.UNCOMMON)
            .maxDamage(EquipmentType.BOOTS.getMaxDamage(33))));
    public static final Item ICE_SKATES= registerItem("ice_skates", new IceSkateItem(
            ModArmourMaterials.ICE_SKATES, net.minecraft.item.ArmorItem.Type.BOOTS, (new Item.Settings()).rarity(Rarity.RARE)
            .maxDamage(net.minecraft.item.ArmorItem.Type.BOOTS.getMaxDamage(33))));
}