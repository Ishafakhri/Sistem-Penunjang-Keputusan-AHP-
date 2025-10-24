package com.spk.dao;

import com.spk.config.Koneksi;
import com.spk.model.User;
import com.spk.service.ServiceUser;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO implements ServiceUser{

    private final Connection conn;  
    public UserDAO() {
        conn = Koneksi.getConnection();
    }

    @Override
    public boolean isUserExist() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            String sql = "SELECT COUNT(*) FROM user";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean validateUsername(User model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean valid = false;
        try{
            String sql = "SELECT username FROM user WHERE username LIKE BINARY ?";
            st = conn.prepareStatement(sql);
            st.setString(1, model.getUsername());
            rs = st.executeQuery();
            
            valid = !rs.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return valid;    
    }

    @Override
    public void insertData(User model) {
        PreparedStatement st = null;
        
        try{
            String sql = "INSERT INTO user(nama, email, username, password, role) VALUES (?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            
            st.setString(1, model.getNama());
            st.setString(2, model.getEmail());
            st.setString(3, model.getUsername());
            st.setString(4, BCrypt.hashpw(model.getPassword(), BCrypt.gensalt()));
            st.setString(5, model.getRole());
            
            st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    
}
