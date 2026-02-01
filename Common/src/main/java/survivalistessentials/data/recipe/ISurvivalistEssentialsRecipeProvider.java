package survivalistessentials.data.recipe;

import org.jetbrains.annotations.NotNull;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import survivalistessentials.common.TagManager;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;

import static survivalistessentials.util.ResourceLocationHelper.loc;
import static survivalistessentials.util.ResourceLocationHelper.prefix;

public interface ISurvivalistEssentialsRecipeProvider {

    RecipeOutput modLoaded(RecipeOutput recipeOutput, String modid);

    RecipeOutput modNotLoaded(RecipeOutput recipeOutput, String modid);

    RecipeOutput configResourceCondition(RecipeOutput recipeOutput, String configOption);

    Criterion<InventoryChangeTrigger.TriggerInstance> _has(ItemLike itemLike);

    Criterion<InventoryChangeTrigger.TriggerInstance> _has(TagKey<Item> tag);

    default void buildModRecipes(HolderLookup.Provider registries, @NotNull RecipeOutput recipeOutput) {
        HolderLookup.RegistryLookup<Item> itemRegistry = registries.lookupOrThrow(Registries.ITEM);
        ItemLike rockStone = SurvivalistEssentialsWorld.ROCK_STONE;
        ItemLike flintShard = SurvivalistEssentialsItems.FLINT_SHARD;
        ItemLike plantFiber = SurvivalistEssentialsItems.PLANT_FIBER;
        ItemLike plantString = SurvivalistEssentialsItems.PLANT_STRING;
        ItemLike mortar = SurvivalistEssentialsItems.MORTAR_AND_PESTLE;
        ItemLike plantPaste = SurvivalistEssentialsItems.PLANT_PASTE;
        ItemLike ointment = SurvivalistEssentialsItems.OINTMENT;
        ItemLike cloth = SurvivalistEssentialsItems.CLOTH;
        ItemLike crudeKnife = SurvivalistEssentialsItems.CRUDE_KNIFE;
        RecipeOutput wrapped;

        // Material Recipes
        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLESTONE)
            .define('R', rockStone)
            .pattern("RR")
            .pattern("RR")
            .unlockedBy("has_loose_rock", _has(rockStone))
            .save(recipeOutput, prefix("cobblestone_from_rocks").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, rockStone, 4)
            .requires(Blocks.COBBLESTONE)
            .unlockedBy("has_cobblestone", _has(Blocks.COBBLESTONE))
            .save(recipeOutput, prefix("rocks_from_cobblestone").toString());

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.DECORATIONS, Items.FLINT)
            .define('S', flintShard)
            .pattern("SS")
            .pattern("SS")
            .unlockedBy("has_flint_shard", _has(flintShard))
            .save(recipeOutput, prefix("flint_from_shards").toString());

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.MISC, plantString)
            .define('F', plantFiber)
            .pattern("FF")
            .pattern("F ")
            .unlockedBy("has_plant_fiber", _has(plantFiber))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.MISC, plantPaste)
            .define('F', plantFiber)
            .define('U', mortar)
            .pattern("F")
            .pattern("U")
            .unlockedBy("has_plant_fiber", _has(plantFiber))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.MISC, ointment)
            .define('P', plantPaste)
            .pattern("PP")
            .pattern("PP")
            .unlockedBy("has_plant_paste", _has(plantPaste))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.MISC, cloth)
            .define('S', TagManager.Items.STRINGS)
            .pattern("SSS")
            .unlockedBy("has_string", _has(TagManager.Items.STRINGS))
            .save(recipeOutput);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(plantString), RecipeCategory.MISC, Items.STRING, 0.1F, 50)
            .unlockedBy("has_plant_string", _has(plantString))
            .save(recipeOutput, prefix("string_from_plant_string").toString());

        // Add condition for recipes to hide stuff if TinkerSurvival is loaded
        wrapped = modNotLoaded(recipeOutput, SurvivalistEssentialsIntegration.TS_MODID);

        // Saw Blades
        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_SAW_BLADE)
            .define('D', flintShard)
            .define('S', plantString)
            .define('I', Items.STICK)
            .pattern("ID")
            .pattern("SD")
            .unlockedBy("has_plant_string", _has(plantString))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_SAW_BLADE)
            .define('D', Items.IRON_INGOT)
            .define('S', TagManager.Items.STRINGS)
            .define('I', Items.STICK)
            .pattern("ID")
            .pattern("SD")
            .unlockedBy("has_iron_ingot", _has(Items.IRON_INGOT))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_SAW_BLADE)
            .define('D', Items.DIAMOND)
            .define('S', TagManager.Items.STRINGS)
            .define('I', Items.STICK)
            .pattern("ID")
            .pattern("SD")
            .unlockedBy("has_diamond", _has(Items.DIAMOND))
            .save(recipeOutput);

        // Tool Recipes
        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, crudeKnife)
            .define('S', flintShard)
            .define('T', Items.STICK)
            .pattern("S")
            .pattern("T")
            .unlockedBy("has_flint_shard", _has(flintShard))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_KNIFE)
            .define('I', Items.IRON_INGOT)
            .define('S', Items.STICK)
            .define('X', TagManager.Items.STRINGS)
            .pattern("IX")
            .pattern(" S")
            .unlockedBy("has_iron_ingot", _has(Items.IRON_INGOT))
            .save(wrapped);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_KNIFE)
            .define('D', Items.DIAMOND)
            .define('S', Items.STICK)
            .define('X', TagManager.Items.STRINGS)
            .pattern("DX")
            .pattern(" S")
            .unlockedBy("has_diamond", _has(Items.DIAMOND))
            .save(wrapped);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_HATCHET)
            .define('R', rockStone)
            .define('S', plantString)
            .define('I', Items.STICK)
            .pattern("SR")
            .pattern("I ")
            .unlockedBy("has_loose_rock", _has(rockStone))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.SAW_HANDLE)
            .define('S', plantString)
            .define('I', Items.STICK)
            .pattern("IS")
            .pattern(" I")
            .group("saw_handles")
            .unlockedBy("has_plant_string", _has(plantString))
            .save(recipeOutput, prefix("saw_handle_with_plant_string").toString());
        
        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.SAW_HANDLE)
            .define('S', Items.STRING)
            .define('I', Items.STICK)
            .pattern("IS")
            .pattern(" I")
            .group("saw_handles")
            .unlockedBy("has_string", _has(Items.STRING))
            .save(recipeOutput, prefix("saw_handle_with_string").toString());

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_SAW)
            .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
            .define('B', SurvivalistEssentialsItems.CRUDE_SAW_BLADE)
            .define('S', plantString)
            .pattern("BS")
            .pattern(" H")
            .unlockedBy("has_crude_saw_handle", _has(SurvivalistEssentialsItems.SAW_HANDLE))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_SAW)
            .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
            .define('B', SurvivalistEssentialsItems.BASIC_SAW_BLADE)
            .define('S', TagManager.Items.STRINGS)
            .pattern("BS")
            .pattern(" H")
            .unlockedBy("has_iron_ingot", _has(Items.IRON_INGOT))
            .save(wrapped);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_SAW)
            .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
            .define('B', SurvivalistEssentialsItems.SHARP_SAW_BLADE)
            .define('S', TagManager.Items.STRINGS)
            .pattern("BS")
            .pattern(" H")
            .unlockedBy("has_diamond", _has(Items.DIAMOND))
            .save(wrapped);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.TOOLS, mortar)
            .define('I', Items.STICK)
            .define('P', ItemTags.PLANKS)
            .define('R', rockStone)
            .pattern("  I")
            .pattern("PRP")
            .pattern(" P ")
            .unlockedBy("has_plant_fiber", _has(plantFiber))
            .save(recipeOutput);

        // Knife Recipes
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, Items.STICK)
            .requires(ItemTags.SAPLINGS)
            .requires(TagManager.Items.KNIFE_TOOLS)
            .group("sticks")
            .unlockedBy("has_sapling", _has(ItemTags.SAPLINGS))
            .save(recipeOutput, prefix("stick_from_sapling").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, Items.STRING, 2)
            .requires(ItemTags.WOOL)
            .requires(crudeKnife)
            .group("string")
            .unlockedBy("has_wool", _has(ItemTags.WOOL))
            .save(recipeOutput, prefix("string_from_wool").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, Items.STRING, 4)
            .requires(ItemTags.WOOL)
            .requires(TagManager.Items.ADVANCED_KNIFE_TOOLS)
            .group("string")
            .unlockedBy("has_wool", _has(ItemTags.WOOL))
            .save(recipeOutput, prefix("string_from_wool_advanced").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, flintShard, 2)
            .requires(SurvivalistEssentialsWorld.ROCK_STONE)
            .requires(TagManager.Items.KNIFE_TOOLS)
            .group("flint_shards")
            .unlockedBy("has_crude_knife", _has(crudeKnife))
            .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, flintShard, 4)
            .requires(Items.FLINT)
            .requires(TagManager.Items.KNIFE_TOOLS)
            .group("flint_shards")
            .unlockedBy("has_crude_knife", _has(crudeKnife))
            .save(recipeOutput, prefix("flint_shards_from_flint").toString());

        //Bandages
        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.MISC, SurvivalistEssentialsItems.CRUDE_BANDAGE)
            .define('P', plantString)
            .define('S', Items.STICK)
            .define('F', plantFiber)
            .pattern("SF")
            .pattern("PF")
            .unlockedBy("has_plant_string", _has(plantString))
            .save(recipeOutput);

        ShapedRecipeBuilder.shaped(itemRegistry, RecipeCategory.MISC, SurvivalistEssentialsItems.BANDAGE)
            .define('P', plantString)
            .define('S', Items.STICK)
            .define('C', cloth)
            .define('O', ointment)
            .pattern("SC")
            .pattern("PO")
            .unlockedBy("has_ointment", _has(ointment))
            .save(recipeOutput);

        // Book
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, SurvivalistEssentialsItems.BOOK)
            .requires(SurvivalistEssentialsWorld.ROCK_STONE)
            .group("books")
            .unlockedBy("has_loose_rock", _has(rockStone))
            .save(recipeOutput, prefix("book_from_rocks").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, SurvivalistEssentialsWorld.ROCK_STONE)
            .requires(SurvivalistEssentialsItems.BOOK)
            .group("books")
            .unlockedBy("has_intro_book", _has(SurvivalistEssentialsItems.BOOK))
            .save(recipeOutput, prefix("rock_from_book").toString());

        // Modpack Book
        wrapped = configResourceCondition(recipeOutput, "disableModpackBook");
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, SurvivalistEssentialsItems.MODPACK_BOOK)
            .requires(SurvivalistEssentialsWorld.ROCK_STONE)
            .requires(SurvivalistEssentialsWorld.ROCK_STONE)
            .group("books")
            .unlockedBy("has_loose_rock", _has(rockStone))
            .save(wrapped, prefix("modpack_book_from_rocks").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, SurvivalistEssentialsWorld.ROCK_STONE, 2)
            .requires(SurvivalistEssentialsItems.MODPACK_BOOK)
            .group("books")
            .unlockedBy("has_modpack_book", _has(SurvivalistEssentialsItems.MODPACK_BOOK))
            .save(wrapped, prefix("rocks_from_modpack_book").toString());

        // Saw Recipes
        // Minecraft
        plankRecipeBuilder(recipeOutput, Blocks.OAK_PLANKS, ItemTags.OAK_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, "has_log", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, "has_log", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.WARPED_PLANKS, ItemTags.WARPED_STEMS, "has_logs", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, "has_logs", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.CHERRY_PLANKS, ItemTags.CHERRY_LOGS, "has_logs", itemRegistry);
        bambooRecipeBuilder(recipeOutput, Blocks.BAMBOO_PLANKS, ItemTags.BAMBOO_BLOCKS, "has_bamboo_block", itemRegistry);
        plankRecipeBuilder(recipeOutput, Blocks.PALE_OAK_PLANKS, ItemTags.PALE_OAK_LOGS, "has_logs", itemRegistry);

        // Fruit Trees
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.FT_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.CHERRY_PLANKS, TagManager.Items.CHERRY_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.CITRUS_PLANKS, TagManager.Items.CITRUS_LOGS, "has_logs", itemRegistry);

        // Biome Makeover
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_ANCIENT_OAK_LOG, "has_logs", "wood/ancient_oak/", "ancient_oak_planks", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_LOG, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_stripped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_ANCIENT_OAK_WOOD, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_wood", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_WOOD, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_wood_stripped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_BLIGHTED_BALSA_LOG, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_LOG, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_stripped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_BLIGHTED_BALSA_WOOD, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_wood", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_WOOD, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_wood_stipped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_SWAMP_CYPRESS_LOG, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_LOG, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_stripped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_SWAMP_CYPRESS_WOOD, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_wood", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_WOOD, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_wood_stripped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_WILLOW_LOG, "has_logs", "wood/willow/", "willow_planks", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_STRIPPED_WILLOW_LOG, "has_logs", "wood/willow/", "willow_planks_stripped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_WILLOW_WOOD, "has_logs", "wood/willow/", "willow_planks_wood", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_STRIPPED_WILLOW_WOOD, "has_logs", "wood/willow/", "willow_planks_wood_stripped", SurvivalistEssentialsIntegration.BMO_MODID, itemRegistry);

        //Biomes O' Plenty
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.BOP_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_DEAD_PLANKS, TagManager.Items.BOP_DEAD_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_FIR_PLANKS, TagManager.Items.BOP_FIR_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_HELLBARK_PLANKS, TagManager.Items.BOP_HELLBARK_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_JACARANDA_PLANKS, TagManager.Items.BOP_JACARANDA_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_MAGIC_PLANKS, TagManager.Items.BOP_MAGIC_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_MAHOGANY_PLANKS, TagManager.Items.BOP_MAHOGANY_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_PALM_PLANKS, TagManager.Items.BOP_PALM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_REDWOOD_PLANKS, TagManager.Items.BOP_REDWOOD_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_UMBRAN_PLANKS, TagManager.Items.BOP_UMBRAN_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOP_WILLOW_PLANKS, TagManager.Items.BOP_WILLOW_LOGS, "has_logs", itemRegistry);

        //Quark
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.QUARK_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.QUARK_AZALEA_PLANKS, TagManager.Items.QUARK_AZALEA_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.QUARK_BLOSSOM_PLANKS, TagManager.Items.QUARK_BLOSSOM_LOGS, "has_logs", itemRegistry);

        //All You Can Eat
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.AYCE_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.AYCE_HAZEL_PLANKS, TagManager.Items.AYCE_HAZEL_LOGS, "has_logs", itemRegistry);

        // Tinkers' Construct
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.TCON_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TCON_BLOODSHROOM_PLANKS, TagManager.Items.TCON_BLOODSHROOM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TCON_GREENHEART_PLANKS, TagManager.Items.TCON_GREENHEART_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TCON_SKYROOT_PLANKS, TagManager.Items.TCON_SKYROOT_LOGS, "has_logs", itemRegistry);

        // Water Source
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.WS_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.WS_PALM_TREE_PLANKS, TagManager.Items.WS_PALM_TREE_LOGS, "has_logs", itemRegistry);

        // Botania
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.BOTANIA_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOTANIA_DREAMWOOD_PLANKS, TagManager.Items.BOTANIA_DREAMWOOD_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BOTANIA_LIVINGWOOD_PLANKS, TagManager.Items.BOTANIA_LIVINGWOOD_LOGS, "has_logs", itemRegistry);

        // Ars Nouveau
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.AN_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.AN_ARCHWOOD_PLANKS, TagManager.Items.ARCHWOOD_LOGS, "has_logs", itemRegistry);

        // Undergarden
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.UNDERGARDEN_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.UNDERGARDEN_GRONGLE_PLANKS, TagManager.Items.UNDERGARDEN_GRONGLE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.UNDERGARDEN_SMOGSTEM_PLANKS, TagManager.Items.UNDERGARDEN_SMOGSTEM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.UNDERGARDEN_WIGGLEWOOD_PLANKS, TagManager.Items.UNDERGARDEN_WIGGLEWOOD_LOGS, "has_logs", itemRegistry);

        // BYG
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.BYG_MODID);

        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_WHITE_MANGROVE_PLANKS, TagManager.Items.BYG_WHITE_MANGROVE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_REDWOOD_PLANKS, TagManager.Items.BYG_REDWOOD_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_BLUE_ENCHANTED_PLANKS, TagManager.Items.BYG_BLUE_ENCHANTED_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_GREEN_ENCHANTED_PLANKS, TagManager.Items.BYG_GREEN_ENCHANTED_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_MAHOGANY_PLANKS, TagManager.Items.BYG_MAHOGANY_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_BAOBAB_PLANKS, TagManager.Items.BYG_BAOBAB_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_JACARANDA_PLANKS, TagManager.Items.BYG_JACARANDA_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_CYPRESS_PLANKS, TagManager.Items.BYG_CYPRESS_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_PALM_PLANKS, TagManager.Items.BYG_PALM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_EBONY_PLANKS, TagManager.Items.BYG_EBONY_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_RAINBOW_EUCALYPTUS_PLANKS, TagManager.Items.BYG_RAINBOW_EUCALYPTUS_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_ASPEN_PLANKS, TagManager.Items.BYG_ASPEN_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_FIR_PLANKS, TagManager.Items.BYG_FIR_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_SKYRIS_PLANKS, TagManager.Items.BYG_SKYRIS_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_CIKA_PLANKS, TagManager.Items.BYG_CIKA_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_HOLLY_PLANKS, TagManager.Items.BYG_HOLLY_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_MAPLE_PLANKS, TagManager.Items.BYG_MAPLE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_PINE_PLANKS, TagManager.Items.BYG_PINE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_WILLOW_PLANKS, TagManager.Items.BYG_WILLOW_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_WITCH_HAZEL_PLANKS, TagManager.Items.BYG_WITCH_HAZEL_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_ZELKOVA_PLANKS, TagManager.Items.BYG_ZELKOVA_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_IRONWOOD_PLANKS, TagManager.Items.BYG_IRONWOOD_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_SAKURA_PLANKS, TagManager.Items.BYG_SAKURA_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_PALO_VERDE_PLANKS, TagManager.Items.BYG_PALO_VERDE_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_SPIRIT_PLANKS, TagManager.Items.BYG_SPIRIT_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.BYG_FLORUS_STEM, TagManager.Items.BYG_FLORUS_STEMS, "has_logs", itemRegistry);

        // Twilight Forest
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_LOG, "has_logs", "wood/", "canopy_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_STRIPPED_LOG, "has_logs", "wood/", "canopy_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_WOOD, "has_logs", "wood/", "canopy_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_STRIPPED_WOOD, "has_logs", "wood/", "canopy_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_LOG, "has_logs", "wood/", "darkwood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_STRIPPED_LOG, "has_logs", "wood/", "darkwood_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_WOOD, "has_logs", "wood/", "darkwood_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_STRIPPED_WOOD, "has_logs", "wood/", "darkwood_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_LOG, "has_logs", "wood/", "mangrove_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_STRIPPED_LOG, "has_logs", "wood/", "mangrove_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_WOOD, "has_logs", "wood/", "mangrove_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_STRIPPED_WOOD, "has_logs", "wood/", "mangrove_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_LOG, "has_logs", "wood/", "mining_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_STRIPPED_LOG, "has_logs", "wood/", "mining_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_WOOD, "has_logs", "wood/", "mining_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_STRIPPED_WOOD, "has_logs", "wood/", "mining_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_LOG, "has_logs", "wood/", "sorting_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_STRIPPED_LOG, "has_logs", "wood/", "sorting_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_WOOD, "has_logs", "wood/", "sorting_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_STRIPPED_WOOD, "has_logs", "wood/", "sorting_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_LOG, "has_logs", "wood/", "time_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_STRIPPED_LOG, "has_logs", "wood/", "time_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_WOOD, "has_logs", "wood/", "time_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_STRIPPED_WOOD, "has_logs", "wood/", "time_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_LOG, "has_logs", "wood/", "transformation_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_STRIPPED_LOG, "has_logs", "wood/", "transformation_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_WOOD, "has_logs", "wood/", "transformation_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_STRIPPED_WOOD, "has_logs", "wood/", "transformation_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_LOG, "has_logs", "wood/", "twilight_oak_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_LOG, "has_logs", "wood/", "twilight_oak_from_stripped_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_WOOD, "has_logs", "wood/", "twilight_oak_from_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);
        itemPlankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_WOOD, "has_logs", "wood/", "twilight_oak_from_stripped_wood_planks", SurvivalistEssentialsIntegration.TF_MODID, itemRegistry);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 64)
            .requires(TagManager.Items.TF_GIANT_LOGS)
            .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
            .group("planks")
            .unlockedBy("has_advanced_saw", _has(TagManager.Items.ADVANCED_SAW_TOOLS))
            .save(wrapped, loc(SurvivalistEssentialsIntegration.TF_MODID, "giant_log_to_oak_planks").toString());

        // Aquaculture
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.AQUA_MODID);
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 2)
            .requires(SurvivalistEssentialsIntegration.AQUA_DRIFTWOOD)
            .requires(SurvivalistEssentialsItems.CRUDE_SAW)
            .group("planks")
            .unlockedBy("has_driftwood", _has(TagManager.Items.AQUA_DRIFTWOOD))
            .save(wrapped, loc(SurvivalistEssentialsIntegration.AQUA_MODID, "planks_from_driftwood").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 4)
            .requires(SurvivalistEssentialsIntegration.AQUA_DRIFTWOOD)
            .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
            .group("planks")
            .unlockedBy("has_driftwood", _has(TagManager.Items.AQUA_DRIFTWOOD))
            .save(wrapped, prefix("planks_from_driftwood").toString());

        // Immersive Engineering
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.IE_MODID);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, SurvivalistEssentialsIntegration.IE_STICK_TREATED, 2)
            .requires(TagManager.Items.IE_TREATED_WOOD)
            .requires(SurvivalistEssentialsItems.CRUDE_SAW)
            .group("treated_sticks")
            .unlockedBy("has_treated_planks", _has(TagManager.Items.IE_TREATED_WOOD))
            .save(wrapped, prefix("stick_treated").toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, SurvivalistEssentialsIntegration.IE_STICK_TREATED, 4)
            .requires(TagManager.Items.IE_TREATED_WOOD)
            .requires(TagManager.Items.IE_TREATED_WOOD)
            .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
            .group("treated_sticks")
            .unlockedBy("has_treated_planks", _has(TagManager.Items.IE_TREATED_WOOD))
            .save(wrapped, loc(SurvivalistEssentialsIntegration.IE_MODID, "crafting/stick_treated").toString());

        // Ecologics
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.ECO_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.ECO_COCONUT_PLANKS, TagManager.Items.ECO_COCONUT_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.ECO_WALNUT_PLANKS, TagManager.Items.ECO_WALNUT_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.ECO_AZALEA_PLANKS, TagManager.Items.ECO_AZALEA_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.ECO_FLOWERING_AZALEA_PLANKS, TagManager.Items.ECO_FLOWERING_AZALEA_LOGS, "has_logs", itemRegistry);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, Items.STICK, 2)
            .requires(ItemTags.PLANKS)
            .requires(TagManager.Items.SAW_TOOLS)
            .group("sticks")
            .unlockedBy("has_planks", _has(ItemTags.PLANKS))
            .save(recipeOutput);

        // Malum
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.MALUM_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.MALUM_RUNEWOOD_PLANKS, TagManager.Items.MALUM_RUNEWOOD_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.MALUM_SOULWOOD_PLANKS, TagManager.Items.MALUM_SOULWOOD_LOGS, "has_logs", itemRegistry);

        // Ice and Fire; Dragons
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.IFD_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.IFD_DREADWOOD_PLANKS, TagManager.Items.IFD_DREADWOOD_LOGS, "has_logs", itemRegistry);

        // Regions Unexplored
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.REGIONS_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_ALPHA_PLANKS, TagManager.Items.RU_ALPHA_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_ACACIA_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_acacia_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BAOBAB_PLANKS, TagManager.Items.RU_BAOBAB_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BAOBAB_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_baobab_branch", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BIRCH_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_birch_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BLACKWOOD_PLANKS, TagManager.Items.RU_BLACKWOOD_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BLACKWOOD_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_blackwood_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BLUE_BIOSHROOM_PLANKS, TagManager.Items.RU_BLUE_BIOSHROOM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BIOSHROOM_PLANKS, TagManager.Items.RU_BIOSHROOM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BRIMWOOD_PLANKS, TagManager.Items.RU_BRIMWOOD_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_BRIMWOOD_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_brimwood_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_COBALT_PLANKS, TagManager.Items.RU_COBALT_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_COBALT_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_cobalt_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_CYPRESS_PLANKS, TagManager.Items.RU_CYPRESS_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_CYPRESS_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_cypress_branch", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_DARK_OAK_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_dark_oak_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_DEAD_PLANKS, TagManager.Items.RU_DEAD_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_DEAD_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_dead_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_EUCALYPTUS_PLANKS, TagManager.Items.RU_EUCALYPTUS_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_EUCALYPTUS_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_eucalyptus_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_GREEN_BIOSHROOM_PLANKS, TagManager.Items.RU_GREEN_BIOSHROOM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_JOSHUA_PLANKS, TagManager.Items.RU_JOSHUA_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_JOSHUA_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_joshua_branch", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_JUNGLE_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_jungle_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_KAPOK_PLANKS, TagManager.Items.RU_KAPOK_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_KAPOK_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_kapok_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_LARCH_PLANKS, TagManager.Items.RU_LARCH_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_LARCH_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_larch_branch", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_MANGROVE_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_mangrove_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_MAGNOLIA_PLANKS, TagManager.Items.RU_MAGNOLIA_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_MAGNOLIA_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_magnolia_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_MAPLE_PLANKS, TagManager.Items.RU_MAPLE_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_MAPLE_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_maple_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_MAUVE_PLANKS, TagManager.Items.RU_MAUVE_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_MAUVE_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_mauve_branch", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_OAK_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_oak_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_PALM_PLANKS, TagManager.Items.RU_PALM_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_PALM_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_palm_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_PINE_PLANKS, TagManager.Items.RU_PINE_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_PINE_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_pine_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_PINK_BIOSHROOM_PLANKS, TagManager.Items.RU_PINK_BIOSHROOM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_REDWOOD_PLANKS, TagManager.Items.RU_REDWOOD_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_REDWOOD_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_redwood_branch", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_SILVER_BIRCH_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_silver_birch_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_SOCOTRA_PLANKS, TagManager.Items.RU_SOCOTRA_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_SOCOTRA_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_socotra_branch", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_SPRUCE_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_spruce_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_WILLOW_PLANKS, TagManager.Items.RU_WILLOW_LOGS, "has_logs", itemRegistry);
        sticksFromBranchRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_WILLOW_BRANCH, SurvivalistEssentialsIntegration.REGIONS_MODID, "", "stick_from_willow_branch", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.RU_YELLOW_BIOSHROOM_PLANKS, TagManager.Items.RU_YELLOW_BIOSHROOM_LOGS, "has_logs", itemRegistry);

        // Let's Do
        // Beach
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.LD_BEACH_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_PALM_PLANKS, TagManager.Items.LD_PALM_LOGS, "has_logs", itemRegistry);
        // Blooming Nature
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.LD_BLOOMING_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_LARCH_PLANKS, TagManager.Items.LD_LARCH_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_BAOBAB_PLANKS, TagManager.Items.LD_BAOBAB_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_ASPEN_PLANKS, TagManager.Items.LD_ASPEN_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_SWAMP_OAK_PLANKS, TagManager.Items.LD_SWAMP_OAK_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_SWAMP_CYPRESS_PLANKS, TagManager.Items.LD_SWAMP_CYPRESS_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_FAN_PALM_PLANKS, TagManager.Items.LD_FAN_PALM_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_EBONY_PLANKS, TagManager.Items.LD_EBONY_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_CHESTNUT_PLANKS, TagManager.Items.LD_CHESTNUT_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_FIR_PLANKS, TagManager.Items.LD_FIR_LOGS, "has_logs", itemRegistry);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_CACTUS_PLANKS, TagManager.Items.LD_CACTUS, "has_cactus", itemRegistry);
        // Meadow
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.LD_MEADOW_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_PINE_PLANKS, TagManager.Items.LD_PINE_LOGS, "has_logs", itemRegistry);
        // Vinery
        wrapped = modLoaded(recipeOutput, SurvivalistEssentialsIntegration.LD_VINERY_MODID);
        plankRecipeBuilder(wrapped, SurvivalistEssentialsIntegration.LD_DARK_CHERRY_PLANKS, TagManager.Items.LD_DARK_CHERRY_LOGS, "has_logs", itemRegistry);
    }

    default void sticksFromBranchRecipeBuilder(RecipeOutput recipeOutput, ItemLike item, String modid, String folder, String name, RegistryLookup<Item> itemRegistry) {
        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, Items.STICK, 2)
            .requires(item)
            .requires(SurvivalistEssentialsItems.CRUDE_SAW)
            .group("sticks")
            .unlockedBy("has_branch", _has(item))
            .save(recipeOutput, loc(modid, folder + name).toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.MISC, Items.STICK, 4)
            .requires(item)
            .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
            .group("sticks")
            .unlockedBy("has_branch", _has(item))
            .save(recipeOutput, prefix(modid + "_" + name).toString());
    }

    default void plankRecipeBuilder(RecipeOutput recipeOutput, ItemLike item, TagKey<Item> itemTag, String label, RegistryLookup<Item> itemRegistry) {
        ShapelessRecipeBuilder plankOverrideRecipe = ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, item, 2)
            .requires(itemTag)
            .requires(SurvivalistEssentialsItems.CRUDE_SAW)
            .group("planks")
            .unlockedBy(label, _has(itemTag));

        Identifier itemLoc = BuiltInRegistries.ITEM.getKey(item.asItem());
        String name = itemLoc.getPath();
        String modid = itemLoc.getNamespace();

        if (modid.contains(SurvivalistEssentialsIntegration.TCON_MODID)) {
            plankOverrideRecipe.save(recipeOutput, loc(SurvivalistEssentialsIntegration.TCON_MODID, "world/wood/" + name.split("_")[0] + "/planks").toString());
        } else if (modid.contains(SurvivalistEssentialsIntegration.QUARK_MODID)) {
            plankOverrideRecipe.save(recipeOutput, loc(SurvivalistEssentialsIntegration.QUARK_MODID, "world/crafting/woodsets/" + name.split("_")[0] + "/planks").toString());
        } else if (modid.contains(SurvivalistEssentialsIntegration.IFD_MODID)) {
            plankOverrideRecipe.save(recipeOutput, loc(SurvivalistEssentialsIntegration.IFD_MODID, "dread_wood_planks").toString());
        } else if (itemTag.equals(TagManager.Items.BYG_PALO_VERDE_LOGS)) {
            plankOverrideRecipe.save(recipeOutput, loc(SurvivalistEssentialsIntegration.BYG_MODID, "birch_planks_from_palo_verde_logs").toString());
            modid = SurvivalistEssentialsIntegration.BYG_MODID;
        } else {
            plankOverrideRecipe.save(recipeOutput);
        }

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, item, 4)
            .requires(itemTag)
            .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
            .group("planks")
            .unlockedBy(label, _has(TagManager.Items.ADVANCED_SAW_TOOLS))
            .save(recipeOutput, prefix(modid + "_" + name).toString());
    }

    default void itemPlankRecipeBuilder(RecipeOutput recipeOutput, ItemLike output, TagKey<Item> input, String label, String path, String name, String modid, RegistryLookup<Item> itemRegistry) {
        ShapelessRecipeBuilder plankOverrideRecipe = ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, output, 2)
            .requires(input)
            .requires(SurvivalistEssentialsItems.CRUDE_SAW)
            .group("planks")
            .unlockedBy(label, _has(input));

        plankOverrideRecipe.save(recipeOutput, loc(modid, path + name).toString());

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, output, 4)
            .requires(input)
            .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
            .group("planks")
            .unlockedBy(label, _has(TagManager.Items.ADVANCED_SAW_TOOLS))
            .save(recipeOutput, prefix(modid + "_" + name).toString());
    }

    default void bambooRecipeBuilder(RecipeOutput recipeOutput, ItemLike item, TagKey<Item> itemTag, String label, RegistryLookup<Item> itemRegistry) {
        ShapelessRecipeBuilder plankOverrideRecipe = ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, item, 1)
            .requires(itemTag)
            .requires(SurvivalistEssentialsItems.CRUDE_SAW)
            .group("planks")
            .unlockedBy(label, _has(itemTag));

        Identifier itemLoc = BuiltInRegistries.ITEM.getKey(item.asItem());
        String name = itemLoc.getPath();
        String modid = itemLoc.getNamespace();

        plankOverrideRecipe.save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(itemRegistry, RecipeCategory.BUILDING_BLOCKS, item, 2)
            .requires(itemTag)
            .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
            .group("planks")
            .unlockedBy(label, _has(TagManager.Items.ADVANCED_SAW_TOOLS))
            .save(recipeOutput, prefix(modid + "_" + name).toString());
    }

    default void smokedLeatherRecipeBuilder(RecipeOutput recipeOutput, ItemLike item, String name) {
        SimpleCookingRecipeBuilder.smoking(
                Ingredient.of(item),
                RecipeCategory.FOOD,
                Items.LEATHER,
                0.35F,
                100
            )
            .unlockedBy("has_cooked_meat", _has(TagManager.Items.COOKED_MEAT))
            .save(recipeOutput, prefix("leather_from_" + name + "_smoking").toString());
    }

}
