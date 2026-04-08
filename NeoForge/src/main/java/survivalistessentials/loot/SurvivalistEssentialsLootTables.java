package survivalistessentials.loot;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;

import com.google.common.base.Suppliers;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;

import survivalistessentials.common.SurvivalistEssentialsModule;

public class SurvivalistEssentialsLootTables extends SurvivalistEssentialsModule {

    public static DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<LootTableModifier>> ADD_LOOT = LOOT_MODIFIER_REGISTRY.register("add_loot", LootTableModifier.CODEC_SUPPLIER);

    public static void init() {}

    public static class LootTableModifier extends LootModifier {

        public static final Supplier<MapCodec<LootTableModifier>> CODEC_SUPPLIER = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec(inst ->
            codecStart(inst)
                .and(ItemStackTemplate.CODEC.fieldOf("additional").forGetter(LootTableModifier::getStack))
                .apply(inst, LootTableModifier::new)));

        private final ItemStackTemplate stack;

        public LootTableModifier(LootItemCondition[] conditionsIn, ItemStackTemplate itemStack) {
            super(conditionsIn);

            this.stack = itemStack;
        }

        public LootItemCondition[] getConditions() {
            return this.conditions;
        }

        public ItemStackTemplate getStack() {
            return stack;
        }

        @Override
        @NotNull
        protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
            generatedLoot.add(stack.create());

            return generatedLoot;
        }

        @Override
        public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
            return ADD_LOOT.get();
        }

    }

}
