package survivalistessentials.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public abstract class RecipeRemainderTieredItem extends Item implements IRecipeRemainder {

    public RecipeRemainderTieredItem(ToolMaterial toolMaterial, float speed, float damage, Properties properties) {
        super(toolMaterial.applyToolProperties(properties, BlockTags.MINEABLE_WITH_AXE, speed, damage, 0.0F));
    }

    public abstract ItemStack getRemainingItem(ItemStack stack);

    @Override
    public final ItemStack getRecipeRemainder(ItemStack itemStack) {
        return getRemainingItem(itemStack);
    }

    @Override
    public final ItemStack getCraftingRemainder(ItemStack itemStack) {
        return getRemainingItem(itemStack);
    }

}
