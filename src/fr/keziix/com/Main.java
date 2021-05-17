package fr.keziix.com;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    public static Main INSTANCE;

    public SqlMaisLautre mysql = new SqlMaisLautre();

    @Override
    public void onEnable()
    {
        INSTANCE = this;
        registerListeners();
        registerCommands();
        mysql.connect("localhost", "uwu", 3306, "root", "");
    }

    @Override
    public void onDisable() {
        mysql.disconnect();
    }

    private void registerListeners(){PluginManager pm = Bukkit.getPluginManager(); }

    private void registerCommands(){ }

}
