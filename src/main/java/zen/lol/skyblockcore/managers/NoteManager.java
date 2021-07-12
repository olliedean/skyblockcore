package zen.lol.skyblockcore.managers;

import de.tr7zw.nbtapi.NBTItem;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zen.lol.skyblockcore.Skyblockcore;

import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    public static boolean withdraw(Player player, double value) {
        if(Skyblockcore.getEconomy().getBalance(player) > value){
            player.sendMessage("Insufficient funds!");
        } else {
            EconomyResponse response = Skyblockcore.getEconomy().withdrawPlayer(player, value);
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + " - $" + value);
            if (response.transactionSuccess()) {
                // User has enough
                ItemStack item = new ItemStack(Material.PAPER);
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        "&a&lMoney Note &7(RMB)"));
                List<String> lore = new ArrayList<String>();
                lore.add(ChatColor.GRAY + "Value: " + ChatColor.WHITE + value);
                lore.add(ChatColor.GRAY + "Withdrawn by: " + ChatColor.WHITE + player.getDisplayName());
                meta.setLore(lore);

                item.setItemMeta(meta);

                NBTItem nbti = new NBTItem(item);
                nbti.setDouble("noteValue", value);
                nbti.setString("noteOwner", String.valueOf(player.getUniqueId()));

                item = nbti.getItem();

                player.getInventory().addItem(item);
            } else {
                player.sendMessage("Insufficient funds!");
            }
        }
        return false;
    }

    public static boolean deposit(@org.jetbrains.annotations.NotNull Player player){
        ItemStack item = player.getInventory().getItemInMainHand();
        if(isNote(item)){
            NBTItem nbti = new NBTItem(item);
            Double value = nbti.getDouble("noteValue");
            EconomyResponse response = Skyblockcore.getEconomy().depositPlayer(player, value);
            if(response.transactionSuccess()){
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " - $" + value);
                item.setAmount(item.getAmount() - 1);
            }
        } else {
            player.sendMessage("This is impossible to deposit? How did you do this?");
        }
        return false;
    }


    public static boolean isNote(ItemStack item){
        NBTItem nbti = new NBTItem(item);
        return nbti.hasKey("noteValue");
    }
}
