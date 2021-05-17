package fr.keziix.com.Rank;

import fr.keziix.com.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Rank
{

    private Main main;
    public Rank(Main main){this.main=main;}

    public void createAccount(Player user)
    {
        try
        {
            PreparedStatement sts = main.mysql.getConnection().prepareStatement("SELECT `grade` FROM `rank` WHERE `player_uuid`='"+user.getUniqueId().toString()+"'");
            ResultSet rs = sts.executeQuery();
            if(!rs.next())
            {
                sts.close();
                sts = main.mysql.getConnection().prepareStatement("INSERT INTO `rank` (player_uuid, grade) VALUES ('"+ user.getUniqueId().toString() + "', '0')");
                sts.executeUpdate();
                sts.close();
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public RankUnit getRank(Player p)
    {
        RankUnit rank = null;
        try
        {
            PreparedStatement sts = main.mysql.getConnection().prepareStatement("SELECT `grade` FROM `rank` WHERE `player_uuid`= '" + p.getUniqueId().toString() + "' ");
            ResultSet rs = sts.executeQuery();
            if(rs.next()) { rank = RankUnit.getFromPower(rs.getInt("grade")); }
        } catch (SQLException e) { e.printStackTrace(); }
        return rank;
    }

    public void setRank(Player p, RankUnit rank)
    {
        try
        {
            PreparedStatement sts = main.mysql.getConnection().prepareStatement("UPDATE `rank` SET `grade`= '" + rank.getPower() + "' WHERE `player_uuid`='" + p.getUniqueId().toString() +"'");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public enum RankUnit
    {
        JOUEUR(0, "joueur", "§7Joueur "),
        VIP(1, "vip", "§8VIP "),
        LEGENDAIRE(2, "lengendaire", "§3Légendaire "),
        OTTAKO(3, "ottako", "§bOttako "),
        YOUTUBER(4, "youtuber", "§6YouTuber "),
        FRIEND(5, "friend", "§dFriend "),
        GRAPHISTE(6, "graphist", "§aGraphiste "),
        BUILDER(7, "builder", "§2Builder "),
        ASSISTANT(8, "assistant", "§5Helper "),
        MODERATEUR(9, "moderateur", "§9Modérateur "),
        SUPERMODERATEUR(10, "supermoderateur", "§1Modérateur§c+ "),
        DEVELOPPEUR(11, "developpeur", "§cDéveloppeur "),
        ADMIN(12, "admin", "§eAdmin ");

        private int power;
        private String name;
        private String prefix;
        private static Map<Integer, RankUnit> ID_MAP = new HashMap<>();
        private RankUnit(int power, String name, String prefix) { this.power = power;this.name = name;this.prefix = prefix; }

        static { for(RankUnit rank : values()) { ID_MAP.put(rank.power, rank); } }

        public static RankUnit getFromPower(int power) { return ID_MAP.get(power); }
        public int getPower() { return power; }
        public String getName() { return name; }
        public String getPrefix() { return prefix; }
    }
}
