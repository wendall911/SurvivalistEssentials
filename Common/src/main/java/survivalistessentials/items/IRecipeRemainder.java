package survivalistessentials.items;

import net.minecraft.world.item.ItemStack;

public interface IRecipeRemainder {

    /*
     * This is used to override the Fabric ItemStack aware getCraftingRemainder()
     * See public interface FabricItem
     */
    ItemStack getRecipeRemainder(ItemStack stack);

    /*
     * This is used to override the NeoForge ItemStack aware getCraftingRemainder()
     * For some reason this is now marked as deprecated in NeoForge, not sure why
     * See public interface IItemExtension in NeoForge
     */
    ItemStack getCraftingRemainder(ItemStack stack);

}
