package net.snickersgenascht.projects.plugin.citybuildsystem.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LevelModuleConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public LevelModuleConfig() {
        this.file = new File("plugins/CityBuild-System/modules/", "level.module");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void startSetup() {
        if (!this.getYamlConfiguration().isSet("settings.permission.level")) {
            this.yamlConfiguration.set("settings.permission.level.level", "level.perms");
            this.yamlConfiguration.set("settings.permission.level.admin", "level.perms.admin");
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

    public String getLevelPermission() {return this.getYamlConfiguration().getString("settings.permission.level.level");}
    public String getAdminLevelPermission() {return this.getYamlConfiguration().getString("settings.permission.level.admin");}


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
