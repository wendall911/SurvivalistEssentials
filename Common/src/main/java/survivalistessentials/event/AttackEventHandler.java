package survivalistessentials.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.sound.SurvivalistEssentialsSounds;
import survivalistessentials.util.ItemUse;

public class AttackEventHandler {

    public static boolean hasGenericDamage(DamageSource source) {
        if (source.getDirectEntity() instanceof Player player) {
            if (!player.isCreative()) {
                final ItemStack handStack = player.getMainHandItem();
                final Level level = player.level();
                boolean checkAllowed = source.getMsgId().contains("player");
                boolean bypassArmor = source.is(DamageTypeTags.BYPASSES_ARMOR);

                if (bypassArmor) {
                    checkAllowed = false;
                }

                if (checkAllowed && (handStack.is(Items.AIR) || !ItemUse.isAllowedTool(handStack))) {
                    if (level.isClientSide() && ConfigHandler.Client.enableFailSound() && ConfigHandler.Common.genericDamage() == 0.0F) {
                        level.playSound(null, player.getOnPos(), SurvivalistEssentialsSounds.SWORD_FAIL, SoundSource.PLAYERS, 0.4F, 1.0F);
                    }

                    return true;
                }
            }
        }

        return false;
    }

}
