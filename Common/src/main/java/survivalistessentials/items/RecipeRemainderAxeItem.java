package survivalistessentials.items;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public abstract class RecipeRemainderAxeItem extends AxeItem implements IRecipeRemainder {

    public RecipeRemainderAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, attackDamage, attackSpeed, properties);
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
