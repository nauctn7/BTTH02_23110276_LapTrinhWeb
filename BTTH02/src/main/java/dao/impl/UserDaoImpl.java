package dao.impl;
import java.sql.*;

import dao.UserDao;
import models.User;
import vn.iotstar.DBCon;
public class UserDaoImpl implements UserDao{

	  @Override
	  public User findByUserName(String username) {
	    String sql = "SELECT * FROM [User] WHERE username = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, username);
	      try (ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	          User u = new User();
	          u.setId(rs.getInt("id"));
	          u.setEmail(rs.getString("email"));
	          u.setUserName(rs.getString("username"));
	          u.setFullName(rs.getString("fullname"));
	          u.setPassWord(rs.getString("password")); // mật khẩu đã băm
	          u.setAvatar(rs.getString("avatar"));
	          u.setRoleid(rs.getInt("roleid"));
	          u.setPhone(rs.getString("phone"));
	          u.setCreatedDate(rs.getDate("createdDate"));
	          return u;
	        }
	      }
	    } catch (Exception e) { e.printStackTrace(); }
	    return null;
	  }

	  @Override
	  public void insert(User user) {
	    String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid, phone, createddate) "
	               + "VALUES (?,?,?,?,?,?,?,?)";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, user.getEmail());
	      ps.setString(2, user.getUserName());
	      ps.setString(3, user.getFullName());
	      ps.setString(4, user.getPassWord()); // đã băm
	      ps.setString(5, user.getAvatar());
	      ps.setInt(6, user.getRoleid());
	      ps.setString(7, user.getPhone());
	      ps.setDate(8, user.getCreatedDate());
	      ps.executeUpdate();
	    } catch (Exception e) { e.printStackTrace(); }
	  }

	  @Override
	  public boolean checkExistEmail(String email) {
	    String sql = "SELECT 1 FROM [User] WHERE email = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, email);
	      try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return false;
	  }

	  @Override
	  public boolean checkExistUsername(String username) {
	    String sql = "SELECT 1 FROM [User] WHERE username = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, username);
	      try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return false;
	  }

	  @Override
	  public boolean checkExistPhone(String phone) {
	    String sql = "SELECT 1 FROM [User] WHERE phone = ?";
	    try (Connection conn = new DBCon().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	      ps.setString(1, phone);
	      try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
	    } catch (Exception e) { e.printStackTrace(); }
	    return false;
	  }
}
