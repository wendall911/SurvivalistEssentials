package survivalistessentials.mixin;

import java.util.Objects;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.sound.SurvivalistEssentialsSounds;
import survivalistessentials.util.ResourceLocationHelper;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Unique
    Identifier flintLoc = ResourceLocationHelper.prefix("flint");

    @Shadow
    public abstract Item asItem();

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void survivalistessentials$onUse(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (BuiltInRegistries.ITEM.getKey(this.asItem()).getPath().equals(flintLoc.getPath())) {
            BlockState state = context.getLevel().getBlockState(context.getClickedPos());
            Level level = context.getLevel();
            Player player = context.getPlayer();
            InteractionHand hand = context.getHand();

            if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) &&
                    Objects.requireNonNull(context.getPlayer()).getMainHandItem().getItem().equals(Items.FLINT)) {
                if (level.isClientSide()) {
                    Objects.requireNonNull(player).swing(hand);
                }
                else {
                    if (!level.isClientSide()) {
                        if (level.getRandom().nextFloat() < 0.75) {
                            if (level.getRandom().nextFloat() < ConfigHandler.Common.flintChance()) {
                                NonNullList<ItemStack> dropStack =
                                    NonNullList.withSize(1, new ItemStack(SurvivalistEssentialsItems.FLINT_SHARD, 4));

                                Containers.dropContents(level, Objects.requireNonNull(player).getOnPos(), dropStack);
                            }

                            Objects.requireNonNull(player).getItemInHand(hand).shrink(1);
                        }
                        level.playSound(null, Objects.requireNonNull(player).getOnPos(), SurvivalistEssentialsSounds.FLINT_KNAPPING, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }

                cir.setReturnValue(InteractionResult.PASS);
            }
        }
    }

}
