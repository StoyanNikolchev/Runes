package me.nikolchev98.runes.RuneObjects;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class FireResistanceRune extends Rune {
    public FireResistanceRune() {
        super("Rune of Fire Resistance", getLore(), getEffects());
    }

    public static List<String> getLore() {
        List<String> lore = new ArrayList<>();
        lore.add("Gives you Fire Resistance");
        return lore;
    }

    public static PotionEffect getEffects() {
        return new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
    }
}
