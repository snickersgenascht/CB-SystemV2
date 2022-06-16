package net.snickersgenascht.projects.plugin.citybuildsystem.commands.level;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.LevelModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class LevelCommand implements CommandExecutor {

    private final String prefix;
    private final AddLevelCommand addLevelCommand;
    private final CityBuildConfig cityBuildConfig;
    private final SetLevelCommand setLevelCommand;
    private final HomesAPI homesAPI = new HomesAPI();
    private final LevelModuleConfig levelModuleConfig;
    private final RemoveLevelCommand removeLevelCommand;
    private final MemberAPI memberAPI = new MemberAPI();

    public LevelCommand(String prefix, CityBuildConfig cityBuildConfig, LevelModuleConfig levelModuleConfig) {
        this.prefix = prefix;
        this.cityBuildConfig = cityBuildConfig;
        this.levelModuleConfig = levelModuleConfig;
        this.addLevelCommand = new AddLevelCommand(this.getPrefix(), this.getLevelModuleConfig(), this.getCityBuildConfig(), memberAPI);
        this.setLevelCommand = new SetLevelCommand(this.getPrefix(), this.getLevelModuleConfig(), this.getCityBuildConfig(), memberAPI);
        this.removeLevelCommand = new RemoveLevelCommand(this.getPrefix(), this.getLevelModuleConfig(), this.getCityBuildConfig(), memberAPI);
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(this.getPrefix() + "§7Du hast momentan §a" + player.getLevel() + " §7Level§8!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
        } else if (args.length == 1) {
            if (player.hasPermission(this.getLevelModuleConfig().getAdminLevelPermission())) {
                String target = args[0];
                assert target != null;
                if (args[0].equalsIgnoreCase(target)) {
                    if (getMemberAPI().isUsernameRegistered(target)) {
                        Player target2 = Bukkit.getPlayer(target);
                        if (this.getMemberAPI().getPlayerState(this.getMemberAPI().getUUID(target)).equalsIgnoreCase("online")) {
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
                            player.sendMessage(this.getPrefix() + "§7Der Spieler hat §a" + target2.getLevel() + " §7Level§8!");
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
        } else if (args.length == 3) {
            this.getAddLevelCommand().openCommand(player, args);
            this.getRemoveLevelCommand().openCommand(player, args);
            this.getSetLevelCommand().openCommand(player, args);
        }
        return false;
    }

    public String getPrefix() {
        return prefix;
    }

    public AddLevelCommand getAddLevelCommand() {
        return addLevelCommand;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public LevelModuleConfig getLevelModuleConfig() {
        return levelModuleConfig;
    }

    public RemoveLevelCommand getRemoveLevelCommand() {
        return removeLevelCommand;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

    public SetLevelCommand getSetLevelCommand() {
        return setLevelCommand;
    }
}
