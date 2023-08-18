package com.enderdragen.samplekitsLVER;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.Sound;

public class kitThree implements CommandExecutor
{
	 public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
	        if (!sender.hasPermission("ender.abilities.kit1")) {
	            sender.sendMessage(ChatColor.RED + "No permission.");
	            return true;
	        }
	        if (label.equalsIgnoreCase("kit1")) {
				
		        Player player = (Player) sender;
		 
		        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 0);
		        ItemStack[] items = {new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.SHIELD), new ItemStack(Material.DIAMOND_AXE)};
		        player.getInventory().addItem(items);
		        
		        player.sendMessage(ChatColor.GREEN + "You've been given an Ender Pearl");
		        return true;
		    }
			

		    return false;
		}

	}

	 
	 
	 
	 
	 
