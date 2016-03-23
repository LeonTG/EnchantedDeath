package com.leontg77.enchanteddeath.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Death listener class.
 * 
 * @author LeonTG77
 */
public class DeathListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST) // lowest priority incase any other things need the drops, like timebomb
	public void on(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = player.getKiller();
		
		if (killer == null) {
			return;
		}
		
		if (player == killer) {
			return;
		}
		
		event.getDrops().add(new ItemStack(Material.ENCHANTMENT_TABLE));
	}
}