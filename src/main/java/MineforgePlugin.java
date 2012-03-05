package net.bot2k3.siebe.Minewarp;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.*;

/**
 * Provides the main plugin interface.
 */
public class MineforgePlugin extends JavaPlugin implements Listener
{
    /**
     * Occurs when the plugin is being enabled.
     */
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    /**
     * Occurs when the plugin is being disabled.
     */
    public void onDisable()
    {
    }

    /**
     * Occurs when a furnace is burning.
     */
	@EventHandler
    public void onFurnaceBurnEvent(FurnaceBurnEvent e)
    {
        if (e.getFuel().getType() == Material.LAVA_BUCKET)
        {
            final Block block = e.getBlock();

            // schedule a new simple task to place a new bucket.
            this.getServer().getScheduler().scheduleSyncDelayedTask(
                this,
                new Runnable()
                {
                    /**
                     * Runs the task.
                     */
                    public void run()
                    {
                        FurnaceInventory inventory;

                        // fetch the inventory and set the new item stack.
                        inventory = ((Furnace)block.getState()).getInventory();
                        inventory.setItem(1, new ItemStack(Material.BUCKET, 1));
                    }
                });
        }
    }
}
