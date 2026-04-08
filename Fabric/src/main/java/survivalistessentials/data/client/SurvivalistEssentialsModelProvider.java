package survivalistessentials.data.client;

import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.NonNull;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;

import static survivalistessentials.util.ResourceLocationHelper.prefix;

public class SurvivalistEssentialsModelProvider extends FabricModelProvider {

    // TODO: Generate template file for loose rock currently just a copy of previously generated model file.
    private static final ModelTemplate LOOSE_ROCK = new ModelTemplate(
        Optional.of(prefix("block/loose_rock")),
        Optional.empty(),
        TextureSlot.ALL
    );

    public SurvivalistEssentialsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        for (Map.Entry<Identifier, Block> entry : SurvivalistEssentialsWorld.ALL_BLOCKS.entrySet()) {
            createLooseRock(generator, entry.getValue());
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(SurvivalistEssentialsWorld.ROCK_STONE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.FLINT_SHARD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.PLANT_FIBER, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.PLANT_STRING, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.OINTMENT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.PLANT_PASTE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.CLOTH, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_SAW_BLADE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.BASIC_SAW_BLADE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.SHARP_SAW_BLADE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.BASIC_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.SHARP_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.SAW_HANDLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_SAW, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.BASIC_SAW, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.SHARP_SAW, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.MORTAR_AND_PESTLE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_BANDAGE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.BANDAGE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.WOODEN_CUP, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.BOOK, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(SurvivalistEssentialsItems.MODPACK_BOOK, ModelTemplates.FLAT_ITEM);
    }

    public void createLooseRock(BlockModelGenerators generator, Block block) {
        TextureMapping textureMapping = new TextureMapping();

        textureMapping.put(TextureSlot.ALL, TextureMapping.getBlockTexture(block));

        Identifier modelLocation = LOOSE_ROCK.create(block, textureMapping, generator.modelOutput);
        MultiVariant variant = BlockModelGenerators.plainVariant(modelLocation);

        generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, variant));
        generator.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
    }

}
