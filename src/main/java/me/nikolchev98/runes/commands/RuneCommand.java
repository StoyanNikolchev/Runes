package me.nikolchev98.runes.commands;

import me.nikolchev98.runes.enums.RuneType;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

public class RuneCommand implements CommandExecutor {
    private Player player;
    private RuneType runeType;
    private String invalidCommandMessage = ChatColor.RED + "Command usage: /rune give <OnlinePlayerName> <RuneType/Random>";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        //Checks if the command is sent by a player and if they have the necessary permission
        if (commandSender instanceof Player) {
            Player playerSender = (Player) commandSender;

            if (!playerSender.hasPermission("runes.giveRune")) {
                playerSender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }
        }

        if (!commandIsValid(args)) {
            commandSender.sendMessage(invalidCommandMessage);

        } else {
            ItemStack runeItem = runeType.createRune().getItemStack();

            //Tries to add the rune to the player's inventory. If it fails, the HashMap will have a size of 1
            HashMap<Integer, ItemStack> runeThatDidNotFit = player.getInventory().addItem(runeItem);

            //Drops the rune at the player's feet if their inventory is full
            if (!runeThatDidNotFit.isEmpty()) {
                dropRuneAtPlayerLocation(runeItem);
            }

            player.sendMessage(ChatColor.DARK_GREEN + "You got the " + ChatColor.GOLD + runeType.toString());
        }
        return true;
    }

    private void dropRuneAtPlayerLocation(ItemStack runeItem) {
        World world = player.getWorld();
        Location location = player.getLocation();
        world.dropItem(location, runeItem);
    }

    private boolean commandIsValid(String[] args) {
        if (args.length != 3) {
            return false;
        }

        if (!args[0].equalsIgnoreCase("give")) {
            return false;
        }

        String playerName = args[1];
        player = getOnlinePlayer(playerName);
        if (player == null) {
            invalidCommandMessage = ChatColor.RED + "This player is not online.";
            return false;
        }

        String runeName = args[2];

        for (RuneType value : RuneType.values()) {
            if (value.toString().equalsIgnoreCase(runeName)) {
                runeType = value;
                return true;
            }
        }

        if (runeName.equalsIgnoreCase("random")) {
            Random random = new Random();
            int randomRuneIndex = random.nextInt(RuneType.values().length);
            runeType = RuneType.values()[randomRuneIndex];
            return true;
        }

        runeType = null;
        invalidCommandMessage = ChatColor.RED + "No such rune.";
        return false;
    }

    private Player getOnlinePlayer(String playerName) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.getName().equals(playerName)) {
                return onlinePlayer;
            }
        }
        return null;
    }
}
