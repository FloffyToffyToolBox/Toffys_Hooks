//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package floffy.toffys_hooks.register;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    MULTI_TOOL(2, 250, 6.0F, 2.0F, 14, () -> {
        return Ingredient.ofItems(Items.NETHERITE_SCRAP);
    });

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private ModToolMaterials(int miningLevel_, int itemDurability_, float miningSpeed_, float attackDamage_, int enchantability_, Supplier repairIngredient_) {
        this.miningLevel = miningLevel_;
        this.itemDurability = itemDurability_;
        this.miningSpeed = miningSpeed_;
        this.attackDamage = attackDamage_;
        this.enchantability = enchantability_;
        this.repairIngredient = new Lazy(repairIngredient_);
    }

    @Override
    public int getDurability() {
        return itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
