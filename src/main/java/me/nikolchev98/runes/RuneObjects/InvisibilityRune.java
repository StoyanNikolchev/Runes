package me.nikolchev98.runes.RuneObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class InvisibilityRune extends Rune {
    public InvisibilityRune() {
        super(ChatColor.GREEN + "" + ChatColor.BOLD + "John Cena", List.of("Makes you invisible"), getEffects(), Material.GLASS);
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1);
    }
}
