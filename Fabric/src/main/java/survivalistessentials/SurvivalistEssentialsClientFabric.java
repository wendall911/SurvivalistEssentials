package survivalistessentials;

import java.util.Map;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import survivalistessentials.event.TooltipEventHandler;
import survivalistessentials.items.SurvivalistEssentialsItems;

public class SurvivalistEssentialsClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerCreativeTabItems();
        ItemTooltipCallback.EVENT.register((stack, context, tooltip, lines) -> {
            TooltipEventHandler.onItemToolTip(stack, lines);
        });
    }

    private void registerCreativeTabItems() {
        for (Map.Entry<Identifier, Item> entry : SurvivalistEssentialsItems.getToolsAndUtilities().entrySet()) {
            CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(entries -> entries.accept(entry.getValue()));
        }
        for (Map.Entry<Identifier, Item> entry : SurvivalistEssentialsItems.getAllIngredients().entrySet()) {
            CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(entries -> entries.accept(entry.getValue()));
        }
    }

}
