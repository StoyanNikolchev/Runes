package me.nikolchev98.runes.enums;

import me.nikolchev98.runes.runeObjects.*;

public enum RuneType {
    FIRE_RESISTANCE_RUNE,
    HASTE_RUNE,
    INVISIBILITY_RUNE,
    JUMP_RUNE,
    NIGHT_VISION_RUNE,
    SLOW_FALLING_RUNE;

    public Rune createRune() {
        switch(this) {
            case FIRE_RESISTANCE_RUNE:
                return new FireResistanceRune();

            case HASTE_RUNE:
                return new HasteRune();

            case INVISIBILITY_RUNE:
                return new InvisibilityRune();

            case JUMP_RUNE:
                return new JumpRune();

            case NIGHT_VISION_RUNE:
                return new NightVisionRune();

            case SLOW_FALLING_RUNE:
                return new SlowFallingRune();
        }
        return null;
    }
}
