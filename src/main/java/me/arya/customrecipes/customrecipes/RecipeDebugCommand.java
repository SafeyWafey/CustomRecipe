package me.arya.customrecipes.customrecipes;

import com.google.auto.service.AutoService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@Singleton
@AutoService(Command.class)
public class RecipeDebugCommand extends Command {
    private RecipeManager recipeManager;

    @Inject
    public RecipeDebugCommand(RecipeManager recipeManager) {
        super("RecipeDebug");
        this.recipeManager = recipeManager;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("recipe.debug")) {
                SweetPotato sweetPotato = (SweetPotato) recipeManager.getRecipeByUniqueIdentifier("Sweet Potato");
                if (sweetPotato != null) {
                    player.sendMessage("SweetPotato constructor called");
                    player.sendMessage("Is recipe added: " + Bukkit.recipeIterator().hasNext());
                    player.sendMessage("Is recipe unlocked: " + recipeManager.isRecipeUnlocked(player, sweetPotato));
                } else {
                    player.sendMessage("Sweet Potato recipe not found");
                }
                player.sendMessage("All recipes in RecipeManager:");
                for (Recipe recipe : recipeManager.getRecipes()) {
                    player.sendMessage(recipe.getName());
                }
            } else {
                player.sendMessage("You do not have permission to use this command");
            }
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return ImmutableList.of();
    }
}