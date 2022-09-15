package survivalessentials.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import survivalessentials.config.ConfigHandler;
import survivalessentials.sound.Sounds;
import survivalessentials.SurvivalEssentials;
import survivalessentials.util.ItemUse;

@Mod.EventBusSubscriber(modid = SurvivalEssentials.MODID)
public class LivingEquipmentChangeEventHandler {

    @SubscribeEvent
    public static void onChange(LivingEquipmentChangeEvent event) {
        if (ConfigHandler.Common.enforceWhitelistArmor() && event.getEntityLiving() instanceof Player player) {
            EquipmentSlot slot = event.getSlot();

            SurvivalEssentials.LOGGER.warn("Armor is of type: %s", event.getTo().getItem().getClass());

            if (slot.getType() == EquipmentSlot.Type.ARMOR
                    && ItemUse.isArmor(event.getTo())
                    && !ItemUse.isWhitelistArmor(event.getTo())) {
                final ItemStack itemstack = player.getItemBySlot(slot);
                final Level level = player.getLevel();

                level.playSound(null, player.getOnPos(), Sounds.ARMOR_FAIL.get(), SoundSource.BLOCKS, 0.4F, 1.0F);


                if (!player.addItem(itemstack)) {
                    NonNullList<ItemStack> dropStack = NonNullList.withSize(1, itemstack);

                    Containers.dropContents(player.getLevel(), new BlockPos(player.getEyePosition()), dropStack);
                }

                player.setItemSlot(slot, ItemStack.EMPTY);
            }
        }
    }

}
