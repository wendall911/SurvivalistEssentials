package survivalistessentials.world.feature;

import java.util.List;
import java.util.function.BiConsumer;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import static survivalistessentials.SurvivalistEssentials.loc;

public final class SurvivalistEssentialsFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> LOOSE_ROCKS_KEY = createFeatureKey("loose_rocks");
    public static final ResourceLocation LOOSE_ROCKS_ID = loc("loose_rocks");
    public static final Feature<NoneFeatureConfiguration> LOOSE_ROCKS_FEATURE = new LooseRocks();
    public static final ResourceKey<PlacedFeature> PLACED_LOOSE_ROCKS_KEY = createPlacementKey("loose_rocks");

    public SurvivalistEssentialsFeatures() {}

    public static void setup() {}

    public static void init(BiConsumer<Feature<?>, ResourceLocation> consumer) {
        consumer.accept(LOOSE_ROCKS_FEATURE, LOOSE_ROCKS_ID);
    }

    public static void configuredBootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, SurvivalistEssentialsFeatures.LOOSE_ROCKS_KEY, SurvivalistEssentialsFeatures.LOOSE_ROCKS_FEATURE, NoneFeatureConfiguration.INSTANCE);
    }

    public static void placementBootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        final Holder<ConfiguredFeature<?, ?>> LOOSE_ROCKS_HOLDER = configuredFeatureGetter.getOrThrow(SurvivalistEssentialsFeatures.LOOSE_ROCKS_KEY);

        register(
                context,
                SurvivalistEssentialsFeatures.PLACED_LOOSE_ROCKS_KEY,
                LOOSE_ROCKS_HOLDER,
                List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())
        );
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration) {
        FeatureUtils.register(context, configuredFeatureKey, (Feature<NoneFeatureConfiguration>) feature);
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
        PlacementUtils.register(context, placedFeatureKey, configuredFeature, modifiers);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createFeatureKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, loc(pName));
    }

    private static ResourceKey<PlacedFeature> createPlacementKey(String pKey) {
        return ResourceKey.create(Registries.PLACED_FEATURE, loc(pKey));
    }

}
