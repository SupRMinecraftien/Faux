package fr.SupRMinecraftien;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public final class FauxDuNether extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Le plugin est lancé");
        // Créez une instance de NamespacedKey avec le nom de votre plugin et le nom de l'enchantement
        NamespacedKey enchantmentKey = new NamespacedKey(this, "Verrues");

        // Utilisez cette clé comme argument pour le constructeur de VerruesEnchantment
        FauxDuNether ench = new FauxDuNether(enchantmentKey);

        LoadEnchantments();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();

        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + ench.getName() + " I");
        meta.setDisplayName(ChatColor.YELLOW + "Faux Du Nether");
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(ench, 1);

        p.getInventory().addItem(item);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onDisable(){
        try{
            Field byIDField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIDField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIDField.get(null);
            HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);

            if (byId.containsKey(ench.getID())){
                byId.remove(ench.getID());
            }

            if (byName.containsKey(ench.getName())){
                byName.remove(ench.getID());
            }
        }
        catch (Exception ignored){}
        System.out.println("Le plugin est arreté");
    }

    private void LoadEnchantments() {
        try{
            try{
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            try{
                Enchantment.registerEnchantment(ench);
            }
            catch(IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
