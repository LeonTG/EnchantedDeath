package com.leontg77.enchanteddeath;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (event.getEntity().getType() == EntityType.PLAYER && event.getEntity().getKiller() != null) {
			event.getDrops().add(new ItemStack (Material.ENCHANTMENT_TABLE));
		}
	}
}
