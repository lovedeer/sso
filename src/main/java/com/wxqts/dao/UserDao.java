package com.wxqts.dao;
/**
* @author 周龙  E-mail:zhoulong@163.com
* @version 创建时间：2018年4月17日 下午4:37:20
*/

import java.util.List;

import com.wxqts.domain.User;

public interface UserDao {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username 用户名
	 * 
	 * @return User 用户
	 */
	User getUserByUserName(String username);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username 用户名
	 * 
	 * @return List<User> 用户列表
	 */
	List<User> queryUserByUserName(String username);

	
	/** 查找所有用户
	 * @return 所有用户列表
	 */
	List<User> queryAllUsers();

	/**添加用户
	 * @param t 用户对象
	 */
	void save(User t);

	/**更新用户
	 * @param t 用户对象
	 */
	void update(User t);

	/**删除用户
	 * @param t 用户对象
	 */
	void remove(User t);

	/**根据用户ID查找用户
	 * @param objectId 用户ID
	 * @return User 用户
	 */
	User get(int objectId);
}