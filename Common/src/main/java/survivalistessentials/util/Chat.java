package survivalistessentials.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import survivalistessentials.SurvivalistEssentials;

public class Chat {

    public static final String NOTICE = "message." + SurvivalistEssentials.MODID + ".notice";
    public static final String WARNING = "message." + SurvivalistEssentials.MODID + ".warning";
    public static final String TOOL_BROKE = "message." + SurvivalistEssentials.MODID + ".tool_broke";
    public static final String WRONG_TOOL = "message." + SurvivalistEssentials.MODID + ".wrong_tool";
    public static final String SARCASTIC_WRONG_TOOL = "message." + SurvivalistEssentials.MODID + ".wrong_tool2";

    public static void sendMessage(Player player, String title) {
        Component message = Component.translatable(title).withStyle(ChatFormatting.RED);

        if (title.contains(NOTICE)) {
            message = Component.translatable(title).withStyle(ChatFormatting.YELLOW);
        }

        sendMessage(player, message, false);
    }

    public static void sendMessage(Player player, String title, String replace, boolean delayed) {
        sendMessage(player, title, Component.translatable(replace), delayed);
    }

    public static void sendMessage(Player player, String title, ItemStack stack, boolean delayed) {
        sendMessage(player, title, stack.getItem(), delayed);
    }

    public static void sendMessage(Player player, String title, Item item, boolean delayed) {
        Component name = Component.translatable(item.getDescriptionId());

        sendMessage(player, title, name, delayed);
    }

    public static void sendMessage(Player player, String title, Component replace, boolean delayed) {
        Component message = Component.translatable(title, replace, delayed);

        sendMessage(player, message, delayed);
    }

    private static void sendMessage(Player player, Component message, boolean delayed) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int delay = 0;

        if (delayed) {
            delay = 700;
        }

        executor.schedule(() -> player.sendSystemMessage(message), delay, TimeUnit.MILLISECONDS);
    }

}
