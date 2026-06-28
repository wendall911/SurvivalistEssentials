package survivalistessentials.util;

import java.util.List;

import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.EnchantmentPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

import survivalistessentials.common.TagManager;
import survivalistessentials.common.loot.LootItemBlockIsTagCondition;

public class LootConditionHelper {

    public static LootItemCondition[] createKnifeChanceCondition(float chance, TagKey<Block> tag, RegistryLookup<Item> itemRegistryLookup) {
        return new LootItemCondition[] {
            LootItemRandomChanceCondition.randomChance(chance).build(),
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(itemRegistryLookup, TagManager.Items.KNIFE_TOOLS)).build(),
            LootItemBlockIsTagCondition.isTag(tag)
        };
    }

    /**
     * Returns a list of Conditions where a player must have broken the block without silk touch and/or shears like items, with the specified chance
     * Provided by Insane96 <delvillano.alberto@gmail.com>
     */
    public static LootItemCondition[] createExtraStickDropConditions(float chance, TagKey<Block> tag,
            RegistryLookup<Enchantment> enchantmentRegistryLookup, RegistryLookup<Item> itemRegistryLookup,
            RegistryLookup<EntityType<?>> entityRegistryLookup) {
        return new LootItemCondition[] {
            LootItemRandomChanceCondition.randomChance(chance).build(),
            LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(entityRegistryLookup, EntityTypes.PLAYER)).build(),
            LootItemBlockIsTagCondition.isTag(tag),
            hasSilkTouch(enchantmentRegistryLookup).invert().build(),
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(itemRegistryLookup, TagManager.Items.SHEAR_TOOLS)).invert().build()
        };
    }

    private static LootItemCondition.Builder hasSilkTouch(RegistryLookup<Enchantment> enchantmentRegistryLookup) {
        return MatchTool.toolMatches(
            ItemPredicate.Builder.item()
                .withComponents(
                    DataComponentMatchers.Builder.components()
                        .partial(
                            DataComponentPredicates.ENCHANTMENTS,
                            EnchantmentsPredicate.enchantments(
                                List.of(
                                    new EnchantmentPredicate(
                                        enchantmentRegistryLookup.getOrThrow(Enchantments.SILK_TOUCH),
                                        MinMaxBounds.Ints.atLeast(1)
                                    )
                                )
                            )
                        )
                        .build()
                )
        );
    }

}
