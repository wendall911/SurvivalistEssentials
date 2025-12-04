package survivalistessentials.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;

public class ClientEventHandler {

    @SubscribeEvent
    public static void clientPlayerLogin(ClientPlayerNetworkEvent event) {
        if (event.getPlayer() != null
                && !ModList.get().isLoaded(SurvivalistEssentialsIntegration.TS_MODID)
                && ModList.get().isLoaded(SurvivalistEssentialsIntegration.TCON_MODID)
                && ConfigHandler.Client.informTConCompat()) {
            Component message = Component.translatable("message.survivalistessentials.tcon_compat")
                .withStyle(ChatFormatting.DARK_GREEN);

            event.getPlayer().sendSystemMessage(message);
            ConfigHandler.Client.disableTConCompatMessage();
        }
    }

}
