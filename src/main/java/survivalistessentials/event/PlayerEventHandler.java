package survivalistessentials.event;

import java.util.Objects;
import java.util.UUID;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.SurvivalistEssentials;

import static survivalistessentials.SurvivalistEssentials.loc;

public class PlayerEventHandler {

    private static final ResourceLocation STARTING_HEALTH_PENALTY = loc("starting_health_penalty");
    //private static final UUID STARTING_HEALTH_PENALTY = UUID.fromString("e86dd51f-e5bd-4606-940a-6ffc961b612d");

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        applyHealthPenalty(event.getEntity());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Player player = event.getEntity();

        applyHealthPenalty(player);

        if (!player.isCreative()
                && !player.isSpectator()
                && !player.level().isClientSide
                && event.isWasDeath()) {
            ServerPlayer sp = (ServerPlayer) player;

            if (ConfigHandler.Common.enableHungerPenalty()) {
                sp.getFoodData().setFoodLevel(ConfigHandler.Common.hunger());
                sp.getFoodData().setSaturation(ConfigHandler.Common.saturation());
            }
            if (ConfigHandler.Common.enableHealthPenalty()) {
                sp.setHealth(ConfigHandler.Common.health());
            }
        }
    }

    private static void applyHealthPenalty(Player player) {
        if (player != null && ConfigHandler.Common.startingHealthPenalty() < 0) {
            float maxHealth = player.getMaxHealth();
            float newHealth = maxHealth + ConfigHandler.Common.startingHealthPenalty();

            if (newHealth >= 1.0F) {
                AttributeInstance attributeInstance = maxHealthAttribute(player);
                AttributeModifier modifier = new AttributeModifier(
                    STARTING_HEALTH_PENALTY,
                    ConfigHandler.Common.startingHealthPenalty(),
                    AttributeModifier.Operation.ADD_VALUE
                );

                attributeInstance.removeModifier(STARTING_HEALTH_PENALTY);
                attributeInstance.addPermanentModifier(modifier);
            }
        }
    }

    private static AttributeInstance maxHealthAttribute(Player player) {
        return Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH));
    }

}
