package floffy.toffys_hooks.register;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
private static final Identifier OMEN = Identifier.of("minecraft", "chests/trial_chambers/reward_ominous");
    private static final Identifier POST = Identifier.of("minecraft", "chests/pillager_outpost");
    private static final Identifier ARMS = Identifier.of("minecraft", "chests/village/village_armorer");
    private static final Identifier ARNS2 = Identifier.of("minecraft", "chests/village/village_toolsmith");
    private static final Identifier ARNS3 = Identifier.of("minecraft", "chests/village/village_weaponsmith");
    private static final Identifier IGLOO = Identifier.of("minecraft", "chests/igloo_chest");
    private static final Identifier ANCIENT_CITY = Identifier.of("minecraft", "chests/ancient_city");
    public static void modifyLootTables() {
        register(OMEN, ModItems.HOOK,.1f,1,1,2);
        register(OMEN, ModItems.GRAPPLE_HOOK,.1f,1,1,1);
        register(POST, ModItems.CLIMBING_HOOK,0.25f,1,1,1);
        register(ARMS, ModItems.CLIMBING_HOOK,.1f,1,1,1);
        register(ARNS2, ModItems.CLIMBING_HOOK,.1f,1,1,1);
        register(ARNS3, ModItems.CLIMBING_HOOK,.1f,1,1,1);
        register(IGLOO, ModItems.ICE_SKATES,1,1,1,1);
        register(ANCIENT_CITY, ModItems.GRAPPLE_HOOK,.1f,1,1,1);
    }

    public static void register(Identifier identifier, Item item, float chance, int rolls, float minCount, float maxCount){
        LootTableEvents.MODIFY.register((resourceManager, registries, key, tableBuilder, source) -> {
            if (identifier.equals(key)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(rolls))
                        .conditionally(RandomChanceLootCondition.builder(chance)) //1=100%
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minCount, maxCount))
                                .build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
