package com.wxqts.service;
/**
* @author zhoulong  E-mail:zhoulong@163.com
* @date 2018年4月17日
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxqts.dao.AppDao;
import com.wxqts.dao.UserDao;
import com.wxqts.domain.User;

@Service
public class UserService {

	private UserDao userDao;
	private AppDao appDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}

	/**
	 * 注册一个新用户
	 *
	 * @param user
	 *            用户
	 */
	public void register(User user) throws Exception {
		User u = this.getUserByUsername(user.getUsername());
		if (u != null) {
			throw new RuntimeException("用户名已经存在");
		} else {
			userDao.save(user);
		}
	}

	/**
	 * 更新用户
	 *
	 * @param user
	 *            用户
	 */
	public void update(User user) {
		userDao.update(user);
	}

	/**
	 * 根据用户名/密码查询 User对象
	 *
	 * @param userName
	 *            用户名
	 * @return User
	 */
	public User getUserByUsername(String userName) {
		return userDao.getUserByUsername(userName);
	}

	/**
	 * 根据userId加载User对象
	 *
	 * @param userId
	 *            用户ID
	 * @return User
	 */
	public User getUserById(int userId) {
		return userDao.get(userId);
	}

	/**
	 * 根据用户名为条件，执行模糊查询操作
	 *
	 * @param userName
	 *            查询用户名
	 * @return 所有用户名前导匹配的userName的用户
	 */
	public List<User> queryUserByUsername(String userName) {
		return userDao.queryUserByUsername(userName);
	}

	/**
	 * 获取所有用户
	 *
	 * @return 所有用户
	 */
	public List<User> getAllUsers() {
		return userDao.queryAllUsers();
	}

	
	/**
	 * 根据用户名获取所有可以访问的应用列表
	 * @param username 用户名
	 * @return 应用列表
	 */
	public List<String> getAppUrlByUsername(String username){
		return appDao.getAppUrlByUsername(username);
	}

}
