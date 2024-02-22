package me.arya.customrecipes.customrecipes;//package com.turqmelon.FridgeSkyblock.customrecipes;
//
//import com.google.auto.service.AutoService;
//import com.google.inject.Inject;
//import com.google.inject.Singleton;
//import org.bukkit.ChatColor;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.inventory.CraftItemEvent;
//import org.bukkit.plugin.java.JavaPlugin;
//
//@Singleton
//@AutoService(Listener.class)
//public class RecipeCraftEvent implements Listener {
//    private JavaPlugin plugin;
//    private RecipeManager recipeManager;
//
//    @Inject
//    public RecipeCraftEvent(JavaPlugin plugin, RecipeManager recipeManager) {
//        this.plugin = plugin;
//        this.recipeManager = recipeManager;
//        plugin.getServer().getPluginManager().registerEvents(this, plugin);
//    }
//
//    @EventHandler
//    public void onCraft(CraftItemEvent event) {
//        Player player = (Player) event.getWhoClicked();
//        player.sendMessage(ChatColor.GREEN + "Success!");
//    }
//}