package com.enderdragen.samplekitsLVER;

import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;



import org.bukkit.*;

public class KitTwo1 implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 if (!sender.hasPermission("ender.abilities.kit1")) {
	            sender.sendMessage(ChatColor.RED + "No permission.");
	            return true;
	        }
	        
	        if (!sender.hasPermission("ender.abilities.kit3")) {
	        	  if (!(sender instanceof Player)) {
	                  sender.sendMessage(ChatColor.GOLD + "You can only execute this command as a player");
	                  return true;
	              }
	        
	        
	        if (label.equalsIgnoreCase("kit22")) {
	       	   	}
		        Player player = (Player) sender;
		 
		        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 0);
		       
		      
		        ItemStack item1 = new ItemStack(Material.DIAMOND_HELMET,1);
		        ItemStack item2 = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
		        ItemStack item3 = new ItemStack(Material.DIAMOND_LEGGINGS,1);
		        ItemStack item4 = new ItemStack(Material.DIAMOND_BOOTS,1);
		      
		        ItemStack item5 = new ItemStack(Material.DIAMOND_AXE,1);
		       
		        
		        
		     
		        item1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        item2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item2.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        item3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item3.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        item4.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item4.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        
		        
		        item5.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		        ItemMeta meta = item5.getItemMeta();
		        item5.setItemMeta(meta);
		        
		        meta.setDisplayName(ChatColor.GOLD + "Axe of  Death...");
		        player.getInventory().addItem(item1, item2, item3, item4, item5);
		        
		        
		        player.sendMessage(ChatColor.GREEN + "You've recieved the" + " " +ChatColor.BOLD + "Prototype" + " " +"Kit");
		        return true;
	 } {
		 
	        if (label.equalsIgnoreCase("k22")) {
				
		        Player player = (Player) sender;
		 
		        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 0);
		        ItemStack item1 = new ItemStack(Material.DIAMOND_HELMET,1);
		        ItemStack item2 = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
		        ItemStack item3 = new ItemStack(Material.DIAMOND_LEGGINGS,1);
		        ItemStack item4 = new ItemStack(Material.DIAMOND_BOOTS,1);
		      
		        ItemStack item5 = new ItemStack(Material.DIAMOND_AXE,1);
		       
		        
		        
		     
		        item1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item1.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        item2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item2.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        item3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item3.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        item4.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		        item4.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		        
		        
		        item5.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		        player.getInventory().addItem(item1, item2, item3, item4, item5);
		        
		        
		        player.sendMessage(ChatColor.GREEN + "You've recieved the" + ChatColor.BOLD + "Prototype" + ChatColor.GREEN+"Kit");
		     
		    }

		    return false;
		}
}
}



	 
	 
	 
	 
	 
