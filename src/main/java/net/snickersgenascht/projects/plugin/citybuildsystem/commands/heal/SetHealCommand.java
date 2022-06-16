package net.snickersgenascht.projects.plugin.citybuildsystem.commands.heal;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.FoodModuleConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.HealModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SetHealCommand {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final HealModuleConfig healModuleConfig;

    public SetHealCommand(String prefix, HealModuleConfig healModuleConfig, CityBuildConfig cityBuildConfig, MemberAPI memberAPI) {
        this.prefix = prefix;
        this.memberAPI = memberAPI;
        this.cityBuildConfig = cityBuildConfig;
        this.healModuleConfig = healModuleConfig;
    }

    public void openCommand(Player player, String[] args) {
        if (player.hasPermission(this.getHealModuleConfig().getAdminHealPermission())) {
            if (args[0].equalsIgnoreCase("set")) {
                String target = args[1];
                assert target != null;
                if (args[1].equalsIgnoreCase(target)) {
                    Integer integer = Integer.valueOf(args[2]);
                    if (args[2].equalsIgnoreCase(String.valueOf(integer))) {
                        if (this.getMemberAPI().isUsernameRegistered(target)) {
                            Player target2 = Bukkit.getPlayer(target);
                            assert target2 != null;
                            if (this.getMemberAPI().getPlayerState(this.getMemberAPI().getUUID(target)).equalsIgnoreCase("online")) {
                                target2.setHealth(integer);
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
                                player.sendMessage(this.getPrefix() + "§7Der Spieler §a" + target2.getName() + " §7wurden die §a" + integer + " §7Leben gesetzt§8!");
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
        } else {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
            player.sendMessage(this.getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
        }
    }

    public String getPrefix() {
        return prefix;
    }
    public HealModuleConfig getHealModuleConfig() {
        return healModuleConfig;
    }
    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }
    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

}