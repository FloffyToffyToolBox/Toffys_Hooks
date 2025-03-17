package floffy.toffys_hooks.register;

import floffy.toffys_hooks.ToffysHooks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> MULTITOOL_MINEABLE = TagKey.of(Registries.BLOCK.getKey(), Identifier.of(ToffysHooks.MOD_ID, "mineable/multitool"));
    public static void register() {
        ToffysHooks.LOGGER.debug("Registering tags for " + ToffysHooks.MOD_ID);
    }
}
