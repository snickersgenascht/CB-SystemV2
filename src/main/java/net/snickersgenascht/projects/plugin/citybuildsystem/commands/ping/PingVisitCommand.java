package net.snickersgenascht.projects.plugin.citybuildsystem.commands.ping;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.PingModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class PingVisitCommand {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final PingModuleConfig pingModuleConfig;

    public PingVisitCommand(String prefix, CityBuildConfig cityBuildConfig, MemberAPI memberAPI, PingModuleConfig pingModuleConfig) {
        this.prefix = prefix;
        this.memberAPI = memberAPI;
        this.cityBuildConfig = cityBuildConfig;
        this.pingModuleConfig = pingModuleConfig;
    }

    public void openCommand(Player player, String[] args) {
        if (player.hasPermission(this.getPingModuleConfig().getPingPermission())) {
            if (args[0].equalsIgnoreCase("visit")) {
                String target = args[1];
                if (args[1].equalsIgnoreCase(target)) {
                    if (this.getMemberAPI().isUsernameRegistered(target)) {
                        if (this.getMemberAPI().getPlayerState(target).equalsIgnoreCase("online")) {
                            Player player1 = Bukkit.getPlayer(target);
                            player.sendMessage("§7");
                            player.sendMessage("§7 " + this.getPrefix() + "§7" + target + "'s aktueller Ping§8:");
                            if (getPing(player1) < 100) {
                                player.sendMessage("§7  §8(§2Gut§8) - §2" + getPing(player1));
                            } else if (getPing(player1) < 500) {
                                player.sendMessage("§7  §8(§cSchlecht§8) - §c" + getPing(player1));
                            } else if (getPing(player1) > 500) {
                                player.sendMessage("§7  §8(§4Vodafone Empfang§8) - §4" + getPing(player1));
                            }
                            player.sendMessage("§7");
                        } else {
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                            player.sendMessage(this.getPrefix() + "§cDieser Spieler muss dafür Online sein§8!");
                        }
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                        player.sendMessage(this.getPrefix() + "§cDer Spieler wurde nicht gefunden§8!");
                    }
                }
            }
        } else {
            player.sendMessage(this.getCityBuildConfig().getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
        }
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public int getPing(Player player) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

    public PingModuleConfig getPingModuleConfig() {
        return pingModuleConfig;
    }
}
