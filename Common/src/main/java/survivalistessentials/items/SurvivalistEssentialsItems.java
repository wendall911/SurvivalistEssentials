package survivalistessentials.items;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
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

    private static final Map<Identifier, Item> MISC_ITEMS = new LinkedHashMap<>();
    private static final Map<Identifier, Item> TOOLS_AND_UTILITIES = new LinkedHashMap<>();
    private static final Map<Identifier, Item> ALL = new LinkedHashMap<>();

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
    public static Item CRUDE_KNIFE = registerKnifeTool("crude_knife", ToolMaterials.FLINT);
    public static Item BASIC_KNIFE = registerKnifeTool("basic_knife", ToolMaterials.IRON);
    public static Item SHARP_KNIFE = registerKnifeTool("sharp_knife", ToolMaterials.DIAMOND);
    public static Item CRUDE_HATCHET = registerHatchetTool("crude_hatchet", ToolMaterials.STONE);
    public static Item SAW_HANDLE = registerSawTool("saw_handle", ToolMaterials.NONE, -8.0F);
    public static Item CRUDE_SAW_BLADE = registerSawBlade("crude_saw_blade");
    public static Item BASIC_SAW_BLADE = registerSawBlade("basic_saw_blade");
    public static Item SHARP_SAW_BLADE = registerSawBlade("sharp_saw_blade");
    public static Item CRUDE_SAW = registerSawTool("crude_saw", ToolMaterials.FLINT, -4.0F);
    public static Item BASIC_SAW = registerSawTool("basic_saw", ToolMaterials.IRON, -4.0F);
    public static Item SHARP_SAW = registerSawTool("sharp_saw", ToolMaterials.DIAMOND, -4.0F);
    public static Item MORTAR_AND_PESTLE = registerMortar("mortar_and_pestle");

    // Bandages
    public static Item CRUDE_BANDAGE = make("crude_bandage", new CrudeBandage(
        (new Item.Properties()).stacksTo(8).setId(ResourceKey.create(Registries.ITEM, prefix("crude_bandage")))
    ), false, true);
    public static Item BANDAGE = make("bandage", new Bandage(
        (new Item.Properties()).stacksTo(16).setId(ResourceKey.create(Registries.ITEM, prefix("bandage")))
    ), false, true);

    // Zombie Jesus
    public static Item WOODEN_CUP = make("wooden_cup", new WoodenCup(
        (new Item.Properties()).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, prefix("wooden_cup")))
    ), false, false);

    public static void init(BiConsumer<Item, Identifier> consumer) {
        for (Map.Entry<Identifier, Item> entry : ALL.entrySet()) {
            consumer.accept(entry.getValue(), entry.getKey());
        }
    }

    public static Item registerSawBlade(String name) {
        return make(name, new Item(
            new Item.Properties().setId(ResourceKey.create(Registries.ITEM, prefix(name)))
        ), true, false);
    }

    private static Item make(String name) {
        Item item = new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, prefix(name))));

        return make(name, item, false, true);
    }

    private static Item make(String name, Item item, boolean isTool, boolean noCategory) {
        Identifier loc = prefix(name);

        ALL.put(loc, item);

        if (isTool) {
            TOOLS_AND_UTILITIES.put(loc, item);
        }

        if (noCategory) {
            MISC_ITEMS.put(loc, item);
        }

        return item;
    }

    private static Item registerKnifeTool(String name, ToolMaterial toolMaterial) {
        Item knifeTool = new SurvivalKnife(
            new Item.Properties().sword(
                toolMaterial,
                1.0F,
                -3.0F
            ).setId(ResourceKey.create(Registries.ITEM, prefix(name)))
        );

        return make(name, knifeTool, true, false);
    }

    private static Item registerHatchetTool(String name, ToolMaterial toolMaterial) {
        Item hatchetTool = new CrudeHatchet(
            toolMaterial,
            4.0F,
            -3.0F,
            new Item.Properties().setId(ResourceKey.create(Registries.ITEM, prefix(name)))
        );

        return make(name, hatchetTool, true, false);
    }

    private static Item registerSawTool(String name, ToolMaterial toolMaterial, float speed) {
        Item sawTool = new SurvivalSaw(name, toolMaterial, speed, 0.0F, new Item.Properties().attributes(ItemAttributeModifiers.builder().add(
            Attributes.ATTACK_SPEED,
            new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND
        ).build()).setId(ResourceKey.create(Registries.ITEM, prefix(name))));

        return make(name, sawTool, true, false);
    }

    private static Item registerMortar(String name) {
        return make(name, new Mortar(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, prefix(name)))), false, false);
    }

    public static Item registerBook(String name, boolean noCategory) {
        return make(name, new SurvivalistEssentialsBook(
            new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, prefix(name)))
        ), false, noCategory);
    }

    public static Map<Identifier, Item> getAllIngredients() {
        return MISC_ITEMS;
    }

    public static Map<Identifier, Item> getToolsAndUtilities() {
        return TOOLS_AND_UTILITIES;
    }

    public static Map<Identifier, Item> getAll() {
        return ALL;
    }

}
