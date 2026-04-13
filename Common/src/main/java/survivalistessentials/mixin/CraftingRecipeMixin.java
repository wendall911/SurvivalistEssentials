package survivalistessentials.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import survivalistessentials.items.item.Mortar;
import survivalistessentials.items.tool.SurvivalKnife;
import survivalistessentials.items.tool.SurvivalSaw;

@Mixin(CraftingRecipe.class)
public interface CraftingRecipeMixin {

    @Inject(method = "defaultCraftingReminder", at = @At("HEAD"), cancellable = true)
    private static void se$defaultCraftingRemainder(CraftingInput input, CallbackInfoReturnable<NonNullList<ItemStack>> cir) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        boolean hasRemainder = false;

        for (int slot = 0; slot < remaining.size(); slot++) {
            ItemStack stack = input.getItem(slot);

            if (!stack.isEmpty()) {
                ItemStackTemplate remainder = stack.getItem().getCraftingRemainder();

                if (remainder != null) {
                    remaining.set(slot, remainder.create());
                }
                else if (stack.getItem() instanceof SurvivalSaw saw) {
                    remaining.set(slot, saw.getRemainingItem(stack));
                    hasRemainder = true;
                }
                else if (stack.getItem() instanceof SurvivalKnife knife) {
                    remaining.set(slot, knife.getRemainingItem(stack));
                    hasRemainder = true;
                }
                else if (stack.getItem() instanceof Mortar) {
                    remaining.set(slot, stack.copy());
                    hasRemainder = true;
                }
            }
        }

        if (hasRemainder) {
            cir.setReturnValue(remaining);
        }
    }

}
