package survivalistessentials.common.loot;

import java.util.function.BiConsumer;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import static survivalistessentials.util.ResourceLocationHelper.prefix;

public class SurvivalistEssentialsLootConditionTypes {

    public static final Identifier BLOCK_IS_TAG_ID = prefix("block_is_tag");

    public static void init(BiConsumer<MapCodec<? extends LootItemCondition>, Identifier> consumer) {
        consumer.accept(LootItemBlockIsTagCondition.CODEC, BLOCK_IS_TAG_ID);
    }

}
