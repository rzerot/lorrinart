package clientvideo.dao;

import clientvideo.POJO.User;

public interface UserDao {
	public int insertUser(String name, String auth);

	public int deleteUser(int user_id);

	int deleteUser(String username);

	public User searchUser(int user_id);

	public User searchUser(String name);
	

}
