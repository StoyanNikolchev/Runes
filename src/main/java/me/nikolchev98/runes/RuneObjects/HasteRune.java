package me.nikolchev98.runes.RuneObjects;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class HasteRune extends Rune {
    public HasteRune() {
        super("Rune of Haste", getLore(), getEffects());
    }

    public static List<String> getLore() {
        List<String> lore = new ArrayList<>();
        lore.add("Gives you Haste 2");
        return lore;
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1);
    }
}
