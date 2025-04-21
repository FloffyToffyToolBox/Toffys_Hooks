//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package floffy.toffys_hooks.item;

import floffy.toffys_hooks.entity.HookEntity;
import floffy.toffys_hooks.util.PlayerWithHookData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class HookItem extends Item {
    public HookItem(Item.Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        HookEntity hookEntity = ((PlayerWithHookData)player).getHook();
        if (hookEntity != null) {
            HookItem.discard(world, player, hookEntity);
        } else {
            if (!world.isClient) {
                itemStack.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            this.fire(world, player);
        }
        return ActionResult.SUCCESS;
    }

    private void fire(World world, PlayerEntity player) {
        if (!world.isClient) {
            world.spawnEntity(new HookEntity(world, player));
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        player.emitGameEvent(GameEvent.ITEM_INTERACT_START);
    }

    private static void discard(World world, PlayerEntity player, HookEntity hookEntity) {
        if (!world.isClient()) {
            hookEntity.discard();
            ((PlayerWithHookData)player).setHook(null);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
    }
}
