package com.enderdragen.samplekitsLVER.particles;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuraParticlesEventListener implements Listener {
	 private final AuraParticlesPlugin plugin;		
   
	 public AuraParticlesEventListener(AuraParticlesPlugin  plugin) {
        this.plugin = plugin;
    }
    public static Map<UUID, Long> cooldownPlayers = new HashMap<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!event.hasItem()) {
            return;
        }

        // Check if the event is a right-click action
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        // Assuming you've created a custom sword with an item name "CustomSword"
        if (event.getItem().getItemMeta().getDisplayName().equals("Zanpakuto")) {
            event.setCancelled(true); // Prevent default right-click behavior

            // Check if the player is under cooldown
            long currentTime = System.currentTimeMillis();
            if (cooldownPlayers.containsKey(player.getUniqueId())) {
                long cooldownEndTime = cooldownPlayers.get(player.getUniqueId());
                long remainingCooldown = (cooldownEndTime - currentTime) / 1000;

                if (remainingCooldown > 0) {
                    player.sendMessage("You are on cooldown. Time remaining: " + remainingCooldown + " seconds.");
                    return;
                }
            } else {
                // Apply potion effect for invincibility
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4));

                // Apply potion effect for immobilization
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255));

                // Trigger particle effects only if not on cooldown
                Location playerLocation = player.getLocation();
                AuraParticlesPlugin.OuterRingParticlesTask outerRingTask =
                    new AuraParticlesPlugin.OuterRingParticlesTask(playerLocation, 15, 3.0, 5.0, 3, 0.25);
                AuraParticlesPlugin.ParticleStreamTask particleStreamTask =
                    new AuraParticlesPlugin.ParticleStreamTask(playerLocation);

                // Schedule the tasks
                outerRingTask.runTaskTimer(plugin, 0L, 1L);
                particleStreamTask.runTaskTimer(plugin, 0L, 1L);

                // Add player to cooldown map and schedule removal after 5 minutes
                long cooldownEndTime = currentTime + (5 * 60 * 1000); // 5 minutes cooldown
                cooldownPlayers.put(player.getUniqueId(), cooldownEndTime);
            }
        }
    }
}