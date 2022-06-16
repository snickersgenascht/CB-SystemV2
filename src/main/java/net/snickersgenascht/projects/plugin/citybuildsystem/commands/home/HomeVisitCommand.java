package net.snickersgenascht.projects.plugin.citybuildsystem.commands.home;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.HomeModuleConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.inventories.HomeInventory;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HomeVisitCommand {

    private final HomesAPI homesAPI;
    private final CityBuildConfig cityBuildConfig;
    private final HomeModuleConfig homeModuleConfig;

    public HomeVisitCommand(HomesAPI homesAPI, CityBuildConfig cityBuildConfig) {
        this.homesAPI = homesAPI;
        this.cityBuildConfig = cityBuildConfig;
        this.homeModuleConfig = new HomeModuleConfig();
    }

    public void openCommand(Player player, String[] args) {
        if (args[0].equalsIgnoreCase("visit")) {
            final MemberAPI memberAPI = new MemberAPI();
            String target = args[1];
            if (args[1].equalsIgnoreCase(target)) {
                if (player.hasPermission(this.homeModuleConfig.getHomeSupPermission())) {
                    if (!this.homesAPI.isHomePlayerRegistered(target)) {
                        if (memberAPI.isUsernameRegistered(target)) {
                            HomeInventory homeInventory = new HomeInventory();
                            homeInventory.openInventory("", player, target);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                        } else {
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                            player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDer Spieler ist nicht Online§8!");
                        }
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDer Spieler existiert nicht§8!");
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDazu hast du keine Rechte§8!");
                }
            }
        }
    }

    private HomesAPI getHomesAPI() {
        return homesAPI;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public HomeModuleConfig getHomeModuleConfig() {
        return homeModuleConfig;
    }
}
