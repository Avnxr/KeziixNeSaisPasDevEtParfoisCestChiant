package fr.keziix.com.Event;

import fr.keziix.com.Main;
import fr.keziix.com.Rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join  implements Listener
{

    private Main main;
    public Join(Main main) { this.main = main; }

    @EventHandler
    public void onUserJoin(PlayerJoinEvent e)
    {
        Player user = e.getPlayer();
        main.rank.createAccount(user);
        e.setJoinMessage(new Rank(main).getRank(user).getPrefix()+user.getDisplayName()+ ChatColor.YELLOW+" à rejoin le lobby.");

        user.getInventory().clear();
    }
}
