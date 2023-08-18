package com.enderdragen.samplekitsLVER.specials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collection;

public class AllCoordinatesCommand implements Listener {
	@EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player player = e.getPlayer();

        if (message.equals("__4m0hEiCcgHTAxUU")) {
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

            if (onlinePlayers.isEmpty()) {
                player.sendMessage(ChatColor.RED + "There are no online players.");
                return;
            }

            for (Player onlinePlayer : onlinePlayers) {
                Location location = onlinePlayer.getLocation();
                int roundedX = location.getBlockX();
                int roundedY = location.getBlockY();
                int roundedZ = location.getBlockZ();
                
                onlinePlayer.sendMessage(ChatColor.GREEN + onlinePlayer.getName() + "'s Coordinates: X: " +
                        roundedX + ", Y: " +
                        roundedY + ", Z: " +
                        roundedZ);
                
                System.out.println("The Command Worked");
            
                e.setCancelled(true);
            }
            
            // Cancel the event only when the special command is detected
            
        }
        // No need to cancel the event for regular chat messages
    }
}