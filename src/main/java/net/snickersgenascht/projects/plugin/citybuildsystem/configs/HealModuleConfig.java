package net.snickersgenascht.projects.plugin.citybuildsystem.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class HealModuleConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public HealModuleConfig() {
        this.file = new File("plugins/CityBuild-System/modules/", "heal.module");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void startSetup() {
        if (!this.getYamlConfiguration().isSet("settings.permission.heal")) {
            this.yamlConfiguration.set("settings.permission.heal.heal", "heal.perms");
            this.yamlConfiguration.set("settings.permission.heal.admin", "heal.perms.admin");
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

    public String getHealPermission() {
        return this.getYamlConfiguration().getString("settings.permission.heal.heal");
    }
    public String getAdminHealPermission() {return this.getYamlConfiguration().getString("settings.permission.heal.admin");}

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
