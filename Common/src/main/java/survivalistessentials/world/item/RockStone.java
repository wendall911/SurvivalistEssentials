package survivalistessentials.world.item;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.sound.SurvivalistEssentialsSounds;

public class RockStone extends Item {

    public RockStone(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        Level level = context.getLevel();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();

        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) &&
                Objects.requireNonNull(context.getPlayer()).getMainHandItem().getItem() instanceof RockStone) {
            if (level.isClientSide()) {
                Objects.requireNonNull(player).swing(hand);
            }
            else {
                if (!level.isClientSide()) {
                    if (level.getRandom().nextFloat() < 0.5) {
                        if (level.getRandom().nextFloat() < ConfigHandler.Common.flintChance()) {
                            NonNullList<ItemStack> dropStack =
                                NonNullList.withSize(1, new ItemStack(SurvivalistEssentialsItems.FLINT_SHARD, 2));

                            Containers.dropContents(level, Objects.requireNonNull(player).getOnPos(), dropStack);
                        }

                        Objects.requireNonNull(player).getItemInHand(hand).shrink(1);
                    }
                    level.playSound(null, Objects.requireNonNull(player).getOnPos(), SurvivalistEssentialsSounds.FLINT_KNAPPING, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }

            return InteractionResult.PASS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    @NotNull
    public InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        return InteractionResult.FAIL;
    }

}
