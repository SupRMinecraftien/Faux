package fr.suprminecraftien.superhoes;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Enchantments {

    public static final Enchantment WART = new EnchantmentsWrapper("wart", "Wart", 1);
    public static final Enchantment WHEAT = new EnchantmentsWrapper("wheat", "Wheat", 1);
    public static final Enchantment CARROT = new EnchantmentsWrapper("carrot", "Carrot", 1);
    public static final Enchantment POTATO = new EnchantmentsWrapper("potato", "Potato", 1);

    public static void register()
    {

        //Definition of enchantments
        boolean registeredWart = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(WART);
        boolean registeredWheat = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(WHEAT);
        boolean registeredCarrot = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CARROT);
        boolean registeredPotato = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(POTATO);

        //If enchants aren't registered, we register them
        if (!registeredWart)
            registerEnchantment(WART);
        if (!registeredWheat)
            registerEnchantment(WHEAT);
        if (!registeredCarrot)
            registerEnchantment(CARROT);
        if (!registeredPotato)
            registerEnchantment(POTATO);

        System.out.println("Enchantments are successfully registered !");
    }

    public static void registerEnchantment(Enchantment enchantment) {
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
