package dao.impl;
import java.sql.*;

import dao.UserDao;
import models.User;
import vn.iotstar.DBCon;
public class UserDaoImpl implements UserDao{

	  @Override
	  public User findByUserName(String username) {
	    String sql = "SELECT * FROM [Users] WHERE username = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, username);
	      try (ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	        	User u = new User();
	        	u.setId(rs.getInt("userId"));
	        	u.setEmail(rs.getString("email"));
	        	u.setUserName(rs.getString("username"));
	        	u.setFullName(rs.getString("fullname"));
	        	u.setPassWord(rs.getString("password"));
	        	u.setRoleid(rs.getInt("roleid"));
	        	u.setPhone(rs.getString("phone"));
	        	return u;

	        }
	      }
	    } catch (Exception e) { e.printStackTrace(); }
	    return null;
	  }

	  @Override
	  public void insert(User user) {
		  String sql = "INSERT INTO [Users](email, username, fullname, password, roleid, phone) "
		           + "VALUES (?,?,?,?,?,?)";
		try (Connection conn = new DBCon().getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {
		  ps.setString(1, user.getEmail());
		  ps.setString(2, user.getUserName());
		  ps.setString(3, user.getFullName());
		  ps.setString(4, user.getPassWord());
		  ps.setInt(5, user.getRoleid());
		  ps.setString(6, user.getPhone());
		  ps.executeUpdate();

	    } catch (Exception e) { e.printStackTrace(); }
	  }

	  @Override
	  public boolean checkExistEmail(String email) {
	    String sql = "SELECT 1 FROM [Users] WHERE email = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, email);
	      try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return false;
	  }

	  @Override
	  public boolean checkExistUsername(String username) {
	    String sql = "SELECT 1 FROM [Users] WHERE username = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, username);
	      try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return false;
	  }

	  @Override
	  public boolean checkExistPhone(String phone) {
	    String sql = "SELECT 1 FROM [Users] WHERE phone = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, phone);
	      try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return false;
	  }

	  @Override
	  public User get(String username) {
		// TODO Auto-generated method stub
		return null;
	  }
}
