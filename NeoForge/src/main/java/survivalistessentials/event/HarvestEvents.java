package survivalistessentials.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

import org.apache.commons.lang3.tuple.Pair;

import survivalistessentials.common.HarvestBlock;

public class HarvestEvents {

    @SubscribeEvent
    public static void levelLoaded(LevelEvent.Load event) {
        HarvestBlock.setup();
    }

    @SubscribeEvent
    public static void breakBlock(BreakBlockEvent event) {
        if (event.getPlayer() instanceof FakePlayer) return;

        if (HarvestEventHandler.shouldCancelBreakBlock(event.getLevel(), event.getPos(), event.getPlayer())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void harvestCheckEvent(PlayerEvent.HarvestCheck event) {
        final Player player = event.getEntity();
        final BlockState state = event.getTargetBlock();

        if (!(player instanceof FakePlayer)) {
            event.setCanHarvest(HarvestEventHandler.canHarvest(player, state, event.canHarvest()));
        }
    }

    // Controls the slow mining speed of blocks that aren't the right tool
    @SubscribeEvent
    public static void slowMining(PlayerEvent.BreakSpeed event) {
        final Player player = event.getEntity();

        if (player instanceof FakePlayer) return;

        Pair<Boolean, Float> slowdown = HarvestEventHandler.getMiningSlowdown(player, event.getState());

        if (slowdown.getLeft()) {
            event.setNewSpeed(slowdown.getRight());
        }
    }

}
