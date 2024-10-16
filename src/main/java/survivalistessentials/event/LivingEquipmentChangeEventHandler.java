package survivalistessentials.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

import survivalistessentials.sound.Sounds;
import survivalistessentials.util.ItemUse;

public class LivingEquipmentChangeEventHandler {

    @SubscribeEvent
    public static void onChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            EquipmentSlot slot = event.getSlot();

            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR
                    && ItemUse.isArmor(event.getTo())
                    && !ItemUse.isAllowedArmor(event.getTo())) {
                final ItemStack itemstack = player.getItemBySlot(slot);
                final Level level = player.level();

                level.playSound(null, player.getOnPos(), Sounds.ARMOR_FAIL, SoundSource.PLAYERS, 0.4F, 1.0F);

                if (!player.addItem(itemstack)) {
                    NonNullList<ItemStack> dropStack = NonNullList.withSize(1, itemstack);
                    Vec3 pos = player.getEyePosition(1.0F);

                    Containers.dropContents(player.level(), new BlockPos((int)pos.x(), (int)pos.y(), (int)pos.z()), dropStack);
                }

                player.setItemSlot(slot, ItemStack.EMPTY);
            }
        }
    }

}
