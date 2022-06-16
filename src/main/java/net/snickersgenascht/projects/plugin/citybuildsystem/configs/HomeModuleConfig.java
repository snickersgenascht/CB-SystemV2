package net.snickersgenascht.projects.plugin.citybuildsystem.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class HomeModuleConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public HomeModuleConfig() {
        this.file = new File("plugins/CityBuild-System/modules/", "home.module");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void startSetup() {
        if (!this.getYamlConfiguration().isSet("settings.permission.home")) {
            this.yamlConfiguration.set("settings.permission.home.home.sup.perms", "homes.sup.perms");
            this.yamlConfiguration.set("settings.permission.home.home.mod.perms", "homes.mod.perms");
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

    public String getHomeSupPermission() {return this.getYamlConfiguration().getString("settings.permission.home.home.sup.perms");}
    public String getHomeModPermission() {return this.getYamlConfiguration().getString("settings.permission.home.home.mod.perms");}

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
