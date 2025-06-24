package floffy.toffys_hooks.item;

import floffy.toffys_hooks.register.ModArmourMaterials;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HookClawItem extends ArmorItem {
    public HookClawItem(ModArmourMaterials material, Type type, Settings settings) {
        super(material, type, settings);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
            if (slot == EquipmentSlot.FEET.getEntitySlotId() && !(((PlayerEntity)entity).getMainHandStack().getItem() instanceof HookClawItem) && !(((PlayerEntity)entity).getInventory().main.getFirst().getItem() instanceof HookClawItem)) {
                if (entity.horizontalCollision && (entity.horizontalSpeed!=0)&&!entity.isOnGround() && entity.getVelocity().y<=0){
                    entity.setVelocity(entity.getVelocity().x,MinecraftClient.getInstance().options.sneakKey.isPressed()?-0.25f:0.001f,entity.getVelocity().z);
                if (MinecraftClient.getInstance().options.jumpKey.isPressed()) entity.setVelocity(entity.getVelocity().x,getEntityJumpStrength((PlayerEntity)entity),entity.getVelocity().z);
            }
        }
    }

    public float getEntityJumpStrength(PlayerEntity entity){
        return 0.5f;
    }
}