//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package floffy.toffys_hooks.entity;

import floffy.toffys_hooks.register.ModEntities;
import floffy.toffys_hooks.register.ModItems;
import floffy.toffys_hooks.util.PlayerWithHookData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HookEntity extends ProjectileEntity {
    public static final TrackedData<Boolean> IN_BLOCK = DataTracker.registerData(HookEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Float> LENGTH= DataTracker.registerData(HookEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final float MAX_RANGE = 100.0F;
    private static final double SPEED = 5.0;

    public HookEntity(EntityType<? extends ProjectileEntity> entityType, World level) {
        super(entityType, level);
        this.ignoreCameraFrustum = true;
    }
    public HookEntity(PlayerEntity player,World level) {
        this(ModEntities.HOOK_ENTITY, level);
        this.setOwner(player);
        this.setPosition(player.getX(), player.getEyeY(), player.getZ());
        this.setVelocity(player.getRotationVec(1.0F).multiply(2.5));
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }
    @Override
    protected void initDataTracker() {
        this.getDataTracker().startTracking(IN_BLOCK, false);
        this.getDataTracker().startTracking(LENGTH, 0.0F);
    }
    @Override
    public void tick() {
        super.tick();
        PlayerEntity playerEntity = this.getPlayer();
        if (playerEntity == null || !this.getWorld().isClient() && this.discardIfInvalid(playerEntity)) {
            this.discard();
            return;
        }
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitResult.getType() != HitResult.Type.MISS) {
            this.onCollision(hitResult);
        }
        this.setPosition(hitResult.getPos());
        this.checkBlockCollision();
        this.isInBlock();
    }

    private boolean discardIfInvalid(PlayerEntity player) {
        if (player.isRemoved() || !player.isAlive() || !player.isHolding(ModItems.GRAPPLE_HOOK) || this.squaredDistanceTo(player) > 10000.0) {
            this.discard();
            return true;
        }
        return false;
    }


    @Override
    protected boolean canHit(Entity entity) {
        return false;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.setVelocity(Vec3d.ZERO);
        this.setInBlock(true);
        PlayerEntity playerEntity = this.getPlayer();
        if (playerEntity != null) {
            double d = playerEntity.getEyePos().subtract(blockHitResult.getPos()).length();
            this.setLength(Math.max((float)d * 0.5f - 3.0f, 1.5f));
        }
    }
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("in_block", this.isInBlock());
        nbt.putFloat("length", this.getLength());
    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setInBlock(nbt.getBoolean("in_block"));
        this.setLength(nbt.getFloat("length"));
    }


    private void setInBlock(boolean inBlock) {
        this.getDataTracker().set(IN_BLOCK, inBlock);
    }

    private void setLength(float length) {
        this.getDataTracker().set(LENGTH, Float.valueOf(length));
    }

    public boolean isInBlock() {
        return this.getDataTracker().get(IN_BLOCK);
    }

    public float getLength() {
        return this.getDataTracker().get(LENGTH).floatValue();
    }

    @Override
    protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.NONE;
    }
    @Override
    public void remove(Entity.RemovalReason reason) {
        this.setHookForPlayer(null);
        super.remove(reason);
    }
    @Override
    public void onRemoved() {
        this.setHookForPlayer(null);
    }

    @Override
    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
        this.setHookForPlayer(this);
    }


    private void setHookForPlayer(@Nullable HookEntity hookEntity) {
        PlayerEntity playerEntity = this.getPlayer();
        if (playerEntity != null) {
            ((PlayerWithHookData)playerEntity).setHook(hookEntity);
        }
    }
    @Nullable
    public  PlayerEntity getPlayer() {
        Entity entity = this.getOwner();
        return entity instanceof PlayerEntity ? (PlayerEntity)entity : null;
    }

    public boolean canUsePortals() {
        return false;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        Entity entity = this.getOwner();
        return new EntitySpawnS2CPacket(this, entity == null ? this.getId() : entity.getId());
    }
    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        if (this.getPlayer() == null) {
            this.kill();
        }
    }
}
