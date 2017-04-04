package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.User;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 03/02/2017
 */

public class UserDAO
{
    private Connection conn;
    
    public UserDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    public void addUser(User user) throws SQLException 
    {
        //call user_insert(<name_user>, <login>, <password>)
        String sql = "call user_insert(?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setString(1, user.getNameUser());
            psmt.setString(2, user.getLogin());
            psmt.setString(3, user.getPassword());

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute addUser in UserDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    public void updateUser(User user) throws SQLException 
    {
        //call user_update(<id_user>, <name_user>, <login>, <password>)
        String sql = "call user_update(?,?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, user.getIdUser());
            psmt.setString(2, user.getNameUser());
            psmt.setString(3, user.getLogin());
            psmt.setString(4, user.getPassword());

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute updateUser in UserDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    public void removeUser(int idUser) throws SQLException 
    {
        //call user_delete(<id_user>)
        String sql = "call user_delete(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idUser);

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute removeUser in UserDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    public User getUser (int idUser) throws SQLException
    {
        //call user_select(<id_user>)
        String sql = "call user_select(?);";
        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            ResultSet rs = psmt.executeQuery();
            
            User user = new User();
            while(rs.next())
            {
                user.setIdUser(rs.getInt("id_user"));
                user.setNameUser(rs.getString("name_user"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getUser in UserDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    public List<User> getUserList (int idUser) throws SQLException
    {
        //call user_select_list();
        String sql = "call user_select_list();";
            
        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {            
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<User> userList = new ArrayList<>();
            while(rs.next())
            {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setNameUser(rs.getString("name_user"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getUserList in UserDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    public int loginExists (String login, String password) throws SQLException
    {
       
        //call user_check_login(<login>, md5(<password>));
        String sql = "call user_check_login(?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setString(1, login);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();

            while(rs.next())
            {
                return rs.getInt("id_user");
            }
            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute loginExists in UserDAO: " + ex);
        }finally{
            conn.close();
        }
    }
}
