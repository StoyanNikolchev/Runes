package me.nikolchev98.runes.Listeners;

import me.nikolchev98.runes.RuneObjects.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.*;

public class BossKillListener implements Listener {
    @EventHandler
    public void onBossKill(EntityDeathEvent e) {
        //Valid mobs
        Set<EntityType> mobs = new HashSet<>(Set.of(
                EntityType.ENDER_DRAGON
                , EntityType.ELDER_GUARDIAN
                , EntityType.WITHER));

        //Checks if the mob is a boss
        Entity mob = e.getEntity();
        EntityType mobType = mob.getType();
        if (mobs.contains(mobType)) {
            if (mob.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent lastDamageCause = (EntityDamageByEntityEvent) mob.getLastDamageCause();

                //Checks if the mob was killed by a player
                if (lastDamageCause.getDamager() instanceof Player) {
                    Random random = new Random();

                    int dropChance = getDropChance(mobType);

                    if (random.nextInt(100) < dropChance) {
                        Rune rune = getRandomRune();
                        e.getDrops().add(rune.getItemStack());
                    }
                }
            }
        }
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
        Random random = new Random();
        int index = random.nextInt(6);
        switch (index) {
            case 0:
                return new HasteRune();

            case 1:
                return new InvisibilityRune();

            case 2:
                return new SlowFallingRune();

            case 3:
                return new FireResistanceRune();

            case 4:
                return new JumpRune();

            case 5:
                return new NightVisionRune();
        }
        return null;
    }
}
