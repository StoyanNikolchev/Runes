package me.nikolchev98.runes.listeners;

import me.nikolchev98.runes.enums.RuneType;
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
import java.util.stream.Collectors;

public class InventoryInteractListener implements Listener {
    private final Plugin plugin;
    private List<Player> playersWithRuneEffects = new ArrayList<>();

    private final List<PotionEffectType> effectList = Arrays.stream(RuneType.values())
            .map(runeType -> runeType.createRune().getEffect().getType()).collect(Collectors.toList());

    private final List<Material> runeItems = Arrays.stream(RuneType.values())
            .map(runeType -> runeType.createRune().getItemType()).collect(Collectors.toList());

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
        String itemLore = lore.get(0);

        for (RuneType runeType : RuneType.values()) {
            if (runeType.createRune().getLore().get(0).equals(itemLore)) {
                player.addPotionEffect(runeType.createRune().getEffect());
            }
        }
        playersWithRuneEffects.add(player);
    }
    public boolean isRune(ItemStack item) {

        if (runeItems.contains(item.getType()) && item.hasItemMeta() && item.getItemMeta().getLore() != null) {
            String itemLore = item.getItemMeta().getLore().get(0);

            for (RuneType runeType : RuneType.values()) {
                String runeLore = runeType.createRune().getLore().get(0);

                if (runeLore.equals(itemLore)) {
                    return true;
                }
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