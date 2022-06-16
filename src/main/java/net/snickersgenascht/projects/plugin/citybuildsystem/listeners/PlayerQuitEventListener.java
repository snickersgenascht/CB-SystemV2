package net.snickersgenascht.projects.plugin.citybuildsystem.listeners;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.ListenersConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventListener implements Listener {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final ListenersConfig listenersConfig;

    public PlayerQuitEventListener(String prefix) {
        this.prefix = prefix;
        this.memberAPI = new MemberAPI();
        this.listenersConfig = new ListenersConfig();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.registerFunctions(event.getPlayer());
        this.registerQuitMessage(event);
    }

    private void registerFunctions(Player player) {
        this.registerPlayer(player);
    }

    private void registerQuitMessage(PlayerQuitEvent event) {
        if (this.getListenersConfig().getQuitMessageState().equals(true)) {
            event.setQuitMessage(this.getListenersConfig().getQuitMessage());
        } else {
            event.setQuitMessage(null);
        }
    }

    private void registerPlayer(Player player) {
        if (!this.getMemberAPI().isUUIDRegistered(player)) {
            this.getMemberAPI().registerPerson(player);
        } else {
            this.getMemberAPI().setStatus(player, "offline");
        }
    }

    public MemberAPI getMemberAPI() {
        return memberAPI;
    }
    public String getPrefix() {
        return prefix;
    }
    public ListenersConfig getListenersConfig() {
        return listenersConfig;
    }

}