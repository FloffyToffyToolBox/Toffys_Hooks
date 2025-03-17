//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package floffy.toffys_hooks.mixins;

import floffy.toffys_hooks.entity.HookEntity;
import floffy.toffys_hooks.util.PlayerWithHookData;
import net.minecraft.entity.player.PlayerEntity;
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

    }
}
