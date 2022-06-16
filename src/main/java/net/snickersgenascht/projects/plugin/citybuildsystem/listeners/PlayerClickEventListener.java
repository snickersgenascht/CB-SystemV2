package net.snickersgenascht.projects.plugin.citybuildsystem.listeners;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryView;

import java.util.Objects;

public class PlayerClickEventListener implements Listener {

    private final HomesAPI homesAPI;

    public PlayerClickEventListener() {
        this.homesAPI = new HomesAPI();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onClickHome(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        for (String home : this.homesAPI.getHomes(player, 45)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §7ID§8: §c" + home + " §8┃ §7Rechtsklick")) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 50, 50);
                Location location = Bukkit.getWorld(this.homesAPI.getHomeWorldLocation(player, home)).getBlockAt(this.homesAPI.getHomeXLocation(player, home), this.homesAPI.getHomeYLocation(player, home), this.homesAPI.getHomeZLocation(player, home)).getLocation();
                player.teleport(location);
                player.updateInventory();
                player.closeInventory();
                event.setCancelled(true);
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c-/-")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDrag(InventoryDragEvent event) {
        InventoryView inventoryView = event.getView();
        if (inventoryView.getTitle().equalsIgnoreCase("HomesMenu ┃ Vents")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMoveItemEvent(InventoryMoveItemEvent event) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            for (String home : this.homesAPI.getHomes(all, 45)) {
                if (Objects.requireNonNull(event.getItem().getItemMeta()).getDisplayName().equalsIgnoreCase("§8» §7ID§8: §c" + home + " §8┃ §7Rechtsklick")) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
