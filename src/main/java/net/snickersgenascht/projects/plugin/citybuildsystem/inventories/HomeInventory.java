package net.snickersgenascht.projects.plugin.citybuildsystem.inventories;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.CreatorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class HomeInventory {

    private final HomesAPI homesAPI = new HomesAPI();
    private final ArrayList<String> homesLore = new ArrayList<>();

    public Inventory inventory;

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(null, 54, "HomesMenu ┃ Vents");
        this.getBackground(inventory);
        this.getHomes(player, inventory);
        player.openInventory(inventory);
    }

    public void openInventory(String t, Player player, String target) {
        inventory = Bukkit.createInventory(null, 54, "HomesMenu ┃ Vents");
        this.getBackground(inventory);
        this.getHomes("", target, inventory);
        player.openInventory(inventory);
    }

    private void getBackground(Inventory inventory) {
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, CreatorAPI.itemcreator(Material.BLACK_STAINED_GLASS_PANE, "§c-/-", 1, null));
        }
        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, CreatorAPI.itemcreator(Material.BLACK_STAINED_GLASS_PANE, "§c-/-", 1, null));
        }
    }

    private void getHomes(String t, String target, Inventory inventory) {
        int i = 9;
        for (String home : this.homesAPI.getHomes(target, 45)) {
            this.registerHomesLore(target, Integer.valueOf(home));
            inventory.setItem(i, CreatorAPI.itemcreator(Material.valueOf(this.homesAPI.getHomeBlock(target, home)), "§8» §7ID§8: §c" + home + " §8┃ §7Rechtsklick", 1, this.homesLore));
            i++;
        }
    }

    private void getHomes(Player player, Inventory inventory) {
        int i = 9;
        for (String home : this.homesAPI.getHomes(player, 45)) {
            this.registerHomesLore(player, Integer.valueOf(home));
            inventory.setItem(i, CreatorAPI.itemcreator(Material.valueOf(this.homesAPI.getHomeBlock(player, home)), "§8» §7ID§8: §c" + home + " §8┃ §7Rechtsklick", 1, this.homesLore));
            i++;
        }
    }

    private void registerHomesLore(String player, Integer id) {
        this.homesLore.clear();
        this.homesLore.add("§7 §8» §cName §8┃ §7" + this.homesAPI.getHomeName(player, String.valueOf(id)));
    }

    private void registerHomesLore(Player player, Integer id) {
        this.homesLore.clear();
        this.homesLore.add("§7 §8» §cName §8┃ §7" + this.homesAPI.getHomeName(player, String.valueOf(id)));
    }

}
