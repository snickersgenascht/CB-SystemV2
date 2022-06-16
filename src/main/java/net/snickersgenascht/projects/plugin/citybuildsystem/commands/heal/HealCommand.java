package net.snickersgenascht.projects.plugin.citybuildsystem.commands.heal;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.FoodModuleConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.HealModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class HealCommand implements CommandExecutor {

    private final String prefix;
    private final AddHealCommand addHealCommand;
    private final SetHealCommand setHealCommand;
    private final CityBuildConfig cityBuildConfig;
    private final HealModuleConfig healModuleConfig;
    private final RemoveHealCommand removeHealCommand;

    private final MemberAPI memberAPI = new MemberAPI();

    public HealCommand(String prefix, CityBuildConfig cityBuildConfig, HealModuleConfig healModuleConfig) {
        this.prefix = prefix;
        this.cityBuildConfig = cityBuildConfig;
        this.healModuleConfig = healModuleConfig;
        this.addHealCommand = new AddHealCommand(this.getPrefix(), this.getHealModuleConfig(), this.getCityBuildConfig(), memberAPI);
        this.setHealCommand = new SetHealCommand(this.getPrefix(), this.getHealModuleConfig(), this.getCityBuildConfig(), memberAPI);
        this.removeHealCommand = new RemoveHealCommand(this.getPrefix(), this.getHealModuleConfig(), this.getCityBuildConfig(), memberAPI);
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(this.getPrefix() + "§7Du hast §a" + player.getHealth() + " §7Leben§8!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
        } else if (args.length == 1) {
            if (player.hasPermission(this.getHealModuleConfig().getAdminHealPermission())) {
                String target = args[0];
                assert target != null;
                if (args[0].equalsIgnoreCase(target)) {
                    if (getMemberAPI().isUsernameRegistered(target)) {
                        Player target2 = Bukkit.getPlayer(target);
                        if (this.getMemberAPI().getPlayerState(target2).equalsIgnoreCase("online")) {
                            player.sendMessage(this.getPrefix() + "§7Der Spieler §a" + target2.getName() + " §7hat §a" + player.getHealth() + " §7Leben§8!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
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
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 30, 30);
                player.sendMessage(this.getPrefix() + this.getCityBuildConfig().getPermissionsMessage());
            }
        } else {
            this.getAddHealCommand().openCommand(player, args);
            this.getSetHealCommand().openCommand(player, args);
            this.getRemoveHealCommand().openCommand(player, args);
        }
        return false;
    }

    public String getPrefix() {
        return prefix;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public HealModuleConfig getHealModuleConfig() {
        return healModuleConfig;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

    public SetHealCommand getSetHealCommand() {
        return setHealCommand;
    }

    public AddHealCommand getAddHealCommand() {
        return addHealCommand;
    }

    public RemoveHealCommand getRemoveHealCommand() {
        return removeHealCommand;
    }
}