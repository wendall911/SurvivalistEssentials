package survivalistessentials.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class RecipeRemainderSwordItem extends Item implements IRecipeRemainder {

    public RecipeRemainderSwordItem(Item.Properties properties) {
        super(properties);
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
