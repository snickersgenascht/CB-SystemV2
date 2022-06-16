package net.snickersgenascht.projects.plugin.citybuildsystem.commands.home;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.HomeModuleConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class HomeCommand implements CommandExecutor {

    private final String prefix;
    private final HomesAPI homesAPI;
    private final HomeUserCommand homeUserCommand;
    private final CityBuildConfig cityBuildConfig;
    private final HomeModuleConfig homeModuleConfig;
    private final HomeVisitCommand homeVisitCommand;
    private final HomeDeleteCommand homeDeleteCommand;
    private final HomeCreateCommand homeCreateCommand;

    public HomeCommand(String prefix) {
        this.prefix = prefix;
        this.homesAPI = new HomesAPI();
        this.cityBuildConfig = new CityBuildConfig();
        this.homeUserCommand = new HomeUserCommand();
        this.homeModuleConfig = new HomeModuleConfig();
        this.homeVisitCommand = new HomeVisitCommand(this.homesAPI, cityBuildConfig);
        this.homeDeleteCommand = new HomeDeleteCommand(this.homesAPI, cityBuildConfig);
        this.homeCreateCommand = new HomeCreateCommand(this.homesAPI, cityBuildConfig);
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            this.getCommand(player);
        } else if (args.length == 1) {
            this.getHomeCreateCommand().openCommand(player, args);
        } else if (args.length == 2) {
            this.getHomeCreateCommand().openCommand2(player, args);
            this.getHomeDeleteCommand().openCommand(player, args);
            this.getHomeVisitCommand().openCommand(player, args);
        } else if (args.length == 3) {
            this.getHomeCreateCommand().openCommand3(player, args);
        } else if (args.length == 4) {
            this.getHomeUserCommand().openCommand(player, args);
        }
        return false;
    }

    private void getCommand(Player player) {
        player.sendMessage("§7");
        player.sendMessage(this.getPrefix() + "§8/§chome create §8(§eName§8) §8(§cBlock§8)");
        player.sendMessage(this.getPrefix() + "§8/§chome delete §8(§eID§8)");
        if (player.hasPermission(this.getHomeModuleConfig().getHomeSupPermission())) {
            player.sendMessage(this.getPrefix() + "§8/§chome visit §8(§eSpieler§8)");
        } else if (player.hasPermission(this.getHomeModuleConfig().getHomeModPermission())) {
            player.sendMessage(this.getPrefix() + "§8/§chome user §8(§eSpieler§8) §cdelete §8(§eID§8)");
        }
        player.sendMessage("§7");
    }

    public String getPrefix() {
        return prefix;
    }

    public HomeCreateCommand getHomeCreateCommand() {
        return homeCreateCommand;
    }

    public HomeVisitCommand getHomeVisitCommand() {
        return homeVisitCommand;
    }

    public HomeDeleteCommand getHomeDeleteCommand() {
        return homeDeleteCommand;
    }

    public HomeUserCommand getHomeUserCommand() {
        return homeUserCommand;
    }

    public HomeModuleConfig getHomeModuleConfig() {
        return homeModuleConfig;
    }
}
