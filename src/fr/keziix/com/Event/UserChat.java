package fr.keziix.com.Event;

import fr.keziix.com.Main;
import fr.keziix.com.Rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class UserChat implements Listener
{

    private Main main;
    public UserChat(Main main) { this.main = main; }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        e.setFormat(new Rank(main).getRank(p).getPrefix() + "%1$s " + ChatColor.WHITE + ": " + ChatColor.DARK_GRAY + "%2$s");
    }
}