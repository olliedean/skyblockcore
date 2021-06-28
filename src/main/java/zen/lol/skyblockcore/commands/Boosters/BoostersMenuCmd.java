package zen.lol.skyblockcore.commands.Boosters;


import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zen.lol.skyblockcore.Skyblockcore;

import java.util.ArrayList;
import java.util.List;

public class BoostersMenuCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            ChestGui gui = new ChestGui(3, "Boosters");
            gui.setOnGlobalClick(event -> event.setCancelled(true));
            OutlinePane navigationPane = new OutlinePane(2, 1, 5, 1);
            ItemStack xpItem = new ItemStack(Material.EXPERIENCE_BOTTLE);
            ItemMeta meta = xpItem.getItemMeta();
            assert meta != null;
            meta.setDisplayName(ChatColor.GREEN + "XP Boosts");
            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "RMB to view your");
            lore.add(ChatColor.GRAY + "XP Boosts");
            meta.setLore(lore);
            xpItem.setItemMeta(meta);

            ItemStack moneyItem = new ItemStack(Material.PAPER);
            meta = moneyItem.getItemMeta();
            assert meta != null;
            meta.setDisplayName(ChatColor.GOLD + "Money Boosts");
            lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "RMB to view your");
            lore.add(ChatColor.GRAY + "XP Boosts");
            meta.setLore(lore);
            moneyItem.setItemMeta(meta);


            navigationPane.addItem(new GuiItem(xpItem, event -> {
                // handle interaction with booster menus
            }));
            navigationPane.addItem(new GuiItem(moneyItem, event -> {
                // handle interaction with booster menus
            }));
            gui.addPane(navigationPane);
            gui.show(player);


        } else {
            sender.sendMessage("You cannot use this command as console");
        }
        return false;
    }
}
