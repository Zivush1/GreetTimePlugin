package me.zivush.greettimeplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GreetTimePlugin extends JavaPlugin {

    // Store the greeting message from the config
    private String greetingMessage;

    @Override
    public void onEnable() {
        // Save default config if it doesn't exist
        saveDefaultConfig();

        // Load greeting message from config and apply color codes
        FileConfiguration config = getConfig();
        greetingMessage = ChatColor.translateAlternateColorCodes('&', config.getString("greeting-message", "Hello, %player%!"));

        getLogger().info("GreetTime plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GreetTime plugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Move commands to their respective handlers
        if (command.getName().equalsIgnoreCase("greet")) {
            return handleGreetCommand(sender, args);
        } else if (command.getName().equalsIgnoreCase("timecheck")) {
            return handleTimeCheckCommand(sender);
        }
        return false;
    }

    private boolean handleGreetCommand(CommandSender sender, String[] args) {
        // Check if a player name was provided
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /greet <player>");
            return true;
        }

        // Check if the player is online
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        // Send the greeting message to the target player
        String message = greetingMessage.replace("%player%", target.getName());
        target.sendMessage(message);

        return true;
    }

    private boolean handleTimeCheckCommand(CommandSender sender) {
        // Get the current time of the main world
        long time = sender.getServer().getWorld("world").getTime();

        // Convert Minecraft time to real-world time
        int hours = (int) ((time / 1000 + 6) % 24);
        int minutes = (int) ((time % 1000) * 60 / 1000);

        // Format the time as hh:mm
        String timeString = String.format("%02d:%02d", hours, minutes);

        // Send the formatted time to the player
        sender.sendMessage(ChatColor.YELLOW + "Current server time: " + timeString);
        return true;
    }
}