package net.snickersgenascht.projects.plugin.citybuildsystem.commands.home;

import net.snickersgenascht.projects.plugin.citybuildsystem.inventories.HomeInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class HomesCommand implements CommandExecutor {

    private final HomeInventory homeInventory;

    public HomesCommand() {
        this.homeInventory = new HomeInventory();
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {

        Player player = (Player) sender;
        this.getHomeInventory().openInventory(player);
        return false;

    }

    public HomeInventory getHomeInventory() {
        return homeInventory;
    }
}
