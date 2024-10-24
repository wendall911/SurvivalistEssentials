package survivalistessentials.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec.DoubleValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.ItemUse;

public final class ConfigHandler {

    private ConfigHandler() {}

    public static void init(ModContainer container) {
        container.registerConfig(ModConfig.Type.STARTUP, ConfigHandler.Client.CONFIG_SPEC, SurvivalistEssentials.MODID + "-client.toml");
        container.registerConfig(ModConfig.Type.COMMON, ConfigHandler.Common.CONFIG_SPEC);
    }

    public static final class Client {

        private static final ModConfigSpec CONFIG_SPEC;
        private static final Client CONFIG;
        private final ModConfigSpec.BooleanValue ENABLE_FAIL_SOUND;

        static {
            Pair<Client, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Client::new);

            CONFIG_SPEC = specPair.getRight();
            CONFIG = specPair.getLeft();
        }

        Client(ModConfigSpec.Builder builder) {
            ENABLE_FAIL_SOUND = builder
                .comment("Enables the fail sound if using the wrong tool.")
                .define("ENABLE_FAIL_SOUND", true);
        }

        public static boolean enableFailSound() {
            return CONFIG.ENABLE_FAIL_SOUND.get();
        }

    }

    public static final class Common {
        private static final ModConfigSpec CONFIG_SPEC;
        private static final Common CONFIG;
        private final DoubleValue FLINT_CHANCE;
        private final DoubleValue HEAL_RATE;
        private final DoubleValue SLOW_DOWN_SPEED;
        private final BooleanValue ENABLE_HUNGER_PENALTY;
        private final IntValue HUNGER;
        private final IntValue SATURATION;
        private final BooleanValue ENABLE_HEALTH_PENALTY;
        private final DoubleValue HEALTH;
        private final DoubleValue STARTING_HEALTH_PENALTY;
        private final IntValue GENERIC_DAMAGE;
        private final BooleanValue INVERT_LIST_TO_WHITELIST;

        private static final List<String> MODS_LIST = List.of("mods");
        private static final String[] modsStrings = new String[] {};
        // See: https://github.com/MinecraftForge/MinecraftForge/blob/1.18.x/fmlloader/src/main/java/net/minecraftforge/fml/loading/moddiscovery/ModInfo.java
        private static final Predicate<Object> modidValidator = s -> s instanceof String
                && ((String) s).matches("^[a-z][a-z0-9_]{1,63}$");
        private static ConfigValue<List<? extends String>> MODS;

        private static final List<String> ITEMS_LIST = List.of("items");
        private static final String[] itemsStrings = new String[] {};
        private static final Predicate<Object> itemidValidator = s -> s instanceof String
                && ((String) s).matches("[a-z]+[-]{1}[a-z][a-z0-9_]{1,63}+[:]{1}[a-z_]+");
        private static ConfigValue<List<? extends String>> ITEMS;

        private static BooleanValue LOG_MODPACK_DATA;

        private static final List<String> BLOCK_MODS_LIST = List.of("blockmods");
        private static final String[] blockModsStrings = new String[] {
            "comforts"
        };
        private static ConfigValue<List<? extends String>> BLOCK_MODS;

        private static final List<String> ARMOR_MODS_LIST = List.of("armormods");
        private static final String[] armorModsStrings = new String[] {};
        private static ConfigValue<List<? extends String>> ARMOR_MODS;
        private static final List<String> ARMOR_LIST = List.of("armor");
        private static final String[] armorStrings = new String[] {};
        private static final Predicate<Object> resourceLocationValidator = s -> s instanceof String
                && ((String) s).matches("[a-z]+[:]{1}[a-z_]+");
        private static ConfigValue<List<? extends String>> ARMORS;
        private static final List<String> TAG_LIST = List.of("tag");
        private static final String[] tagStrings = new String[] {
            "c:blacklist_tools"
        };
        private static final List<TagKey<Item>> tagList = new ArrayList<>();
        private static ConfigValue<List<? extends String>> TAGS;

        static {
            Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);

            CONFIG_SPEC = specPair.getRight();
            CONFIG = specPair.getLeft();
        }

        Common(ModConfigSpec.Builder builder) {
            FLINT_CHANCE = builder
                .comment("Chance for a successful flint knapping. (1.0 = 100%, 0.4 = 40%, etc.)")
                .defineInRange("FLINT_CHANCE", 0.6, 0.1, 1.0);
            HEAL_RATE = builder
                .comment("Heal rate for bandages. Crude bandages are 50% less effective. (1.0 = 100%, 0.4 = 40%, etc.)")
                .defineInRange("HEAL_RATE", 0.14, 0.1, 1.0);
            SLOW_DOWN_SPEED = builder
                .comment("Slowdown speed when using incorrect tool.")
                .defineInRange("SLOW_DOWN_SPEED", 0.4, 0.1, 1.0);
            INVERT_LIST_TO_WHITELIST = builder
                .comment("Inverts blacklist to be whitelist. This allows for immersion mods/modpacks to only allow tools or armor for specific mods. Default: false")
                .define("INVERT_LIST_TO_WHITELIST", false);
            MODS = builder
                .comment("List of mods that tools will become wet noodles. If inverted, acts as a whitelist. Default: "
                        + "[\"" + String.join("\", \"", modsStrings) + "\"]")
                .defineListAllowEmpty(MODS_LIST, getFields(modsStrings), modidValidator);
            ITEMS = builder
                .comment("List of individual tools that will always work. Format tooltype-modid:item Default: "
                        + "[\"" + String.join("\", \"", itemsStrings) + "\"]")
                .defineListAllowEmpty(ITEMS_LIST, getFields(itemsStrings), itemidValidator);
            LOG_MODPACK_DATA = builder
                .comment("Used to dump log info for Survivalist Essentials Modpack. Ignore.")
                .define("LOG_MODPACK_DATA", false);
            BLOCK_MODS = builder
                .comment("List of mods that have blocks that are generally decorative in nature and require no tool for harvesting blocks. Default: "
                        + "[\"" + String.join("\", \"", blockModsStrings) + "\"]")
                .defineListAllowEmpty(BLOCK_MODS_LIST, getFields(blockModsStrings), modidValidator);
            ENABLE_HUNGER_PENALTY = builder
                .comment("Hunger penalty feature. If after dying, player is rewarded with reduced hunger levels.")
                .define("ENABLE_HUNGER_PENALTY", false);
            HUNGER = builder
                .comment("Hunger value after death in half shanks. (0 = Really? That's just cruel, 20 = No penalty.)")
                .defineInRange("HUNGER", 8, 0, 20);
            SATURATION = builder
                .comment("Saturation value after death. Range 0 to 20.")
                .defineInRange("SATURATION", 0, 0, 20);
            ENABLE_HEALTH_PENALTY = builder
                .comment("Health penalty feature. If after dying, player is rewarded with reduced health levels.")
                .define("ENABLE_HEALTH_PENALTY", false);
            HEALTH = builder
                .comment("Health value after death in half hearts.")
                .defineInRange("HEALTH", 6.0, 0.5, 100.0);
            STARTING_HEALTH_PENALTY = builder
                    .comment("Health penalty in half hearts player starts with. Reduces total starting health by this amount.")
                    .defineInRange("STARTING_HEALTH_PENALTY", 0.0, 0.0, 19.0);
            GENERIC_DAMAGE = builder
                .comment("The amount of generic damage in half hearts a disabled tool, or bare hand should do. Default 0")
                .defineInRange("GENERIC_DAMAGE", 0, 0, 4);
            ARMOR_MODS = builder
                .comment("List of mods that armor will not be equipable for. If inverted, acts as a whitelist. Default: "
                        + "[\"" + String.join("\", \"", armorModsStrings) + "\"]")
                .defineListAllowEmpty(ARMOR_MODS_LIST, getFields(armorModsStrings), modidValidator);
            ARMORS = builder
                .comment("List of individual armor items that will be disabled. If inverted, acts as a whitelist. Format modid:item Default: "
                        + "[\"" + String.join("\", \"", armorStrings) + "\"]")
                .defineListAllowEmpty(ARMOR_LIST, getFields(armorStrings), resourceLocationValidator);
            TAGS = builder
                .comment("List of tags when added to tools or armor will be disabled. If inverted, acts as a whitelist."
                        + "[\"" + String.join("\", \"", tagStrings) + "\"]")
                .defineListAllowEmpty(TAG_LIST, getFields(tagStrings), resourceLocationValidator);
        }

        public static double flintChance() {
            return CONFIG.FLINT_CHANCE.get();
        }

        public static double healRate() {
            return CONFIG.HEAL_RATE.get();
        }

        public static boolean invertListToWhitelist() {
            return CONFIG.INVERT_LIST_TO_WHITELIST.get();
        }

        public static float slowDownSpeed() {
            double slowDownSpeed = CONFIG.SLOW_DOWN_SPEED.get();

            return (float) slowDownSpeed;
        }

        private static Supplier<List<? extends String>> getFields(String[] strings) {
            return () -> Arrays.asList(strings);
        }

        public static List<? extends String> getMods() {
            return MODS.get();
        }

        public static List<? extends String> getItems() {
            return ITEMS.get();
        }

        public static boolean logModpackData() {
            return LOG_MODPACK_DATA.get();
        }

        public static List<? extends String> blockWhitelistMods() {
            return BLOCK_MODS.get();
        }

        public static boolean enableHungerPenalty() {
            return CONFIG.ENABLE_HUNGER_PENALTY.get();
        }

        public static int hunger() {
            return CONFIG.HUNGER.get();
        }

        public static int saturation() {
            return CONFIG.SATURATION.get();
        }

        public static boolean enableHealthPenalty() {
            return CONFIG.ENABLE_HEALTH_PENALTY.get();
        }

        public static float health() {
            double health = CONFIG.HEALTH.get();

            return (float) health;
        }

        public static float startingHealthPenalty() {
            double health = CONFIG.STARTING_HEALTH_PENALTY.get();
            health *= -1;

            return (float) health;
        }

        public static float genericDamage() {
            int damage = CONFIG.GENERIC_DAMAGE.get();

            return (float) damage;
        }

        public static List<? extends String> armorMods() {
            return ARMOR_MODS.get();
        }

        public static List<? extends String> armorItems() {
            return ARMORS.get();
        }

        public static List<TagKey<Item>> tagList() {
            return tagList;
        }

    }

    public static void loadConfigs(final ModConfigEvent.Loading event) {
        loadCommonConfig(event.getConfig());
    }

    public static void onFileChange(final ModConfigEvent.Reloading event) {
        loadCommonConfig(event.getConfig());
    }

    private static void loadCommonConfig(ModConfig config) {
        if (config.getType() == ModConfig.Type.COMMON && config.getModId().equals(SurvivalistEssentials.MODID)) {
            Common.tagList().clear();
            Common.TAGS.get().forEach((s) -> {
                Common.tagList().add(TagKey.create(Registries.ITEM, ResourceLocation.parse(s)));
            });

            ItemUse.init();
        }
    }

}
