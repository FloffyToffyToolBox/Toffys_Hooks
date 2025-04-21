package floffy.toffys_hooks.item;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.system.Platform;

public class HookClawItem extends ArmorItem {
    public HookClawItem(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(material, type, settings);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
            if (slot == EquipmentSlot.FEET.getEntitySlotId() && !(((PlayerEntity)entity).getMainHandStack().getItem() instanceof HookClawItem) && !(((PlayerEntity)entity).getInventory().main.getFirst().getItem() instanceof HookClawItem)) {
                if (entity.horizontalCollision && (entity.horizontalSpeed!=0)&&!entity.isOnGround() && entity.getVelocity().y<=0){
                    entity.setVelocity(entity.getVelocity().x,MinecraftClient.getInstance().options.sneakKey.isPressed()?-0.1f:0f,entity.getVelocity().z);
                    if (((PlayerEntity)entity).shouldIgnoreFallDamageFromCurrentExplosion() && ((PlayerEntity)entity).currentExplosionImpactPos != null) {
                        if (((PlayerEntity)entity).currentExplosionImpactPos.y > ((PlayerEntity)entity).getPos().y) {
                            ((PlayerEntity)entity).currentExplosionImpactPos = ((PlayerEntity)entity).getPos();
                        }
                    }   else {
                        entity.setVelocity(entity.getVelocity().x,0.01f,entity.getVelocity().z);
                        ((PlayerEntity)entity).setIgnoreFallDamageFromCurrentExplosion(true);
                        ((PlayerEntity)entity).currentExplosionImpactPos = ((PlayerEntity)entity).getPos();
                    }
                    entity.onLanding();
                if (MinecraftClient.getInstance().options.jumpKey.isPressed()) entity.setVelocity(entity.getVelocity().x,getEntityJumpStrength((PlayerEntity)entity),entity.getVelocity().z);
            }else
            {
                ((PlayerEntity)entity).setIgnoreFallDamageFromCurrentExplosion(false);
            }
        }
    }

    public float getEntityJumpStrength(PlayerEntity entity){
        return (float) (entity.getAttributes().getValue(EntityAttributes.GENERIC_JUMP_STRENGTH) * 1);
    }
}