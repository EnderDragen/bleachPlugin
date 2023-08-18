package com.enderdragen.samplekitsLVER.particles;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveSwordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && label.equalsIgnoreCase("givesword")) {
            Player player = (Player) sender;
            ItemStack customSword = CustomSword.createCustomSword();

            player.getInventory().addItem(customSword);
            player.sendMessage("You've received the Zanpakuto!");

            return true;
        }
        return false;
    }
}
