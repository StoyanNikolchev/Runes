package me.nikolchev98.runes.RuneObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class FireResistanceRune extends Rune {
    public FireResistanceRune() {
        super(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Arsonist's Best Friend", getLore(), getEffects(), Material.MAGMA_BLOCK);
    }

    public static List<String> getLore() {
        List<String> lore = new ArrayList<>();
        lore.add("Gives you Fire Resistance");
        return lore;
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
    }
}
