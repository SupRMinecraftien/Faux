package fr.suprminecraftien.faux;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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

        recipes();
    }

    public void recipes()
    {
        fauxVerruesRecipe();
        fauxBleRecipe();
        fauxCarotteRecipe();
        fauxPatateRecipe();
    }
    public void fauxVerruesRecipe()
    {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        item.addUnsafeEnchantment(Enchantments.VERRUES, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Faux à verrues");
        meta.setDisplayName(ChatColor.GOLD + "Faux à verrues");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Verrue");
        meta.setLore(lore);
        item.setItemMeta(meta);

        ShapelessRecipe fauxVerrues = new ShapelessRecipe(new NamespacedKey(this, "fauxVerrues"), item);
        fauxVerrues.addIngredient(1, Material.NETHERITE_HOE);
        fauxVerrues.addIngredient(1 ,Material.NETHER_WART);
        getServer().addRecipe(fauxVerrues);
    }

    public void fauxBleRecipe()
    {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        item.addUnsafeEnchantment(Enchantments.BLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Faux à blé");
        meta.setDisplayName(ChatColor.GOLD + "Faux à blé");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Blé");
        meta.setLore(lore);
        item.setItemMeta(meta);

        ShapelessRecipe fauxBle = new ShapelessRecipe(new NamespacedKey(this, "fauxBle"), item);
        fauxBle.addIngredient(1, Material.NETHERITE_HOE);
        fauxBle.addIngredient(1 ,Material.WHEAT_SEEDS);
        getServer().addRecipe(fauxBle);
    }

    public void fauxCarotteRecipe()
    {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        item.addUnsafeEnchantment(Enchantments.CAROTTE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Faux à carotte");
        meta.setDisplayName(ChatColor.GOLD + "Faux à carotte");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Carotte");
        meta.setLore(lore);
        item.setItemMeta(meta);

        ShapelessRecipe fauxCarotte = new ShapelessRecipe(new NamespacedKey(this, "fauxCarotte"), item);
        fauxCarotte.addIngredient(1, Material.NETHERITE_HOE);
        fauxCarotte.addIngredient(1 ,Material.CARROT);
        getServer().addRecipe(fauxCarotte);
    }

    public void fauxPatateRecipe()
    {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        item.addUnsafeEnchantment(Enchantments.PATATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Faux à patate");
        meta.setDisplayName(ChatColor.GOLD + "Faux à patate");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Patate");
        meta.setLore(lore);
        item.setItemMeta(meta);

        ShapelessRecipe fauxPatate = new ShapelessRecipe(new NamespacedKey(this, "fauxPatate"), item);
        fauxPatate.addIngredient(1, Material.NETHERITE_HOE);
        fauxPatate.addIngredient(1 ,Material.POTATO);
        getServer().addRecipe(fauxPatate);
    }
    private void loadConfig()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable()
    {
        System.out.println(YELLOW_BOLD + "Faux plugin disabled");
    }
}
