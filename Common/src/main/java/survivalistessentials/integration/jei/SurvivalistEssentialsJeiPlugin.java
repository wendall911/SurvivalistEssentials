package survivalistessentials.integration.jei;

import java.util.Collections;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.platform.Services;
import survivalistessentials.world.SurvivalistEssentialsWorld;

import static survivalistessentials.util.ResourceLocationHelper.prefix;

@SuppressWarnings("unused")
@JeiPlugin
public class SurvivalistEssentialsJeiPlugin implements IModPlugin {

    @Override
    public @NonNull Identifier getPluginUid() {
        return prefix("jei_plugin");
    }

    @Override
    public void registerRecipes(@NonNull IRecipeRegistration registry) {
        addIngredientInfo(registry, SurvivalistEssentialsWorld.ROCK_STONE);
        addIngredientInfo(registry, SurvivalistEssentialsItems.PLANT_FIBER);
        addIngredientInfo(registry, SurvivalistEssentialsItems.FLINT_SHARD);
        addIngredientInfo(registry, Items.STICK);

        if (Services.WN_PLATFORM.isModLoaded(SurvivalistEssentialsIntegration.TS_MODID)) {
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

        if (ConfigHandler.Common.disableModpackBook()) {
            registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                Collections.singleton(new ItemStack(SurvivalistEssentialsItems.MODPACK_BOOK)));
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
