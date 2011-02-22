package net.gamesketch.bukkit.motd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class LoginListener extends PlayerListener {

    public void onPlayerJoin(PlayerEvent event) {
        Player player = event.getPlayer();
    
       
        try {
            BufferedReader in = new BufferedReader(new FileReader("plugins/gsmotd/gsmotd"));
            String str;
            while ((str = in.readLine()) != null) {
                player.sendMessage(str);
            }
            in.close();
        } catch (IOException e) {
        }


    }
}
