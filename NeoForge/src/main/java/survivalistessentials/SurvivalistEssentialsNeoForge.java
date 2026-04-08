package survivalistessentials;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.SurvivalistEssentialsModule;
import survivalistessentials.common.loot.LootItemBlockIsTagCondition;
import survivalistessentials.common.loot.SurvivalistEssentialsLootConditionTypes;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.data.recipe.ConfigResourceCondition;
import survivalistessentials.event.ClientEvents;
import survivalistessentials.event.HarvestEvents;
import survivalistessentials.event.LivingEquipmentChangeEvents;
import survivalistessentials.event.PlayerEvents;
import survivalistessentials.event.TooltipEvents;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.platform.Services;
import survivalistessentials.registries.SurvivalistEssentialsNeoForgeRegistries;
import survivalistessentials.sound.SurvivalistEssentialsSounds;
import survivalistessentials.world.SurvivalistEssentialsWorld;
import survivalistessentials.world.effect.SurvivalistEssentialsEffects;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;

import static survivalistessentials.util.ResourceLocationHelper.prefix;

@Mod(SurvivalistEssentials.MODID)
public class SurvivalistEssentialsNeoForge {

    public SurvivalistEssentialsNeoForge(IEventBus bus, Dist dist, ModContainer container) {
        SurvivalistEssentials.init();
        registryInit(bus);
        registerListeners(bus);
        SurvivalistEssentialsModule.initRegistries(bus);
        SurvivalistEssentials.initConfig();
    }

    public void registerListeners(IEventBus bus) {
        bus.register(RegistryListener.class);
        SurvivalistEssentialsLootTables.init();
        SurvivalistEssentialsFeatures.setup();
    }

    public static final class RegistryListener {
        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerEvent(RegisterEvent event) {
            //event.register(Registries.LOOT_CONDITION_TYPE, prefix("is_tag"), () -> LootItemBlockIsTagCondition.CODEC);
        }

        @SubscribeEvent
        public static void setup(FMLCommonSetupEvent event) {
            HarvestBlock.init();
            NeoForge.EVENT_BUS.register(HarvestEvents.class);
            NeoForge.EVENT_BUS.register(LivingEquipmentChangeEvents.class);
            NeoForge.EVENT_BUS.register(PlayerEvents.class);

            if (Services.PLATFORM.isPhysicalClient()) {
                NeoForge.EVENT_BUS.register(TooltipEvents.class);
                NeoForge.EVENT_BUS.register(ClientEvents.class);
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerCreativeTab(BuildCreativeModeTabContentsEvent event) {
            if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                for (Map.Entry<Identifier, Item> entry : SurvivalistEssentialsItems.getToolsAndUtilities().entrySet()) {
                    Item item = entry.getValue();

                    event.accept(new ItemStack(item));
                }
            }
            if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
                for (Map.Entry<Identifier, Item> entry : SurvivalistEssentialsItems.getAllIngredients().entrySet()) {
                    Item item = entry.getValue();

                    event.accept(new ItemStack(item));
                }
                for (Map.Entry<Identifier, Item> entry : SurvivalistEssentialsWorld.getAll().entrySet()) {
                    Item item = entry.getValue();

                    event.accept(new ItemStack(item));
                }
            }
        }

    }

    @SubscribeEvent
    public static void serverStart(ServerStartedEvent event) {
        if (ConfigHandler.Common.logModpackData()) {
            BuiltInRegistries.BLOCK.forEach(block -> {
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_WOOD_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_wood_tool - level 0: {}", block);
                }
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_GOLD_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_gold_tool - level 0.1: {}", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_STONE_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_stone_tool - level 1: {}", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_IRON_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_iron_tool - level 2: {}", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_DIAMOND_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_diamond_tool - level 3: {}", block);
                }
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_NETHERITE_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_netherite_tool - level 4: {}", block);
                }
            });
        }
    }

    private void registryInit(IEventBus bus) {
        bind(bus, Registries.FEATURE, SurvivalistEssentialsFeatures::init);
        bind(bus, Registries.MOB_EFFECT, SurvivalistEssentialsEffects::init);
        bind(bus, Registries.ITEM, SurvivalistEssentialsIntegration::init);
        bind(bus, Registries.ITEM, SurvivalistEssentialsItems::init);
        bind(bus, Registries.BLOCK, SurvivalistEssentialsWorld::initBlocks);
        bind(bus, Registries.ITEM, SurvivalistEssentialsWorld::initItems);
        bind(bus, Registries.LOOT_CONDITION_TYPE, SurvivalistEssentialsLootConditionTypes::init);
        bind(bus, Registries.SOUND_EVENT, SurvivalistEssentialsSounds::init);
        SurvivalistEssentialsNeoForgeRegistries.CONDITION_SERIALIZERS_DEFERRED_REGISTER.register(bus);
        SurvivalistEssentialsNeoForgeRegistries.CONDITION_SERIALIZERS_DEFERRED_REGISTER.register(
            ConfigResourceCondition.ID,
            () -> ConfigResourceCondition.CODEC
        );
    }

    private static <T> void bind(IEventBus bus, ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, Identifier>> source) {
        bus.addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }

}
