package survivalistessentials.data.loot;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.TagManager;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.util.LootConditionHelper;

public class GlobalLootModifier extends GlobalLootModifierProvider {

    public GlobalLootModifier(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider, SurvivalistEssentials.MODID);
    }

    @Override
    public @NonNull String getName() {
        return "SurvivalistEssentials - Global Loot Modifier";
    }

    @Override
    protected void start() {
        addPlantFiberDrops(TagManager.Blocks.FIBER_PLANTS, "fiber_plants");
        addStickDrops(BlockTags.LEAVES, "leaves");
    }

    public void addPlantFiberDrops(TagKey<Block> tag, String name) {
        HolderLookup.RegistryLookup<Item> itemRegistryLookup = this.registries.lookupOrThrow(Registries.ITEM);

        this.add(
            "plant_fiber_from_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                LootConditionHelper.createKnifeChanceCondition(0.16F, tag, itemRegistryLookup),
                1000,
                new ItemStackTemplate(SurvivalistEssentialsItems.PLANT_FIBER)
            )
        );
    }

    public void addStickDrops(TagKey<Block> tag, String name) {
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        HolderLookup.RegistryLookup<Item> itemRegistryLookup = this.registries.lookupOrThrow(Registries.ITEM);
        HolderLookup.RegistryLookup<EntityType<?>> entityRegistryLookup = this.registries.lookupOrThrow(Registries.ENTITY_TYPE);

        this.add(
            "stick_drops_from_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                LootConditionHelper.createKnifeChanceCondition(0.16F, tag, itemRegistryLookup),
                1000,
                new ItemStackTemplate(Items.STICK)
            )
        );

        this.add(
            "extra_stick_drops_from_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                LootConditionHelper.createExtraStickDropConditions(0.16F, tag, enchantmentRegistryLookup, itemRegistryLookup, entityRegistryLookup),
                1000,
                new ItemStackTemplate(Items.STICK)
            )
        );
    }


}
