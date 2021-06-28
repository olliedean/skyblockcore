package zen.lol.skyblockcore.events;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import zen.lol.skyblockcore.managers.NoteManager;

public class MoneyNoteListener implements Listener {
    @EventHandler
    public void playerDepositNote(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR ||
                event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.hasItem()) {
                if (NoteManager.isNote(event.getItem())) {
                    event.setCancelled(true);
                    NoteManager.deposit(player);
                }
            }
        }

    }
}
