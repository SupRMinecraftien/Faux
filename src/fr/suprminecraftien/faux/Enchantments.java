package fr.suprminecraftien.faux;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Enchantments {

    public static final Enchantment VERRUES = new EnchantmentsWrapper("verrues", "Verrues", 1);
    public static final Enchantment BLE = new EnchantmentsWrapper("ble", "Blé", 1);
    public static final Enchantment CAROTTE = new EnchantmentsWrapper("carotte", "Carotte", 1);
    public static final Enchantment PATATE = new EnchantmentsWrapper("patate", "Patate", 1);

    public static void register()
    {

        //Definition of enchantments
        boolean registeredVerrues = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(VERRUES);
        boolean registeredBle = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(BLE);
        boolean registeredCarotte = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CAROTTE);
        boolean registeredPatate = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(PATATE);

        //If enchants aren't registered, we register them
        if (!registeredVerrues)
            registerEnchantment(VERRUES);
        if (!registeredBle)
            registerEnchantment(BLE);
        if (!registeredCarotte)
            registerEnchantment(CAROTTE);
        if (!registeredPatate)
            registerEnchantment(PATATE);

        System.out.println("Enchantments are successfully registered !");
    }

    public static void registerEnchantment(Enchantment enchantment) {
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
            System.out.println("Enchantment " + enchantment + " registered");
        }
        catch (Exception e){
            System.out.println(e.getCause());
        }
    }
}
