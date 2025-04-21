//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package floffy.toffys_hooks.register;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import floffy.toffys_hooks.ToffysHooks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModArmourMaterials {
    public static final ArmorMaterial ICE_SKATES;
    public static final ArmorMaterial CLIMBING_HOOK;
    public ModArmourMaterials() {
    }

    public static void register() {
        ToffysHooks.LOGGER.debug("Registering armour for " + ToffysHooks.MOD_ID);
    }
    public static ArmorMaterial getDefault(Registry<ArmorMaterial> registry) {
        return ICE_SKATES;
    }

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Identifier.ofVanilla(id)));
        return register(id, defense, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap(ArmorItem.Type.class);
        ArmorItem.Type[] var9 = Type.values();
        int var10 = var9.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            ArmorItem.Type type = var9[var11];
            enumMap.put(type, (Integer)defense.get(type));
        }

        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.ofVanilla(id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }

    static {
        ICE_SKATES = register("ice_skates", (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
            map.put(EquipmentType.BOOTS, 3);
            map.put(EquipmentType.LEGGINGS, 6);
            map.put(EquipmentType.CHESTPLATE, 8);
            map.put(EquipmentType.HELMET, 3);
            map.put(EquipmentType.BODY, 11);
        }), 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
            return Ingredient.ofItems(new ItemConvertible[]{Items.HEAVY_CORE});
        });
        CLIMBING_HOOK = register("climbing_hook", (EnumMap)Util.make(new EnumMap(ArmorItem.EquipmentType.class), (map) -> {
            map.put(EquipmentType.BOOTS, 2);
            map.put(EquipmentType.LEGGINGS, 6);
            map.put(EquipmentType.CHESTPLATE, 8);
            map.put(EquipmentType.HELMET, 3);
            map.put(EquipmentType.BODY, 11);
        }), 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
            return Ingredient.ofItems(new ItemConvertible[]{Items.HEAVY_CORE});
        });
    }
}
