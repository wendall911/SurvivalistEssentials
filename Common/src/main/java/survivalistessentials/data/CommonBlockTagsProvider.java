package survivalistessentials.data;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.common.TagManager;
import survivalistessentials.util.ResourceLocationHelper;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class CommonBlockTagsProvider extends IntrinsicHolderTagsProvider<Block> {

    @SuppressWarnings("deprecation")
    public CommonBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, Registries.BLOCK, lookupProvider, (block) -> block.builtInRegistryHolder().key());
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        getOrCreateRawBuilder(TagManager.Blocks.ALWAYS_BREAKABLE)
            .addTag(TagManager.Blocks.LOOSE_ROCKS.location())
            .addElement(ResourceLocationHelper.getBlockId(Blocks.GRAVEL))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.BAMBOO))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.SNOW))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.GLOW_LICHEN))
            .addOptionalTag(BlockTags.DIRT.location())
            .addOptionalTag(BlockTags.SAND.location())
            .addOptionalTag(TagManager.Blocks.SANDS.location())
            .addOptionalTag(TagManager.Blocks.GRAVELS.location())
            .addOptionalTag(BlockTags.BEDS.location())
            .addOptionalTag(SurvivalistEssentialsIntegration.tconLoc("slimy_vines"))
            .addOptionalTag(SurvivalistEssentialsIntegration.tconLoc("slimy_leaves"))
            .addTag(TagManager.Blocks.FIBER_PLANTS.location());

        getOrCreateRawBuilder(TagManager.Blocks.ALWAYS_DROPS)
            .addTag(TagManager.Blocks.LOOSE_ROCKS.location())
            .addElement(ResourceLocationHelper.getBlockId(Blocks.GRAVEL))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.BAMBOO))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.SNOW))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.GLOW_LICHEN))
            .addOptionalTag(BlockTags.DIRT.location())
            .addOptionalTag(BlockTags.SAND.location())
            .addOptionalTag(BlockTags.BEDS.location())
            .addOptionalTag(TagManager.Blocks.SANDS.location())
            .addOptionalTag(TagManager.Blocks.GRAVELS.location())
            .addOptionalTag(SurvivalistEssentialsIntegration.tconLoc("slimy_vines"))
            .addOptionalTag(SurvivalistEssentialsIntegration.tconLoc("slimy_leaves"))
            .addTag(TagManager.Blocks.FIBER_PLANTS.location());

        getOrCreateRawBuilder(TagManager.Blocks.LOOSE_ROCK_PLACEABLE_ON)
            .addElement(ResourceLocationHelper.getBlockId(Blocks.GRAVEL))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.STONE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.CALCITE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.GRANITE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.DIORITE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.ANDESITE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.COAL_ORE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.SANDSTONE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.IRON_BLOCK))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.COPPER_ORE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.MOSSY_COBBLESTONE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.RED_SANDSTONE))
            .addOptionalTag(TagManager.Blocks.GRAVELS.location())
            .addOptionalTag(BlockTags.DIRT.location())
            .addOptionalTag(BlockTags.GRASS_BLOCKS.location())
            .addOptionalTag(BlockTags.SAND.location())
            .addOptionalTag(TagManager.Blocks.SANDS.location())
            .addOptionalTag(BlockTags.TERRACOTTA.location())
            .addOptionalTag(TagManager.Blocks.ORES_COAL.location())
            .addOptionalTag(TagManager.Blocks.ORES_COPPER.location())
            .addOptionalTag(TagManager.Blocks.ORES_IRON.location());

        this.tag(TagManager.Blocks.LOOSE_ROCKS)
            .add(SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK);

        getOrCreateRawBuilder(TagManager.Blocks.FIBER_PLANTS)
            .addElement(ResourceLocationHelper.getBlockId(Blocks.VINE))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.FERN))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.LARGE_FERN))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.GRASS_BLOCK))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.SHORT_GRASS))
            .addElement(ResourceLocationHelper.getBlockId(Blocks.TALL_GRASS))
            .addOptionalTag(BlockTags.LEAVES.location())
            .addOptionalElement(SurvivalistEssentialsIntegration.sgcLoc("avocado_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.exnihiloLoc("infested_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.exnihiloLoc("infesting_leaves"))
            .addOptionalTag(TagManager.commonLoc("grass"))
            .addOptionalTag(TagManager.commonLoc("leaves"))
            .addOptionalTag(TagManager.commonLoc("bushes"))
            .addOptionalTag(SurvivalistEssentialsIntegration.regionsLoc("grass"));

        getOrCreateRawBuilder(TagManager.Blocks.BRANCHES)
            .addOptionalTag(SurvivalistEssentialsIntegration.dynamictreesLoc("branches"));

        getOrCreateRawBuilder(TagManager.Blocks.MINEABLE_WITH_SHARP)
            .addElement(ResourceLocationHelper.getBlockId(Blocks.COBWEB))
            .addOptionalTag(BlockTags.WOOL_CARPETS.location())
            .addOptionalTag(BlockTags.WOOL.location())
            .addOptionalTag(BlockTags.CANDLE_CAKES.location());

        // Let's Do Vinery -- Missing Tags for Dark Cherry
        getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_AXE)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_wood"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("stripped_dark_cherry_wood"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("stripped_dark_cherry_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_planks"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_stairs"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_slab"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_pressure_plate"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_button"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_trapdoor"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_door"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_fence"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_fence_gate"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_beam"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_floorboard"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_sign"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_hanging_sign"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_big_table"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_shelf"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_drawer"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_cabinet"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_table"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_chair"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_barrel"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_wine_rack_small"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_wine_rack_mid"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_wine_rack_big"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_lattice"));
        getOrCreateRawBuilder(BlockTags.LEAVES)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("apple_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("grapevine_leaves"));
        getOrCreateRawBuilder(BlockTags.REPLACEABLE_BY_TREES)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("apple_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("grapevine_leaves"));
        getOrCreateRawBuilder(BlockTags.SWORD_EFFICIENT)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("apple_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("grapevine_leaves"));
        getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_HOE)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("apple_leaves"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("grapevine_leaves"));
    }

}
