package ovh.corail.tombstone.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.items.IItemHandler;

/**
 * Called when recovering items from a grave and allowing to auto-equip custom slots (not cancelable)
 */
public class RestoreInventoryEvent extends Event {
    private final EntityPlayer player;
    private final BlockPos gravePos;
    private final IItemHandler inventory;
    private final String ownerName;
    private final long ownerDeathTime;

    public RestoreInventoryEvent(EntityPlayer player, BlockPos gravePos, IItemHandler inventory, String ownerName, long ownerDeathTime) {
        this.player = player;
        this.gravePos = gravePos;
        this.inventory = inventory;
        this.ownerName = ownerName;
        this.ownerDeathTime = ownerDeathTime;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public BlockPos getGravePos() {
        return gravePos;
    }

    public IItemHandler getInventory() {
        return inventory;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getOwnerDeathTime() {
        return ownerDeathTime;
    }
}
