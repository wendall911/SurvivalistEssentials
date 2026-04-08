package survivalistessentials;

import java.util.List;
import java.util.function.BiConsumer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.TagManager.Blocks;
import survivalistessentials.common.loot.SurvivalistEssentialsLootConditionTypes;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.data.recipe.ConfigResourceCondition;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.sound.SurvivalistEssentialsSounds;
import survivalistessentials.util.LootConditionHelper;
import survivalistessentials.world.SurvivalistEssentialsWorld;
import survivalistessentials.world.effect.SurvivalistEssentialsEffects;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;

public class SurvivalistEssentialsFabric implements ModInitializer {

    @SuppressWarnings("deprecation")
    @Override
    public void onInitialize() {
        /*
         * Ok, why in the fuck do people like Fabric? They do some clever shit like LootTableEvents.MODIFY, BUT!!!!
         * Fucking tags aren't loaded. So you 1. Have to know the loot table you are targeting. OR 2. Guess what
         * block the loot table might represent. I mean GlobalLootModifierProvider in NeoForge is like a million
         * times better and wasn't created by people with their heads rammed up their asses. Why does this shit even
         * exist in this broken ass state? I do want to support mods on Fabric, but shit like this is just busted.
         *
         * This is likely because of registry loading order in Minecraft, but there are ways to deal with this in a
         * reasonable way. Reference: NeoForge
         *
         * To top it off, there are discussions like this:
         * https://github.com/orgs/FabricMC/discussions/3415 where the solution is basically doing broken dumb shit.
         * I could probably re-implement GlobalLootModifierProvider in Fabric, but then I'm not modding.
         *
         * I hope one day soon, Fabric and NeoForge have a baby, and we get an actually not clusterfucked solution. And
         * hosting providers stop taking payoffs from Lex to keep pushing Forge on servers. The fucking stupidity needs
         * to end at some point. I mean there are good things in Fabric, but this dumbassery is just perplexing.
         *
         * This is literally pre-1.12 oredict (aka tags) all over again.
         * I already have to deal with people not tagging things correctly, or
         * at all, but that's a minor inconvenience compared to entire mod loaders that don't do the right thing.
         */
        List<String> fiberPlantBlocks = List.of(
            "fern",
            "grass",
            "leaves"
        );

        registryInit();
        SurvivalistEssentials.init();
        SurvivalistEssentialsFeatures.setup();

        ServerLevelEvents.LOAD.register((level, world) -> {
            HarvestBlock.setup();
        });

        ServerEntityEvents.EQUIPMENT_CHANGE.register((entity, slot, from, to) -> {
            if (entity instanceof Player player) {
                survivalistessentials.event.EquipmentChangeHandler.handleChange(player, slot, to);
            }
        });

        LootTableEvents.MODIFY.register(((resourceKey, builder, lootTableSource, provider) -> {
            String pathString = resourceKey.identifier().getPath();

            if (fiberPlantBlocks.stream().anyMatch(pathString::contains)) {
                HolderLookup.RegistryLookup<Item> itemRegistryLookup = provider.lookupOrThrow(Registries.ITEM);

                builder.withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(SurvivalistEssentialsItems.PLANT_FIBER))
                    // No damn idea how to do this in the "Fabric" way, since their documentation is garbage. Deprecated, but whatever.
                    // I'll just add the mixin back if they remove it. This is hot garbage anyhow.
                    .when(List.of(LootConditionHelper.createKnifeChanceCondition(0.16F, Blocks.FIBER_PLANTS, itemRegistryLookup)))
                );
                if (pathString.contains("leaves")) {
                    HolderLookup.RegistryLookup<Enchantment> holderLookup = provider.lookupOrThrow(Registries.ENCHANTMENT);
                    HolderLookup.RegistryLookup<EntityType<?>> entityRegistryLookup = provider.lookupOrThrow(Registries.ENTITY_TYPE);

                    builder.withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STICK))
                        .when(List.of(LootConditionHelper.createKnifeChanceCondition(0.16F, BlockTags.LEAVES, itemRegistryLookup)))
                    );
                    builder.withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STICK))
                        .when(List.of(LootConditionHelper.createExtraStickDropConditions(0.16F, BlockTags.LEAVES, holderLookup, itemRegistryLookup, entityRegistryLookup)))
                    );
                }
            }
        }));
    }

    /*
     * Note: The order of these initializations can be important due to dependencies between registries
     * for example, items that depend on blocks being registered first.
     *
     * Unlike NeoForge, Fabric does not have a built-in deferred registry system, so we manually ensure
     * that all registrations occur during mod initialization in the correct order.
     */
    private void registryInit() {
        SurvivalistEssentialsFeatures.init(bind(BuiltInRegistries.FEATURE));
        SurvivalistEssentialsEffects.init(bind(BuiltInRegistries.MOB_EFFECT));
        SurvivalistEssentialsIntegration.init(bind(BuiltInRegistries.ITEM));
        SurvivalistEssentialsItems.init(bind(BuiltInRegistries.ITEM));
        SurvivalistEssentialsWorld.initBlocks(bind(BuiltInRegistries.BLOCK));
        SurvivalistEssentialsWorld.initItems(bind(BuiltInRegistries.ITEM));
        SurvivalistEssentialsLootConditionTypes.init(bind(BuiltInRegistries.LOOT_CONDITION_TYPE));
        SurvivalistEssentialsSounds.init(bind(BuiltInRegistries.SOUND_EVENT));

        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            Decoration.TOP_LAYER_MODIFICATION,
            SurvivalistEssentialsFeatures.PLACED_LOOSE_ROCKS_KEY
        );

        ConfigResourceCondition.register();
    }

    private static <T> BiConsumer<T, Identifier> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }

}
