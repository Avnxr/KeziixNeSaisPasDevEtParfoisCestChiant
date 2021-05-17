package fr.keziix.com;

import fr.keziix.com.Event.Join;
import fr.keziix.com.Event.UserChat;
import fr.keziix.com.MySql.MySql;
import fr.keziix.com.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    public static Main INSTANCE;

    public Rank rank = new Rank(this);
    public MySql mysql = new MySql();

    @Override
    public void onEnable()
    {
        INSTANCE = this;
        registerListeners();
        mysql.connect("localhost", "uwu", 3306, "root", "");
    }

    @Override
    public void onDisable() {
        mysql.disconnect();
    }

    private void registerListeners(){PluginManager pm = Bukkit.getPluginManager();
    pm.registerEvents(new UserChat(this), this);
    pm.registerEvents(new Join(this), this);}

}

// TODO: add Rank [89%]
// TODO: add MySql Connection [OK]