package survivalistessentials.data.loot;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import net.minecraftforge.registries.ForgeRegistries;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK, ModBlockLootTables::createLooseRockDrops);
        this.add(SurvivalistEssentialsWorld.ROCK_STONE_BLOCK, ModBlockLootTables::createLooseRockDrops);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
            .filter(block -> SurvivalistEssentials.MODID.equals(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace()))
            .collect(Collectors.toSet());
    }

    private static LootTable.Builder createLooseRockDrops(Block block) {
        return LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(SurvivalistEssentialsWorld.ROCK_STONE)));
        /*
         * Bonus Flint disabled
                .withPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FLINT).when(
                        LootItemRandomChanceCondition.randomChance(ConfigHandler.Server.flintFromLooseRocksChance())
                    )));
        */
    }

}
