package app.dao;

import app.model.User;;

/**
 * 
 * @author abdullahsattar
 *
 * Interface for user data access object
 */
public interface UserDAO 
{
	public User findByUsername(String userName);
	public void save(User user);
}
