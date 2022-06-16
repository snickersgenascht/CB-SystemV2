package net.snickersgenascht.projects.plugin.citybuildsystem;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.HomesAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.day.DayCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.food.FoodCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.heal.HealCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.home.HomeCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.home.HomesCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.level.LevelCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.night.NightCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.ping.PingCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.commands.weather.WeatherCommand;
import net.snickersgenascht.projects.plugin.citybuildsystem.completer.home.HomeCreateTabCompleter;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.*;
import net.snickersgenascht.projects.plugin.citybuildsystem.database.MySQL;
import net.snickersgenascht.projects.plugin.citybuildsystem.listeners.PlayerChatEventListener;
import net.snickersgenascht.projects.plugin.citybuildsystem.listeners.PlayerClickEventListener;
import net.snickersgenascht.projects.plugin.citybuildsystem.listeners.PlayerJoinEventListener;
import net.snickersgenascht.projects.plugin.citybuildsystem.listeners.PlayerQuitEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CityBuildSystem extends JavaPlugin {

    private final TeleportModuleConfig teleportModuleConfig = new TeleportModuleConfig();
    private final WeatherModuleConfig weatherModuleConfig = new WeatherModuleConfig();
    private final LevelModuleConfig levelModuleConfig = new LevelModuleConfig();
    private final NightModuleConfig nightModuleConfig = new NightModuleConfig();
    private final PingModuleConfig pingModuleConfig = new PingModuleConfig();
    private final HomeModuleConfig homeModuleConfig = new HomeModuleConfig();
    private final FoodModuleConfig foodModuleConfig = new FoodModuleConfig();
    private final HealModuleConfig healModuleConfig = new HealModuleConfig();
    private final CityBuildConfig cityBuildConfig = new CityBuildConfig();
    private final ListenersConfig listenersConfig = new ListenersConfig();
    private final DayModuleConfig dayModuleConfig = new DayModuleConfig();
    private final MemberAPI memberAPI = new MemberAPI();
    private final HomesAPI homesAPI = new HomesAPI();

    @Override
    public void onEnable() {
        this.start();
    }

    private void start() {
        this.registerSetups();
        this.registerDatabase();
        this.registerCommands();
        this.registerListeners();
        this.registerTabCompleter();
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerClickEventListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(this.getCityBuildConfig().getPrefix()), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitEventListener(this.getCityBuildConfig().getPrefix()), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChatEventListener(this.getCityBuildConfig().getPrefix()), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("food")).setExecutor(new FoodCommand(this.getCityBuildConfig().getPrefix(), this.getCityBuildConfig(), this.getFoodModuleConfig()));
        Objects.requireNonNull(this.getCommand("hearts")).setExecutor(new HealCommand(this.getCityBuildConfig().getPrefix(), this.getCityBuildConfig(), this.getHealModuleConfig()));
        Objects.requireNonNull(this.getCommand("level")).setExecutor(new LevelCommand(this.getCityBuildConfig().getPrefix(), this.getCityBuildConfig(), this.getLevelModuleConfig()));
        Objects.requireNonNull(this.getCommand("day")).setExecutor(new DayCommand(this.getCityBuildConfig().getPrefix(), this.getCityBuildConfig(), this.getDayModuleConfig()));
        Objects.requireNonNull(this.getCommand("night")).setExecutor(new NightCommand(this.getCityBuildConfig().getPrefix(), this.getCityBuildConfig(), this.getNightModuleConfig()));
        Objects.requireNonNull(this.getCommand("home")).setExecutor(new HomeCommand(this.getCityBuildConfig().getPrefix()));
        Objects.requireNonNull(this.getCommand("weather")).setExecutor(new WeatherCommand(this.getWeatherModuleConfig()));
        Objects.requireNonNull(this.getCommand("ping")).setExecutor(new PingCommand(this.getPingModuleConfig()));
        Objects.requireNonNull(this.getCommand("homes")).setExecutor(new HomesCommand());
    }

    private void registerTabCompleter() {
        this.getCommand("home").setTabCompleter(new HomeCreateTabCompleter(this.homesAPI, this.memberAPI));
    }

    private void registerDatabase() {
        MySQL mySQL = new MySQL();
        mySQL.connect();
        memberAPI.connect();
        homesAPI.createHomesTable();
    }

    private void registerSetups() {
        this.getCityBuildConfig().startSetup();
        this.getListenersConfig().startSetup();
        this.getDayModuleConfig().startSetup();
        this.getFoodModuleConfig().startSetup();
        this.getPingModuleConfig().startSetup();
        this.getHealModuleConfig().startSetup();
        this.getHomeModuleConfig().startSetup();
        this.getLevelModuleConfig().startSetup();
        this.getNightModuleConfig().startSetup();
        this.getWeatherModuleConfig().startSetup();
        this.getTeleportModuleConfig().startSetup();
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }

    public ListenersConfig getListenersConfig() {
        return listenersConfig;
    }

    @Override
    public void onDisable() {}

    public TeleportModuleConfig getTeleportModuleConfig() {
        return teleportModuleConfig;
    }

    public LevelModuleConfig getLevelModuleConfig() {
        return levelModuleConfig;
    }

    public FoodModuleConfig getFoodModuleConfig() {
        return foodModuleConfig;
    }

    public HealModuleConfig getHealModuleConfig() {
        return healModuleConfig;
    }

    public NightModuleConfig getNightModuleConfig() {
        return nightModuleConfig;
    }

    public DayModuleConfig getDayModuleConfig() {
        return dayModuleConfig;
    }

    public HomeModuleConfig getHomeModuleConfig() {
        return homeModuleConfig;
    }

    public PingModuleConfig getPingModuleConfig() {
        return pingModuleConfig;
    }

    public WeatherModuleConfig getWeatherModuleConfig() {
        return weatherModuleConfig;
    }
}
