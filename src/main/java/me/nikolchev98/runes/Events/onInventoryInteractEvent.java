package me.nikolchev98.runes.Events;

import me.nikolchev98.runes.RuneObjects.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class onInventoryInteractEvent implements Listener {
    private final Plugin plugin;

    List<PotionEffectType> effectList = new ArrayList<>(Arrays.asList(PotionEffectType.FAST_DIGGING,
            PotionEffectType.FIRE_RESISTANCE,
            PotionEffectType.INVISIBILITY,
            PotionEffectType.JUMP,
            PotionEffectType.NIGHT_VISION,
            PotionEffectType.SLOW_FALLING));

    public onInventoryInteractEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack currentItem = player.getInventory().getItem(event.getNewSlot());
        if (currentItem != null) {
            updateEffects(player, currentItem);
        } else {
            clearEffects(player);
        }
    }

    @EventHandler
    public void onInventoryClickItem(InventoryClickEvent event) {
        onInventoryInteract(event);
    }

    @EventHandler
    public void onInventoryDragItem(InventoryDragEvent event) {
        onInventoryInteract(event);
    }

    private void onInventoryInteract(InventoryInteractEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack heldItem = player.getInventory().getItemInMainHand();

                    //Gives rune effect if the held item is a rune
                    if (runeIsInMainHand(player, heldItem)) {
                            updateEffects(player, heldItem);
                        } else {
                            clearEffects(player);
                        }
                }
            }.runTaskLater(plugin, 1);
        }
    }


    private void updateEffects(Player player, ItemStack item) {
        //Removes a rune effect if there is no longer a rune in the player's main or off-hand
        if (!runeIsInMainHand(player, item)) {
            clearEffects(player);

            //Applies rune effect from rune in main hand
        } else if (runeIsInMainHand(player, item)) {
            List<String> lore = Objects.requireNonNull(item.getItemMeta()).getLore();
            assert lore != null;
            applyEffect(player, lore);
        }
    }

    private void applyEffect(Player player, List<String> lore) {
        if (lore.contains(HasteRune.getLore().get(0))) {
            player.addPotionEffect(HasteRune.getEffects());

        } else if (lore.contains(InvisibilityRune.getLore().get(0))) {
            player.addPotionEffect(InvisibilityRune.getEffects());

        } else if (lore.contains(SlowFallingRune.getLore().get(0))) {
            player.addPotionEffect(SlowFallingRune.getEffects());

        } else if (lore.contains(FireResistanceRune.getLore().get(0))) {
            player.addPotionEffect(FireResistanceRune.getEffects());

        } else if (lore.contains(JumpRune.getLore().get(0))) {
            player.addPotionEffect(JumpRune.getEffects());

        } else if (lore.contains(NightVisionRune.getLore().get(0))) {
            player.addPotionEffect(NightVisionRune.getEffects());

        }
    }

    public boolean runeIsInMainHand(Player player, ItemStack item) {
        if (item.getType() == Material.PLAYER_HEAD && item.hasItemMeta() && item.getItemMeta().getLore() != null) {
            List<String> lore = item.getItemMeta().getLore();
            if (lore.contains(HasteRune.getLore().get(0))) {
                return true;
            } else if (lore.contains(InvisibilityRune.getLore().get(0))) {
                return true;
            } else if (lore.contains(SlowFallingRune.getLore().get(0))) {
                return true;
            } else if (lore.contains(FireResistanceRune.getLore().get(0))) {
                return true;
            } else if (lore.contains(JumpRune.getLore().get(0))) {
                return true;
            } else if (lore.contains(NightVisionRune.getLore().get(0))) {
                return true;
            }
        }
        return false;
    }

    public void clearEffects(Player player) {
        Collection<PotionEffect> activePotionEffects = player.getActivePotionEffects();
        activePotionEffects.forEach(e -> {
            if (effectList.contains(e.getType())) {
                player.removePotionEffect(e.getType());
            }
        });
    }
}
