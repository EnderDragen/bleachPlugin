package com.enderdragen.samplekitsLVER;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class testClass implements CommandExecutor {

	  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	        if (command.getName().equalsIgnoreCase("openanvil")) {
	            if (sender instanceof Player) {
	                Player player = (Player) sender;
	                openVirtualAnvil(player);
	            } else {
	                sender.sendMessage("Only players can use this command.");
	            }
	            return true;
	        }
	        return false;
	    }

	    private void openVirtualAnvil(Player player) {
	        Inventory anvilInventory = Bukkit.createInventory(player, 9, "Virtual Anvil");
	        player.openInventory(anvilInventory);
	    }

	    @EventHandler
	    public void onInventoryClick(InventoryClickEvent event) {
	        Inventory inventory = event.getClickedInventory();
	        if (inventory != null && event.getView().getTitle().equals("Virtual Anvil")) {
	            ItemStack item = event.getCurrentItem();

	            if (item != null && item.getType() != Material.AIR) {
	                // Do something with the clicked item, e.g., modify lore
	                ItemMeta meta = item.getItemMeta();
	                if (meta != null) {
	                	 ArrayList<String> lore = new ArrayList<>(meta.getLore());
	                     lore.add("Modified by Virtual Anvil");
	                     meta.setLore(lore);
	                     item.setItemMeta(meta);
	                     inventory.setItem(event.getSlot(), item);
	                }
	            }
	        }
	    }
	}

