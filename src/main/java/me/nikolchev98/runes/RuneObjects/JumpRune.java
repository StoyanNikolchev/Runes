package me.nikolchev98.runes.RuneObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class JumpRune extends Rune {
    public JumpRune() {
        super(ChatColor.BLUE + "" + ChatColor.BOLD + "I'm not officer, Mr High", getLore(), getEffects(), Material.RABBIT_FOOT);
    }

    public static List<String> getLore() {
        List<String> lore = new ArrayList<>();
        lore.add("Gives you Jump Boost");
        return lore;
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1);
    }
}
