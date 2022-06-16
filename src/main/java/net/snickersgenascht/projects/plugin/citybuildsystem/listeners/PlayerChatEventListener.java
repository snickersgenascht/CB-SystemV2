package net.snickersgenascht.projects.plugin.citybuildsystem.listeners;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.CityBuildConfig;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.ListenersConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEventListener implements Listener {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final CityBuildConfig cityBuildConfig;
    private final ListenersConfig listenersConfig;

    public PlayerChatEventListener(String prefix) {
        this.prefix = prefix;
        this.memberAPI = new MemberAPI();
        this.cityBuildConfig = new CityBuildConfig();
        this.listenersConfig = new ListenersConfig();
    }

    @EventHandler
    public void onPlayerPingChat(AsyncPlayerChatEvent event) {
        this.getChatColorMethode(event);
        this.getPingMethode(event);
    }

    private void getChatColorMethode(AsyncPlayerChatEvent event) {
        if (this.getListenersConfig().getColorMessageState().equals(true)) {
            if (event.getPlayer().hasPermission(this.getListenersConfig().getColorPermission())) {
                event.setMessage(event.getMessage().replaceAll("&", "§"));
            }
        }
    }

    private void getPingMethode(AsyncPlayerChatEvent event) {
        if (this.getListenersConfig().getPingMessageState().equals(true)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (event.getMessage().contains("@" + all.getName())) {
                    if (this.getMemberAPI().isUsernameRegistered(all.getName())) {
                        if (this.getMemberAPI().getPlayerState(all).equalsIgnoreCase("online")) {
                            event.setMessage(event.getMessage().replaceAll("@" + all.getName(), this.getListenersConfig().getPingMessage() + all.getName() + "§7").replaceAll("&", "§"));
                            all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 40, 40);
                        } else {
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 40, 40);
                            event.getPlayer().sendMessage(this.getCityBuildConfig().getPrefix() + "§cDer Spieler ist derzeit nicht Online§8!");
                        }
                    } else {
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 40, 40);
                        event.getPlayer().sendMessage(this.getCityBuildConfig().getPrefix() + "§cDer Spieler wurde nicht gefunden8!");
                    }
                }
            }
        }
    }

    public ListenersConfig getListenersConfig() {
        return listenersConfig;
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }

    public String getPrefix() {
        return prefix;
    }

    public CityBuildConfig getCityBuildConfig() {
        return cityBuildConfig;
    }
}
