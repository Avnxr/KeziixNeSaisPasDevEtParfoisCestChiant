package fr.keziix.com;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class SqlMaisLautre
{
    private Connection conn;

    public void connect(String host, String database, int port, String user, String password) {
        if(!isConnected()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void disconnect() {
        if(isConnected()) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isConnected() {
        try {
            if((conn == null) || (conn.isClosed()) || conn.isValid(5)) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Connection getConnection() {
        return conn;
    }
}

