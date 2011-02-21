package net.gamesketch.bukkit.motd;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener extends PlayerListener {

    public void onPlayerJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Welcome!");
    }
}
