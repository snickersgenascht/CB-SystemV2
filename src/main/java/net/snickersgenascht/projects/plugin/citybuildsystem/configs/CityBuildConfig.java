package net.snickersgenascht.projects.plugin.citybuildsystem.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CityBuildConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public CityBuildConfig() {
        this.file = new File("plugins/CityBuild-System/", "config.yml");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void startSetup() {
        if (!this.getYamlConfiguration().isSet("settings.prefix")) {
            this.yamlConfiguration.set("settings.prefix", "§7 §8» §cCityBuild §8● §7");
            this.yamlConfiguration.set("settings.permission.message", "§cDazu hast du keine Berechtigung§8, §cdiese Aktion durchzuführen§8!");
            this.yamlConfiguration.set("settings.mysql.host", "0.0.0.0");
            this.yamlConfiguration.set("settings.mysql.port", "3306");
            this.yamlConfiguration.set("settings.mysql.username", "example");
            this.yamlConfiguration.set("settings.mysql.password", "example");
            this.yamlConfiguration.set("settings.mysql.database", "example");
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

    public String getPrefix() {
        return this.getYamlConfiguration().getString("settings.prefix");
    }
    public String getHost() {
        return this.getYamlConfiguration().getString("settings.mysql.host");
    }
    public String getUsername() {
        return this.getYamlConfiguration().getString("settings.mysql.username");
    }
    public String getPermissionsMessage() {return this.getYamlConfiguration().getString("settings.permission.message");}
    public String getDatabase() {
        return this.getYamlConfiguration().getString("settings.mysql.database");
    }
    public Integer getPort() {
        return this.getYamlConfiguration().getInt("settings.mysql.port");
    }
    public String getPassword() {
        return this.getYamlConfiguration().getString("settings.mysql.password");
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
