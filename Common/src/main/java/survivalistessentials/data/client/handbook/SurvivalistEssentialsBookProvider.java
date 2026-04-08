package survivalistessentials.data.client.handbook;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;

import handbook.api.data.BookBuilder;
import handbook.api.data.HandbookBookProvider;

import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;
import survivalistessentials.SurvivalistEssentials;

import static survivalistessentials.util.ResourceLocationHelper.loc;
import static survivalistessentials.util.ResourceLocationHelper.prefix;

public class SurvivalistEssentialsBookProvider extends HandbookBookProvider {

    public SurvivalistEssentialsBookProvider(@NotNull final PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
        super(packOutput, SurvivalistEssentials.MODID, "en_us", lookupProvider);
    }

    @Override
    protected void addBooks(Consumer<BookBuilder> consumer, HolderLookup.Provider provider) {
        String bookName = "item.survivalistessentials.book";
        String landingText = "info.survivalistessentials.book.intro";
        String subTitle = "info.survivalistessentials.book.subtitle";

        BookBuilder bookBuilder = createBookBuilder("book", bookName, landingText)
            .setSubtitle(subTitle)
            .setCustomBookItem(new ItemStackTemplate(SurvivalistEssentialsItems.BOOK))
            .setCreativeTab(SurvivalistEssentials.MODID + ".items")
            .setModel(SurvivalistEssentials.MODID + ":book")
            .setDontGenerateBook(true)
            .setShowProgress(false)
            .setUseBlockyFont(true)
            .setI18n(true);

        bookBuilder = addGettingStarted(bookBuilder);
        bookBuilder = addTools(bookBuilder);
        bookBuilder = addHealth(bookBuilder);

        bookBuilder.build(consumer);
    }

    private BookBuilder addGettingStarted(BookBuilder bookBuilder) {
        return bookBuilder.addCategory(
                "getting_started",
                "info.survivalistessentials.book.getting_started.name",
                "info.survivalistessentials.book.getting_started.desc",
                new ItemStackTemplate(SurvivalistEssentialsWorld.ROCK_STONE)
        )
        .setSortnum(0)
        .addEntry(
        "getting_started/materials",
     "info.survivalistessentials.book.getting_started.materials.name",
            new ItemStackTemplate(SurvivalistEssentialsWorld.ROCK_STONE)
        )
        .addSpotlightPage(new ItemStackTemplate(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK.asItem()))
            .setTitle("info.survivalistessentials.book.getting_started.materials.gather_stones.title")
            .setText("info.survivalistessentials.book.getting_started.materials.gather_stones.desc").build()
        .addSpotlightPage(new ItemStackTemplate(Items.OAK_LEAVES))
            .setTitle("info.survivalistessentials.book.getting_started.materials.gather_sticks.title")
            .setText("info.survivalistessentials.book.getting_started.materials.gather_sticks.desc").build()
        .addSpotlightPage(new ItemStackTemplate(SurvivalistEssentialsItems.FLINT_SHARD))
            .setTitle("info.survivalistessentials.book.getting_started.materials.flint_shards.title")
            .setText("info.survivalistessentials.book.getting_started.materials.flint_shards.desc").build()
        .addSpotlightPage(new ItemStackTemplate(SurvivalistEssentialsItems.PLANT_FIBER))
            .setTitle("info.survivalistessentials.book.getting_started.materials.plant_fiber.title")
            .setText("info.survivalistessentials.book.getting_started.materials.plant_fiber.desc").build()
        .addCraftingPage(prefix("plant_string"))
            .setTitle("info.survivalistessentials.book.getting_started.materials.plant_string.title")
            .setText("info.survivalistessentials.book.getting_started.materials.plant_string.desc").build()
        .build().build();
    }

    private BookBuilder addTools(BookBuilder bookBuilder) {
        return bookBuilder.addCategory(
            "tools",
            "info.survivalistessentials.book.tools.name",
            "info.survivalistessentials.book.tools.desc",
            new ItemStackTemplate(SurvivalistEssentialsItems.SHARP_SAW)
        )
        .setSortnum(1)
        .addEntry(
            "tools/crude_tools",
            "info.survivalistessentials.book.tools.crude_tools.name",
            new ItemStackTemplate(SurvivalistEssentialsItems.CRUDE_SAW)
        )
        .addCraftingPage(prefix("crude_knife"))
            .setTitle("item.survivalistessentials.crude_knife")
            .setText("info.survivalistessentials.book.tools.crude_tools.knife.desc").build()
        .addCraftingPage(prefix("flint_shard"))
            .setTitle("info.survivalistessentials.book.tools.crude_tools.knife_recipes.name")
            .setText("info.survivalistessentials.book.tools.crude_tools.knife_recipes.desc").build()
        .addCraftingPage(prefix("crude_hatchet"))
            .setTitle("item.survivalistessentials.crude_hatchet")
            .setText("info.survivalistessentials.book.tools.crude_tools.hatchet.desc").build()
        .addCraftingPage(prefix("crude_saw_blade"))
            .setTitle("item.survivalistessentials.crude_saw_blade")
            .setText("info.survivalistessentials.book.tools.crude_tools.crude_saw_blade.desc").build()
        .addCraftingPage(prefix("saw_handle_with_plant_string"))
            .setTitle("item.survivalistessentials.saw_handle")
            .setText("info.survivalistessentials.book.tools.crude_tools.saw_handle.desc").build()
        .addCraftingPage(prefix("crude_saw"))
            .setTitle("item.survivalistessentials.crude_saw")
            .setText("info.survivalistessentials.book.tools.crude_tools.crude_saw.desc").build()
        .addCraftingPage(loc("minecraft", "oak_planks"))
            .setTitle("info.survivalistessentials.book.tools.crude_tools.planks.name")
            .setText("info.survivalistessentials.book.tools.crude_tools.planks.desc").build()
        .addCraftingPage(loc("minecraft","stick"))
            .setTitle("info.survivalistessentials.book.tools.crude_tools.sticks.name")
            .setText("info.survivalistessentials.book.tools.crude_tools.sticks.desc").build()
        .build()
        .addEntry(
            "tools/improved_tools",
            "info.survivalistessentials.book.tools.improved_tools.name",
            new ItemStackTemplate(SurvivalistEssentialsItems.SHARP_SAW_BLADE)
        )
        .setSortnum(1)
        .addSpotlightPage(new ItemStackTemplate(SurvivalistEssentialsItems.SHARP_SAW))
            .setText("info.survivalistessentials.book.tools.improved_tools.intro")
            .setTitle("info.survivalistessentials.book.tools.improved_tools.subtitle").build()
        .addCraftingPage(prefix("basic_saw"))
            .setRecipe2(prefix("basic_saw_blade")).build()
        .addCraftingPage(prefix("sharp_saw"))
            .setRecipe2(prefix("sharp_saw_blade")).build()
        .addCraftingPage(prefix("basic_knife"))
            .setRecipe2(prefix("sharp_knife")).build()
        .build().build();
    }

    private BookBuilder addHealth(BookBuilder bookBuilder) {
        return bookBuilder.addCategory(
            "health",
            "info.survivalistessentials.book.health.name",
            "info.survivalistessentials.book.health.desc",
            new ItemStackTemplate(SurvivalistEssentialsItems.BANDAGE)
        )
        .setSortnum(2)
        .addEntry(
            "health/ingredients",
            "info.survivalistessentials.book.health.ingredients.name",
            new ItemStackTemplate(SurvivalistEssentialsItems.CLOTH)
        )
        .addSpotlightPage(new ItemStackTemplate(SurvivalistEssentialsItems.CLOTH))
            .setText("info.survivalistessentials.book.health.ingredients.desc")
            .setTitle("info.survivalistessentials.book.health.ingredients.subtitle").build()
        .addCraftingPage(prefix("cloth"))
            .setTitle("item.survivalistessentials.cloth")
            .setText("info.survivalistessentials.book.health.ingredients.cloth.desc").build()
        .addCraftingPage(prefix("mortar_and_pestle"))
            .setTitle("item.survivalistessentials.mortar_and_pestle")
            .setText("info.survivalistessentials.book.health.ingredients.mortar_and_pestle.desc").build()
        .addCraftingPage(prefix("plant_paste"))
            .setTitle("item.survivalistessentials.plant_paste")
            .setText("info.survivalistessentials.book.health.ingredients.plant_paste.desc").build()
        .addCraftingPage(prefix("ointment"))
            .setTitle("item.survivalistessentials.ointment")
            .setText("info.survivalistessentials.book.health.ingredients.ointment.desc").build().build()
        .addEntry(
            "health/bandages",
            "info.survivalistessentials.book.health.bandages.name",
            new ItemStackTemplate(SurvivalistEssentialsItems.BANDAGE)
        )
        .setSortnum(1)
        .addSpotlightPage(new ItemStackTemplate(SurvivalistEssentialsItems.BANDAGE))
            .setText("info.survivalistessentials.book.health.bandages.desc")
            .setTitle("info.survivalistessentials.book.health.bandages.subtitle").build()
        .addCraftingPage(prefix("crude_bandage"))
            .setTitle("item.survivalistessentials.crude_bandage")
            .setText("info.survivalistessentials.book.health.bandages.crude_bandage.desc").build()
        .addCraftingPage(prefix("bandage"))
            .setTitle("item.survivalistessentials.bandage")
            .setText("info.survivalistessentials.book.health.bandages.bandage.desc").build()
        .build().build();
    }

}
