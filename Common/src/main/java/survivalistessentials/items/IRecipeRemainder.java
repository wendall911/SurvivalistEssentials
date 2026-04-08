package survivalistessentials.items;

import net.minecraft.world.item.ItemStack;

public interface IRecipeRemainder {

    /*
     * This is used to override the Fabric ItemStack aware getCraftingRemainder()
     * See public interface FabricItem
     */
    ItemStack getRecipeRemainder(ItemStack stack);

}
