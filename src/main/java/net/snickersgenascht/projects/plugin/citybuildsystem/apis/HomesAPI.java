package net.snickersgenascht.projects.plugin.citybuildsystem.apis;

import net.snickersgenascht.projects.plugin.citybuildsystem.database.MySQL;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomesAPI {

    private final Date date = new Date();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.GERMANY);

    private final MemberAPI memberAPI = new MemberAPI();

    public void createHomesTable() {
        try {
            PreparedStatement ps = MySQL.getStatement("CREATE TABLE IF NOT EXISTS citybuild_homes (id INT(100), uuid VARCHAR(100), name VARCHAR(100), block VARCHAR(100), world_location VARCHAR(100), x_location INT(100), y_location INT(100), z_location INT(100))");
            assert ps != null;
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isHomePlayerRegistered(Player player) {
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE uuid= ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            boolean user = rs.next();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String getHomeBlock(Player player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String description = rs.getString("block");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String getHomeName(String player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ?, username= ?");
            ps.setString(1, id);
            ps.setString(2, player);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String description = rs.getString("name");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    public String getHomeName(Player player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ? AND uuid= ?");
            ps.setString(1, id);
            ps.setString(2, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String description = rs.getString("name");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String getHomeWorldLocation(Player player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ? AND uuid= ?");
            ps.setString(1, id);
            ps.setString(2, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String description = rs.getString("world_location");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getHomeYLocation(Player player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ? AND uuid= ?");
            ps.setString(1, id);
            ps.setString(2, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer description = rs.getInt("y_location");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getHomeZLocation(Player player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ? AND uuid= ?");
            ps.setString(1, id);
            ps.setString(2, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer description = rs.getInt("z_location");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getHomeXLocation(Player player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ? AND uuid= ?");
            ps.setString(1, id);
            ps.setString(2, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer description = rs.getInt("x_location");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteHome(Player player, Integer id) {
        try {
            PreparedStatement ps = MySQL.getStatement("delete from citybuild_homes where id= ? AND uuid= ?");
            ps.setInt(1, id);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getHomes(String player, int max) {
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE username= ? ORDER BY id DESC LIMIT ?");
            preparedStatement.setString(1, player);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Integer> getHomes(String string, Player player, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE uuid= ? ORDER BY id DESC LIMIT ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Integer> getHomes(String string, String player, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE username= ? ORDER BY id DESC LIMIT ?");
            preparedStatement.setString(1, player);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setID(Player player, Integer id, Integer id2) {PreparedStatement st = null;try {st = MySQL.con.prepareStatement("UPDATE citybuild_homes SET id = ? WHERE uuid = ? AND id = ?");st.setInt(1, id2 - 1);st.setString(2, player.getUniqueId().toString());st.setInt(3, id);st.executeUpdate();} catch (SQLException e) {e.printStackTrace();}}

    public void removeIDINT(Player player, Integer id, Integer id2) {
        try {
            PreparedStatement ps = MySQL.getStatement("UPDATE citybuild_homes SET id= ? WHERE uuid= ? AND id= ?");
            if (this.getID(player, id).equals(id)) {
                ps.setInt(1, id - 1);
            }
            ps.setString(2, player.getUniqueId().toString());
            ps.setInt(3, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> getHomes(Player player, int max) {
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE uuid= ? ORDER BY id DESC LIMIT ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer countRowsInOnlineTable(Player player){
        try{
            PreparedStatement sql = MySQL.getStatement("SELECT COUNT(*) FROM citybuild_homes WHERE uuid= ?");
            sql.setString(1, player.getUniqueId().toString());
            assert sql != null;
            ResultSet rs = sql.executeQuery();
            rs.next();
            int numberOfRows = rs.getInt("COUNT(*)");
            sql.close();
            return numberOfRows;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public Integer getID(Player player, Integer id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE uuid= ? AND id= ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer description = rs.getInt("id");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getID(String player){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE uuid= ?");
            ps.setString(1, memberAPI.getUUID(player));
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer description = rs.getInt("id");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean isHomePlayerRegistered(String player) {
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE uuid= ?");
            ps.setString(1, memberAPI.getUUID(player));
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            boolean user = rs.next();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String getHomeBlock(String player, String id){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE id= ? AND username= ?");
            ps.setString(1, id);
            ps.setString(2, player);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String description = rs.getString("block");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteHome(String player, Integer id) {
        try {
            PreparedStatement ps = MySQL.getStatement("delete from citybuild_homes where id= ? AND username= ?");
            ps.setInt(1, id);
            ps.setString(2, player);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Integer getID(Player player){
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM citybuild_homes WHERE uuid= ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer description = rs.getInt("id");
            rs.close();
            ps.close();
            return description;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    public void registerHome(Player player, Integer id, String name, String block) {
        try {
            PreparedStatement ps = MySQL.getStatement("INSERT INTO citybuild_homes (id, uuid, name, block, world_location, x_location, y_location, z_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, player.getUniqueId().toString());
            ps.setString(3, name);
            ps.setString(4, block);
            ps.setString(5, player.getWorld().getName());
            ps.setInt(6, player.getLocation().getBlockX());
            ps.setInt(7, player.getLocation().getBlockY());
            ps.setInt(8, player.getLocation().getBlockZ());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
