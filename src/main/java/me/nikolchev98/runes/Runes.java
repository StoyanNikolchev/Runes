package me.nikolchev98.runes;

import me.nikolchev98.runes.commands.RuneCommand;
import me.nikolchev98.runes.listeners.InventoryInteractListener;
import me.nikolchev98.runes.listeners.BossKillListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Runes extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Runes are enabled!");
        getServer().getPluginManager().registerEvents(new BossKillListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryInteractListener(this), this);
        getCommand("rune").setExecutor(new RuneCommand());
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        System.out.println("Runes are disabled!");
        // Plugin shutdown logic
    }
}
