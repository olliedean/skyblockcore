package zen.lol.skyblockcore.commands.Economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import zen.lol.skyblockcore.managers.NoteManager;

public class WithdrawCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1){
                double value;
                try{
                   value = Double.parseDouble(args[0]);
                } catch(NumberFormatException e) {
                    player.sendMessage("That isn't a valid number format!");
                    return true;
                }
                if(value < 1){
                    player.sendMessage("The minimum withdraw is 1");
                    return true;
                }

                NoteManager.withdraw(player, value);

            } else {
                player.sendMessage("Too many arguments!");
            }
        } else {
            sender.sendMessage("This command cannot be sent through console");
        }
        return false;
    }
}
