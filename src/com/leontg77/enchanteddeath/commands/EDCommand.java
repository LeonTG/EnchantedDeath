package com.leontg77.enchanteddeath.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.HandlerList;

import com.leontg77.enchanteddeath.Main;
import com.leontg77.enchanteddeath.listeners.CraftListener;
import com.leontg77.enchanteddeath.listeners.DeathListener;

/**
 * Enchated Death command class.
 * 
 * @author LeonTG77
 */
public class EDCommand implements CommandExecutor, TabCompleter {
	private static final String PERMISSION = "edeath.manage";
	
	private final Main plugin;
	
	private final CraftListener craft;
	private final DeathListener death;
	
	/**
	 * Enchated Death command class constructor.
	 * 
	 * @param plugin The main class.
	 * @param craft The crafting listener.
	 * @param death The death listener.
	 */
	public EDCommand(Main plugin, CraftListener craft, DeathListener death) {
		this.plugin = plugin;
		
		this.craft = craft;
		this.death = death;
	}
	
	public boolean enabled = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Main.PREFIX + "Usage: /edeath <info|enable|disable>");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("info")) {
			sender.sendMessage(Main.PREFIX + "Scenario by: §a/r/mindcrack");
			sender.sendMessage(Main.PREFIX + "Plugin creator: §aLeonTG77");
			sender.sendMessage(Main.PREFIX + "Version: §av" + plugin.getDescription().getVersion());
			sender.sendMessage(Main.PREFIX + "Description:");
			sender.sendMessage("§8» §f" + plugin.getDescription().getDescription());
			return true;
		}
		
		if (args[0].equalsIgnoreCase("enable")) {
			if (!sender.hasPermission(PERMISSION)) {
				sender.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
			
			if (enabled) {
				sender.sendMessage(Main.PREFIX + "Enchanted Death is already enabled.");
				return true;
			}
			
			plugin.broadcast(Main.PREFIX + "Enchanted Death has been enabled.");
			enabled = true;
			
			Bukkit.getPluginManager().registerEvents(craft, plugin);
			Bukkit.getPluginManager().registerEvents(death, plugin);
			return true;
		}

		if (args[0].equalsIgnoreCase("disable")) {
			if (!sender.hasPermission(PERMISSION)) {
				sender.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
			
			if (!enabled) {
				sender.sendMessage(Main.PREFIX + "Enchanted Death is not enabled.");
				return true;
			}

			plugin.broadcast(Main.PREFIX + "Enchanted Death has been disabled.");
			enabled = false;
			
			HandlerList.unregisterAll(craft);
			HandlerList.unregisterAll(death);
			return true;
		}
		
		sender.sendMessage(Main.PREFIX + "Usage: /edeath <info|enable|disable>");
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> toReturn = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		
		if (args.length != 1) {
			return toReturn;
		}
		
		list.add("info");
		
		if (sender.hasPermission(PERMISSION)) {
			list.add("enable");
			list.add("disable");
		}

		// make sure to only tab complete what starts with what they 
		// typed or everything if they didn't type anything
		for (String str : list) {
			if (args[0].isEmpty() || str.startsWith(args[0].toLowerCase())) {
				toReturn.add(str);
			}
		}
		
		return toReturn;
	}
}