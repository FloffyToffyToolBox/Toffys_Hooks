//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package floffy.toffys_hooks.mixins;

import floffy.toffys_hooks.item.IceSkateItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class})
public abstract class LivingEntityMixin {

    @Unique
    public void entityGetSlipperiness(LivingEntity entity){
        if ((entity).getEquippedStack(EquipmentSlot.FEET).getItem() instanceof IceSkateItem){
//
        }
    }

    @Inject(
            method = {"travel"},
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;")
    )
    public void travel(Vec3d movement, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        entityGetSlipperiness(entity);
    }
}
