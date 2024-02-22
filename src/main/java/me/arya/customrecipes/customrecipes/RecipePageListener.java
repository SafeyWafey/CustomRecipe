package me.arya.customrecipes.customrecipes;

import com.google.auto.service.AutoService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Singleton
@AutoService(Listener.class)
public class RecipePageListener implements Listener {

    private RecipeManager recipeManager;

    @Inject
    public RecipePageListener(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        System.out.println("PlayerInteractEvent triggered");
        System.out.println("Action: " + event.getAction());

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = event.getItem();

            if (item != null) {
                System.out.println("Item is not null");

                if (item.hasItemMeta()) {
                    System.out.println("Item has item meta");

                    if (item.getItemMeta().hasDisplayName()) {
                        System.out.println("Item has display name: " + item.getItemMeta().getDisplayName());

                        String displayName = item.getItemMeta().getDisplayName();

                        if (displayName.contains(" Recipe: ")) {
                            System.out.println("Display name contains ' Recipe: '");

                            String recipeName = displayName
                                    .replace("§f§lCommon Recipe: ", "")
                                    .replace("§e§lAncient Recipe: ", "")
                                    .replace("§6§lLegendary Recipe: ", "")
                                    .replace("§c§lImmortal Recipe: ", "");

                            Recipe recipe = this.recipeManager.getRecipe(recipeName);

                            // Add logging
                            System.out.println("Recipe name: " + recipeName);
                            System.out.println("Found recipe: " + (recipe != null));

                            if (recipe != null) {
                                if (this.recipeManager.isRecipeUnlocked(event.getPlayer(), recipe)) {
                                    event.getPlayer().sendMessage(ChatColor.RED + "You have already unlocked this recipe!");
                                } else {
                                    System.out.println("Unlocking recipe for player: " + event.getPlayer().getName());
                                    this.recipeManager.unlockRecipe(event.getPlayer(), recipe);
                                    item.setAmount(item.getAmount() - 1);
                                    event.getPlayer().getInventory().setItemInHand(item.getAmount() > 0 ? item : null);
                                    event.getPlayer().sendMessage(ChatColor.GREEN + "You successfully unlocked this recipe!");
                                }
                            }
                        } else {
                            System.out.println("Display name does not contain ' Recipe: '");
                        }
                    }
                }
            }
        }
    }
}