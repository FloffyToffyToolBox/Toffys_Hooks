package floffy.toffys_hooks.register;

import floffy.toffys_hooks.ToffysHooks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final SoundEvent GRAPPLING_HOOK_FIRE = registerSound("grappling_hook_fire");
    public static final SoundEvent RETRIEVE_HOOK = registerSound("retrieve_hook");
    public static final SoundEvent MUSHROOM_SHELF_JUMP = registerSound("mushroom_shelf_jump");
    public static final SoundEvent HOOK_CLAW_CLIMB = registerSound("hook_claw_climb");
    public static final SoundEvent MUSHROOM_SHELF_LAND = registerSound("mushroom_shelf_land");
    public static final BlockSoundGroup MUSHROOM_SHELF_SOUNDS = new BlockSoundGroup(1f,1f, SoundEvents.BLOCK_FROGLIGHT_BREAK,SoundEvents.BLOCK_FROGLIGHT_STEP,SoundEvents.BLOCK_FROGLIGHT_PLACE,SoundEvents.BLOCK_FROGLIGHT_HIT, MUSHROOM_SHELF_LAND);
    public static void register() {

        ToffysHooks.LOGGER.debug("Registering soundEvents for " + ToffysHooks.MOD_ID);
    }
    private static SoundEvent registerSound(String name) {
        Identifier id = Identifier.of(ToffysHooks.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
