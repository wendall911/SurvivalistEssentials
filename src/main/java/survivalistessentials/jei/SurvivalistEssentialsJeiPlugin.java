package survivalistessentials.jei;

import java.util.Collections;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;

import net.neoforged.fml.ModList;

import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

import static survivalistessentials.SurvivalistEssentials.loc;

@SuppressWarnings("unused")
@JeiPlugin
public class SurvivalistEssentialsJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return loc("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        addIngredientInfo(registry, SurvivalistEssentialsWorld.ROCK_STONE);
        addIngredientInfo(registry, SurvivalistEssentialsItems.PLANT_FIBER);
        addIngredientInfo(registry, SurvivalistEssentialsItems.FLINT_SHARD);
        addIngredientInfo(registry, Items.STICK);

        if (ModList.get().isLoaded(ModIntegration.TS_MODID)) {
            registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                Collections.singleton(new ItemStack(SurvivalistEssentialsItems.BASIC_SAW_BLADE)));
            registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                Collections.singleton(new ItemStack(SurvivalistEssentialsItems.SHARP_SAW_BLADE)));
            registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                Collections.singleton(new ItemStack(SurvivalistEssentialsItems.BASIC_SAW)));
            registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                Collections.singleton(new ItemStack(SurvivalistEssentialsItems.SHARP_SAW)));
            registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                Collections.singleton(new ItemStack(SurvivalistEssentialsItems.BASIC_KNIFE)));
            registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                Collections.singleton(new ItemStack(SurvivalistEssentialsItems.SHARP_KNIFE)));
        }
    }

    private void addIngredientInfo(IRecipeRegistration registry, Item item) {
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();

        registry.addIngredientInfo(
            new ItemStack(item),
            VanillaTypes.ITEM_STACK,
            Component.translatable("jei." + SurvivalistEssentials.MODID + ".description." + name)
        );
    }

}
