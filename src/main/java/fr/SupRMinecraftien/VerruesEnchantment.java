package fr.SupRMinecraftien;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class VerruesEnchantment extends Enchantment implements Listener {


    public VerruesEnchantment(NamespacedKey key) {
        super(key);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();

            ItemStack mainhand = p.getInventory().getItemInMainHand();

            if(mainhand.containsEnchantment(this)){
                p.getWorld().createExplosion(e.getEntity().getLocation(), 1, false);
            }
        }
    }
    @Override
    public String getName() {
        return "Verrues";
    }

    @Override
    public int getID(){
        return 101;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }
}
