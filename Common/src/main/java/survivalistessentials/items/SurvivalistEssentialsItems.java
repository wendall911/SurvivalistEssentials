package survivalistessentials.items;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import survivalistessentials.items.item.Bandage;
import survivalistessentials.items.item.CrudeBandage;
import survivalistessentials.items.item.Mortar;
import survivalistessentials.items.item.SurvivalistEssentialsBook;
import survivalistessentials.items.item.WoodenCup;
import survivalistessentials.items.tool.CrudeHatchet;
import survivalistessentials.items.tool.SurvivalKnife;
import survivalistessentials.items.tool.SurvivalSaw;

import static net.minecraft.world.item.Item.BASE_ATTACK_SPEED_ID;
import static survivalistessentials.util.ResourceLocationHelper.prefix;

public final class SurvivalistEssentialsItems {

    private static final Map<ResourceLocation, Item> MISC_ITEMS = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> TOOLS_AND_UTILITIES = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> ALL = new LinkedHashMap<>();

    // Items
    public static Item FLINT_SHARD = make("flint_shard");
    public static Item PLANT_FIBER = make("plant_fiber");
    public static Item PLANT_STRING = make("plant_string");
    public static Item OINTMENT = make("ointment");
    public static Item PLANT_PASTE = make("plant_paste");
    public static Item CLOTH = make("cloth");

    // Books
    public static Item BOOK = registerBook("book", true);
    public static Item MODPACK_BOOK = registerBook("modpack_book", false);

    // Tools
    public static Item CRUDE_KNIFE = registerKnifeTool("crude_knife", ItemTiers.FLINT_TIER);
    public static Item BASIC_KNIFE = registerKnifeTool("basic_knife", ItemTiers.IRON_TIER);
    public static Item SHARP_KNIFE = registerKnifeTool("sharp_knife", ItemTiers.DIAMOND_TIER);
    public static Item CRUDE_HATCHET = registerHatchetTool("crude_hatchet", ItemTiers.STONE_TIER);
    public static Item SAW_HANDLE = registerSawTool("saw_handle", ItemTiers.NO_TIER, -8.0F);
    public static Item CRUDE_SAW_BLADE = registerSawBlade("crude_saw_blade");
    public static Item BASIC_SAW_BLADE = registerSawBlade("basic_saw_blade");
    public static Item SHARP_SAW_BLADE = registerSawBlade("sharp_saw_blade");
    public static Item CRUDE_SAW = registerSawTool("crude_saw", ItemTiers.FLINT_TIER, -4.0F);
    public static Item BASIC_SAW = registerSawTool("basic_saw", ItemTiers.IRON_TIER, -4.0F);
    public static Item SHARP_SAW = registerSawTool("sharp_saw", ItemTiers.DIAMOND_TIER, -4.0F);
    public static Item MORTAR_AND_PESTLE = registerMortar("mortar_and_pestle");

    // Bandages
    public static Item CRUDE_BANDAGE = make("crude_bandage", new CrudeBandage(
        (new Item.Properties()).stacksTo(8)
    ), false, true);
    public static Item BANDAGE = make("bandage", new Bandage(
        (new Item.Properties()).stacksTo(16)
    ), false, true);

    // Zombie Jesus
    public static Item WOODEN_CUP = make("wooden_cup", new WoodenCup(
        (new Item.Properties()).stacksTo(1)
    ), false, false);

    public static void init(BiConsumer<Item, ResourceLocation> consumer) {
        for (Map.Entry<ResourceLocation, Item> entry : ALL.entrySet()) {
            consumer.accept(entry.getValue(), entry.getKey());
        }
    }

    public static Item registerSawBlade(String name) {
        return make(name, new Item(
            new Item.Properties()
        ), true, false);
    }

    private static Item make(String name) {
        Item item = new Item(new Item.Properties());

        return make(name, item, false, true);
    }

    private static Item make(String name, Item item, boolean isTool, boolean noCategory) {
        ResourceLocation loc = prefix(name);

        ALL.put(loc, item);

        if (isTool) {
            TOOLS_AND_UTILITIES.put(loc, item);
        }

        if (noCategory) {
            MISC_ITEMS.put(loc, item);
        }

        return item;
    }

    private static Item registerKnifeTool(String name, Tier tier) {
        Item knifeTool = new SurvivalKnife(
            tier,
            new Item.Properties().attributes(
                SurvivalKnife.createAttributes(tier, 1, -1.4F)
            )
        );

        return make(name, knifeTool, true, false);
    }

    private static Item registerHatchetTool(String name, Tier tier) {
        Item hatchetTool = new CrudeHatchet(
            tier,
            new Item.Properties().attributes(
                CrudeHatchet.createAttributes(tier, 4, -3.0F)
            )
        );

        return make(name, hatchetTool, true, false);
    }

    private static Item registerSawTool(String name, Tier tier, float speed) {
        Item sawTool = new SurvivalSaw(name, tier, speed, new Item.Properties().attributes(ItemAttributeModifiers.builder().add(
            Attributes.ATTACK_SPEED,
            new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND
        ).build()));

        return make(name, sawTool, true, false);
    }

    private static Item registerMortar(String name) {
        return make(name, new Mortar(new Item.Properties()), false, false);
    }

    public static Item registerBook(String name, boolean noCategory) {
        return make(name, new SurvivalistEssentialsBook(), false, noCategory);
    }

    public static Map<ResourceLocation, Item> getAllIngredients() {
        return MISC_ITEMS;
    }

    public static Map<ResourceLocation, Item> getToolsAndUtilities() {
        return TOOLS_AND_UTILITIES;
    }

    public static Map<ResourceLocation, Item> getAll() {
        return ALL;
    }

}
