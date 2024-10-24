package survivalistessentials.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import survivalistessentials.util.ItemUse;

public class TooltipEventHandler {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemToolTip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Component message;
        String tooltip = "";

        if (!ItemUse.getToolClass(stack).equals("unknown") && !ItemUse.isAllowedTool(stack)) {
            String type = ItemUse.getToolClass(stack);
            tooltip = "tooltip.uselessTool2";

            if (!type.equals("unknown")) {
                switch (type) {
                    case "bow", "crossbow" -> tooltip = "tooltip.uselessBow1";
                    case "hoe" -> tooltip = "tooltip.uselessHoe1";
                    case "pickaxe" -> tooltip = "tooltip.uselessTool1";
                    case "axe", "darkstar", "spear", "sword", "weapon" -> tooltip = "tooltip.uselessWeapon1";
                    default -> {
                    }
                }
            }
        }
        else if (ItemUse.isArmor(stack) && !ItemUse.isAllowedArmor(stack)) {
            tooltip = "tooltip.uselessArmor1";
        }

        if (!tooltip.isEmpty()) {
            message = Component.translatable(tooltip).withStyle(ChatFormatting.DARK_RED);

            event.getToolTip().add(message);
        }

    }

}
