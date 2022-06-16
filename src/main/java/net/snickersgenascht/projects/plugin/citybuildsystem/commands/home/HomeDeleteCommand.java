package net.snickersgenascht.projects.plugin.citybuildsystem.commands.home;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;

public class HomeDeleteCommand {

    private final HomesAPI homesAPI;
    private final CityBuildConfig cityBuildConfig;

    public HomeDeleteCommand(HomesAPI homesAPI, CityBuildConfig cityBuildConfig) {
        this.homesAPI = homesAPI;
        this.cityBuildConfig = cityBuildConfig;
    }

    public void openCommand(Player player, String[] args) {
        if (args[0].equalsIgnoreCase("delete")) {
            Integer id = Integer.valueOf(args[1]);
            if (args[1].equalsIgnoreCase(String.valueOf(id))) {
                if (!(this.getHomesAPI().countRowsInOnlineTable(player) == 0)) {
                    ArrayList<Integer> i2 = this.getHomesAPI().getHomes("", player, this.getHomesAPI().countRowsInOnlineTable(player));
                    if (i2.contains(id)) {
                        this.getHomesAPI().deleteHome(player, id);
                        for (Integer integer : this.getHomesAPI().getHomes("", player, this.getHomesAPI().countRowsInOnlineTable(player))) {
                            this.getHomesAPI().removeIDINT(player, integer, 1);
                        }
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast diese ID gelöscht§8!");
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDiese ID existiert bei dir nicht§8!");
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast überhaupt keine Homes§8!");
                }
            }
        }
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    private HomesAPI getHomesAPI() {
        return homesAPI;
    }

}
