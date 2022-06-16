package net.snickersgenascht.projects.plugin.citybuildsystem.database;

import java.sql.*;

public class MySQL {

    public static Connection con;

    public void connect() {
        if (!isConnected()) {
            try {

                int port = 3306;

                String host = "45.142.114.254";
                String database = "ventsmc_privat";
                String username = "ventsmc_privat2";
                String password = "0tzG3*b0";

                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                con.close();            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement getStatement(String sql){
        if(isConnected()){
            PreparedStatement ps;
            try {
                ps = con.prepareStatement(sql);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ResultSet getResult(String sql){
        if(isConnected()){
            PreparedStatement ps;
            ResultSet rs;
            try {
                ps = getStatement(sql);
                rs = ps.executeQuery();
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static boolean isConnected() { return (con != null); }

}
