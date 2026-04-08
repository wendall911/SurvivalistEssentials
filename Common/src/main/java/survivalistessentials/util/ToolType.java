package survivalistessentials.util;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jetbrains.annotations.Nullable;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import survivalistessentials.common.TagManager;

/**
 * This represents a primary tool that must be present on a given block
 * Blocks can be added to any number of tags to determine their validity with such a tool
 * However, all blocks have a fallback 'tool type' which is known to NTP
 * This is because when we check if a block is harvestable by an item, we have no way of knowing if that block is harvestable by *any* item.
 */
public enum ToolType {

    PICKAXE(TagManager.Items.PICKAXE_TOOLS),
    AXE(TagManager.Items.AXE_TOOLS),
    SHOVEL(TagManager.Items.SHOVEL_TOOLS),
    HOE(TagManager.Items.HOE_TOOLS),
    SHARP(TagManager.Items.SHARP_TOOLS),
    NONE(null);

    @Nullable
    private final TagKey<Item> tag;

    ToolType(@Nullable TagKey<Item> tag) {
        this.tag = tag;
    }

    public boolean is(Item item) {
        if (this.tag == null) {
            return false;
        }

        ItemStack stack = new ItemStack(item);
        AtomicBoolean hasKey = new AtomicBoolean(false);

        stack.tags().takeWhile((TagKey<Item> n) -> !hasKey.get())
            .filter(tagKey -> tag == tagKey).map(tagKey -> true).forEach(hasKey::set);

        return hasKey.get();
    }

}
