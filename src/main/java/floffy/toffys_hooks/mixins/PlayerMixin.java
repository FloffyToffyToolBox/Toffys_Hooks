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
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
    private float timeRunning;

    @Inject(
            method = {"tickMovement"},
            at = {@At("HEAD")}
    )
    @Environment(EnvType.CLIENT)
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
                    player.setVelocity(player.getVelocity().x, player.isSneaking()?-0.25f:0.0f,player.getVelocity().z);
                    if (MinecraftClient.getInstance().player.input.jumping) player.setVelocity(player.getVelocity().x,getEntityJumpStrength((PlayerEntity)player),player.getVelocity().z);
                }
            }
        }
        if (feetSlot.isOf(ModItems.ICE_SKATES) && IceSkateItem.isUsable(feetSlot)) {
            player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        }
    }
    @Inject(method ="getMovementSpeed",at= {@At("TAIL")},cancellable = true)
    public void getMovementSpeed(CallbackInfoReturnable<Float> cir){
        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack feetSlot = player.getEquippedStack(EquipmentSlot.FEET);
        if(player.isSprinting())timeRunning+=.005f;
        else timeRunning=0f;
        if (timeRunning>2) timeRunning=2;
        if (timeRunning<0) timeRunning=0;
        if(feetSlot.isOf(ModItems.ICE_SKATES)) cir.setReturnValue((float) (player.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*(timeRunning+0.75f)));
    }
    @Inject(
            method = {"checkFallFlying"},
            at = {@At("HEAD")},
            cancellable = true)
    public void checkFallFlying(CallbackInfoReturnable<Boolean> cir){
        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack feetSlot = player.getEquippedStack(EquipmentSlot.FEET);
        if (feetSlot.isOf(ModItems.CLIMBING_HOOK) && HookClawItem.isUsable(feetSlot)) {
            if (player.horizontalCollision && (player.horizontalSpeed!=0)&&!player.isOnGround() && player.getVelocity().y<=0 ) cir.setReturnValue(false);
        }
    }
}

