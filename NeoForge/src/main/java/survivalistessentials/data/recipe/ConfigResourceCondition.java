package survivalistessentials.data.recipe;

import org.jspecify.annotations.NonNull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;

import net.neoforged.neoforge.common.conditions.ICondition;

import survivalistessentials.config.ConfigHandler;

public record ConfigResourceCondition(String configValue) implements ICondition {

    public static final String ID = "config_disabled";
    public static final MapCodec<ConfigResourceCondition> CODEC = RecordCodecBuilder.mapCodec(b -> b.group(
        Codec.STRING.fieldOf(ID).forGetter(ConfigResourceCondition::configValue)
    ).apply(b, ConfigResourceCondition::new));

    @Override
    public @NonNull String toString() {
        return ID + "(\"" + configValue + "\")";
    }

    @Override
    public boolean test(@NonNull IContext context) {
        return !ConfigHandler.Common.getConfigValue(configValue);
    }

    @Override
    public @NonNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

}