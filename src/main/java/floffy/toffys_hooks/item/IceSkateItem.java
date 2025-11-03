package floffy.toffys_hooks.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class IceSkateItem extends ArmorItem {
    public IceSkateItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }
    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.FEET;
    }
}