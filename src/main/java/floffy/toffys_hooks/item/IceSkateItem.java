package floffy.toffys_hooks.item;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class IceSkateItem extends ArmorItem {
    public IceSkateItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (slot == EquipmentSlot.FEET.getEntitySlotId() && !(((PlayerEntity)entity).getMainHandStack().getItem() instanceof IceSkateItem) && !(((PlayerEntity)entity).getInventory().main.getFirst().getItem() instanceof IceSkateItem) && entity.isOnGround() && world.getBlockState(entity.getBlockPos()).getBlock() != Blocks.WATER) {
            BlockPos supporting = new BlockPos(entity.getBlockX(), entity.getBlockY() - 1, entity.getBlockZ());
            if (world.getBlockState(supporting).getBlock() != Blocks.AIR && !entity.isSneaking()) {
                if ((entity.getVelocity().getZ()+entity.getVelocity().getX())/2< 2f&&(entity.getVelocity().getZ()+entity.getVelocity().getX())/2> -2f) {
                    if ((world.getBlockState(supporting).getBlock().getSlipperiness() == 0.6f)) {
                        entity.setVelocity(entity.getVelocity().x * 1.45f, entity.getVelocity().y, entity.getVelocity().z * 1.45f);
                        //1.35 0?
                        //1.45 1?
                        //1.55 2?
                        //1.7  3?
                    } else {
                        entity.setVelocity(entity.getVelocity().x * 1.075f, entity.getVelocity().y, entity.getVelocity().z * 1.075f);
                    }
                }
            }
        }
    }
}