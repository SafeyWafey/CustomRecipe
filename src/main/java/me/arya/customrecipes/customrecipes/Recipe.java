package me.arya.customrecipes.customrecipes;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public abstract class Recipe {
    private String name;
    private int tier;
    private Map<Character, ItemStack> ingredients;
    private ItemStack result;
    private List<String> shape;

    public Recipe(String name, int tier, Map<Character, ItemStack> ingredients, ItemStack result, List<String> shape) {
        this.name = name;
        this.tier = tier;
        this.ingredients = ingredients;
        this.result = result;
        this.shape = shape;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public String getTier() {
        switch (this.tier) {
            case 1:
                return "Common Recipe:";
            case 2:
                return "Ancient Recipe:";
            case 3:
                return "Legendary Recipe:";
            case 4:
                return "Immortal Recipe:";
            default:
                return "Uncommon Recipe:";
        }
    }

    public Map<Character, ItemStack> getIngredients() {
        return this.ingredients;
    }
}