package survivalistessentials.data;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.Translations;

public class SurvivalistEssentialsLanguageProvider extends FabricLanguageProvider {

    protected SurvivalistEssentialsLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryFuture) {
        super(dataOutput, "en_us", registryFuture);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider provider, TranslationBuilder builder) {
        // Creative Tab
        builder.add(SurvivalistEssentials.MODID + ".items", "Survivalist Essentials Items");

        // Items
        addTranslationItem(builder, "plant_fiber", "Plant Fibers");
        addTranslationItem(builder, "plant_string", "Plant String");
        addTranslationItem(builder, "flint_shard", "Flint Shard");
        addTranslationItem(builder, "crude_knife", "Crude Knife");
        addTranslationItem(builder, "basic_knife", "Basic Knife");
        addTranslationItem(builder, "sharp_knife", "Sharp Knife");
        addTranslationItem(builder, "crude_hatchet", "Crude Hatchet");
        addTranslationItem(builder, "crude_saw", "Crude Saw");
        addTranslationItem(builder, "basic_saw", "Basic Saw");
        addTranslationItem(builder, "sharp_saw", "Sharp Saw");
        addTranslationItem(builder, "crude_saw_blade", "Crude Saw Blade");
        addTranslationItem(builder, "basic_saw_blade", "Basic Saw Blade");
        addTranslationItem(builder, "sharp_saw_blade", "Sharp Saw Blade");
        addTranslationItem(builder, "saw_handle", "Saw Handle");
        addTranslationItem(builder, "knife", "Knife");
        addTranslationItem(builder, "knife.description", "For cutting stuff.");
        addTranslationItem(builder, "mortar_and_pestle", "Mortar and Pestle");
        addTranslationItem(builder, "saw", "Saw");
        addTranslationItem(builder, "saw_blade", "Saw Blade");
        addTranslationItem(builder, "saw.description", "For cutting wood!");
        addTranslationItem(builder, "saw_blade_cast", "Saw Blade Cast");
        addTranslationItem(builder, "saw_blade_sand_cast", "Saw Blade Sand Cast");
        addTranslationItem(builder, "saw_blade_red_sand_cast", "Saw Blade Red Sand Cast");
        addTranslationItem(builder, "reinforced_wool_helmet", "Reinforced Wool Helmet");
        addTranslationItem(builder, "reinforced_wool_chestplate", "Reinforced Wool Chestplate");
        addTranslationItem(builder, "reinforced_wool_leggings", "Reinforced Wool Leggings");
        addTranslationItem(builder, "reinforced_wool_boots", "Reinforced Wool Boots");
        addTranslationItem(builder, "reinforced_jelled_slime_helmet", "Reinforced Slime Helmet");
        addTranslationItem(builder, "reinforced_jelled_slime_chestplate", "Reinforced Slime Chestplate");
        addTranslationItem(builder, "reinforced_jelled_slime_leggings", "Reinforced Slime Leggings");
        addTranslationItem(builder, "reinforced_jelled_slime_boots", "Reinforced Slime Boots");
        addTranslationItem(builder, "bandage", "Bandage");
        addTranslationItem(builder, "cloth", "Cloth");
        addTranslationItem(builder, "crude_bandage", "Crude Bandage");
        addTranslationItem(builder, "ointment", "Ointment");
        addTranslationItem(builder, "plant_paste", "Plant Paste");
        addTranslationItem(builder, "wooden_cup", "Mysterious Wooden Cup");
        addTranslationItem(builder, "book", "Survivalist Essentials");
        addTranslationItem(builder, "modpack_book", "Survivalist Essentials+");
        addTranslationItem(builder, "rock_stone", "Rock");

        // Blocks
        addTranslationBlock(builder, "stone_loose_rock", "Loose Rock (Stone)");
        addTranslationItem(builder, "stone_loose_rock", "Loose Rock (Stone)");
        addTranslationBlock(builder, "andesite_loose_rock", "Loose Rock (Andesite)");
        addTranslationItem(builder, "andesite_loose_rock", "Loose Rock (Andesite)");
        addTranslationBlock(builder, "diorite_loose_rock", "Loose Rock (Diorite)");
        addTranslationItem(builder, "diorite_loose_rock", "Loose Rock (Diorite)");
        addTranslationBlock(builder, "granite_loose_rock", "Loose Rock (Granite)");
        addTranslationItem(builder, "granite_loose_rock", "Loose Rock (Granite)");
        addTranslationBlock(builder, "sandstone_loose_rock", "Loose Rock (Sandstone)");
        addTranslationItem(builder, "sandstone_loose_rock", "Loose Rock (Sandstone)");
        addTranslationBlock(builder, "red_sandstone_loose_rock", "Loose Rock (Red Sandstone)");
        addTranslationItem(builder, "red_sandstone_loose_rock", "Loose Rock (Red Sandstone)");

        // JEI Descriptions
        addJeiDescription(builder, "rock_stone", "Rocks are found lying on the ground. These can be used with the knife in a crafting table to get flint shards.");
        addJeiDescription(builder, "plant_fiber", "Plant fibers are dropped when breaking grass or leaves with a knife. They are used to make plant string for tools and plant paste for bandage ointment.");
        addJeiDescription(builder, "stick", "Sticks are dropped from leaves. Using any knife doubles the chance of getting a stick.");
        addJeiDescription(builder, "flint_shard", "Flint shards can be obtained from striking a rock on any rocky surface. Interact with any stone-like block with a rock, and it might split into two flint shards.");

        // Tooltips
        addTranslationTooltip(builder, "uselessTool1", "This tool cannot mine anything!");
        addTranslationTooltip(builder, "uselessTool2", "Can only be used for crafting.");
        addTranslationTooltip(builder, "uselessHoe1", "This hoe cannot hoe anything!");
        addTranslationTooltip(builder, "uselessWeapon1", "This weapon hits like a wet noodle!");
        addTranslationTooltip(builder, "uselessBow1", "This bows bowstring snapped!");
        addTranslationTooltip(builder, "uselessArmor1", "Axiom sizing: XXXL. Probably won't fit.");
        addTranslationTooltip(builder, "cxp", "Crafty");
        addTranslationMessage(builder, "tcon_compat", "It is recommended to install Tinkers' Survival with Survivalist Essentials for better compatibility with Tinkers' Construct. It offers Tinkers' versions of the Saw and Knife!");
        addTranslationMessage(builder, "notice", "Notice");
        addTranslationMessage(builder, "warning", "Warning");
        addTranslationMessage(builder, "wrong_tool", "A %s is required.");
        addTranslationMessage(builder, "wrong_tool2", "Ummm ... %s?");
        addTranslationMessage(builder, "tool_broke", "Your %s broke.");
        addTranslationMessage(builder, "spawn_set", "Not sleepy, but spawn set!");

        // Effects
        addTranslationEffect(builder, "stop_bleeding", "Stop Bleeding");
        addTranslationEffect(builder, "zombie_essence", "Zombie Essence");

        // TCon Patterns
        addTranslationPattern(builder, "saw_blade", "Saw Blade");

        // Configuration
        addConfigurationTitle(builder, "Survivalist Essentials");
        addConfigurationName(builder, "enablefailsound", "Enable Fail Sound");
        addConfigurationDescription(builder, "enablefailsound", "enablefailsound");
        addConfigurationName(builder, "informtconcompat", "Inform Tinkers' Construct Compatibility");
        addConfigurationDescription(builder, "informtconcompat", "informtconcompat");
        addConfigurationName(builder, "flintchance", "Flint Knapping Chance");
        addConfigurationDescription(builder, "flintchance", "flintchance");
        addConfigurationName(builder, "healrate", "Bandage Heal Rate");
        addConfigurationDescription(builder, "healrate", "healrate");
        addConfigurationName(builder, "slowdownspeed", "Incorrect Tool Slowdown Speed");
        addConfigurationDescription(builder, "slowdownspeed", "slowdownspeed");
        addConfigurationName(builder, "invertlisttowhitelist", "Invert Tool List to Whitelist");
        addConfigurationDescription(builder, "invertlisttowhitelist", "invertlisttowhitelist");
        addConfigurationName(builder, "mods", "Tool Mods List");
        addConfigurationDescription(builder, "mods", "modslist");
        addConfigurationName(builder, "items", "Tool Items List");
        addConfigurationDescription(builder, "items", "itemslist");
        addConfigurationName(builder, "logmodpackdata", "Log Modpack Data");
        addConfigurationDescription(builder, "logmodpackdata", "logmodpackdata");
        addConfigurationName(builder, "blockmods", "Block Mods List");
        addConfigurationDescription(builder, "blockmods", "blockmodslist");
        addConfigurationName(builder, "enablehungerpenalty", "Enable Hunger Penalty");
        addConfigurationDescription(builder, "enablehungerpenalty", "enablehungerpenalty");
        addConfigurationName(builder, "hunger", "Hunger After Death");
        addConfigurationDescription(builder, "hunger", "hunger");
        addConfigurationName(builder, "saturation", "Saturation After Death");
        addConfigurationDescription(builder, "saturation", "saturation");
        addConfigurationName(builder, "enablehealthpenalty", "Enable Health Penalty");
        addConfigurationDescription(builder, "enablehealthpenalty", "enablehealthpenalty");
        addConfigurationName(builder, "health", "Health After Death");
        addConfigurationDescription(builder, "health", "health");
        addConfigurationName(builder, "startinghealthpenalty", "Starting Health Penalty");
        addConfigurationDescription(builder, "startinghealthpenalty", "startinghealthpenalty");
        addConfigurationName(builder, "genericdamage", "Generic Tool Damage");
        addConfigurationDescription(builder, "genericdamage", "genericdamage");
        addConfigurationName(builder, "armormods", "Armor Mods List");
        addConfigurationDescription(builder, "armormods", "armormodslist");
        addConfigurationName(builder, "armor", "Armor Items List");
        addConfigurationDescription(builder, "armor", "armorlist");
        addConfigurationName(builder, "tag", "Tool and Armor Tags List");
        addConfigurationDescription(builder, "tag", "taglist");
        addConfigurationName(builder, "disablemodpackbook", "Disable Modpack Book");
        addConfigurationDescription(builder, "disablemodpackbook", "disablemodpackbook");

        // Advancements
        addTranslationAdvancement(builder, "root.title", "Survivalist Essentials");
        addTranslationAdvancement(builder, "root.desc", "You're bound to ...");
        addTranslationAdvancement(builder, "sticks_and_stones.title", "Throw your Sticks and Stones");
        addTranslationAdvancement(builder, "sticks_and_stones.desc", "Pick up a rock from the ground and leaves might give some sticks");
        addTranslationAdvancement(builder, "flint_shard.title", "Rock + rock");
        addTranslationAdvancement(builder, "flint_shard.desc", "Maybe hitting your round rock on a cube rock will work");
        addTranslationAdvancement(builder, "crude_knife.title", "Crude, but still a knife");
        addTranslationAdvancement(builder, "crude_knife.desc", "Not a really good weapon, but pretty good as a lawnmower");
        addTranslationAdvancement(builder, "plant_string.title", "Crude String");
        addTranslationAdvancement(builder, "plant_string.desc", "Obtain a Plant String");
        addTranslationAdvancement(builder, "crude_hatchet.title", "Your first wood?");
        addTranslationAdvancement(builder, "crude_hatchet.desc", "Obtain a Crude Hatchet");
        addTranslationAdvancement(builder, "crude_saw.title", "Your first planks");
        addTranslationAdvancement(builder, "crude_saw.desc", "Obtain a Crude Saw");
        addTranslationAdvancement(builder, "getting_wood.title", "Getting Wood");
        addTranslationAdvancement(builder, "getting_wood.desc", "I got some wood.");

        // Guidebook
        addGuidebookTranslation(builder, "subtitle", "A Path to Survival");
        addGuidebookTranslation(builder, "intro", "Welcome to Survivalist Essentials!$(br)$(br)There are few very important things to remember when playing:$(br)$(li)A correct tool is required for harvesting.$(li)The only way to craft Wooden Planks and Sticks is with a saw.");
        addGuidebookTranslation(builder, "getting_started.name", "Getting Started");
        addGuidebookTranslation(builder, "getting_started.desc", "Gathering basic materials for crafting crude tools is essential for getting started. The following pages will cover what you MUST gather to survive.");
        addGuidebookTranslation(builder, "getting_started.materials.name", "Materials Gathering");
        addGuidebookTranslation(builder, "getting_started.materials.gather_stones.title", "Gathering Stones");
        addGuidebookTranslation(builder, "getting_started.materials.gather_stones.desc", "You may notice right away some small piles of rocks lying randomly on the ground throughout the world.$(br)$(br)Break and gather them quickly, they are a valuable AND necessary resource and needed for crafting tools.");
        addGuidebookTranslation(builder, "getting_started.materials.gather_sticks.title", "Get Sticks!!!");
        addGuidebookTranslation(builder, "getting_started.materials.gather_sticks.desc", "Sticks are essenial for creating your first Crude Tools. These are the most basic handles.$(br)$(br)Punching leaves and will drop sticks. The drop rate is not great but it will allow you to get sticks. Once you craft a knife, you can use it to increase the drop rate.");
        addGuidebookTranslation(builder, "getting_started.materials.flint_shards.title", "Flint Shards");
        addGuidebookTranslation(builder, "getting_started.materials.flint_shards.desc", "Crude tools need Flint Shards!$(br)$(br)Simply knapp (right-click) Rocks you've gathered on a hard surface (any regular Stone/Cobblestone/Granite/etc. hard blocks) and with a bit of luck, you can split them in half to obtain shards.$(br)$(br)Once you have the $(l:survivalistessentials:tools/crude_tools)Crude Knife$(), you can make more shards in your crafting grid.");
        addGuidebookTranslation(builder, "getting_started.materials.plant_fiber.title", "Plant Fiber");
        addGuidebookTranslation(builder, "getting_started.materials.plant_fiber.desc", "Next you'll need to get some Plant Fiber.$(br)$(br)Plant Fiber is a new drop when breaking grass or most plants with the Crude Knife. Grass is probably the easiest way to get Plant Fiber.$(br)$(br)You'll need Plant Fiber to craft Plant String used in crafting the Crude Hatchet, Crude Saw and Bandages.");
        addGuidebookTranslation(builder, "getting_started.materials.plant_string.title", "Plant String");
        addGuidebookTranslation(builder, "getting_started.materials.plant_string.desc", "Plant String is essential for crafting the Crude Hatchet and Crude Saw. Gather enough Plant Fiber!");
        addGuidebookTranslation(builder, "tools.name", "Tools");
        addGuidebookTranslation(builder, "tools.desc", "Tools are essential for gathering materials and getting your first wood planks!$(br)$(br)Punching trees is no longer an option, so you'll need these most basic tools to be able to craft better tools, or more advanced items using the crafting table.");
        addGuidebookTranslation(builder, "tools.crude_tools.name", "Crude Tools");
        addGuidebookTranslation(builder, "tools.crude_tools.knife.desc", "Crude Knife is essential for gathering Plant Fiber. This MUST be the first tool crafted to progress.");
        addGuidebookTranslation(builder, "tools.crude_tools.knife_recipes.name", "Crude Knife Recipes");
        addGuidebookTranslation(builder, "tools.crude_tools.knife_recipes.desc", "In addition to more shards, you can also craft in any crafting grid:$(br)$(li)String: knife and wool$(br)$(li)Sticks: knife and saplings");
        addGuidebookTranslation(builder, "tools.crude_tools.hatchet.desc", "Crude Hatchet is essential for gathering wood. You can now chop down trees!");
        addGuidebookTranslation(builder, "tools.crude_tools.crude_saw_blade.desc", "Crude Saw Blade is the first part for the Crude Saw. Blades are replaceable on the Saw. The crude blades have only half the output of sharper blade materials.");
        addGuidebookTranslation(builder, "tools.crude_tools.saw_handle.desc", "The Saw Handle is a reusable part for crafting the Crude Saw. Once the blade breaks, just reuse the left over handle to create a new Saw.");
        addGuidebookTranslation(builder, "tools.crude_tools.crude_saw.desc", "Crude Saw will allow you to craft planks! Upgrade the blade to increase plank output and durability.");
        addGuidebookTranslation(builder, "tools.crude_tools.planks.name", "Planks");
        addGuidebookTranslation(builder, "tools.crude_tools.planks.desc", "Here is an example recipe with the Crude Saw that will allow you to craft planks!");
        addGuidebookTranslation(builder, "tools.crude_tools.sticks.name", "Sticks");
        addGuidebookTranslation(builder, "tools.crude_tools.sticks.desc", "Here is an example recipe with the Crude Saw that will allow you to craft sticks!");
        addGuidebookTranslation(builder, "tools.improved_tools.name", "Improved Tools");
        addGuidebookTranslation(builder, "tools.improved_tools.subtitle", "Get sharper tools!");
        addGuidebookTranslation(builder, "tools.improved_tools.intro", "While crude tools will get you started, you'll want to create more durable tools.");
        addGuidebookTranslation(builder, "health.name", "Health");
        addGuidebookTranslation(builder, "health.desc", "It's a little more difficult getting started. To help with the process, some basic bandages are available to help in the early game.");
        addGuidebookTranslation(builder, "health.ingredients.name", "Ingredients");
        addGuidebookTranslation(builder, "health.ingredients.subtitle", "It's the little things...");
        addGuidebookTranslation(builder, "health.ingredients.desc", "There are several basic materials required for making bandages.");
        addGuidebookTranslation(builder, "health.ingredients.cloth.desc", "Cloth is an ingredient used for making Bandages.");
        addGuidebookTranslation(builder, "health.ingredients.mortar_and_pestle.desc", "Essential tool for crafting plant paste.");
        addGuidebookTranslation(builder, "health.ingredients.plant_paste.desc", "Essential ingredient for Ointment.");
        addGuidebookTranslation(builder, "health.ingredients.ointment.desc", "Ointment is an ingredient used for making Bandages.");
        addGuidebookTranslation(builder, "health.bandages.name", "Bandages");
        addGuidebookTranslation(builder, "health.bandages.subtitle", "Stop the bleeding...");
        addGuidebookTranslation(builder, "health.bandages.desc", "Crude bandages are simple to make. Bandages are a little more work, but do a much better job of stopping the bleeding.");
        addGuidebookTranslation(builder, "health.bandages.crude_bandage.desc", "Cheap bandage, great for early game.");
        addGuidebookTranslation(builder, "health.bandages.bandage.desc", "Better bandage, great for mid game.");
    }

    private void addTranslationItem(TranslationBuilder builder, String id, String name) {
        builder.add("item." + SurvivalistEssentials.MODID + "." + id, name);
    }

    private void addTranslationBlock(TranslationBuilder builder, String id, String name) {
        builder.add("block." + SurvivalistEssentials.MODID + "." + id, name);
    }

    private void addJeiDescription(TranslationBuilder builder, String id, String description) {
        builder.add("jei." + SurvivalistEssentials.MODID + ".description." + id, description);
    }

    private void addTranslationTooltip(TranslationBuilder builder, String id, String tooltip) {
        builder.add("tooltip." + SurvivalistEssentials.MODID + "." + id, tooltip);
    }

    private void addTranslationMessage(TranslationBuilder builder, String id, String message) {
        builder.add("message." + SurvivalistEssentials.MODID + "." + id, message);
    }

    private void addTranslationEffect(TranslationBuilder builder, String id, String effect) {
        builder.add("effect." + SurvivalistEssentials.MODID + "." + id, effect);
    }

    private void addTranslationPattern(TranslationBuilder builder, String id, String pattern) {
        builder.add("pattern." + SurvivalistEssentials.MODID + "." + id, pattern);
    }

    private void addTranslationAdvancement(TranslationBuilder builder, String id, String text) {
        builder.add("advancements." + SurvivalistEssentials.MODID + "." + id, text);
    }

    private void addGuidebookTranslation(TranslationBuilder builder, String id, String text) {
        builder.add("info." + SurvivalistEssentials.MODID + ".book." + id, text);
    }

    private void addConfigurationTitle(TranslationBuilder builder, String title) {
        builder.add(SurvivalistEssentials.MODID + ".configuration.title", title);
    }

    private void addConfigurationName(TranslationBuilder builder, String id, String name) {
        builder.add(SurvivalistEssentials.MODID + ".configuration." + id + ".name", name);
    }

    private void addConfigurationDescription(TranslationBuilder builder, String id) {
        builder.add(SurvivalistEssentials.MODID + ".configuration." + id + ".description", Translations.get(id));
    }

    private void addConfigurationDescription(TranslationBuilder builder, String id, String key) {
        builder.add(SurvivalistEssentials.MODID + ".configuration." + id + ".description", Translations.get(key));
    }

}
