package survivalistessentials.items.item;

import org.jspecify.annotations.NonNull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import survivalistessentials.items.RecipeRemainderItem;

public class Mortar extends RecipeRemainderItem {

    public Mortar(Item.Properties tabGroup) {
        super(tabGroup);
    }

    @NonNull
    @Override
    public ItemStack getRemainingItem(@NonNull ItemStack stack) {
        return stack.copy();
    }

}
