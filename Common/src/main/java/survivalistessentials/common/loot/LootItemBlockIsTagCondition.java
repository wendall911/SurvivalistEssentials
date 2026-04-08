package survivalistessentials.common.loot;

import org.jspecify.annotations.NonNull;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public record LootItemBlockIsTagCondition(TagKey<Block> tag) implements LootItemCondition {

    public static final MapCodec<LootItemBlockIsTagCondition> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
        TagKey.codec(Registries.BLOCK).fieldOf("tag").forGetter(LootItemBlockIsTagCondition::tag)
    ).apply(builder, LootItemBlockIsTagCondition::new));

    public static LootItemBlockIsTagCondition isTag(TagKey<Block> tag) {
        return new LootItemBlockIsTagCondition(tag);
    }

    @Override
    public boolean test(LootContext lootContext) {
        BlockState state = lootContext.getOptionalParameter(LootContextParams.BLOCK_STATE);

        return state != null && state.is(this.tag);
    }

    @Override
    public @NonNull MapCodec<? extends LootItemCondition> codec() {
        return CODEC;
    }

}
