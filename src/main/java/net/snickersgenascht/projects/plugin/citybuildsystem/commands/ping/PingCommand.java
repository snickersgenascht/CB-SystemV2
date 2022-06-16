package net.snickersgenascht.projects.plugin.citybuildsystem.commands.ping;

import net.minecraft.server.level.EntityPlayer;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.PingModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;

public class PingCommand implements CommandExecutor {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final PingModuleConfig pingModuleConfig;
    private final PingVisitCommand pingVisitCommand;

    public PingCommand(PingModuleConfig pingModuleConfig) {
        this.memberAPI = new MemberAPI();
        this.pingModuleConfig = pingModuleConfig;
        this.cityBuildConfig = new CityBuildConfig();
        this.prefix = this.getCityBuildConfig().getPrefix();
        this.pingVisitCommand = new PingVisitCommand(this.getPrefix(), this.getCityBuildConfig(), this.getMemberAPI(), this.getPingModuleConfig());
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("§7");
            player.sendMessage("§7 " + this.getPrefix() + "§7Dein aktueller Ping§8:");
            if (getPing(player) < 100) {
                player.sendMessage("§7  §8(§2Gut§8) - §2" + getPing(player));
            } else if (getPing(player) < 500) {
                player.sendMessage("§7  §8(§cSchlecht§8) - §c" + getPing(player));
            } else if (getPing(player) > 500) {
                player.sendMessage("§7  §8(§4Vodafone Empfang§8) - §4" + getPing(player));
            }
            player.sendMessage("§7");
        } else if (args.length == 1) {
            if (player.hasPermission(this.getPingModuleConfig().getPingPermission())) {
                String target = args[0];
                if (args[0].equalsIgnoreCase(target)) {
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
            } else {
                player.sendMessage(this.getCityBuildConfig().getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
            }
        } else {
            this.getPingVisitCommand().openCommand(player, args);
        }
        return false;
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

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public String getPrefix() {
        return prefix;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

    public PingVisitCommand getPingVisitCommand() {
        return pingVisitCommand;
    }

    public PingModuleConfig getPingModuleConfig() {
        return pingModuleConfig;
    }

}
