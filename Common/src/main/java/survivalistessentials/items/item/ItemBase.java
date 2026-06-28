package survivalistessentials.items.item;

import org.jspecify.annotations.NonNull;

import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import survivalistessentials.world.effect.SurvivalistEssentialsEffects;

public class ItemBase extends Item {

    private final ItemUseAnimation animation;

    public ItemBase(Item.Properties props, ItemUseAnimation animation) {
        super(props);

        this.animation = animation;
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity entity) {
        Player player = entity instanceof Player ? (Player)entity : null;

        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
        }

        if (!level.isClientSide()) {
            String name = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();

            if (name.contains("bandage")) {
                int amplifier = 0;

                if (!name.contains("crude")) {
                    amplifier = 1;
                }

                Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.STOP_BLEEDING);
                entity.addEffect(new MobEffectInstance(effect, 600, amplifier));
            }
            else if (name.contains("cup")) {
                Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.ZOMBIE_ESSENCE);
                entity.addEffect(new MobEffectInstance(effect, 3600, 1));
            }

        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if (animation == ItemUseAnimation.DRINK) {
            level.gameEvent(entity, GameEvent.DRINK, entity.getEyePosition());
        }

        return stack;
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        String name = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        boolean stopBleeding = player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.STOP_BLEEDING));
        boolean zombieEssence = player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.ZOMBIE_ESSENCE));

        if (name.contains("bandage")) {
            if (stopBleeding || player.getHealth() >= player.getMaxHealth()) {
                return InteractionResult.FAIL;
            }
        }
        else if (name.contains("cup") && zombieEssence) {
            return InteractionResult.FAIL;
        }

        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public @NonNull ItemUseAnimation getUseAnimation(@NonNull ItemStack stack) {
        return animation;
    }

    @Override
    public int getUseDuration(@NonNull ItemStack pStack, @NonNull LivingEntity pEntity) {
        return 1;
    }

}
