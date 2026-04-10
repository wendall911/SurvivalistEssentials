package survivalistessentials.world.effect;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import survivalistessentials.config.ConfigHandler;

public class StopBleeding extends MobEffect {

    public StopBleeding() {
        super(MobEffectCategory.BENEFICIAL, 0xf7b7ad);
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel level, LivingEntity entity, int amplifier) {
        float healRate = (float) ConfigHandler.Common.healRate();

        if (entity.getHealth() >= entity.getMaxHealth()) {
            entity.removeEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.STOP_BLEEDING));
        }

        entity.heal(healRate * (float)(amplifier + 1));

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int j = 50 >> amplifier;

        if (j > 0) {
            return duration % j == 0;
        }
        else {
            return true;
        }
    }

}
