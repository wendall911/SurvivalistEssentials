package survivalistessentials.data.loot;

import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.mixin.BlockLootSubProviderAccessor;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModBlockLootTables extends BlockLootSubProvider {

    protected ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
        this.add(SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
    }

    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
            .filter(block -> SurvivalistEssentials.MODID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace()))
            .collect(Collectors.toSet());
    }

    private static LootTable.Builder createLooseRockDrops(Block block) {
        return LootTable.lootTable().withPool(
            LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(SurvivalistEssentialsWorld.ROCK_STONE)));
    }

    /*
     * Implements overrride similar to NeoForge's BlockLootSubProvider to only output loot tables for this mods blocks.
     */
    @Override
    public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, Builder> output) {
        this.generate();
        Set<ResourceKey<LootTable>> set = new HashSet<>();
        BlockLootSubProviderAccessor accessor = (BlockLootSubProviderAccessor) this;

        for(Block block : getKnownBlocks()) {
            if (block.isEnabled(accessor.getEnabledFeatures())) {
                Optional<ResourceKey<LootTable>> resourcekey = block.getLootTable();

                if (resourcekey.isPresent() && set.add(resourcekey.get())) {
                    LootTable.Builder loottable$builder = accessor.getMap().remove(resourcekey.get());
                    if (loottable$builder == null) {
                        throw new IllegalStateException(
                            String.format(
                                Locale.ROOT,
                                "Missing loottable '%s' for '%s'",
                                resourcekey.get().identifier(),
                                BuiltInRegistries.BLOCK.getKey(block)
                            )
                        );
                    }

                    output.accept(resourcekey.get(), loottable$builder);
                }
            }
        }

        if (!accessor.getMap().isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + accessor.getMap().keySet());
        }
    }


}
