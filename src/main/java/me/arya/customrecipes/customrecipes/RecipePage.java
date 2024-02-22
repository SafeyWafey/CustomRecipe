package me.arya.customrecipes.customrecipes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RecipePage {
    private String name;
    private String tier;
    private Recipe recipe;

    public RecipePage(String name, String tier, Recipe recipe) {
        this.name = name;
        this.tier = tier;
        this.recipe = recipe;
    }

    public ItemStack createItemStack() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();

        ChatColor tierColor;
        switch (this.tier) {
            case "Common Recipe:":
                tierColor = ChatColor.WHITE;
                break;
            case "Ancient Recipe:":
                tierColor = ChatColor.YELLOW;
                break;
            case "Legendary Recipe:":
                tierColor = ChatColor.GOLD;
                break;
            case "Immortal Recipe:":
                tierColor = ChatColor.RED;
                break;
            default:
                tierColor = ChatColor.WHITE;
                break;
        }

        meta.setDisplayName(tierColor.toString() + ChatColor.BOLD.toString() + this.tier + " " + this.name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Custom Recipe Page");
        lore.add(" ");
        lore.add(ChatColor.WHITE + ChatColor.BOLD.toString() + "Ingredients: " + ChatColor.GOLD + ChatColor.BOLD.toString() + this.recipe.getIngredients().toString());
        lore.add(ChatColor.WHITE + ChatColor.BOLD.toString() + "Rarity: " + tierColor.toString() + ChatColor.BOLD.toString() + this.tier.replace(" Recipe:", ""));
        lore.add(" ");
        lore.add(ChatColor.AQUA + ChatColor.BOLD.toString() + "Right Click" + ChatColor.WHITE + " to unlock this recipe.");
        lore.add(ChatColor.AQUA + ChatColor.BOLD.toString() + "SHIFT + Right Click" + ChatColor.RESET + ChatColor.WHITE + " to view this recipe.");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }
}