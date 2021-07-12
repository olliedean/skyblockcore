package zen.lol.skyblockcore.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import zen.lol.skyblockcore.Skyblockcore;

import static sun.security.krb5.SCDynamicStoreConfig.getConfig;

public class JoinLeaveMessages implements Listener {
    Skyblockcore Main = Skyblockcore.getPlugin(Skyblockcore.class);

    @EventHandler
    public void JoinMessage(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String joinMessage = Main.getConfig().getString("join-message");
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage.replace("%player%", player.getName())));

        // once we save user data, make a check for a welcome message
    }

    @EventHandler
    public void LeaveMessage(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String leaveMessage = Main.getConfig().getString("leave-message");
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', leaveMessage.replace("%player%", player.getName())));
    }
}
