package survivalistessentials.platform;

import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;

import survivalistessentials.platform.services.IREIHelper;

public class FabricREIHelper implements IREIHelper {

    @Override
    public boolean isVanillaItemType(EntryStack<?> entryStack) {
        //TODO Re-enable when REI updates to 26.1
        //return entryStack.getType() == VanillaEntryTypes.ITEM;
        return true;
    }

}
