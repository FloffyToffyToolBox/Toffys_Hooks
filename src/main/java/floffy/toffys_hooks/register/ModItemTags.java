package floffy.toffys_hooks.register;

import floffy.toffys_hooks.ToffysHooks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> ICE_SKATES = TagKey.of(Registries.ITEM.getKey(), Identifier.of(ToffysHooks.MOD_ID, "ice_skates"));
    public static void register() {
        ToffysHooks.LOGGER.debug("Registering tags for " + ToffysHooks.MOD_ID);
    }
}
