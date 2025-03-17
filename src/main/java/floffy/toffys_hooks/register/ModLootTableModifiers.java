package floffy.toffys_hooks.register;

import floffy.toffys_hooks.register.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
private static final Identifier GRASS_BLOCK_ID = Identifier.of("minecraft", "blocks/grass_block");

    public static void modifyLootTables() {
    }

    public static void register(Identifier identifier, Item item, float chance, int rolls, float minCount, float maxCount){
        LootTableEvents.MODIFY.register(((key, tableBuilder, source, registries) -> {
            if (identifier.equals(key.getValue())){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(rolls))
                        .conditionally(RandomChanceLootCondition.builder(chance)) //1=100%
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minCount, maxCount))
                                .build());
                tableBuilder.pool(poolBuilder.build());
            }
        }));
    }
}
