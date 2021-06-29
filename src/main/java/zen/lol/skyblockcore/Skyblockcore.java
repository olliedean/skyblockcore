package zen.lol.skyblockcore;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import zen.lol.skyblockcore.commands.Economy.WithdrawCmd;
import zen.lol.skyblockcore.events.JoinLeaveMessages;
import zen.lol.skyblockcore.events.MoneyNoteListener;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Skyblockcore extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.getCommand("withdraw").setExecutor(new WithdrawCmd());
        getServer().getPluginManager().registerEvents(new MoneyNoteListener(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveMessages(), this);
        this.saveDefaultConfig();
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getServer().getOnlinePlayers()){
                    List<String> messages = getConfig().getStringList("messages");
                    int count = messages.size();
                    Random rand = new Random();
                    int i = rand.nextInt(count);

                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + " ? " +
                            ChatColor.translateAlternateColorCodes('&', messages.get(i))
                    );
                }
            }
        }, 6000L, 6000L);
    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
