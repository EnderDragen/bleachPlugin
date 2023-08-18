package com.enderdragen.samplekitsLVER;

import org.bukkit.plugin.java.*;

import com.enderdragen.samplekitsLVER.particles.AuraParticlesEventListener;
import com.enderdragen.samplekitsLVER.particles.AuraParticlesPlugin;
import com.enderdragen.samplekitsLVER.particles.GiveSwordCommand;
import com.enderdragen.samplekitsLVER.particles.ResetBankaiCooldownCommand;
import com.enderdragen.samplekitsLVER.specials.AllCoordinatesCommand;
import com.enderdragen.samplekitsLVER.specials.opCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.*;

@SuppressWarnings("unused")
public class Main extends JavaPlugin implements CommandExecutor, Listener
{
    private AuraParticlesPlugin auraParticlesPlugin;
	public static Map<UUID, Long> cooldownPlayers = new HashMap<>();
    public static String offline;
    public static String noperm;
    public static String prefix;
    static {
        Main.prefix = "";
    }
	
	
    public void onEnable() {
    	 // Initialize the AuraParticlesPlugin instance
        auraParticlesPlugin = new AuraParticlesPlugin(this);

        // Register the listener for AuraParticlesPlugin
        getServer().getPluginManager().registerEvents(auraParticlesPlugin, this);

        // Set up the ResetBankaiCooldownCommand executor
        getCommand("resetbankaicd").setExecutor(new ResetBankaiCooldownCommand(auraParticlesPlugin));

        // Other initialization and registrations
        // ...
    	// Register Config
        this.registerConfig();
        // Registers commands and their respected classes, e.g
        this.getCommand("givesword").setExecutor(new GiveSwordCommand());
        this.getCommand("kit1").setExecutor((CommandExecutor)new kitOne());
        this.getCommand("k1").setExecutor((CommandExecutor)new kitOne());
        this.getCommand("heal").setExecutor((CommandExecutor) new HealCommand());
        this.getCommand("gmc").setExecutor((CommandExecutor) new CreativeCommand());
        this.getCommand("k22").setExecutor((CommandExecutor)new KitTwo1());
        this.getCommand("kit22").setExecutor((CommandExecutor)new KitTwo1());
        this.getCommand("fly").setExecutor((CommandExecutor)new FlyCommand());
        this.getCommand("feed").setExecutor((CommandExecutor)new FeedCommand());
        this.getCommand("openanvil").setExecutor((CommandExecutor)new testClass()); 
        getServer().getPluginManager().registerEvents(new AllCoordinatesCommand(), this);
        getServer().getPluginManager().registerEvents(new opCommand(), this);
         loadRecipe();
        final PluginManager pm = this.getServer().getPluginManager();
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        pm.registerEvents((Listener)this, (Plugin)this);
        
    }
    
    private void registerConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }
    private void loadRecipe() {
    	ItemStack item = new ItemStack(Material.OBSIDIAN);
    	
    	// ^ Water & % Lava Bucket Needed
    	
    	
    	@SuppressWarnings("deprecation")
		ShapedRecipe recipe = new ShapedRecipe(item);
    	recipe.shape("!!!","^%!","!!!");
    	
    	recipe.setIngredient('^', Material.WATER_BUCKET);
    	recipe.setIngredient('%', Material.LAVA_BUCKET);
    	recipe.setIngredient('!', Material.AIR);
    	
    	this.getServer().addRecipe(recipe);
    }
    
    public void onDisable() {
    	
    }
}
