package survivalistessentials.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.apache.commons.lang3.tuple.Pair;

import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.TagManager;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.mixin.AbstractBlockStateAccessor;
import survivalistessentials.platform.Services;
import survivalistessentials.sound.SurvivalistEssentialsSounds;
import survivalistessentials.util.Chat;
import survivalistessentials.util.ItemUse;
import survivalistessentials.util.ResourceLocationHelper;
import survivalistessentials.util.ToolType;

import static technology.roughness.whitenoise.platform.Services.WN_PLATFORM;

public class HarvestEventHandler {

    private static final Map<Player, BlockPos> harvestAttempts = new HashMap<>();
    private static Block spellHitBlock = null;
    private static int breakBlockStep = 0;

    public static boolean shouldCancelBreakBlock(LevelAccessor level, BlockPos pos, Player player) {
        final BlockState state = level.getBlockState(pos);
        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);
        boolean cancel = false;
        boolean alwaysBreakable = state.is(TagManager.Blocks.ALWAYS_BREAKABLE) ||
            ItemUse.isAlwaysBreakable(state);

        if (WN_PLATFORM.isModLoaded(SurvivalistEssentialsIntegration.CARRYON_MODID)) {
            final ItemStack handStack = player.getMainHandItem();
            final ItemStack offhandStack = player.getOffhandItem();

            if (handStack.isEmpty() && offhandStack.isEmpty()) {
                if (Services.PLATFORM_HELPER.isCarryonKeyPressed(player)) {
                    alwaysBreakable = true;
                }
            }
        }

        if (WN_PLATFORM.isModLoaded(SurvivalistEssentialsIntegration.CREATE_MODID)) {
            final ItemStack handStack = player.getMainHandItem();

            if (ResourceLocationHelper.getModId(handStack).equals(SurvivalistEssentialsIntegration.CREATE_MODID)
                    && ResourceLocationHelper.getModId(state.getBlock()).equals(SurvivalistEssentialsIntegration.CREATE_MODID)) {
                alwaysBreakable = true;
            }
        }

        if (!alwaysBreakable && !player.isCreative()) {
            if (expectedToolType != ToolType.NONE) {
                final ItemStack handStack = getHandStack(player, state);

                boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
                boolean isAllowedTool = ItemUse.isAllowedTool(handStack);
                String toolClass = ItemUse.getToolClass(handStack);

                if (toolClass.equals("unknown") || (isAllowedTool && !correctTool)) {
                    cancel = true;

                    if (harvestAttempts.containsKey(player)
                        || harvestAttempts.get(player) == null
                        || !harvestAttempts.get(player).equals(pos)) {

                        harvestAttempts.put(player, pos);

                        Chat.sendMessage(player, Chat.WRONG_TOOL, expectedToolType.toString().toLowerCase(), false);
                    }
                    else {
                        Chat.sendMessage(player, Chat.WARNING);
                        Chat.sendMessage(player, Chat.SARCASTIC_WRONG_TOOL, expectedToolType.toString().toLowerCase(), true);
                        player.hurt(player.damageSources().generic(), 0.1f);
                    }

                    if (!toolClass.equals("unknown") && player.level().isClientSide() && ConfigHandler.Client.enableFailSound()) {
                        level.playSound(null, player.getOnPos(), SurvivalistEssentialsSounds.TOOL_FAIL, SoundSource.PLAYERS, 0.6F, 1.0F);
                    }
                }
                else {
                    // Reset spell hit
                    if (spellHitBlock != null) {
                        if (breakBlockStep == 1) {
                            breakBlockStep = -1;
                            spellHitBlock = null;
                        }
                        breakBlockStep++;
                    }
                }

            }
        }

        return cancel;
    }

    public static void setSpellHitBlock(Block block) {
        spellHitBlock = block;
    }

    public static boolean canHarvest(Player player, BlockState state, boolean originalCanHarvest) {
        if (!player.isCreative()) {
            final ItemStack handStack = getHandStack(player, state);

            final boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
            final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);
            boolean canHarvest = originalCanHarvest
                || ItemUse.alwaysDrops(state)
                || expectedToolType == ToolType.NONE;

            if (!canHarvest) {
                final boolean isOre = state.is(TagManager.Blocks.ORES) || state.is(TagManager.Blocks.OBSIDIANS);

                if (isOre && expectedToolType == ToolType.PICKAXE) {
                    canHarvest = (correctTool && handStack.isCorrectToolForDrops(state));
                }
                else {
                    canHarvest = correctTool || handStack.isCorrectToolForDrops(state);
                }
            }

            return canHarvest;
        }

        return true;
    }

    // Controls the slow mining speed of blocks that aren't the right tool
    public static Pair<Boolean, Float> getMiningSlowdown(Player player, BlockState state) {
        final float destroySpeed = ((AbstractBlockStateAccessor) state).getDestroySpeed();
        float slowdown = destroySpeed;
        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);

        if (!player.isCreative() && expectedToolType != ToolType.NONE) {
            ItemStack handStack = getHandStack(player, state);
            boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
            boolean alwaysBreakable = state.is(TagManager.Blocks.ALWAYS_BREAKABLE);
            boolean isAllowedTool = ItemUse.isAllowedTool(handStack);

            if (!alwaysBreakable) {
                if (!isAllowedTool) {
                    slowdown = ConfigHandler.Common.slowDownSpeed() / 2;
                }
                else if (!correctTool) {
                    slowdown = ConfigHandler.Common.slowDownSpeed();
                }
            }
            else {
                if (!correctTool) {
                    slowdown = ConfigHandler.Common.slowDownSpeed();
                }
                else if (!isAllowedTool) {
                    slowdown = ConfigHandler.Common.slowDownSpeed() / 2;
                }
            }
        }

        if (slowdown != destroySpeed) {
            return Pair.of(true, slowdown);
        }

        return Pair.of(false, destroySpeed);
    }

    private static ItemStack getHandStack(Player player, BlockState blockState) {
        ItemStack stack = player.getMainHandItem();

        if (spellHitBlock != null && spellHitBlock.equals(blockState.getBlock())) {
            String toolClass = ItemUse.getToolClass(stack);

            if (!Objects.equals(toolClass, "spell") && !Objects.equals(toolClass, "cad")) {
                stack = player.getOffhandItem();
            }
        }

        return stack;
    }

}
