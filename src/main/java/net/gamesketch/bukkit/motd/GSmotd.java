
package net.gamesketch.bukkit.motd;

import java.io.File;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;

/**
 * GameSketch Network message of the day
 *
 * @author Streammz
 * @author Cogito
 */
public class GSmotd extends JavaPlugin {
	private final PlayerListener playerListener = new PlayerListener();
	
    public GSmotd(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) {
        super(pluginLoader, instance, desc, folder, plugin, cLoader);
    }

    public void onDisable() {
        //PluginManager pm = getServer().getPluginManager();
    }

    public void onEnable() {       
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String commandName = command.getName().toLowerCase();

        if (commandName.equals("setmotd")) {
        }
        return false;
    }
    
    public void onPlayerJoin(PlayerLoginEvent event) {
    	Player player = event.getPlayer();
    	player.sendMessage("Welcome!");
    }
}