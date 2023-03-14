package me.nikolchev98.runes.RuneObjects;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class JumpRune extends Rune {
    public JumpRune() {
        super("Rune of Jump Boost", getLore(), getEffects());
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
