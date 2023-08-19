package com.enderdragen.samplekitsLVER.particles;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.enderdragen.samplekitsLVER.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuraParticlesEventListener implements Listener {
    private final Main plugin;

    public AuraParticlesEventListener(Main plugin) {
        this.plugin = plugin;
    }

    public static Map<UUID, Long> cooldownPlayers = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!event.hasItem()) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (event.getItem().getItemMeta().getDisplayName().equals("Zanpakuto")) {
            event.setCancelled(true);

            long currentTime = System.currentTimeMillis();
            if (cooldownPlayers.containsKey(player.getUniqueId())) {
                long cooldownEndTime = cooldownPlayers.get(player.getUniqueId());
                long remainingCooldown = (cooldownEndTime - currentTime) / 1000;

                if (remainingCooldown > 0) {
                    player.sendMessage("You are on cooldown. Time remaining: " + remainingCooldown + " seconds.");
                    return;
                } else {
                    // Remove the player from the cooldown map since the cooldown period is over
                    cooldownPlayers.remove(player.getUniqueId());
                }
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255));

                Location playerLocation = player.getLocation();
                triggerAuraParticles(player, playerLocation);

                long cooldownEndTime = currentTime + (5 * 60 * 1000); // 5 minutes cooldown
                cooldownPlayers.put(player.getUniqueId(), cooldownEndTime);
            }
        }
    }

    private void triggerAuraParticles(Player player, Location playerLocation) {
        AuraParticlesPlugin.OuterRingParticlesTask outerRingTask =
            new AuraParticlesPlugin.OuterRingParticlesTask(playerLocation, 15, 3.0, 5.0, 3, 0.25);
        AuraParticlesPlugin.ParticleStreamTask particleStreamTask =
            new AuraParticlesPlugin.ParticleStreamTask(playerLocation);

        // Schedule the particle tasks only if the player is not on cooldown
        outerRingTask.runTaskTimer(plugin, 0L, 1L);
        particleStreamTask.runTaskTimer(plugin, 0L, 1L);
    }
}
