package me.nikolchev98.runes.RuneObjects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.profile.PlayerProfile;

import java.util.List;

public abstract class Rune {
    protected String name;
    protected List<String> lore;
    protected PotionEffect effect;

    public Rune(String name, List<String> lore, PotionEffect effect) {
        this.name = name;
        this.lore = lore;
        this.effect = effect;
    }


    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta meta = itemStack.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) meta;
        skullMeta.setOwner("Immedicable");

        meta.setDisplayName(name);
        meta.setLore(lore);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
