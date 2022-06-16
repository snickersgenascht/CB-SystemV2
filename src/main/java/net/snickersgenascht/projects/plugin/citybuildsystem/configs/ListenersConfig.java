package net.snickersgenascht.projects.plugin.citybuildsystem.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ListenersConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public ListenersConfig() {
        this.file = new File("plugins/CityBuild-System/", "listeners.yml");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void startSetup() {
        if (!this.getYamlConfiguration().isSet("settings.listeners")) {
            this.yamlConfiguration.set("settings.listeners.joinmessage.state", false);
            this.yamlConfiguration.set("settings.listeners.joinmessage.message", "");
            this.yamlConfiguration.set("settings.listeners.pingmessage.state", true);
            this.yamlConfiguration.set("settings.listeners.pingmessage.message", "§b@");
            this.yamlConfiguration.set("settings.listeners.colormessage.state", true);
            this.yamlConfiguration.set("settings.listeners.colormessage.permission", "color.chat");
            this.yamlConfiguration.set("settings.listeners.quitmessage.state", false);
            this.yamlConfiguration.set("ssettings.listeners.quitmessage.message", "");
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

    public String getJoinMessage() {return this.getYamlConfiguration().getString("settings.listeners.joinmessage.message");}
    public Boolean getJoinMessageState() {return this.getYamlConfiguration().getBoolean("settings.listeners.joinmessage.state");}
    public String getPingMessage() {return this.getYamlConfiguration().getString("settings.listeners.pingmessage.message");}
    public Boolean getPingMessageState() {return this.getYamlConfiguration().getBoolean("settings.listeners.pingmessage.state");}
    public String getColorPermission() {return this.getYamlConfiguration().getString("settings.listeners.colormessage.permission");}
    public Boolean getColorMessageState() {return this.getYamlConfiguration().getBoolean("settings.listeners.colormessage.state");}
    public String getQuitMessage() {return this.getYamlConfiguration().getString("settings.listeners.quitmessage.message");}
    public Boolean getQuitMessageState() {return this.getYamlConfiguration().getBoolean("settings.listeners.quitmessage.state");}

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
