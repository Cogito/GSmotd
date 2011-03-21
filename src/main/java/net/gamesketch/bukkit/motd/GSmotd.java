
package net.gamesketch.bukkit.motd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
    private List<String> message;

    public void onDisable() {
        //PluginManager pm = getServer().getPluginManager();
    }

    public void onEnable() {
        message = new LinkedList<String>();
        try {
            File dataFile = getDataFile("motd", false);
            if (!dataFile.exists()) {
                message.add("The MOTD needs to be set!");
                writeOutMOTD(dataFile);
            } else {
                readInMOTD(dataFile);
            }
        } catch (IOException e) {
            message.add("ERROR: Something is wrong with the MOTD file!");
        }
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
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
                // reset message and set it to the new message
                message = new LinkedList<String>();
                message.add(builder.substring(0, builder.length() - 1));
                try {
                    writeOutMOTD(getDataFile("motd", false));
                } catch (IOException e) {
                    System.out.println("Could not write MOTD.");
                }
            }
            return true;
        }
        return false;
    }

    public List<String> getMessage() {
        return message;
    }

    /**
     * @param dataFile
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void readInMOTD(File dataFile) throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(dataFile));
        String str;
        while ((str = in.readLine()) != null) {
            message.add(str);
        }
        in.close();
    }

    /**
     * @param dataFile
     * @throws IOException
     */
    private void writeOutMOTD(File dataFile) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(dataFile));
        for (String str : message) {
            out.write(str);
        }
        out.close();
    }

    /**
     * Initialise the data directory for this plugin.
     *
     * @return true if the directory has been created or already exists.
     */
    private boolean createDataDirectory() {
        File file = this.getDataFolder();
        if (!file.isDirectory()){
            if (!file.mkdirs()) {
                // failed to create the non existent directory, so failed
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieve a File description of a data file for your plugin.
     * This file will be looked for in the data directory of your plugin, wherever that is.
     * There is no need to specify the data directory in the filename such as "plugin/datafile.dat"
     * Instead, specify only "datafile.dat"
     *
     * @param filename The name of the file to retrieve.
     * @param mustAlreadyExist True if the file must already exist on the filesystem.
     *
     * @return A File descriptor to the specified data file, or null if there were any issues.
     */
    private File getDataFile(String filename, boolean mustAlreadyExist) {
        if (createDataDirectory()) {
            File file = new File(this.getDataFolder(), filename);
            if (mustAlreadyExist) {
                if (file.exists()) {
                    return file;
                }
            } else {
                return file;
            }
        }
        return null;
    }
}
