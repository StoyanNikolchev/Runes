package me.nikolchev98.runes.RuneObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class NightVisionRune extends Rune {
    public NightVisionRune() {
        super(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sunglasses Required", getLore(), getEffects(), Material.SPYGLASS);
    }

    public static List<String> getLore() {
        List<String> lore = new ArrayList<>();
        lore.add("Gives you Night Vision");
        return lore;
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1);
    }
}
