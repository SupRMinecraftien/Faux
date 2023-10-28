package fr.suprminecraftien.superhoes;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public class Main extends JavaPlugin implements Listener {
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW


    @Override
    public void onEnable()
    {
        System.out.println(YELLOW_BOLD + "Faux plugin enabled");
        Bukkit.getPluginManager().registerEvents(this, this);
        //Load Config
        loadConfig();
        getServer().getPluginManager().registerEvents(new Events(), this);

        //Register enchantments
        Enchantments.register();
        this.getServer().getPluginManager().registerEvents(this, this);

        recipesIngredients();
    }

    public void recipesIngredients()
    {
        recipes(Enchantments.WART, "Wart", Material.NETHER_WART);
        recipes(Enchantments.WHEAT, "Wheat", Material.WHEAT);
        recipes(Enchantments.CARROT, "Carrot", Material.CARROT);
        recipes(Enchantments.POTATO, "Potato", Material.POTATO);
    }

    public void recipes(Enchantment ench, String name, Material ingredient)
    {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        item.addUnsafeEnchantment(ench, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name + " hoe");
        meta.setDisplayName(ChatColor.GOLD + name + " hoe");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        ShapelessRecipe hoe = new ShapelessRecipe(new NamespacedKey(this, name + "Hoe"), item);
        hoe.addIngredient(1, Material.NETHERITE_HOE);
        hoe.addIngredient(1 ,ingredient);
        getServer().addRecipe(hoe);
    }

    private void loadConfig()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable()
    {
        System.out.println(YELLOW_BOLD + "Super H plugin disabled");
    }
}
