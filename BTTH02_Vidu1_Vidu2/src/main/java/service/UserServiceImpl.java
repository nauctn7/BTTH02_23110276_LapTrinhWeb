package service;

import java.sql.Date;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;

public class UserServiceImpl implements UserService {
	  private final UserDao userDao = new UserDaoImpl();

	  @Override

	  public User login(String username, String password) {
	      User user = this.findByUserName(username);
	      if (user != null && password.equals(user.getPassWord())) {
	          return user;
	      }
	      return null;
	  }


	  @Override
	  public boolean register(String username, String password, String email, String fullname, String phone) {
	    // Theo slide: chặn trùng username trước khi insert, role = 5, set createdDate = now :contentReference[oaicite:11]{index=11}
	    if (userDao.checkExistUsername(username)) return false;

	    long millis = System.currentTimeMillis();
	    Date date = new Date(millis);
	    String hashed = PasswordUtil.hash(password);

	    User u = new User();
	    u.setEmail(email);
	    u.setUserName(username);
	    u.setFullName(fullname);
	    u.setPassWord(hashed);
	    u.setAvatar(null);
	    u.setRoleid(5); // theo slide đăng ký mặc định 5 :contentReference[oaicite:12]{index=12}
	    u.setPhone(phone);
	    u.setCreatedDate(date);
	    userDao.insert(u);
	    return true;
	  }

	  @Override public void insert(User user) { userDao.insert(user); }
	  @Override public boolean checkExistEmail(String email) { return userDao.checkExistEmail(email); }
	  @Override public boolean checkExistUsername(String username) { return userDao.checkExistUsername(username); }
	  @Override public boolean checkExistPhone(String phone) { return userDao.checkExistPhone(phone); }
	  @Override public User findByUserName(String username) { return userDao.findByUserName(username); }

	  @Override
	  public User get(String username) {
		// TODO Auto-generated method stub
		return null;
	  }

	  @Override
	  public boolean register(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	  }
	}