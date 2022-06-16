package net.snickersgenascht.projects.plugin.citybuildsystem.commands.heal;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.FoodModuleConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.HealModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AddHealCommand {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final HealModuleConfig healModuleConfig;

    public AddHealCommand(String prefix, HealModuleConfig healModuleConfig, CityBuildConfig cityBuildConfig, MemberAPI memberAPI) {
        this.prefix = prefix;
        this.memberAPI = memberAPI;
        this.cityBuildConfig = cityBuildConfig;
        this.healModuleConfig = healModuleConfig;
    }

    public void openCommand(Player player, String[] args) {
        if (player.hasPermission(this.getHealModuleConfig().getAdminHealPermission())) {
            if (args[0].equalsIgnoreCase("add")) {
                String target = args[1];
                assert target != null;
                if (args[1].equalsIgnoreCase(target)) {
                    Integer integer = Integer.valueOf(args[2]);
                    if (args[2].equalsIgnoreCase(String.valueOf(integer))) {
                        if (this.getMemberAPI().isUsernameRegistered(target)) {
                            Player target2 = Bukkit.getPlayer(target);
                            if (this.getMemberAPI().getPlayerState(this.getMemberAPI().getUUID(target)).equalsIgnoreCase("online")) {
                                assert target2 != null;
                                if (target2.getHealth() < integer && integer < 21) {
                                    target2.setHealth(target2.getHealth() + integer);
                                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
                                    player.sendMessage(this.getPrefix() + "§7Der Spieler §a" + target2.getName() + " §7hat die §a" + integer + " §7Leben dazu bekommen§8!");
                                } else if (target2.getHealth() > integer) {
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                                    player.sendMessage(this.getPrefix() + "§7Der Spieler §a" + target2.getName() + " §7hat die §a" + integer + " §7Leben dazu bekommen§8!");
                                    player.sendMessage(this.getPrefix() + "§cDer Spieler hat mehr als " + integer + " Leben§8!");
                                } else if (integer > 20) {
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                                    player.sendMessage(this.getPrefix() + "§cDu kannst mindestens 20 Leben vergeben§8!");
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
