package net.snickersgenascht.projects.plugin.citybuildsystem.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class TeleportModuleConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public TeleportModuleConfig() {
        this.file = new File("plugins/CityBuild-System/modules/", "teleport.module");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void startSetup() {
        if (!this.getYamlConfiguration().isSet("settings.permission.teleport.teleport")) {
            this.yamlConfiguration.set("settings.permission.teleport.teleport", "teleport.perms");
            this.yamlConfiguration.set("settings.permission.teleport.tphere", "teleport.perms.tphere");
            this.yamlConfiguration.set("settings.permission.teleport.admin", "teleport.perms.admin");
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

    public String getTeleportHerePermission() {return this.getYamlConfiguration().getString("settings.permission.teleport.tphere");}
    public String getAdminTeleportPermission() {return this.getYamlConfiguration().getString("settings.permission.teleport.admin");}
    public String getTeleportPermission() {return this.getYamlConfiguration().getString("settings.permission.teleport.teleport");}

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
