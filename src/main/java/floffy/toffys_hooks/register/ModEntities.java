package floffy.toffys_hooks.register;

import floffy.toffys_hooks.ToffysHooks;
import floffy.toffys_hooks.entity.HookEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Supplier;

public class ModEntities
{
    public static final EntityType<HookEntity> HOOK_ENTITY;
    //public static final EntityType<HookEntity> HOOK_ENTITY = register("lashing_potato_hook",
            //EntityType.Builder.create(HookEntity::new, SpawnGroup.MISC).disableSaving().dimensions(
                    //0.25f, 0.25f).maxTrackingRange(4).trackingTickInterval(5));

    public static void register() {
        ToffysHooks.LOGGER.debug("Registering entities for " + ToffysHooks.MOD_ID);
    }
    static {
        HOOK_ENTITY = (EntityType<HookEntity>) Registry.register(Registries.ENTITY_TYPE, Identifier.of
                (ToffysHooks.MOD_ID, "hook_entity"),  EntityType.Builder.<HookEntity>create(HookEntity::new,SpawnGroup.MISC).dimensions(0.25F, 0.25F).disableSummon().disableSaving().maxTrackingRange(4).trackingTickInterval(5).build());

    }
}