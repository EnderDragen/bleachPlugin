package com.enderdragen.samplekitsLVER.particles;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.enderdragen.samplekitsLVER.Main;


public class AuraParticlesPlugin implements Listener {
    public static Map<UUID, Long> cooldownPlayers = new HashMap<>();
    private static AuraParticlesPlugin instance;
    private final Main plugin;
   
    public static AuraParticlesPlugin getInstance(Main plugin) {
        if (instance == null) {
            instance = new AuraParticlesPlugin(plugin);
        }
        return instance;
    }
    public AuraParticlesPlugin(Main plugin) {
        this.plugin = plugin;
    }
    public void triggerAuraParticles(Player player) {
        Location playerLocation = player.getLocation();
        new OuterRingParticlesTask(playerLocation, 15, 3.0, 5.0, 3, 0.25)
            .runTaskTimer(plugin, 0L, 1L);
        new ParticleStreamTask(playerLocation)
            .runTaskTimer(plugin, 0L, 1L);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && label.equalsIgnoreCase("auraparticles")) {
            Player player = (Player) sender;
            Location playerLocation = player.getLocation(); // Player's location

        }
        return false;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().toString().contains("RIGHT_CLICK") && player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
            Location playerLocation = player.getLocation();
            // Adjust particle parameters as needed
            int numOuterParticles = 15;
            double outerRadius = 3.0;
            int numInnerRings = 3;
            double innerRadiusStep = 0.25;
            double duration = 5.0;
            
            new OuterRingParticlesTask(playerLocation, numOuterParticles, outerRadius, duration, numInnerRings, innerRadiusStep)
                    .runTaskTimer(plugin, 0L, 1L);

            new ParticleStreamTask(playerLocation).runTaskTimer(plugin, 0L, 1L);
        }
    }
    
    

   public static class OuterRingParticlesTask extends BukkitRunnable {
        private final Location playerLocation;
        private final int numOuterParticles;
        private final double outerRadius;
        private final double duration;
        private final double startTime;
        private final int numInnerRings;
        private final double innerRadiusStep;

        public OuterRingParticlesTask(Location playerLocation, int numOuterParticles, double outerRadius,
                double duration, int numInnerRings, double innerRadiusStep) {
            this.playerLocation = playerLocation;
            this.numOuterParticles = numOuterParticles;
            this.outerRadius = outerRadius;
            this.duration = duration;
            this.startTime = System.currentTimeMillis() / 1000.0;
            this.numInnerRings = numInnerRings;
            this.innerRadiusStep = innerRadiusStep;
        }

        @Override
        public void run() {
            double currentTime = System.currentTimeMillis() / 1000.0;
            double elapsedTime = currentTime - startTime;

            if (elapsedTime > duration) {
                cancel();
                return;
            }

            double angleIncrementOuter = 2 * Math.PI / numOuterParticles;

            for (int layer = 0; layer < numInnerRings; layer++) {
                double currentLayerOffset = layer * innerRadiusStep;
                double currentOuterRadius = (elapsedTime / duration) * outerRadius + currentLayerOffset;

                for (int i = 0; i < numOuterParticles; i++) {
                    double angleOuter = angleIncrementOuter * i + (currentTime * Math.PI);
                    double xOuter = playerLocation.getX() + currentOuterRadius * Math.cos(angleOuter);
                    double yOuter = playerLocation.getY(); // Stick to player's feet
                    double zOuter = playerLocation.getZ() + currentOuterRadius * Math.sin(angleOuter);

                    Location particleLocationOuter = new Location(playerLocation.getWorld(), xOuter, yOuter, zOuter);

                    playerLocation.getWorld().spawnParticle(Particle.FLAME, particleLocationOuter, 1);
                }
            }
        }
    }

    public static class ParticleStreamTask extends BukkitRunnable {
        private final Location playerLocation;
        private double t = 0;

        public ParticleStreamTask(Location playerLocation) {
            this.playerLocation = playerLocation;
        }

        @Override
        public void run() {
            if (t > 1.0) {
                cancel();
                return;
            }

            Location startLocation = playerLocation.clone().add(0, 1, 0);
            Location endLocation = playerLocation.clone().add(0, 20, 0);

            int numParticlesPerStep = 200;
            int numRings = 5;

            double x = startLocation.getX();
            double y = startLocation.getY() + t * (endLocation.getY() - startLocation.getY());
            double z = startLocation.getZ();

            for (int ring = 0; ring < numRings; ring++) {
                double radius = 0.5 * ring;

                for (int i = 0; i < numParticlesPerStep; i++) {
                    double angle = 2 * Math.PI * i / numParticlesPerStep;
                    double offsetX = radius * Math.cos(angle);
                    double offsetZ = radius * Math.sin(angle);

                    Location particleLocation = new Location(playerLocation.getWorld(), x + offsetX, y, z + offsetZ);

                    float gradient = (float) (ring) / numRings;

                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(255, (int)(gradient * 255), 0), 1);

                    playerLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1, 0, 0, 0, 0, dustOptions);
                }
            }

            t += 0.005;
        }
    }
}
