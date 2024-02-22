package me.arya.customrecipes.customrecipes;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

@Singleton
@AutoService(Command.class)
public class RecipePageCommand extends Command {
    private RecipeManager recipeManager;

    @Inject
    public RecipePageCommand(RecipeManager recipeManager) {
        super("recipepage", "Access the recipe page.", "/recipepage", Arrays.asList("rp", "recipep"));
        this.recipeManager = recipeManager;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Please specify a recipe name.");
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            List<Recipe> recipes = this.recipeManager.getRecipes();
            if (recipes.isEmpty()) {
                player.sendMessage("There are no recipes.");
            } else {
                for (Recipe recipe : recipes) {
                    player.sendMessage(recipe.getName());
                }
            }
            return true;
        }

        String recipeName = String.join(" ", args).toLowerCase();

        Recipe recipe = this.recipeManager.getRecipe(recipeName);

        if (recipe == null) {
            player.sendMessage("There is no recipe with that name.");
        } else {
            player.sendMessage("Found recipe: " + recipe.getName());
            RecipePage recipePage = new RecipePage(recipe.getName(), recipe.getTier(), recipe);
            player.getInventory().addItem(recipePage.createItemStack());
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return ImmutableList.of();
    }
}