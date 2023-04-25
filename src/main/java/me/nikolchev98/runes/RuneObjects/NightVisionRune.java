package me.nikolchev98.runes.RuneObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class NightVisionRune extends Rune {
    public NightVisionRune() {
        super(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Sunglasses Required", List.of("Gives you Night Vision"), getEffects(), Material.SPYGLASS);
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1);
    }
}
