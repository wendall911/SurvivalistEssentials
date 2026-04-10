package survivalistessentials.data;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import survivalistessentials.common.TagManager;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.util.ResourceLocationHelper;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class CommonItemTagsProvider extends IntrinsicHolderTagsProvider<Item> {

    @SuppressWarnings("deprecation")
    public CommonItemTagsProvider(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
        super(packOutput, Registries.ITEM, lookupProvider, (item) -> item.builtInRegistryHolder().key());
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        getOrCreateRawBuilder(TagManager.Items.PICKAXE_TOOLS)
            .addOptionalTag(TagManager.Items.MINING_TOOL_TOOLS.location())
            .addOptionalTag(SurvivalistEssentialsIntegration.mcLoc("pickaxes"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("pickaxe"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("pickadze"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("sledge_hammer"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("vein_hammer"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tetraLoc("modular_sword"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("buzzsaw"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("drill"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("hammer"));
        getOrCreateRawBuilder(TagManager.Items.AXE_TOOLS)
            .addOptionalTag(SurvivalistEssentialsIntegration.mcLoc("axes"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("mattock"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("hand_axe"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("broad_axe"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("buzzsaw"));
        builder(
            TagManager.Items.AXE_TOOLS,
            SurvivalistEssentialsItems.CRUDE_HATCHET
        );
        getOrCreateRawBuilder(TagManager.Items.SAW_TOOLS)
            .addOptionalElement(SurvivalistEssentialsIntegration.tsLoc("saw"));
        builder(
            TagManager.Items.SAW_TOOLS,
            SurvivalistEssentialsItems.CRUDE_SAW,
            SurvivalistEssentialsItems.BASIC_SAW,
            SurvivalistEssentialsItems.SHARP_SAW
        );
        getOrCreateRawBuilder(TagManager.Items.ADVANCED_SAW_TOOLS)
            .addOptionalElement(SurvivalistEssentialsIntegration.tsLoc("saw"));
        builder(
            TagManager.Items.ADVANCED_SAW_TOOLS,
            SurvivalistEssentialsItems.BASIC_SAW,
            SurvivalistEssentialsItems.SHARP_SAW
        );
        getOrCreateRawBuilder(TagManager.Items.SHOVEL_TOOLS)
            .addOptionalTag(SurvivalistEssentialsIntegration.mcLoc("shovels"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("mattock"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("pickadze"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("excavator"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("drill"));
        getOrCreateRawBuilder(TagManager.Items.HOE_TOOLS )
            .addOptionalTag(SurvivalistEssentialsIntegration.mcLoc("hoes"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("mattock"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("kama"));
        getOrCreateRawBuilder(TagManager.Items.KNIFE_TOOLS)
            .addOptionalElement(SurvivalistEssentialsIntegration.tsLoc("knife"));
        builder(
            TagManager.Items.KNIFE_TOOLS,
            SurvivalistEssentialsItems.CRUDE_KNIFE,
            SurvivalistEssentialsItems.BASIC_KNIFE,
            SurvivalistEssentialsItems.SHARP_KNIFE
        );
        getOrCreateRawBuilder(TagManager.Items.ADVANCED_KNIFE_TOOLS)
            .addOptionalElement(SurvivalistEssentialsIntegration.tsLoc("knife"));
        builder(
            TagManager.Items.ADVANCED_KNIFE_TOOLS,
            SurvivalistEssentialsItems.BASIC_KNIFE,
            SurvivalistEssentialsItems.SHARP_KNIFE
        );
        getOrCreateRawBuilder(TagManager.Items.SHARP_TOOLS)
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("kama"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("dagger"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("cleaver"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tconLoc("sword"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("revolver"))
            .addOptionalElement(SurvivalistEssentialsIntegration.tetraLoc("modular_sword"))
            .addOptionalTag(SurvivalistEssentialsIntegration.mcLoc("swords"))
            .addOptionalTag(TagManager.Items.KNIFE_TOOLS.location())
            .addOptionalTag(TagManager.Items.AXE_TOOLS.location());
        getOrCreateRawBuilder(TagManager.Items.SHEAR_TOOLS)
            .addOptionalTag(TagManager.Items.TOOLS_SHEAR.location());
        builder(TagManager.Items.ROCK, SurvivalistEssentialsWorld.ROCK_STONE);
        builder(
            TagManager.Items.SAW_PARTS,
            SurvivalistEssentialsItems.SAW_HANDLE,
            SurvivalistEssentialsItems.CRUDE_SAW_BLADE
        );
        builder(
            TagManager.Items.BANDAGES,
            SurvivalistEssentialsItems.CRUDE_BANDAGE,
            SurvivalistEssentialsItems.BANDAGE
        );
         getOrCreateRawBuilder(TagManager.Items.COOKED_MEAT)
            .addElement(ResourceLocationHelper.getItemId(Items.COOKED_BEEF))
            .addElement(ResourceLocationHelper.getItemId(Items.COOKED_CHICKEN))
            .addElement(ResourceLocationHelper.getItemId(Items.COOKED_COD))
            .addElement(ResourceLocationHelper.getItemId(Items.COOKED_MUTTON))
            .addElement(ResourceLocationHelper.getItemId(Items.COOKED_PORKCHOP))
            .addElement(ResourceLocationHelper.getItemId(Items.COOKED_RABBIT))
            .addElement(ResourceLocationHelper.getItemId(Items.COOKED_SALMON))
            .addOptionalElement(SurvivalistEssentialsIntegration.aquaLoc("fish_fillet_cooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.aquaLoc("frog_legs_cooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.bapLoc("turkey_cooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.bapLoc("venisoncooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.bapLoc("pheasantcooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.bapLoc("crab_meat_cooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.bapLoc("turkey_leg_cooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.bapLoc("eel_meat_cooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.bapLoc("calamari_cooked"))
            .addOptionalElement(SurvivalistEssentialsIntegration.alexLoc("cooked_lobster_tail"))
            .addOptionalElement(SurvivalistEssentialsIntegration.alexLoc("cooked_moose_ribs"))
            .addOptionalElement(SurvivalistEssentialsIntegration.alexLoc("cooked_kangaroo_meat"))
            .addOptionalElement(SurvivalistEssentialsIntegration.alexLoc("cooked_catfish"));

        // Fruit Trees
        addLogVariants(TagManager.Items.CHERRY_LOGS, "cherry", SurvivalistEssentialsIntegration::ftLoc);
        addLogVariants(TagManager.Items.CITRUS_LOGS, "citrus", SurvivalistEssentialsIntegration::ftLoc);

        // Biome Makeover
        getOrCreateRawBuilder(TagManager.Items.BMO_ANCIENT_OAK_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_ANCIENT_OAK_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_ANCIENT_OAK_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_ANCIENT_OAK_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_ANCIENT_OAK_WOOD);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_ANCIENT_OAK_WOOD);
        getOrCreateRawBuilder(TagManager.Items.BMO_BLIGHTED_BALSA_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_BLIGHTED_BALSA_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_BLIGHTED_BALSA_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_BLIGHTED_BALSA_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_BLIGHTED_BALSA_WOOD);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_BLIGHTED_BALSA_WOOD);
        getOrCreateRawBuilder(TagManager.Items.BMO_SWAMP_CYPRESS_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_SWAMP_CYPRESS_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_SWAMP_CYPRESS_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_SWAMP_CYPRESS_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_SWAMP_CYPRESS_WOOD);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_SWAMP_CYPRESS_WOOD);
        getOrCreateRawBuilder(TagManager.Items.BMO_WILLOW_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_WILLOW_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_WILLOW_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_WILLOW_LOG);
        getOrCreateRawBuilder(TagManager.Items.BMO_WILLOW_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_WILLOW_WOOD);
        getOrCreateRawBuilder(TagManager.Items.BMO_STRIPPED_WILLOW_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.BMO_STRIPPED_WILLOW_WOOD);

        // Biomes O' Plenty
        addLogVariants(TagManager.Items.BOP_DEAD_LOGS, "dead", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_FIR_LOGS, "fir", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_HELLBARK_LOGS, "hellbark", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_JACARANDA_LOGS, "jacaranda", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_MAGIC_LOGS, "magic", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_MAHOGANY_LOGS, "mahogany", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_PALM_LOGS, "palm", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_REDWOOD_LOGS, "redwood", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_UMBRAN_LOGS, "umbran", SurvivalistEssentialsIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_WILLOW_LOGS, "willow", SurvivalistEssentialsIntegration::bopLoc);

        // Botania
        addLogVariants(TagManager.Items.BOTANIA_DREAMWOOD_LOGS, "dreamwood", SurvivalistEssentialsIntegration::botaniaLoc);
        addLogVariants(TagManager.Items.BOTANIA_LIVINGWOOD_LOGS, "livingwood", SurvivalistEssentialsIntegration::botaniaLoc);

        // Quark
        addLogVariants(TagManager.Items.QUARK_AZALEA_LOGS, "azalea", SurvivalistEssentialsIntegration::qLoc);
        addLogVariants(TagManager.Items.QUARK_BLOSSOM_LOGS, "blossom", SurvivalistEssentialsIntegration::qLoc);

        // All You Can Eat
        addLogVariants(TagManager.Items.AYCE_HAZEL_LOGS, "hazel", SurvivalistEssentialsIntegration::ayceLoc);

        // Tinkers' Construct
        addLogVariants(TagManager.Items.TCON_BLOODSHROOM_LOGS, "bloodshroom", SurvivalistEssentialsIntegration::tconLoc);
        addLogVariants(TagManager.Items.TCON_GREENHEART_LOGS, "greenheart", SurvivalistEssentialsIntegration::tconLoc);
        addLogVariants(TagManager.Items.TCON_SKYROOT_LOGS, "skyroot", SurvivalistEssentialsIntegration::tconLoc);

        // Water Source
        addSimpleLogVariants(TagManager.Items.WS_PALM_TREE_LOGS, "palm_tree", SurvivalistEssentialsIntegration::wsLoc);

        // Undergarden
        addLogVariants(TagManager.Items.UNDERGARDEN_GRONGLE_LOGS, "grongle", SurvivalistEssentialsIntegration::undergardenLoc);
        addLogVariants(TagManager.Items.UNDERGARDEN_SMOGSTEM_LOGS, "smogstem", SurvivalistEssentialsIntegration::undergardenLoc);
        addLogVariants(TagManager.Items.UNDERGARDEN_WIGGLEWOOD_LOGS, "wigglewood", SurvivalistEssentialsIntegration::undergardenLoc);

        // BYG
        addLogVariants(TagManager.Items.BYG_WHITE_MANGROVE_LOGS, "white_mangrove", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_REDWOOD_LOGS, "redwood", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_BLUE_ENCHANTED_LOGS, "blue_enchanted", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_GREEN_ENCHANTED_LOGS, "green_enchanted", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_MAHOGANY_LOGS, "mahogany", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_BAOBAB_LOGS, "baobab", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_JACARANDA_LOGS, "jacaranda", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_CYPRESS_LOGS, "cypress", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_PALM_LOGS, "palm", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_EBONY_LOGS, "ebony", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_RAINBOW_EUCALYPTUS_LOGS, "rainbow_eucalyptus", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_ASPEN_LOGS, "aspen", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_FIR_LOGS, "fir", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_SKYRIS_LOGS, "skyris", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_CIKA_LOGS, "cika", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_HOLLY_LOGS, "holly", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_MAPLE_LOGS, "maple", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_PINE_LOGS, "pine", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_WILLOW_LOGS, "willow", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_WITCH_HAZEL_LOGS, "witch_hazel", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_ZELKOVA_LOGS, "zelkova", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_IRONWOOD_LOGS, "ironwood", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_SAKURA_LOGS, "sakura", SurvivalistEssentialsIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_SPIRIT_LOGS, "spirit", SurvivalistEssentialsIntegration::bygLoc);
        getOrCreateRawBuilder(TagManager.Items.BYG_PALO_VERDE_LOGS)
            .addOptionalTag(SurvivalistEssentialsIntegration.bygLoc("palo_verde_logs"));
        getOrCreateRawBuilder(TagManager.Items.BYG_FLORUS_STEMS)
            .addOptionalTag(SurvivalistEssentialsIntegration.bygLoc("florus_logs"));

        // Twilight Forest
        getOrCreateRawBuilder(TagManager.Items.TF_GIANT_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.tfLoc("giant_log"));
        getOrCreateRawBuilder(TagManager.Items.TF_CANOPY_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_CANOPY_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_CANOPY_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_CANOPY_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_CANOPY_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_CANOPY_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_CANOPY_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_CANOPY_STRIPPED_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_DARK_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_DARK_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_DARK_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_DARK_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_DARK_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_DARK_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_DARK_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_DARK_STRIPPED_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_MANGROVE_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MANGROVE_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_MANGROVE_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MANGROVE_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_MANGROVE_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MANGROVE_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_MANGROVE_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MANGROVE_STRIPPED_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_MINING_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MINING_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_MINING_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MINING_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_MINING_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MINING_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_MINING_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_MINING_STRIPPED_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_SORTING_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_SORTING_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_SORTING_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_SORTING_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_SORTING_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_SORTING_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_SORTING_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_SORTING_STRIPPED_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_TIME_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TIME_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_TIME_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TIME_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_TIME_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TIME_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_TIME_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TIME_STRIPPED_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_TRANSFORMATION_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TRANSFORMATION_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_TRANSFORMATION_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TRANSFORMATION_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_TRANSFORMATION_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TRANSFORMATION_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_TRANSFORMATION_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TRANSFORMATION_STRIPPED_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_TWILIGHT_OAK_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_LOG)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_STRIPPED_LOG);
        getOrCreateRawBuilder(TagManager.Items.TF_TWILIGHT_OAK_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_WOOD);
        getOrCreateRawBuilder(TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.TF_TWILIGHT_OAK_STRIPPED_WOOD);

        // Ecologics
        addLogVariants(TagManager.Items.ECO_COCONUT_LOGS, "coconut", SurvivalistEssentialsIntegration::ecoLoc);
        addLogVariants(TagManager.Items.ECO_WALNUT_LOGS, "walnut", SurvivalistEssentialsIntegration::ecoLoc);
        addLogVariants(TagManager.Items.ECO_AZALEA_LOGS, "azalea", SurvivalistEssentialsIntegration::ecoLoc);
        addLogVariants(TagManager.Items.ECO_FLOWERING_AZALEA_LOGS, "flowering_azalea", SurvivalistEssentialsIntegration::ecoLoc);

        // Malum
        addSoulwoodVariants(TagManager.Items.MALUM_RUNEWOOD_LOGS, "runewood");
        addRunewoodVariants(TagManager.Items.MALUM_SOULWOOD_LOGS, "soulwood");

        // Ice and Fire; Dragons
        getOrCreateRawBuilder(TagManager.Items.IFD_DREADWOOD_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ifdLoc("dreadwood_log"));

        // Aquaculture
        getOrCreateRawBuilder(TagManager.Items.AQUA_DRIFTWOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.aquaLoc("driftwood"));

        // Immersive Engineering
        getOrCreateRawBuilder(TagManager.Items.IE_TREATED_WOOD)
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("treated_wood_horizontal"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("treated_wood_vertical"))
            .addOptionalElement(SurvivalistEssentialsIntegration.ieLoc("treated_wood_packaged"));

        // Regions Unexplored
        getOrCreateRawBuilder(TagManager.Items.RU_ALPHA_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.regionsLoc("alpha_log"));
        addLogVariants(TagManager.Items.RU_BAOBAB_LOGS, "baobab", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_BLACKWOOD_LOGS, "blackwood", SurvivalistEssentialsIntegration::regionsLoc);
        addShroomVariants(TagManager.Items.RU_BIOSHROOM_LOGS, "bioshroom", SurvivalistEssentialsIntegration::regionsLoc);
        addShroomVariants(TagManager.Items.RU_BLUE_BIOSHROOM_LOGS, "blue_bioshroom", SurvivalistEssentialsIntegration::regionsLoc);
        addSimpleLogVariants(TagManager.Items.RU_BRIMWOOD_LOGS, "brimwood", SurvivalistEssentialsIntegration::regionsLoc)
            .addOptionalElement(SurvivalistEssentialsIntegration.regionsLoc("brimwood_log_magma"))
            .addOptionalElement(SurvivalistEssentialsIntegration.regionsLoc("brimwood_wood"));
        addLogVariants(TagManager.Items.RU_COBALT_LOGS, "cobalt", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_CYPRESS_LOGS, "cypress", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_DEAD_LOGS, "dead", SurvivalistEssentialsIntegration::regionsLoc)
            .addOptionalElement(SurvivalistEssentialsIntegration.regionsLoc("ashen_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.regionsLoc("ashen_wood"));
        addLogVariants(TagManager.Items.RU_EUCALYPTUS_LOGS, "eucalyptus", SurvivalistEssentialsIntegration::regionsLoc);
        addShroomVariants(TagManager.Items.RU_GREEN_BIOSHROOM_LOGS, "green_bioshroom", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_JOSHUA_LOGS, "joshua", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_KAPOK_LOGS, "kapok", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_LARCH_LOGS, "larch", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_MAGNOLIA_LOGS, "magnolia", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_MAPLE_LOGS, "maple", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_MAUVE_LOGS, "mauve", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_PALM_LOGS, "palm", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_PINE_LOGS, "pine", SurvivalistEssentialsIntegration::regionsLoc);
        addShroomVariants(TagManager.Items.RU_PINK_BIOSHROOM_LOGS, "pink_bioshroom", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_REDWOOD_LOGS, "redwood", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_SOCOTRA_LOGS, "socotra", SurvivalistEssentialsIntegration::regionsLoc);
        addLogVariants(TagManager.Items.RU_WILLOW_LOGS, "willow", SurvivalistEssentialsIntegration::regionsLoc);
        addShroomVariants(TagManager.Items.RU_YELLOW_BIOSHROOM_LOGS, "yellow_bioshroom", SurvivalistEssentialsIntegration::regionsLoc);

        // Let's Do
        // Beach
        getOrCreateRawBuilder(TagManager.Items.LD_PALM_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBeachLoc("palm_log"));
        // Blooming Nature
        getOrCreateRawBuilder(TagManager.Items.LD_LARCH_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("larch_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_BAOBAB_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("baobab_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_ASPEN_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("aspen_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_SWAMP_OAK_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("swamp_oak_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_SWAMP_CYPRESS_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("swamp_cypress_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_FAN_PALM_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("fan_palm_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_EBONY_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("ebony_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_CHESTNUT_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("chestnut_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_FIR_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldBloomingLoc("fir_log"));
        getOrCreateRawBuilder(TagManager.Items.LD_CACTUS)
            .addOptionalTag(SurvivalistEssentialsIntegration.ldBloomingLoc("cactus"));
        // Meadow
        getOrCreateRawBuilder(TagManager.Items.LD_PINE_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldMeadowLoc("pine_log"));
        // Vinery
        getOrCreateRawBuilder(TagManager.Items.LD_DARK_CHERRY_LOGS)
            .addOptionalElement(SurvivalistEssentialsIntegration.ldVineryLoc("dark_cherry_log"));
    }

    private TagBuilder addSimpleLogVariants(TagKey<Item> tag, String type, Function<String, Identifier> modLoc) {
        return getOrCreateRawBuilder(tag)
            .addOptionalElement(modLoc.apply(type + "_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.wsLoc("stripped_" + type + "_log"));
    }

    private void addShroomVariants(TagKey<Item> tag, String type, Function<String, Identifier> modLoc) {
        getOrCreateRawBuilder(tag)
            .addOptionalElement(modLoc.apply(type + "_hyphae"))
            .addOptionalElement(modLoc.apply("stripped_" + type + "_hyphae"))
            .addOptionalElement(modLoc.apply(type + "_stem"))
            .addOptionalElement(SurvivalistEssentialsIntegration.wsLoc("stripped_" + type + "_stem"));
    }

    private TagBuilder addLogVariants(TagKey<Item> tag, String type, Function<String, Identifier> modLoc) {
        return getOrCreateRawBuilder(tag)
            .addOptionalElement(modLoc.apply(type + "_log"))
            .addOptionalElement(modLoc.apply("stripped_" + type + "_log"))
            .addOptionalElement(modLoc.apply(type + "_wood"))
            .addOptionalElement(modLoc.apply("stripped_" + type + "_wood"));
    }

    private void addRunewoodVariants(TagKey<Item> tag, String type) {
        getOrCreateRawBuilder(tag)
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc(type))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc(type + "_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("stripped_" + type + "_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("exposed_" + type + "_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("revealed_" + type + "_log"));
    }

    private void addSoulwoodVariants(TagKey<Item> tag, String type) {
        getOrCreateRawBuilder(tag)
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc(type))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc(type + "_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("stripped_" + type))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("stripped_" + type + "_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("blighted_" + type))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("exposed_" + type + "_log"))
            .addOptionalElement(SurvivalistEssentialsIntegration.malumLoc("revealed_" + type + "_log"));
    }

    private void builder(TagKey<Item> tag, ItemLike... items) {
        this.tag(tag).add(Arrays.stream(items).map(ItemLike::asItem).toArray(Item[]::new));
    }

}
