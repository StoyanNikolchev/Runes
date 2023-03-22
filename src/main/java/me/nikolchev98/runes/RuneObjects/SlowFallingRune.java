package me.nikolchev98.runes.RuneObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SlowFallingRune extends Rune {
    public SlowFallingRune() {
        super(ChatColor.WHITE + "" + ChatColor.BOLD + "Light as Light", getLore(), getEffects(), Material.FEATHER);
    }

    public static List<String> getLore() {
        List<String> lore = new ArrayList<>();
        lore.add("Gives you Slow Falling");
        return lore;
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 1);
    }
}
