package net.snickersgenascht.projects.plugin.citybuildsystem.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WeatherModuleConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public WeatherModuleConfig() {
        this.file = new File("plugins/CityBuild-System/modules/", "weather.module");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void startSetup() {
        if (!this.getYamlConfiguration().isSet("settings.permission.weather")) {
            this.yamlConfiguration.set("settings.permission.weather.use", "weather.use");
            this.yamlConfiguration.set("settings.permission.weather.clear", "weather.clear");
            this.yamlConfiguration.set("settings.permission.weather.rain", "weather.rain");
            this.yamlConfiguration.set("settings.permission.weather.thunder", "weather.thunder");
            this.saveLoadFile();
        } else {
            this.loadFile();
        }
    }

    public void loadFile() {
        try {
            this.yamlConfiguration.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            this.yamlConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWeatherUsePermission() {
        return this.getYamlConfiguration().getString("settings.permission.weather.use");
    }

    public String getWeatherClearPermission() {
        return this.getYamlConfiguration().getString("settings.permission.weather.clear");
    }

    public String getWeatherRainPermission() {
        return this.getYamlConfiguration().getString("settings.permission.weather.rain");
    }

    public String getWeatherSunPermission() {
        return this.getYamlConfiguration().getString("settings.permission.weather.sun");
    }

    public void saveLoadFile() {
        try {
            this.yamlConfiguration.save(this.file);
            this.yamlConfiguration.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }
    public YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }

}
