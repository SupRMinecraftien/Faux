package fr.suprminecraftien.superhoes;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class Events implements Listener {


    private int damages = 0;

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e)
    {

        //Checking all conditions

        //If click is a right click
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK))
            return;

        //ATTENTION IF YOU REMOVE THE FOLLOWING LINE THE EVENT WILL BE CALLED TWICE !
        if (e.getHand() == EquipmentSlot.HAND) return;

        //Defining who is the player
        Player p = e.getPlayer();

        if (p.getGameMode().equals(GameMode.ADVENTURE))
            return;
        if (!(p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_HOE))
            return;

        //If the player is sneaking
        if (p.isSneaking())
        {

            //Is Verrues enchant
            if(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).hasEnchant(Enchantments.WART))
            {
                Material[] materials = verruesProceed();
                placeBLocks(p, materials[0], materials[1], materials[2]);
            }

            //Is Blé enchant
            if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantments.WHEAT))
            {
                if (p.getTargetBlock(null, 6).getType().equals(Material.GRASS_BLOCK))
                    return;
                Material[] materials = bleProceed();
                placeBLocks(p, materials[0], materials[1], materials[2]);
            }

            //Is Carotte enchant
            if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantments.CARROT))
            {
                if (p.getTargetBlock(null, 6).getType().equals(Material.GRASS_BLOCK))
                    return;

                Material[] materials = carotteProceed();
                placeBLocks(p, materials[0], materials[1], materials[2]);
            }

            if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantments.POTATO))
            {
                if (p.getTargetBlock(null, 6).getType().equals(Material.GRASS_BLOCK))
                    return;
                Material[] materials = patateProceed();
                placeBLocks(p, materials[0], materials[1], materials[2]);
            }


        }
    }

    public static Material[] verruesProceed()
    {

        //Defining materials for verrues plantations
        Material[] result = new Material[3];
        result[0] = Material.NETHER_WART;
        result[1] = Material.NETHER_WART;
        result[2] = Material.SOUL_SAND;
        return result;
        //placeBLocks(p, plantationMaterial, baseMaterial, plantationItem);
    }

    public Material[] bleProceed()
    {

        //Defining materials for wheat plantations
        Material[] result = new Material[3];
        result[0] = Material.WHEAT_SEEDS;
        result[1] = Material.WHEAT;
        result[2] = Material.FARMLAND;
        return result;
    }

    public Material[] carotteProceed()
    {

        //Defining materials for wheat plantations
        Material[] result = new Material[3];
        result[0] = Material.CARROT;
        result[1] = Material.CARROTS;
        result[2] = Material.FARMLAND;
        return result;
    }

    public Material[] patateProceed()
    {

        //Defining materials for wheat plantations
        Material[] result = new Material[3];
        result[0] = Material.POTATO;
        result[1] = Material.POTATOES;
        result[2] = Material.FARMLAND;
        return result;
    }

    public void placeBLocks(Player p, Material plantationItem, Material plantationMaterial, Material baseMaterial) {

        //Finding where the player clicked
        Block currentBlock = p.getTargetBlock(null, 6);
        Material currentType = currentBlock.getType();

        //Click is on the base material or directly on the plantation
        if (!currentType.equals(baseMaterial)
                && !currentType.equals(plantationMaterial))
            return;

        //Placing current block at the base block
        if (currentType.equals(plantationMaterial))
            currentBlock = getBelowBlock(p, currentBlock);

        Location currentLocation = currentBlock.getLocation();


        //Set others blocks

        //Setting the blocks of the Z axis
        for (int zAxis = -1; zAxis <= 1; zAxis++) {

            //Setting the blocks of the X axis
            for (int xAxis = -1; xAxis <= 1; xAxis++) {

                //This block is the currentBlock for each blocks of the loop
                Block loopBlock = p.getWorld().getBlockAt(currentLocation.getBlockX() + xAxis, currentLocation.getBlockY(), currentLocation.getBlockZ() + zAxis);

                //Check if the loop block is a soul_sand ground, if not we cannot apply nether_wart
                if (loopBlock.getType() != baseMaterial)
                    continue;
                //Check if  there is not a nether_wart applied on the soul_sand, if some we keep it in stand
                Block upperLoopBlock = getUpperBlock(p, loopBlock);
                if (upperLoopBlock.getType() != Material.AIR)
                    continue;

                //Checking if the player is in game mode survival for a little clear of 1 plantation item
                if (p.getGameMode().equals(GameMode.SURVIVAL)) {

                    //Remove 1 durability of the "faux". Durability count is reversed, I don't know why
                    damages += 1;

                    //Check if the player have one or more plantation items
                    if (!p.getInventory().contains(plantationItem))
                        return;

                    //If true, clear 1 !
                    p.getInventory().removeItem(new ItemStack(plantationItem, 1));
                }

                //If we are on the correct base block and previous conditions are true, place the plantation block
                upperLoopBlock.setType(plantationMaterial);

            }
        }
        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.DURABILITY)){
                return;
            }
            p.getInventory().getItemInMainHand().setDurability((short) (p.getInventory().getItemInMainHand().getDurability() + damages));
        }
    }

    public Block getBelowBlock(Player p, Block currentBlock)
    {
        Location currentLocation = currentBlock.getLocation();
        return p.getWorld().getBlockAt(currentLocation.getBlockX(), currentLocation.getBlockY() - 1, currentLocation.getBlockZ());
    }

    public Block getUpperBlock(Player p, Block currentBlock)
    {
        Location currentLocation = currentBlock.getLocation();
        return p.getWorld().getBlockAt(currentLocation.getBlockX(), currentLocation.getBlockY() + 1, currentLocation.getBlockZ());
    }
}
