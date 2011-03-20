
package net.gamesketch.bukkit.motd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerListener;
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
    private final PlayerListener playerListener = new LoginListener(this);
    private String message;

    public void onDisable() {
        //PluginManager pm = getServer().getPluginManager();
    }

    public void onEnable() {
        message = "Welcome!";
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
        	if (args.length == 0) {
            sender.sendMessage("Please enter a message.");
            return true;
            }
            // build the message from the arguments, one by one
            StringBuilder builder = new StringBuilder();
            for (String s : args) {
                builder.append(s);
                builder.append(" ");
            }
            // return the built message, minus the last space that was appended
            if (builder.length() > 0) {
                message = builder.substring(0, builder.length() - 1);
            }
            return true;
        }
        return false;
    }

    public String getMessage() {
        return message;
    }

}
