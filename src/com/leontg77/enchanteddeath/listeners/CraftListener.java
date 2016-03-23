package com.leontg77.enchanteddeath.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

/**
 * Craft listener class.
 * 
 * @author LeonTG77
 */
public class CraftListener implements Listener {
	
	@EventHandler
	public void on(PrepareItemCraftEvent event) {
		CraftingInventory inv = event.getInventory();
		ItemStack item = event.getRecipe().getResult();
		
		if (item == null) {
			return;
		}
		
		if (item.getType() == Material.ENCHANTMENT_TABLE) {
			inv.setResult(new ItemStack(Material.AIR));
		}
	}
}