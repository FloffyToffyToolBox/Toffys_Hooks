//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package floffy.toffys_hooks.mixins;

import floffy.toffys_hooks.entity.HookEntity;
import floffy.toffys_hooks.item.HookClawItem;
import floffy.toffys_hooks.item.IceSkateItem;
import floffy.toffys_hooks.register.ModItems;
import floffy.toffys_hooks.util.PlayerWithHookData;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerEntity.class})
public class PlayerMixin implements PlayerWithHookData {
    private HookEntity hookEntity;

    public PlayerMixin() {
    }
    public float getEntityJumpStrength(PlayerEntity entity){
        return 0.5f;
    }
    public HookEntity getHook() {
        return this.hookEntity;
    }

    public void setHook(HookEntity value) {
        this.hookEntity = value;
    }

    @Inject(
            method = {"tickMovement"},
            at = {@At("HEAD")}
    )
    public void tickMovement(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (this.hookEntity != null && this.hookEntity.isInBlock()) {
            player.onLanding();
            if (player.isLogicalSideForUpdatingMovement()) {
                Vec3d vec3 = this.hookEntity.getPos().subtract(player.getEyePos());
                float g = this.hookEntity.getLength();
                double d = vec3.length();
                if (d > (double)g) {
                    double e = d / (double)g * 0.1;
                    player.addVelocity(vec3.multiply(1.0 / d).multiply(e, e * 1.1, e));
                }
            }
        }
        ItemStack feetSlot = player.getEquippedStack(EquipmentSlot.FEET);
        if (feetSlot.isOf(ModItems.CLIMBING_HOOK) && HookClawItem.isUsable(feetSlot)) {
            boolean condition = (player.horizontalCollision && (player.horizontalSpeed!=0)&&!player.isOnGround() && player.getVelocity().y<=0);
            if (player.getVelocity().y>= -0.1)player.onLanding();
            if (condition){
                if (player.isLogicalSideForUpdatingMovement()) {
                    player.setVelocity(player.getVelocity().x, MinecraftClient.getInstance().options.sneakKey.isPressed()?-0.25f:0.0f,player.getVelocity().z);
                    if (MinecraftClient.getInstance().options.jumpKey.isPressed()) player.setVelocity(player.getVelocity().x,getEntityJumpStrength((PlayerEntity)player),player.getVelocity().z);
                }
            }
        }
        if (feetSlot.isOf(ModItems.ICE_SKATES) && IceSkateItem.isUsable(feetSlot)) {
            BlockPos supporting = new BlockPos(player.getBlockX(), player.getBlockY() - 1, player.getBlockZ());
            if (player.getWorld().getBlockState(supporting).getBlock() != Blocks.AIR && !player.isSneaking()) {
                if ((player.getVelocity().getZ()+player.getVelocity().getX())/2< 2f&&(player.getVelocity().getZ()+player.getVelocity().getX())/2> -2f) {
                    if ((player.getWorld().getBlockState(supporting).getBlock().getSlipperiness() == 0.6f)) {
                        player.setVelocity(player.getVelocity().x * 1.45f, player.getVelocity().y, player.getVelocity().z * 1.45f);
                        //1.35 0?
                        //1.45 1?
                        //1.55 2?
                        //1.7  3?
                    } else {
                        player.setVelocity(player.getVelocity().x * 1.075f, player.getVelocity().y, player.getVelocity().z * 1.075f);
                    }
                }
            }
        }
    }
}

