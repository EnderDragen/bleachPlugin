package com.enderdragen.samplekitsLVER;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreativeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	
	{
		
		  if (!(sender instanceof Player)) {
	            sender.sendMessage(ChatColor.GOLD + "You can only execute this command as a player");
	            return true;
	        }
	    Player p = (Player) sender;
	    if (!p.hasPermission("samplekits.gmc")) {
       	 p.sendMessage(ChatColor.DARK_RED + "You do not have permission to run this command");
           return true;
       }
	    if (p.getGameMode().equals(GameMode.CREATIVE))
	    {
	        p.setGameMode(GameMode.SURVIVAL);
	    	p.sendMessage(ChatColor.RED + " "+ "You're now in Gamemode Survival!");
	    }
	    else if (p.getGameMode().equals(GameMode.SURVIVAL))
	    {
	        p.setGameMode(GameMode.CREATIVE);
	     	p.sendMessage(ChatColor.RED + " "+ "You're now in Gamemode Creative!");
	        return true;
	    }
	    else if (p.getGameMode().equals(GameMode.SPECTATOR))
	    {
	        p.setGameMode(GameMode.CREATIVE);
	     	p.sendMessage(ChatColor.RED + " "+ "You're now in Gamemode Creative!");
	        return true;
	    }
	    return false;
	
			}
		
	}
 