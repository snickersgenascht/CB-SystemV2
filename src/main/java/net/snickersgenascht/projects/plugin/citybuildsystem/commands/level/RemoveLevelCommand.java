package net.snickersgenascht.projects.plugin.citybuildsystem.commands.level;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.LevelModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class RemoveLevelCommand {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final LevelModuleConfig levelModuleConfig;

    public RemoveLevelCommand(String prefix, LevelModuleConfig levelModuleConfig, CityBuildConfig cityBuildConfig, MemberAPI memberAPI) {
        this.prefix = prefix;
        this.memberAPI = memberAPI;
        this.cityBuildConfig = cityBuildConfig;
        this.levelModuleConfig = levelModuleConfig;
    }

    public void openCommand(Player player, String[] args) {
        if (args.length == 3) {
            if (player.hasPermission(this.getLevelModuleConfig().getAdminLevelPermission())) {
                if (args[0].equalsIgnoreCase("remove")) {
                    String target = args[1];
                    if (args[1].equalsIgnoreCase(target)) {
                        Integer integer = Integer.valueOf(args[2]);
                        if (args[2].equalsIgnoreCase(String.valueOf(integer))) {
                            if (this.getMemberAPI().isUsernameRegistered(target)) {
                                Player target2 = Bukkit.getPlayer(target);
                                if (this.getMemberAPI().getPlayerState(this.getMemberAPI().getUUID(target)).equalsIgnoreCase("online")) {
                                    assert target2 != null;
                                    if (target2.getLevel() > integer) {
                                        target2.setLevel(target2.getLevel() - integer);
                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
                                        player.sendMessage(this.getPrefix() + "§7Der Spieler §a" + target2.getName() + " §7hat die §a" + integer + " §7Level abgezogen bekommen§8!");
                                    } else {
                                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                                        player.sendMessage(this.getPrefix() + "§cDer Spieler hat nicht so viel Level§8!");
                                    }
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
                }
            }
        }
    }

    public String getPrefix() {
        return prefix;
    }
    public LevelModuleConfig getLevelModuleConfig() {
        return levelModuleConfig;
    }
    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

}
