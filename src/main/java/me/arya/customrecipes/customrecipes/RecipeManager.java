package me.arya.customrecipes.customrecipes;

import com.google.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.*;

public class RecipeManager {
    private List<Recipe> recipes;
    private Map<UUID, List<Recipe>> unlockedRecipes;
    private JavaPlugin plugin;

    @Inject
    public RecipeManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.recipes = new ArrayList<>();
        this.unlockedRecipes = new HashMap<>();

        File folder = new File("unlockedRecipes");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            try {
                UUID playerId = UUID.fromString(file.getName());
                List<Recipe> playerRecipes = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    Recipe recipe = getRecipe(line);
                    if (recipe != null) {
                        playerRecipes.add(recipe);
                    }
                }
                reader.close();
                unlockedRecipes.put(playerId, playerRecipes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Recipe> getRecipes() {
        if (this.recipes.isEmpty()) {
            this.plugin.getLogger().info("No recipes found ");
        }
        return Collections.unmodifiableList(this.recipes);
    }

    public Recipe getRecipe(String name) {
        for (Recipe recipe : this.recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                return recipe;
            }
        }
        return null;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void unlockRecipe(Player player, Recipe recipe) {
        List<Recipe> playerRecipes = this.unlockedRecipes.getOrDefault(player.getUniqueId(), new ArrayList<>());
        playerRecipes.add(recipe);
        this.unlockedRecipes.put(player.getUniqueId(), playerRecipes);

        try {
            File file = new File("unlockedRecipes/" + player.getUniqueId().toString());
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            for (Recipe unlockedRecipe : playerRecipes) {
                writer.println(unlockedRecipe.getName());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRecipeUnlocked(Player player, Recipe recipe) {
        List<Recipe> playerRecipes = this.unlockedRecipes.get(player.getUniqueId());
        return playerRecipes != null && playerRecipes.contains(recipe);
    }

    public Recipe getRecipeByUniqueIdentifier(String uniqueIdentifier) {
        for (Recipe recipe : this.recipes) {
            if (recipe.getName().equalsIgnoreCase(uniqueIdentifier)) {
                return recipe;
            }
        }
        return null;
    }
}