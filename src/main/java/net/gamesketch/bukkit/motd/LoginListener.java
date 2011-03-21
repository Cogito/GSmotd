package net.gamesketch.bukkit.motd;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class LoginListener extends PlayerListener {

    private GSmotd plugin;

    public LoginListener(GSmotd plugin) {
        this.plugin = plugin;
    }

    public void onPlayerJoin(PlayerEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Welcome!");
    }

}
