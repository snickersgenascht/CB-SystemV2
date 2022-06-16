package net.snickersgenascht.projects.plugin.citybuildsystem.commands.night;

import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.DayModuleConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.NightModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Objects;

public class NightCommand implements CommandExecutor {

    private final String prefix;
    private final CityBuildConfig cityBuildConfig;
    private final NightModuleConfig nightModuleConfig;

    public NightCommand(String prefix, CityBuildConfig cityBuildConfig, NightModuleConfig nightModuleConfig) {
        this.prefix = prefix;
        this.cityBuildConfig = cityBuildConfig;
        this.nightModuleConfig = nightModuleConfig;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.hasPermission(this.getNightModuleConfig().getNightPermission())) {
                Objects.requireNonNull(Bukkit.getPlayer(player.getName())).getWorld().setTime(13500);
                player.sendMessage(this.getPrefix() + "§7Es sollte nun wieder §eNacht §7sein§8!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                player.sendMessage(this.getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
            }
        }
        return false;
    }

    public NightModuleConfig getNightModuleConfig() {
        return nightModuleConfig;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public String getPrefix() {
        return prefix;
    }
}