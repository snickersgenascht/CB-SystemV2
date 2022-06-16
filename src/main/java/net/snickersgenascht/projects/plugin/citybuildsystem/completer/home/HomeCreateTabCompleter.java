package net.snickersgenascht.projects.plugin.citybuildsystem.completer.home;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeCreateTabCompleter implements TabCompleter {

    private final HomesAPI homesAPI;
    private final MemberAPI memberAPI;

    public HomeCreateTabCompleter(HomesAPI homesAPI, MemberAPI memberAPI) {
        this.homesAPI = homesAPI;
        this.memberAPI = memberAPI;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            return this.registerArgs1Commands(player);
        } else if (args.length == 2) {
            return this.registerArgs2Commands(args, player);
        } else if (args.length == 3) {
            return this.registerArgs3Commands(args);
        } else if (args.length > 3) {
            return this.registerArgsHigher3Commands(args);
        }
        return null;
    }

    private List<String> registerArgsHigher3Commands(String[] args) {
        List<String> commands = new ArrayList<>();
        Player player1 = Bukkit.getPlayer(args[1]);
        if (args[0].equalsIgnoreCase("user")) {
            commands.addAll(this.homesAPI.getHomes(player1, 45));
        }
        return commands;
    }

    private List<String> registerArgs3Commands(String[] args) {
        List<String> commands = new ArrayList<>();
        if (args[0].equalsIgnoreCase("create")) {
            for (Material material : Material.values()) {
                commands.add(material.name());
            }
        } else if (args[0].equalsIgnoreCase("user")) {
            commands.add("delete");
        }
        return commands;
    }

    private List<String> registerArgs2Commands(String[] args, Player player) {
        List<String> commands = new ArrayList<>();
        if (args[0].equalsIgnoreCase("create")) {
            commands.add("&cKeinen");
        } else if (args[0].equalsIgnoreCase("delete")) {
            commands.addAll(this.homesAPI.getHomes(player, 45));
        } else if (args[0].equalsIgnoreCase("visit")) {
            commands.addAll(memberAPI.getUserNames(memberAPI.countRowsInOnlineTable()));
        } else if (args[0].equalsIgnoreCase("user")) {
            commands.addAll(memberAPI.getUserNames(memberAPI.countRowsInOnlineTable()));
        }
        return commands;
    }

    private List<String> registerArgs1Commands(Player player) {
        List<String> commands = new ArrayList<>();
        commands.add("create");
        commands.add("delete");
        if (player.hasPermission("homes.sup.perms")) {
            commands.add("visit");
        } else if (player.hasPermission("homes.mod.perms")) {
            commands.add("user");
        }
        return commands;
    }

}
