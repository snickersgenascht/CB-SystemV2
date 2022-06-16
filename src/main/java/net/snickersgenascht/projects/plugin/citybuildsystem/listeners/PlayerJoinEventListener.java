package net.snickersgenascht.projects.plugin.citybuildsystem.listeners;

import net.snickersgenascht.projects.plugin.citybuildsystem.apis.MemberAPI;
import net.snickersgenascht.projects.plugin.citybuildsystem.configs.ListenersConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

    private final String prefix;
    private final MemberAPI memberAPI;
    private final ListenersConfig listenersConfig;

    public PlayerJoinEventListener(String prefix) {
        this.prefix = prefix;
        this.memberAPI = new MemberAPI();
        this.listenersConfig = new ListenersConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.registerFunctions(event.getPlayer());
        this.registerJoinMessage(event);
    }

    private void registerFunctions(Player player) {
        this.registerPlayer(player);
    }

    private void registerJoinMessage(PlayerJoinEvent event) {
        if (this.getListenersConfig().getQuitMessageState().equals(true)) {
            event.setJoinMessage(this.getListenersConfig().getJoinMessage());
        } else {
            event.setJoinMessage(null);
        }
    }

    private void registerPlayer(Player player) {
        if (!this.getMemberAPI().isUUIDRegistered(player)) {
            this.getMemberAPI().registerPerson(player);
        } else {
            if (!this.getMemberAPI().getUserName(player).equalsIgnoreCase(player.getName())) {
                this.getMemberAPI().setUsername(player, player.getName());
                this.getMemberAPI().setStatus(player, "online");
            } else {
                this.getMemberAPI().setStatus(player, "online");
            }
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
