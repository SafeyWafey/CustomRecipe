package me.arya.customrecipes.customrecipes.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SweetPotato extends Recipe {
    private String name;
    private ItemStack result;
    private ShapedRecipe recipe;
    private RecipeManager recipeManager;
    private JavaPlugin plugin;

    public SweetPotato(RecipeManager recipeManager, JavaPlugin plugin) {
        super("Sweet Potato", 1, createIngredients(), createResult(), Arrays.asList("XXX", "OOO", "XXX"));
        this.name = "Sweet Potato";
        this.result = createResult();
        this.recipe = new ShapedRecipe(this.result);
        this.recipeManager = recipeManager;
        this.plugin = plugin;

        Map<Character, ItemStack> ingredients = createIngredients();
        this.recipe.shape("XXX", "OOO", "XXX");
        for (String row : this.recipe.getShape()) {
            for (char key : row.toCharArray()) {
                this.recipe.setIngredient(key, ingredients.get(key).getType());
            }
        }

        boolean isRecipeAdded = Bukkit.addRecipe(this.recipe);
        if (isRecipeAdded) {
            recipeManager.addRecipe(this);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    private static Map<Character, ItemStack> createIngredients() {
        Map<Character, ItemStack> ingredients = new HashMap<>();
        ingredients.put('X', new ItemStack(Material.POTATO, 9));
        ingredients.put('O', new ItemStack(Material.SUGAR, 3));
        return ingredients;
    }

    private static ItemStack createResult() {
        ItemStack result = new ItemStack(Material.POTATO_ITEM, 6);
        ItemMeta meta = result.getItemMeta();

        meta.setDisplayName("Sweet Potato");
        result.setItemMeta(meta);

        return result;
    }
}