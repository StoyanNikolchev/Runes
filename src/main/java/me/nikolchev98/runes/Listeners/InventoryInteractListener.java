package me.nikolchev98.runes.Listeners;

import me.nikolchev98.runes.RuneObjects.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class InventoryInteractListener implements Listener {
    private final Plugin plugin;
    private List<Player> playersWithRuneEffects = new ArrayList<>();

    private final List<PotionEffectType> effectList = new ArrayList<>(Arrays.asList(
            PotionEffectType.FAST_DIGGING,
            PotionEffectType.FIRE_RESISTANCE,
            PotionEffectType.INVISIBILITY,
            PotionEffectType.JUMP,
            PotionEffectType.NIGHT_VISION,
            PotionEffectType.SLOW_FALLING));

    private final List<Material> runeItems = new ArrayList<>(List.of(
            Material.MAGMA_BLOCK,
            Material.TOTEM_OF_UNDYING,
            Material.GLASS,
            Material.RABBIT_FOOT,
            Material.SPYGLASS,
            Material.FEATHER,
            //Keeps runes from previous version functional
            Material.PLAYER_HEAD));

    public InventoryInteractListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClickItem(InventoryClickEvent event) {
        onInventoryInteract(event);
    }

    @EventHandler
    public void onInventoryDragItem(InventoryDragEvent event) {
        onInventoryInteract(event);
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                activateRuneEffects(player);
            }
        }.runTaskLater(plugin, 1);
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                activateRuneEffects(player);
            }
        }.runTaskLater(plugin, 1);
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            new BukkitRunnable() {
                @Override
                public void run() {
                    activateRuneEffects(player);
                }
            }.runTaskLater(plugin, 1);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        activateRuneEffects(player);
    }

    private void onInventoryInteract(InventoryInteractEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            new BukkitRunnable() {
                @Override
                public void run() {
                    activateRuneEffects(player);
                }
            }.runTaskLater(plugin, 1);
        }
    }

    private void activateRuneEffects(Player player) {
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();
        clearEffects(player);

        if (isRune(heldItem) && isRune(offHandItem)) {
            //Check both rune types and apply both effects
            applyEffect(player, heldItem.getItemMeta().getLore());
            applyEffect(player, offHandItem.getItemMeta().getLore());

        } else if (isRune(heldItem) && !isRune(offHandItem)) {
            //Check mainHandRune and apply its effect
            applyEffect(player, heldItem.getItemMeta().getLore());
        } else if (!isRune(heldItem) && isRune(offHandItem)) {
            //Check offHandRune and apply its effect
            applyEffect(player, offHandItem.getItemMeta().getLore());
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
        playersWithRuneEffects.add(player);
    }
    public boolean isRune(ItemStack item) {
        if (runeItems.contains(item.getType()) && item.hasItemMeta() && item.getItemMeta().getLore() != null) {

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
            if (effectList.contains(e.getType()) && playersWithRuneEffects.contains(player)) {
                player.removePotionEffect(e.getType());
                playersWithRuneEffects.remove(player);
            }
        });
    }
}