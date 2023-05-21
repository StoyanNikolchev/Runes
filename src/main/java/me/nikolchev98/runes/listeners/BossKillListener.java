package me.nikolchev98.runes.listeners;

import me.nikolchev98.runes.enums.RuneType;
import me.nikolchev98.runes.runeObjects.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.*;

public class BossKillListener implements Listener {
    private final Random random = new Random();
    private final Set<EntityType> mobs = new HashSet<>(Set.of( //Valid mobs
            EntityType.ENDER_DRAGON,
            EntityType.ELDER_GUARDIAN,
            EntityType.WITHER));

    @EventHandler
    public void onBossKill(EntityDeathEvent e) {
        Entity mob = e.getEntity();
        EntityType mobType = mob.getType();
        EntityDamageEvent deathCause = mob.getLastDamageCause();

        if (!isBoss(mobs, mobType) || !killedByPlayer(deathCause)) { //Return if the mob isn't a boss or wasn't killed by a player
            return;
        }

        int dropChance = getDropChance(mobType);

        if (random.nextInt(100) < dropChance) {
            Rune rune = getRandomRune();
            e.getDrops().add(rune.getItemStack());
        }
    }

    private static boolean killedByPlayer(EntityDamageEvent deathCause) {
        if (deathCause instanceof EntityDamageByEntityEvent) {
            return ((EntityDamageByEntityEvent) deathCause).getDamager() instanceof Player;
        }

        return false;
    }

    private static boolean isBoss(Set<EntityType> mobs, EntityType mobType) {
        return mobs.contains(mobType);
    }

    private int getDropChance(EntityType mobType) {
        if (mobType.equals(EntityType.ENDER_DRAGON)) {
            return 25;
        } else if (mobType.equals(EntityType.ELDER_GUARDIAN)) {
            return 10;
        } else
            return 5;
    }

    private Rune getRandomRune() {
        RuneType[] values = RuneType.values();
        int index = random.nextInt(values.length);
        RuneType chosenType = values[index];
        return chosenType.createRune();
    }
}
