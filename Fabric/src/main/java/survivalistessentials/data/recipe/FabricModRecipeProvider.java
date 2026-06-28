package survivalistessentials.data.recipe;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import survivalistessentials.SurvivalistEssentials;

public class FabricModRecipeProvider extends FabricRecipeProvider implements ISurvivalistEssentialsRecipeProvider {

    private InternalRecipeProvider internalRecipeProvider;

    public FabricModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryFuture) {
        super(output, registryFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
        internalRecipeProvider = new InternalRecipeProvider(provider, recipeOutput);

        return internalRecipeProvider;
    }

    @Override
    protected @NonNull Identifier getRecipeIdentifier(@NonNull Identifier identifier) {
        return identifier;
    }

    @Override
    public @NonNull String getName() {
        return SurvivalistEssentials.MOD_NAME + " - Fabric Recipes";
    }

    @Override
    public RecipeOutput modLoaded(RecipeOutput recipeOutput, String modid) {
        return withConditions(recipeOutput, ResourceConditions.allModsLoaded(modid));
    }

    @Override
    public RecipeOutput modNotLoaded(RecipeOutput recipeOutput, String modid) {
        return withConditions(recipeOutput, ResourceConditions.not(ResourceConditions.allModsLoaded(modid)));
    }

    @Override
    public RecipeOutput configResourceCondition(RecipeOutput recipeOutput, String configOption) {
        return withConditions(recipeOutput, new ConfigResourceCondition(configOption));
    }

    @Override
    public Criterion<TriggerInstance> _has(ItemLike itemLike) {
        return internalRecipeProvider.has(itemLike);
    }

    @Override
    public Criterion<TriggerInstance> _has(TagKey<Item> tag) {
        return internalRecipeProvider.has(tag);
    }

    private class InternalRecipeProvider extends RecipeProvider {

        RecipeOutput recipeOutput;
        HolderLookup.Provider registries;

        protected InternalRecipeProvider(Provider registries, RecipeOutput output) {
            super(registries, output);

            this.recipeOutput = output;
            this.registries = registries;
        }

        @Override
        public void buildRecipes() {
            buildModRecipes(registries, recipeOutput);
        }

    }

}
