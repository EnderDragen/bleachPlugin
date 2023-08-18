package com.enderdragen.samplekitsLVER;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.*;

public class FlyCommand implements CommandExecutor
{
    public FlyCommand() {
        super();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GOLD + "You can only execute this command as a player");
            return true;
        }
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (!p.hasPermission("samplekits.fly")) {
            	 p.sendMessage(ChatColor.DARK_RED + "You do not have permission to run this command");
                return true;
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.GOLD + "/fly <player>");
                return true;
            }
            if (args.length == 0) {
                if (!p.getAllowFlight()) {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage("Fly mode" + " " + ChatColor.GREEN + "activated");
                }
                else {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage("Fly mode" + " " + ChatColor.RED + "deactivated");
                }
            }
            if (args.length == 1) {
                if (!p.hasPermission("samplekits.setfly")) {
                    p.sendMessage(ChatColor.DARK_RED + "You do not have permission to run this command");
                    return true;
                }
                final Player t = Bukkit.getPlayer(args[0]);
                if (t == null) {
                    p.sendMessage(Main.offline);
                    return true;
                }
                if (!t.getAllowFlight()) {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(String.valueOf(Main.prefix) + ChatColor.GOLD + "Your flight mode has been" + ChatColor.GREEN + "activated!");
                    p.sendMessage(String.valueOf(Main.prefix) + "Flight mode of" + t.getName() + " has" + ChatColor.GREEN + ChatColor.BOLD + "activated");
                }
                else {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage(String.valueOf(Main.prefix) + ChatColor.GOLD + "Your flight mode has been" + ChatColor.RED + "deactivated!");
                    p.sendMessage(String.valueOf(Main.prefix) + "Flight mode of" + t.getName() + " has" + ChatColor.RED + ChatColor.BOLD + "deactivated");
                }
            }
        }
        return false;
    }
}
