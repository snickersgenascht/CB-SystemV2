package net.snickersgenascht.projects.plugin.citybuildsystem.commands.home;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.HomeModuleConfig;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HomeUserCommand {

    private final HomesAPI homesAPI;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final HomeModuleConfig homeModuleConfig;

    public HomeUserCommand() {
        this.homesAPI = new HomesAPI();
        this.memberAPI = new MemberAPI();
        this.cityBuildConfig = new CityBuildConfig();
        this.homeModuleConfig = new HomeModuleConfig();
    }

    public void openCommand(Player player, String[] args) {
        if (player.hasPermission(this.getHomeModuleConfig().getHomeModPermission())) {
            if (args[0].equalsIgnoreCase("user")) {
                String target = args[1];
                if (args[1].equalsIgnoreCase(target)) {
                    if (args[2].equalsIgnoreCase("delete")) {
                        Integer id = Integer.valueOf(args[3]);
                        if (args[3].equalsIgnoreCase(String.valueOf(id))) {
                            if (memberAPI.isUsernameRegistered(target)) {
                                if (!homesAPI.isHomePlayerRegistered(target)) {
                                    ArrayList<Integer> i2 = this.getHomesAPI().getHomes("", target, 45);
                                    if (i2.contains(id)) {
                                        homesAPI.deleteHome(target, id);
                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDie ID wurde erfolgreich gelöscht§8!");
                                    } else {
                                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDiese ID existiert nicht§8!");
                                    }
                                } else {
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDer Spieler hat gar keine Homes§8!");
                                }
                            } else {
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDer Spieler existiert nicht§8!");
                            }
                        }
                    }
                }
            }
        } else {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
            player.sendMessage(this.getCityBuildConfig().getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
        }
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public HomesAPI getHomesAPI() {
        return homesAPI;
    }

    public HomeModuleConfig getHomeModuleConfig() {
        return homeModuleConfig;
    }
}
