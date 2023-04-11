package me.nikolchev98.runes.RuneObjects;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public abstract class Rune {
    protected Material itemType;
    protected String name;
    protected List<String> lore;
    protected PotionEffect effect;

    public Rune(String name, List<String> lore, PotionEffect effect, Material itemType) {
        this.name = name;
        this.lore = lore;
        this.effect = effect;
        this.itemType = itemType;
    }

    public Material getItemType() {
        return this.itemType;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(getItemType());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
