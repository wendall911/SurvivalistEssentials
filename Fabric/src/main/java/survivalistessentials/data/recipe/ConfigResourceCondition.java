package survivalistessentials.data.recipe;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.Identifier;

import survivalistessentials.config.ConfigHandler;

import static survivalistessentials.util.ResourceLocationHelper.prefix;

public record ConfigResourceCondition(String configValue) implements ResourceCondition {

    private static final Identifier ID = prefix("config_disabled");
    public static final MapCodec<ConfigResourceCondition> CODEC = RecordCodecBuilder.mapCodec(b -> b.group(
        Codec.STRING.fieldOf("config_disabled").forGetter(ConfigResourceCondition::configValue)
    ).apply(b, ConfigResourceCondition::new));
    private static final ResourceConditionType<ConfigResourceCondition> TYPE = ResourceConditionType.create(ID, CODEC);

    public static void register() {
        ResourceConditions.register(TYPE);
    }

    @Override
    public @NonNull ResourceConditionType<?> getType() {
        return TYPE;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoLookup registryInfoLookup) {
        return !ConfigHandler.Common.getConfigValue(configValue);
    }

}
