package me.nikolchev98.runes.runeObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class FireResistanceRune extends Rune {

    public FireResistanceRune() {
        super(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Arsonist's Best Friend", List.of("Gives you Fire Resistance"), getEffects(), Material.MAGMA_BLOCK);
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
    }
}
