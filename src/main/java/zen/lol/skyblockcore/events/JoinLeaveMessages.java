package zen.lol.skyblockcore.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveMessages implements Listener {

    @EventHandler
    public void JoinMessage(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GRAY + " [" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.WHITE +
                player.getDisplayName());

        // once we save user data, make a check for a welcome message
    }

    @EventHandler
    public void LeaveMessage(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY + " [" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.WHITE +
                player.getDisplayName());
    }
}
