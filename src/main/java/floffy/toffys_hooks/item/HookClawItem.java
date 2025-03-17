package floffy.toffys_hooks.item;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.system.Platform;

public class HookClawItem extends ArmorItem {
    public HookClawItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity.horizontalCollision && (entity.horizontalSpeed!=0)&&!entity.isOnGround() && entity.getVelocity().y<=0){
            entity.setVelocity(entity.getVelocity().x,MinecraftClient.getInstance().options.sneakKey.isPressed()?-0.1f:0f,entity.getVelocity().z);
            entity.onLanding();
            if (MinecraftClient.getInstance().options.jumpKey.isPressed()) entity.setVelocity(entity.getVelocity().x,getEntityJumpStrength((PlayerEntity)entity),entity.getVelocity().z);
        }
    }

    public float getEntityJumpStrength(PlayerEntity entity){
        return (float) (entity.getAttributes().getValue(EntityAttributes.GENERIC_JUMP_STRENGTH) * 1);
    }
}