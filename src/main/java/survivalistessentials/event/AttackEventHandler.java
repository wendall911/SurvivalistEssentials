package survivalistessentials.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.sound.Sounds;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.ItemUse;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class AttackEventHandler {

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (!player.isCreative()) {
                final ItemStack handStack = player.getMainHandItem();
                final Level level = player.level();
                boolean checkAllowed = event.getSource().getMsgId().contains("player");
                boolean bypassArmor = event.getSource().is(DamageTypeTags.BYPASSES_ARMOR);

                if (bypassArmor) {
                    checkAllowed = false;
                }

                if (checkAllowed && (handStack.is(Items.AIR) || !ItemUse.isAllowedTool(handStack))) {
                    if (!level.isClientSide && ConfigHandler.Client.enableFailSound() && ConfigHandler.Common.genericDamage() == 0.0F) {
                        level.playSound(null, player.getOnPos(), Sounds.SWORD_FAIL.get(), SoundSource.PLAYERS, 0.4F, 1.0F);
                    }

                    event.setAmount(ConfigHandler.Common.genericDamage());

                    if (ConfigHandler.Common.genericDamage() == 0.0F) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

}
