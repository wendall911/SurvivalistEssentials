package survivalistessentials.data;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import survivalistessentials.data.client.ModBlockStateProvider;
import survivalistessentials.data.client.ModItemModelProvider;
import survivalistessentials.data.client.patchouli.ModBookProvider;
import survivalistessentials.data.loot.ModLootTables;
import survivalistessentials.data.loot.GlobalLootModifier;
import survivalistessentials.data.overrides.BlockTagsOverrideProvider;
import survivalistessentials.data.recipes.ModRecipesProvider;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;
import survivalistessentials.world.modifier.LooseRockBiomeModifier;
import survivalistessentials.world.modifier.SurvivalistEssentialsBiomeModifiers;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.CONFIGURED_FEATURE, SurvivalistEssentialsFeatures::configuredBootstrap)
        .add(Registries.PLACED_FEATURE, SurvivalistEssentialsFeatures::placementBootstrap)
        .add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
            context.register(SurvivalistEssentialsBiomeModifiers.LOOSE_ROCKS_MODIFIER, new LooseRockBiomeModifier(
                    HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(SurvivalistEssentialsFeatures.PLACED_LOOSE_ROCKS)),
                    GenerationStep.Decoration.TOP_LAYER_MODIFICATION
            ));
        });

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper());
        String modpackOverrides = System.getenv("MOD_OVERRIDES");

        gen.addProvider(event.includeServer(), new ModItemModelProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput, event.getLookupProvider(), blockTags, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModRecipesProvider(packOutput));
        gen.addProvider(event.includeServer(), ModLootTables.create(packOutput));
        gen.addProvider(event.includeServer(), new GlobalLootModifier(packOutput));

        if (modpackOverrides != null && modpackOverrides.contains("all")) {
            gen.addProvider(event.includeServer(), new BlockTagsOverrideProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper()));
        }

        gen.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                packOutput,
                CompletableFuture.supplyAsync(DataGenerators::getProvider),
                Set.of("loose_rocks")
        ));

        gen.addProvider(event.includeServer(), new ModBookProvider(packOutput));
    }

    private static HolderLookup.Provider getProvider() {
        RegistryAccess.Frozen registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

        return BUILDER.buildPatch(registryAccess, VanillaRegistries.createLookup());
    }

}