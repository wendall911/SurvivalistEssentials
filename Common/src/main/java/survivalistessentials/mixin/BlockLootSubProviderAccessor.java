package survivalistessentials.mixin;

import java.util.Map;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;

import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockLootSubProvider.class)
public interface BlockLootSubProviderAccessor {

    @Accessor
    FeatureFlagSet getEnabledFeatures();

    @Accessor
    Map<ResourceKey<LootTable>, Builder> getMap();

}
