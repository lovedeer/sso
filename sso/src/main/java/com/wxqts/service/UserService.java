package com.wxqts.service;
/**
* @author zhoulong  E-mail:zhoulong@163.com
* @date 2018年4月17日
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wxqts.dao.UserDao;
import com.wxqts.domain.User;

@Service
public class UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 注册一个新用户
	 *
	 * @param user 用户
	 */
	public void register(User user) throws Exception {
		User u = this.getUserByUserName(user.getUserName());
		if (u != null) {
			throw new RuntimeException("用户名已经存在");
		} else {
			userDao.save(user);
		}
	}

	/**
	 * 更新用户
	 *
	 * @param user 用户
	 */
	public void update(User user) {
		userDao.update(user);
	}

	/**
	 * 根据用户名/密码查询 User对象
	 *
	 * @param userName 用户名
	 * @return User
	 */
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	/**
	 * 根据userId加载User对象
	 *
	 * @param userId 用户ID
	 * @return User
	 */
	public User getUserById(int userId) {
		return userDao.get(userId);
	}

	/**
	 * 根据用户名为条件，执行模糊查询操作
	 *
	 * @param userName 查询用户名
	 * @return 所有用户名前导匹配的userName的用户
	 */
	public List<User> queryUserByUserName(String userName) {
		return userDao.queryUserByUserName(userName);
	}

	/**
	 * 获取所有用户
	 *
	 * @return 所有用户
	 */
	public List<User> getAllUsers() {
		return userDao.queryAllUsers();
	}

}
