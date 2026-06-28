package survivalistessentials.data.overrides;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import survivalistessentials.SurvivalistEssentials;

public class BlockTagsOverrideProvider extends BlockTagsProvider {

    public BlockTagsOverrideProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider, SurvivalistEssentials.MODID);
    }

    @Override
    public String getName() {
        return "SurvivalistEssentials - BlockTags Overrides";
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.tag(BlockTags.NEEDS_STONE_TOOL)
            .add(
                BlockItemIds.IRON_BLOCK.block(),
                BlockItemIds.RAW_IRON_BLOCK.block(),
                BlockItemIds.IRON_ORE.block(),
                BlockItemIds.DEEPSLATE_IRON_ORE.block(),
                BlockItemIds.LAPIS_BLOCK.block(),
                BlockItemIds.LAPIS_ORE.block(),
                BlockItemIds.DEEPSLATE_LAPIS_ORE.block(),
                BlockItemIds.RAW_COPPER_BLOCK.block(),
                BlockItemIds.COPPER_ORE.block(),
                BlockItemIds.DEEPSLATE_COPPER_ORE.block()
            )
            .addAll(toIds(BlockItemIds.COPPER_BLOCK))
            .addTag(BlockTags.LIGHTNING_RODS);
        this.tag(BlockTags.NEEDS_IRON_TOOL)
            .add(
                BlockItemIds.DIAMOND_BLOCK.block(),
                BlockItemIds.DIAMOND_ORE.block(),
                BlockItemIds.DEEPSLATE_DIAMOND_ORE.block(),
                BlockItemIds.EMERALD_ORE.block(),
                BlockItemIds.DEEPSLATE_EMERALD_ORE.block(),
                BlockItemIds.EMERALD_BLOCK.block(),
                BlockItemIds.GOLD_BLOCK.block(),
                BlockItemIds.RAW_GOLD_BLOCK.block(),
                BlockItemIds.GOLD_ORE.block(),
                BlockItemIds.DEEPSLATE_GOLD_ORE.block(),
                BlockItemIds.REDSTONE_ORE.block(),
                BlockItemIds.DEEPSLATE_REDSTONE_ORE.block()
            );
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(
                BlockItemIds.OBSIDIAN.block(),
                BlockItemIds.CRYING_OBSIDIAN.block(),
                BlockItemIds.NETHERITE_BLOCK.block(),
                BlockItemIds.RESPAWN_ANCHOR.block(),
                BlockItemIds.ANCIENT_DEBRIS.block()
            );
    }

    private static List<ResourceKey<Block>> toIds(WeatheringCopperCollection<BlockItemId> ids) {
        return ids.map(BlockItemId::block).asList();
    }

}
