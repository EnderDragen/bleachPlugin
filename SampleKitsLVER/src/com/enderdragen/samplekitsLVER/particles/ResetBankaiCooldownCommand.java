package com.enderdragen.samplekitsLVER.particles;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ResetBankaiCooldownCommand extends JavaPlugin implements CommandExecutor {
    private final AuraParticlesPlugin plugin;

    public ResetBankaiCooldownCommand(AuraParticlesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("resetbankaicd")) {
            if (sender instanceof Player && sender.hasPermission("bleachmod.resetbankaicooldown")) {
                if (args.length == 1) {
                    String playerName = args[0];
                    Player targetPlayer = plugin.getServer().getPlayerExact(playerName);
                    if (targetPlayer != null) {
                        AuraParticlesPlugin.cooldownPlayers.remove(targetPlayer.getUniqueId());
                        sender.sendMessage("Bankai cooldown reset for player: " + playerName);
                        return true;
                    } else {
                        sender.sendMessage("Player not found.");
                        return false;
                    }
                } else {
                    sender.sendMessage("Usage: /resetbankaicd <player>");
                    return false;
                }
            } else {
                sender.sendMessage("You don't have permission to use this command.");
                return false;
            }
        }
        return false;
    }
}