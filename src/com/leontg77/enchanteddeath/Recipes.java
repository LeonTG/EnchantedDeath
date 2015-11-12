package com.leontg77.enchanteddeath;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;

public class Recipes {
	
	public static void addRecipes() {
		Iterator<Recipe> it = Bukkit.getServer().recipeIterator();
		Recipe recipe;
		
		while(it.hasNext()) {
			recipe = it.next();
			if (recipe != null && recipe.getResult().getType() == Material.ENCHANTMENT_TABLE) {
				it.remove();
			}
		}
	}
	
	public static void clearRecipes() {
		Bukkit.clearRecipes();
	}
}