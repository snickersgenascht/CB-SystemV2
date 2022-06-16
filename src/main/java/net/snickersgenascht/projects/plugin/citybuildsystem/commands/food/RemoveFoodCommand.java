package net.snickersgenascht.projects.plugin.citybuildsystem.commands.food;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.FoodModuleConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.LevelModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class RemoveFoodCommand {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final FoodModuleConfig foodModuleConfig;

    public RemoveFoodCommand(String prefix, FoodModuleConfig foodModuleConfig, CityBuildConfig cityBuildConfig, MemberAPI memberAPI) {
        this.prefix = prefix;
        this.memberAPI = memberAPI;
        this.cityBuildConfig = cityBuildConfig;
        this.foodModuleConfig = foodModuleConfig;
    }

    public void openCommand(Player player, String[] args) {
        if (player.hasPermission(this.getFoodModuleConfig().getAdminFoodPermission())) {
            if (args[0].equalsIgnoreCase("remove")) {
                String target = args[1];
                if (args[1].equalsIgnoreCase(target)) {
                    Integer integer = Integer.valueOf(args[2]);
                    if (args[2].equalsIgnoreCase(String.valueOf(integer))) {
                        if (this.getMemberAPI().isUsernameRegistered(target)) {
                            Player target2 = Bukkit.getPlayer(target);
                            if (this.getMemberAPI().getPlayerState(this.getMemberAPI().getUUID(target)).equalsIgnoreCase("online")) {
                                assert target2 != null;
                                if (target2.getFoodLevel() > integer) {
                                    target2.setFoodLevel(target2.getFoodLevel() - integer);
                                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
                                    player.sendMessage(this.getPrefix() + "§7Der Spieler §a" + target2.getName() + " §7hat die §a" + integer + " §7Hungerbalken abgezogen bekommen§8!");
                                } else {
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                                    player.sendMessage(this.getPrefix() + "§cDer Spieler hat nicht so viele Hungerbalken§8!");
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
    public FoodModuleConfig getFoodModuleConfig() {return foodModuleConfig;}
    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

}
