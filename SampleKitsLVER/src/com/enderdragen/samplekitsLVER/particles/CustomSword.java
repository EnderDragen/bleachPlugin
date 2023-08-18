package com.enderdragen.samplekitsLVER.particles;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomSword implements Listener {

    public static ItemStack createCustomSword() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();

        meta.setDisplayName("Zanpakuto");
        List<String> lore = new ArrayList<>();
        lore.add("This is a special sword!");
        meta.setLore(lore);

        sword.setItemMeta(meta);

        return sword;
    }
}
