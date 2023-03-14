package me.nikolchev98.runes.Listeners;

import me.nikolchev98.runes.RuneObjects.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.*;

public class BossKillListener implements Listener {
    int dropChance = 5;

    @EventHandler
    public void onBossKill(EntityDeathEvent e) {
        //Valid mobs
        Set<EntityType> mobs = new HashSet<>(Set.of(
                EntityType.ENDER_DRAGON,
                EntityType.WITHER,
                EntityType.WARDEN,
                EntityType.ELDER_GUARDIAN));

        //Checks if the mob is a boss
        if (mobs.contains(e.getEntityType())) {
            Entity boss = e.getEntity();
            if (boss.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent lastDamageCause = (EntityDamageByEntityEvent) boss.getLastDamageCause();

                //Checks if the mob was killed by a player
                if (lastDamageCause.getDamager() instanceof Player) {
                    Random random = new Random();

                    //Drops the rune 20% of the time
                    if (random.nextInt(100) < dropChance) {
                        Rune rune = getRandomRune();
                        e.getDrops().add(rune.getItemStack());
                    }
                }
            }
        }
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
