package net.snickersgenascht.projects.plugin.citybuildsystem.commands.weather;

import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.WeatherModuleConfig;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {

    private final WeatherModuleConfig weatherModuleConfig;
    private final CityBuildConfig cityBuildConfig;

    public WeatherCommand(WeatherModuleConfig weatherModuleConfig) {
        this.weatherModuleConfig = weatherModuleConfig;
        this.cityBuildConfig = new CityBuildConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission(this.getWeatherModuleConfig().getWeatherUsePermission())) {
            if (args.length == 0) {
                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§8/§7weather clear");
                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§8/§7weather sun");
                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§8/§7weather rain");
                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§8/§7weather thunder");
            }
        } else {
            player.sendMessage(this.getCityBuildConfig().getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
        }
        return false;
    }

    public WeatherModuleConfig getWeatherModuleConfig() {
        return weatherModuleConfig;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }
}
