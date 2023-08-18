package com.enderdragen.samplekitsLVER;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		 if (label.equalsIgnoreCase("feed")) {
		player.setFoodLevel(20);
		player.sendMessage(ChatColor.GREEN + "Fed!");
		 }
		
		return false;
		
	}
	

}
