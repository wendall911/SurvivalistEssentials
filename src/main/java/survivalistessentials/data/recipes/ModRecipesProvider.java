package survivalistessentials.data.recipes;

import java.util.Objects;
import java.util.function.Consumer;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ItemLike;

import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;

import survivalistessentials.common.TagManager;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.ItemUse;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipesProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ItemLike rockStone = SurvivalistEssentialsWorld.ROCK_STONE;
        ItemLike flintShard = SurvivalistEssentialsItems.FLINT_SHARD;
        ItemLike plantFiber = SurvivalistEssentialsItems.PLANT_FIBER;
        ItemLike plantString = SurvivalistEssentialsItems.PLANT_STRING;
        ItemLike mortar = SurvivalistEssentialsItems.MORTAR_AND_PESTLE;
        ItemLike plantPaste = SurvivalistEssentialsItems.PLANT_PASTE;
        ItemLike ointment = SurvivalistEssentialsItems.OINTMENT;
        ItemLike cloth = SurvivalistEssentialsItems.CLOTH;
        ItemLike crudeKnife = SurvivalistEssentialsItems.CRUDE_KNIFE;
        Consumer<FinishedRecipe> wrapped;

        // Material Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLESTONE)
                .define('R', rockStone)
                .pattern("RR")
                .pattern("RR")
                .unlockedBy("has_loose_rock", has(rockStone))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "cobblestone_from_rocks"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, rockStone, 4)
                .requires(Blocks.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "rocks_from_cobblestone"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.FLINT)
                .define('S', flintShard)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_flint_shard", has(flintShard))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "flint_from_shards"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, plantString)
                .define('F', plantFiber)
                .pattern("FF")
                .pattern("F ")
                .unlockedBy("has_plant_fiber", has(plantFiber))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, plantPaste)
                .define('F', plantFiber)
                .define('U', mortar)
                .pattern("F")
                .pattern("U")
                .unlockedBy("has_plant_fiber", has(plantFiber))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ointment)
                .define('P', plantPaste)
                .pattern("PP")
                .pattern("PP")
                .unlockedBy("has_plant_paste", has(plantPaste))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, cloth)
                .define('S', Tags.Items.STRING)
                .pattern("SSS")
                .unlockedBy("has_string", has(Tags.Items.STRING))
                .save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(plantString), RecipeCategory.MISC, Items.STRING, 0.1F, 50)
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "string_from_plant_string"));


        // Saw Blades
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_SAW_BLADE)
                .define('D', flintShard)
                .define('S', plantString)
                .define('I', Items.STICK)
                .pattern("ID")
                .pattern("SD")
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_SAW_BLADE)
                .define('D', Items.IRON_INGOT)
                .define('S', Tags.Items.STRING)
                .define('I', Items.STICK)
                .pattern("ID")
                .pattern("SD")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_SAW_BLADE)
                .define('D', Items.DIAMOND)
                .define('S', Tags.Items.STRING)
                .define('I', Items.STICK)
                .pattern("ID")
                .pattern("SD")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);
        
        // Tool Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, crudeKnife)
                .define('S', flintShard)
                .define('T', Items.STICK)
                .pattern("S")
                .pattern("T")
                .unlockedBy("has_flint_shard", has(flintShard))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_KNIFE)
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .define('X', Tags.Items.STRING)
                .pattern("IX")
                .pattern(" S")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_KNIFE)
                .define('D', Items.DIAMOND)
                .define('S', Items.STICK)
                .define('X', Tags.Items.STRING)
                .pattern("DX")
                .pattern(" S")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_HATCHET)
                .define('R', rockStone)
                .define('S', plantString)
                .define('I', Items.STICK)
                .pattern("SR")
                .pattern("I ")
                .unlockedBy("has_loose_rock", has(rockStone))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SAW_HANDLE)
                .define('S', plantString)
                .define('I', Items.STICK)
                .pattern("IS")
                .pattern(" I")
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_SAW)
                .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
                .define('B', SurvivalistEssentialsItems.CRUDE_SAW_BLADE)
                .define('S', plantString)
                .pattern("BS")
                .pattern(" H")
                .unlockedBy("has_crude_saw_handle", has(SurvivalistEssentialsItems.SAW_HANDLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_SAW)
                .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
                .define('B', SurvivalistEssentialsItems.BASIC_SAW_BLADE)
                .define('S', Tags.Items.STRING)
                .pattern("BS")
                .pattern(" H")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_SAW)
                .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
                .define('B', SurvivalistEssentialsItems.SHARP_SAW_BLADE)
                .define('S', Tags.Items.STRING)
                .pattern("BS")
                .pattern(" H")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, mortar)
                .define('I', Items.STICK)
                .define('P', ItemTags.PLANKS)
                .define('R', rockStone)
                .pattern("  I")
                .pattern("PRP")
                .pattern(" P ")
                .unlockedBy("has_plant_fiber", has(plantFiber))
                .save(consumer);

        // Knife Recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STICK)
                .requires(ItemTags.SAPLINGS)
                .requires(TagManager.Items.KNIFE_TOOLS)
                .group("sticks")
                .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "stick_from_sapling"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STRING, 2)
                .requires(ItemTags.WOOL)
                .requires(crudeKnife)
                .group("string")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "string_from_wool"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STRING, 4)
                .requires(ItemTags.WOOL)
                .requires(TagManager.Items.ADVANCED_KNIFE_TOOLS)
                .group("string")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "string_from_wool_advanced"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, flintShard, 2)
                .requires(TagManager.Items.FLINT_KNAPPABLE)
                .requires(TagManager.Items.KNIFE_TOOLS)
                .group("flint_shards")
                .unlockedBy("has_crude_knife", has(crudeKnife))
                .save(consumer);

        //Bandages
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SurvivalistEssentialsItems.CRUDE_BANDAGE)
                .define('P', plantString)
                .define('S', Items.STICK)
                .define('F', plantFiber)
                .pattern("SF")
                .pattern("PF")
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SurvivalistEssentialsItems.BANDAGE)
                .define('P', plantString)
                .define('S', Items.STICK)
                .define('C', cloth)
                .define('O', ointment)
                .pattern("SC")
                .pattern("PO")
                .unlockedBy("has_ointment", has(ointment))
                .save(consumer);

        // Leather from Smoking
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(TagManager.Items.COOKED_MEAT), RecipeCategory.FOOD, Items.LEATHER, 0.35F, 100)
            .unlockedBy("has_cooked_meat", has(TagManager.Items.COOKED_MEAT))
            .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, "leather_from_cooked_meat_smoking"));

        // Book
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.PATCHOULI_MODID));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SurvivalistEssentialsItems.BOOK)
            .requires(Items.DIRT)
            .requires(Items.DIRT)
            .group("books")
            .unlockedBy("has_loose_rock", has(rockStone))
            .save(wrapped, new ResourceLocation(SurvivalistEssentials.MODID, "book_from_dirt"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.DIRT, 2)
            .requires(SurvivalistEssentialsItems.BOOK)
            .group("books")
            .unlockedBy("has_intro_book", has(SurvivalistEssentialsItems.BOOK))
            .save(wrapped, new ResourceLocation(SurvivalistEssentials.MODID, "dirt_from_book"));

        // Saw Recipes
        // Minecraft
        plankRecipeBuilder(consumer, Blocks.OAK_PLANKS, ItemTags.OAK_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, "has_log");
        plankRecipeBuilder(consumer, Blocks.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, "has_log");
        plankRecipeBuilder(consumer, Blocks.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.WARPED_PLANKS, ItemTags.WARPED_STEMS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.CHERRY_PLANKS, ItemTags.CHERRY_LOGS, "has_logs");
        bambooRecipeBuilder(consumer, Blocks.BAMBOO_PLANKS, ItemTags.BAMBOO_BLOCKS, "has_bamboo_block");

        // Fruit Trees
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.FT_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.CHERRY_PLANKS, TagManager.Items.CHERRY_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.CITRUS_PLANKS, TagManager.Items.CITRUS_LOGS, "has_logs");

        // Biome Makeover
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.BMO_MODID));
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_ANCIENT_OAK_LOG, "has_logs", "wood/ancient_oak/", "ancient_oak_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_LOG, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_ANCIENT_OAK_WOOD, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_WOOD, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_wood_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_BLIGHTED_BALSA_LOG, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_LOG, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_BLIGHTED_BALSA_WOOD, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_WOOD, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_wood_stipped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_SWAMP_CYPRESS_LOG, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_LOG, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_SWAMP_CYPRESS_WOOD, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_WOOD, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_wood_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_WILLOW_LOG, "has_logs", "wood/willow/", "willow_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_STRIPPED_WILLOW_LOG, "has_logs", "wood/willow/", "willow_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_WILLOW_WOOD, "has_logs", "wood/willow/", "willow_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_STRIPPED_WILLOW_WOOD, "has_logs", "wood/willow/", "willow_planks_wood_stripped", ModIntegration.BMO_MODID);

        //Biomes O' Plenty
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.BOP_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.BOP_DEAD_PLANKS, TagManager.Items.BOP_DEAD_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_FIR_PLANKS, TagManager.Items.BOP_FIR_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_HELLBARK_PLANKS, TagManager.Items.BOP_HELLBARK_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_JACARANDA_PLANKS, TagManager.Items.BOP_JACARANDA_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_MAGIC_PLANKS, TagManager.Items.BOP_MAGIC_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_MAHOGANY_PLANKS, TagManager.Items.BOP_MAHOGANY_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_PALM_PLANKS, TagManager.Items.BOP_PALM_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_REDWOOD_PLANKS, TagManager.Items.BOP_REDWOOD_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_UMBRAN_PLANKS, TagManager.Items.BOP_UMBRAN_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOP_WILLOW_PLANKS, TagManager.Items.BOP_WILLOW_LOGS, "has_logs");

        //Quark
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.QUARK_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.QUARK_AZALEA_PLANKS, TagManager.Items.QUARK_AZALEA_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.QUARK_BLOSSOM_PLANKS, TagManager.Items.QUARK_BLOSSOM_LOGS, "has_logs");

        //All You Can Eat
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.AYCE_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.AYCE_HAZEL_PLANKS, TagManager.Items.AYCE_HAZEL_LOGS, "has_logs");

        // Tinkers' Construct
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.TCON_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.TCON_BLOODSHROOM_PLANKS, TagManager.Items.TCON_BLOODSHROOM_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.TCON_GREENHEART_PLANKS, TagManager.Items.TCON_GREENHEART_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.TCON_SKYROOT_PLANKS, TagManager.Items.TCON_SKYROOT_LOGS, "has_logs");

        // Water Source
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.WS_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.WS_PALM_TREE_PLANKS, TagManager.Items.WS_PALM_TREE_LOGS, "has_logs");

        // Botania
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.BOTANIA_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.BOTANIA_DREAMWOOD_PLANKS, TagManager.Items.BOTANIA_DREAMWOOD_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BOTANIA_LIVINGWOOD_PLANKS, TagManager.Items.BOTANIA_LIVINGWOOD_LOGS, "has_logs");

        // Ars Nouveau
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.AN_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.AN_ARCHWOOD_PLANKS, ItemTags.create(new ResourceLocation("forge:logs/archwood")), "has_logs");

        // Undergarden
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.UNDERGARDEN_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.UNDERGARDEN_GRONGLE_PLANKS, TagManager.Items.UNDERGARDEN_GRONGLE_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.UNDERGARDEN_SMOGSTEM_PLANKS, TagManager.Items.UNDERGARDEN_SMOGSTEM_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.UNDERGARDEN_WIGGLEWOOD_PLANKS, TagManager.Items.UNDERGARDEN_WIGGLEWOOD_LOGS, "has_logs");

        // BYG
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.BYG_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.BYG_ETHER_PLANKS, TagManager.Items.BYG_ETHER_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_WHITE_MANGROVE_PLANKS, TagManager.Items.BYG_WHITE_MANGROVE_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_REDWOOD_PLANKS, TagManager.Items.BYG_REDWOOD_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_BLUE_ENCHANTED_PLANKS, TagManager.Items.BYG_BLUE_ENCHANTED_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_GREEN_ENCHANTED_PLANKS, TagManager.Items.BYG_GREEN_ENCHANTED_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_LAMENT_PLANKS, TagManager.Items.BYG_LAMENT_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_MAHOGANY_PLANKS, TagManager.Items.BYG_MAHOGANY_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_CHERRY_PLANKS, TagManager.Items.BYG_CHERRY_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_BAOBAB_PLANKS, TagManager.Items.BYG_BAOBAB_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_JACARANDA_PLANKS, TagManager.Items.BYG_JACARANDA_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_CYPRESS_PLANKS, TagManager.Items.BYG_CYPRESS_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_PALM_PLANKS, TagManager.Items.BYG_PALM_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_EBONY_PLANKS, TagManager.Items.BYG_EBONY_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_NIGHTSHADE_PLANKS, TagManager.Items.BYG_NIGHTSHADE_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_RAINBOW_EUCALYPTUS_PLANKS, TagManager.Items.BYG_RAINBOW_EUCALYPTUS_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_ASPEN_PLANKS, TagManager.Items.BYG_ASPEN_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_FIR_PLANKS, TagManager.Items.BYG_FIR_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_SKYRIS_PLANKS, TagManager.Items.BYG_SKYRIS_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_CIKA_PLANKS, TagManager.Items.BYG_CIKA_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_HOLLY_PLANKS, TagManager.Items.BYG_HOLLY_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_MAPLE_PLANKS, TagManager.Items.BYG_MAPLE_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_PINE_PLANKS, TagManager.Items.BYG_PINE_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_WILLOW_PLANKS, TagManager.Items.BYG_WILLOW_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_WITCH_HAZEL_PLANKS, TagManager.Items.BYG_WITCH_HAZEL_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_ZELKOVA_PLANKS, TagManager.Items.BYG_ZELKOVA_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_EMBUR_PLANKS, TagManager.Items.BYG_EMBUR_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_SYTHIAN_PLANKS, TagManager.Items.BYG_SYTHIAN_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_IMPARIUS_PLANKS, TagManager.Items.BYG_IMPARIUS_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.BYG_BULBIS_PLANKS, TagManager.Items.BYG_BULBIS_LOGS, "has_logs");

        // Twilight Forest
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.TF_MODID));
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_LOG, "has_logs", "wood/", "canopy_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_STRIPPED_LOG, "has_logs", "wood/", "canopy_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_WOOD, "has_logs", "wood/", "canopy_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_STRIPPED_WOOD, "has_logs", "wood/", "canopy_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_LOG, "has_logs", "wood/", "darkwood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_STRIPPED_LOG, "has_logs", "wood/", "darkwood_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_WOOD, "has_logs", "wood/", "darkwood_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_STRIPPED_WOOD, "has_logs", "wood/", "darkwood_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_LOG, "has_logs", "wood/", "mangrove_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_STRIPPED_LOG, "has_logs", "wood/", "mangrove_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_WOOD, "has_logs", "wood/", "mangrove_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_STRIPPED_WOOD, "has_logs", "wood/", "mangrove_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_LOG, "has_logs", "wood/", "mining_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_STRIPPED_LOG, "has_logs", "wood/", "mining_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_WOOD, "has_logs", "wood/", "mining_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_STRIPPED_WOOD, "has_logs", "wood/", "mining_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_LOG, "has_logs", "wood/", "sorting_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_STRIPPED_LOG, "has_logs", "wood/", "sorting_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_WOOD, "has_logs", "wood/", "sorting_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_STRIPPED_WOOD, "has_logs", "wood/", "sorting_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_LOG, "has_logs", "wood/", "time_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_STRIPPED_LOG, "has_logs", "wood/", "time_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_WOOD, "has_logs", "wood/", "time_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_STRIPPED_WOOD, "has_logs", "wood/", "time_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_LOG, "has_logs", "wood/", "transformation_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_STRIPPED_LOG, "has_logs", "wood/", "transformation_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_WOOD, "has_logs", "wood/", "transformation_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_STRIPPED_WOOD, "has_logs", "wood/", "transformation_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_LOG, "has_logs", "wood/", "twilight_oak_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_LOG, "has_logs", "wood/", "twilight_oak_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_WOOD, "has_logs", "wood/", "twilight_oak_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(wrapped, ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_WOOD, "has_logs", "wood/", "twilight_oak_from_stripped_wood_planks", ModIntegration.TF_MODID);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 64)
                .requires(TagManager.Items.TF_GIANT_LOGS)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy("has_advanced_saw", has(TagManager.Items.ADVANCED_SAW_TOOLS))
                .save(wrapped, new ResourceLocation(ModIntegration.TF_MODID, "giant_log_to_oak_planks"));

        // Aquaculture
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.AQUA_MODID));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 2)
                .requires(ModIntegration.AQUA_DRIFTWOOD)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy("has_driftwood", has(ModIntegration.AQUA_DRIFTWOOD))
                .save(wrapped, new ResourceLocation(ModIntegration.AQUA_MODID, "planks_from_driftwood"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 4)
                .requires(ModIntegration.AQUA_DRIFTWOOD)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy("has_driftwood", has(ModIntegration.AQUA_DRIFTWOOD))
                .save(wrapped, new ResourceLocation(SurvivalistEssentials.MODID, "planks_from_driftwood"));

        // Immersive Engineering
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.IE_MODID));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModIntegration.IE_STICK_TREATED, 2)
                .requires(TagManager.Items.IE_TREATED_WOOD)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("treated_sticks")
                .unlockedBy("has_treated_planks", has(TagManager.Items.IE_TREATED_WOOD))
                .save(wrapped, new ResourceLocation(SurvivalistEssentials.MODID, "stick_treated"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModIntegration.IE_STICK_TREATED, 4)
                .requires(TagManager.Items.IE_TREATED_WOOD)
                .requires(TagManager.Items.IE_TREATED_WOOD)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("treated_sticks")
                .unlockedBy("has_treated_planks", has(TagManager.Items.IE_TREATED_WOOD))
                .save(wrapped, new ResourceLocation(ModIntegration.IE_MODID, "crafting/stick_treated"));

        // Ecologics
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.ECO_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.ECO_COCONUT_PLANKS, TagManager.Items.ECO_COCONUT_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.ECO_WALNUT_PLANKS, TagManager.Items.ECO_WALNUT_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.ECO_AZALEA_PLANKS, TagManager.Items.ECO_AZALEA_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.ECO_FLOWERING_AZALEA_PLANKS, TagManager.Items.ECO_FLOWERING_AZALEA_LOGS, "has_logs");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STICK, 2)
                .requires(ItemTags.PLANKS)
                .requires(TagManager.Items.SAW_TOOLS)
                .group("sticks")
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(consumer);

        // Malum
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.MALUM_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.MALUM_RUNEWOOD_PLANKS, TagManager.Items.MALUM_RUNEWOOD_LOGS, "has_logs");
        plankRecipeBuilder(wrapped, ModIntegration.MALUM_SOULWOOD_PLANKS, TagManager.Items.MALUM_SOULWOOD_LOGS, "has_logs");

        // Ice and Fire; Dragons
        wrapped = withCondition(consumer, new ModLoadedCondition(ModIntegration.IFD_MODID));
        plankRecipeBuilder(wrapped, ModIntegration.IFD_DREADWOOD_PLANKS, TagManager.Items.IFD_DREADWOOD_LOGS, "has_logs");
    }

    private static void plankRecipeBuilder(Consumer<FinishedRecipe> consumer, ItemLike item, TagKey<Item> itemTag, String label) {
        ShapelessRecipeBuilder plankOverrideRecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 2)
                .requires(itemTag)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy(label, has(itemTag));

        String name = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem())).getPath();
        String modid = ItemUse.getModId(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem())).toString());

        if (modid.contains(ModIntegration.TCON_MODID)) {
            plankOverrideRecipe.save(consumer, new ResourceLocation(ModIntegration.TCON_MODID, "world/wood/" + name.split("_")[0] + "/planks"));
        }
        else if (modid.contains(ModIntegration.QUARK_MODID)) {
            plankOverrideRecipe.save(consumer, new ResourceLocation(ModIntegration.QUARK_MODID, "world/crafting/woodsets/" + name.split("_")[0] + "/planks"));
        }
        else if (modid.contains(ModIntegration.MALUM_MODID)) {
            plankOverrideRecipe.save(consumer, new ResourceLocation(ModIntegration.MALUM_MODID, name));
        }
        else if (modid.contains(ModIntegration.IFD_MODID)) {
            plankOverrideRecipe.save(consumer, new ResourceLocation(ModIntegration.IFD_MODID, "dread_wood_planks"));
        }
        else {
            plankOverrideRecipe.save(consumer);
        }

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 4)
                .requires(itemTag)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy(label, has(TagManager.Items.ADVANCED_SAW_TOOLS))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, modid + "_" + name));
    }

    private static void itemPlankRecipeBuilder(Consumer<FinishedRecipe> consumer, ItemLike output, TagKey<Item> input, String label, String path, String name, String modid) {
        ShapelessRecipeBuilder plankOverrideRecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 2)
                .requires(input)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy(label, has(input));

        plankOverrideRecipe.save(consumer, new ResourceLocation(modid, path + name));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .requires(input)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy(label, has(TagManager.Items.ADVANCED_SAW_TOOLS))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, modid + "_" + name));
    }

    private static void bambooRecipeBuilder(Consumer<FinishedRecipe> consumer, ItemLike item, TagKey<Item> itemTag, String label) {
        ShapelessRecipeBuilder plankOverrideRecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 1)
                .requires(itemTag)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy(label, has(itemTag));

        String name = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem())).getPath();
        String modid = ItemUse.getModId(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem())).toString());

        plankOverrideRecipe.save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 2)
                .requires(itemTag)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy(label, has(TagManager.Items.ADVANCED_SAW_TOOLS))
                .save(consumer, new ResourceLocation(SurvivalistEssentials.MODID, modid + "_" + name));
    }

    private static Consumer<FinishedRecipe> withCondition(Consumer<FinishedRecipe> consumer, ICondition... conditions) {
        ConsumerWrapperBuilder builder = ConsumerWrapperBuilder.wrap();

        for (ICondition condition : conditions) {
            builder.addCondition(condition);
        }

        return builder.build(consumer);
    }

}
