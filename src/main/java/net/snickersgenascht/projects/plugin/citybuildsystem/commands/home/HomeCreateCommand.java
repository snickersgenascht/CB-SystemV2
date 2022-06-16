package net.snickersgenascht.projects.plugin.citybuildsystem.commands.home;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.CreatorAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HomeCreateCommand {

    private final HomesAPI homesAPI;
    private final CityBuildConfig cityBuildConfig;

    public HomeCreateCommand(HomesAPI homesAPI, CityBuildConfig cityBuildConfig) {
        this.homesAPI = homesAPI;
        this.cityBuildConfig = cityBuildConfig;
    }

    public void openCommand(Player player, String[] args) {
        if (args[0].equalsIgnoreCase("create")) {
            if (player.hasPermission("homes.admin")) {
                if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 45)) {
                    this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, "§c-", "GRASS");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                }
            } else if (player.hasPermission("homes.premium")) {
                if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 20)) {
                    this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, "§c-", "GRASS");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                }
            } else if (player.hasPermission("homes.member")) {
                if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 10)) {
                    this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, "§c-", "GRASS");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                    player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                }
            }
        }
    }

    public void openCommand2(Player player, String[] args) {
        if (args[0].equalsIgnoreCase("create")) {
            String name = args[1];
            if (args[1].equalsIgnoreCase(name)) {
                if (player.hasPermission("homes.admin")) {
                    if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 45)) {
                        this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, name.replaceAll("_", " ").replace('&', '§'), "GRASS");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                    }
                } else if (player.hasPermission("homes.premium")) {
                    if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 20)) {
                        this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, name.replaceAll("_", " ").replace('&', '§'), "GRASS");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                    }
                } else if (player.hasPermission("homes.member")) {
                    if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 10)) {
                        this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, name.replaceAll("_", " ").replace('&', '§'), "GRASS");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                    }
                }
            }
        }
    }

    public void openCommand3(Player player, String[] args) {
        if (args[0].equalsIgnoreCase("create")) {
            String name = args[1];
            if (args[1].equalsIgnoreCase(name)) {
                Material block = Material.valueOf(args[2]);
                if (args[2].equalsIgnoreCase(block.name())) {
                    if (block != null) {
                        if (player.hasPermission("homes.admin")) {
                            if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 45)) {
                                this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, name.replaceAll("_", " ").replace('&', '§'), block.name());
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                            } else {
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                            }
                        } else if (player.hasPermission("homes.premium")) {
                            if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 20)) {
                                this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, name.replaceAll("_", " ").replace('&', '§'), block.name());
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                            } else {
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                            }
                        } else if (player.hasPermission("homes.member")) {
                            if (!(this.getHomesAPI().countRowsInOnlineTable(player) > 10)) {
                                this.getHomesAPI().registerHome(player, this.getHomesAPI().countRowsInOnlineTable(player) + 1, name.replaceAll("_", " ").replace('&', '§'), block.name());
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§aDu hast dein Home erstellt§8!");
                            } else {
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                                player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDu hast dein Home Limit erreicht§8!");
                            }
                        }
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 20, 20);
                        player.sendMessage(this.getCityBuildConfig().getPrefix() + "§cDieser Block existiert nicht§8!");
                    }
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
}
