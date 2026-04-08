package survivalistessentials.platform;

import net.minecraft.world.entity.player.Player;

import tschipp.carryon.common.carry.CarryOnDataManager;

import survivalistessentials.platform.services.IPlatformHelper;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public boolean isCarryonKeyPressed(Player player) {
        // TODO RE-ENABLE when carryon updates to 26.1
        //return CarryOnDataManager.getCarryData(player).isKeyPressed();
        return false;
    }

}
