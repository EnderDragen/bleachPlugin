package com.enderdragen.samplekitsLVER;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 if (!sender.hasPermission("ender.abilities")) {
	            sender.sendMessage(ChatColor.RED + "No permission.");
	            return true;
	        }
		Player player = (Player) sender;
		
		player.setFoodLevel(20);
		player.setHealth(20.0);
		player.sendMessage(ChatColor.GREEN + " "+ "Healed!");
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
		
		return false;
		
	}
	

}
