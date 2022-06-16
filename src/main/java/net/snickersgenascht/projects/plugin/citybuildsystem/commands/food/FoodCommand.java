package net.snickersgenascht.projects.plugin.citybuildsystem.commands.food;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.FoodModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FoodCommand implements CommandExecutor {

    private final String prefix;
    private final AddFoodCommand addFoodCommand;
    private final SetFoodCommand setFoodCommand;
    private final CityBuildConfig cityBuildConfig;
    private final FoodModuleConfig foodModuleConfig;
    private final RemoveFoodCommand removeFoodCommand;

    private final MemberAPI memberAPI = new MemberAPI();

    public FoodCommand(String prefix, CityBuildConfig cityBuildConfig, FoodModuleConfig foodModuleConfig) {
        this.prefix = prefix;
        this.cityBuildConfig = cityBuildConfig;
        this.foodModuleConfig = foodModuleConfig;
        this.addFoodCommand = new AddFoodCommand(this.getPrefix(), this.getFoodModuleConfig(), this.getCityBuildConfig(), memberAPI);
        this.setFoodCommand = new SetFoodCommand(this.getPrefix(), this.getFoodModuleConfig(), this.getCityBuildConfig(), memberAPI);
        this.removeFoodCommand = new RemoveFoodCommand(this.getPrefix(), this.getFoodModuleConfig(), this.getCityBuildConfig(), memberAPI);
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            int i = player.getFoodLevel();
            player.sendMessage(this.getPrefix() + "§7Du hast §a" + i + " §7Hungerbalken§8!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 30, 30);
        } else if (args.length == 1) {
            if (player.hasPermission(this.getFoodModuleConfig().getAdminFoodPermission())) {
                String target = args[0];
                assert target != null;
                if (args[0].equalsIgnoreCase(target)) {
                    if (getMemberAPI().isUsernameRegistered(target)) {
                        Player target2 = Bukkit.getPlayer(target);
                        if (this.getMemberAPI().getPlayerState(target2).equalsIgnoreCase("online")) {
                            player.sendMessage(this.getPrefix() + "§7Der Spieler §a" + target2.getName() + " §7hat §a" + player.getFoodLevel() + " §7Hungerbalken§8!");
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
            this.getAddFoodCommand().openCommand(player, args);
            this.getSetFoodCommand().openCommand(player, args);
            this.getRemoveFoodCommand().openCommand(player, args);
        }
        return false;
    }

    //                            this.homesAPI.createHomesTable();
    //                            this.homesAPI.registerHome(player, 1, "Test", "GRASS");
    //                            player.sendMessage(this.getPrefix() + "§7Der Spieler hat §a" + this.homesAPI.getHomeName(player, "1") + " §7Level§8!");

    public String getPrefix() {
        return prefix;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public FoodModuleConfig getFoodModuleConfig() {
        return foodModuleConfig;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

    public SetFoodCommand getSetFoodCommand() {
        return setFoodCommand;
    }

    public AddFoodCommand getAddFoodCommand() {
        return addFoodCommand;
    }

    public RemoveFoodCommand getRemoveFoodCommand() {
        return removeFoodCommand;
    }
}