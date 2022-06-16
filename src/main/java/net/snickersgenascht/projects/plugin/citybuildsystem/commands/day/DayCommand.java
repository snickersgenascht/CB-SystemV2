package net.snickersgenascht.projects.plugin.citybuildsystem.commands.day;

import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.DayModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Objects;

public class DayCommand implements CommandExecutor {

    private final String prefix;
    private final CityBuildConfig cityBuildConfig;
    private final DayModuleConfig dayModuleConfig;

    public DayCommand(String prefix, CityBuildConfig cityBuildConfig, DayModuleConfig dayModuleConfig) {
        this.prefix = prefix;
        this.cityBuildConfig = cityBuildConfig;
        this.dayModuleConfig = dayModuleConfig;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.hasPermission(this.getDayModuleConfig().getDayPermission())) {
                Objects.requireNonNull(Bukkit.getPlayer(player.getName())).getWorld().setTime(1000);
                player.sendMessage(this.getPrefix() + "§7Es sollte nun wieder §eTag §7sein§8!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                player.sendMessage(this.getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
            }
        }
        return false;
    }

    public DayModuleConfig getDayModuleConfig() {
        return dayModuleConfig;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public String getPrefix() {
        return prefix;
    }
}
