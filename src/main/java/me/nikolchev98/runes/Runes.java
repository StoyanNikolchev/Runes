package me.nikolchev98.runes;

import me.nikolchev98.runes.Events.onInventoryInteractEvent;
import me.nikolchev98.runes.Listeners.BossKillListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Runes extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Runes are enabled!");
        getServer().getPluginManager().registerEvents(new BossKillListener(), this);
        getServer().getPluginManager().registerEvents(new onInventoryInteractEvent(this), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        System.out.println("Runes are disabled!");
        saveConfig();
        // Plugin shutdown logic
    }
}
