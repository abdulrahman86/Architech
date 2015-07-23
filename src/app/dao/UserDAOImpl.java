package app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import app.model.User;

/**
 * 
 * @author abdulsattar
 *
 * User database access object implementation.
 */

@Repository
public class UserDAOImpl implements UserDAO
{

	//public HashMap<String, Spitter> spitterRepo = new HashMap<String, Spitter>();
	private JdbcOperations jdbcOperations;
	
	private static final String SQL_INSERT_USER =
			 "insert into architechDBO.User(userName, password) values (?, ?)";
	
	private static final String SQL_SELECT_USER =
		      "select id, username, password from architechDBO.User where username = ?";
	
	@Autowired
	public UserDAOImpl(JdbcOperations jdbcOperations) 
	{
		this.jdbcOperations = jdbcOperations;
	}
	
	/**
	 * Finds user by unique user name.
	 */
	@Override
	public User findByUsername(String userName) 
	{
		return jdbcOperations.queryForObject(
	            SQL_SELECT_USER, new UserDAORowMapper(), userName);
	}

	/**
	 * Saves user to the database.
	 */
	@Override
	public void save(User user) 
	{
		jdbcOperations.update(SQL_INSERT_USER, user.getUserName(), user.getPassword());
	}
	
	/**
	 * 
	 * @author abdullahsattar
	 * 
	 * Mapper to deserialize user database object to user java object.
	 *
	 */
	private static final class UserDAORowMapper implements RowMapper<User> 
	{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			User user = new User();

			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			
			return user;
		} 
	}

}
