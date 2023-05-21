package me.nikolchev98.runes.runeObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class JumpRune extends Rune {
    public JumpRune() {
        super(ChatColor.BLUE + "" + ChatColor.BOLD + "I'm not officer, Mr High", List.of("Gives you Jump Boost"), getEffects(), Material.RABBIT_FOOT);
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1);
    }
}
