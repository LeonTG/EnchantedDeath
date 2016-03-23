package com.leontg77.enchanteddeath;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.leontg77.enchanteddeath.commands.EDCommand;
import com.leontg77.enchanteddeath.listeners.CraftListener;
import com.leontg77.enchanteddeath.listeners.DeathListener;

/**
 * The main class of the plugin.
 * 
 * @author LeonTG77
 */
public class Main extends JavaPlugin {
	public static final String PREFIX = "§aEnchanted Death §8» §7";

	@Override
	public void onDisable() {
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " is now disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " v" + file.getVersion() + " is now enabled.");
		
		CraftListener craft = new CraftListener();
		DeathListener death = new DeathListener();
		
		EDCommand command = new EDCommand(this, craft, death);

		getCommand("enchanteddeath").setExecutor(command);
		getCommand("enchanteddeath").setTabCompleter(command);
	}

	/**
	 * Broadcast the given message to everyone online.
	 * 
	 * @param message The message to broadcast.
	 */
	public void broadcast(String message) {
		for (Player online : Bukkit.getOnlinePlayers()) {
			online.sendMessage(message);
		}
		
		Bukkit.getLogger().info(message);
	}
}