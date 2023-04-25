package me.nikolchev98.runes.RuneObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class HasteRune extends Rune {
    public HasteRune() {
        super(ChatColor.GOLD + "" + ChatColor.BOLD + "Diggy Diggy Hole", List.of("Gives you Haste 2"), getEffects(), Material.TOTEM_OF_UNDYING);
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1);
    }
}
