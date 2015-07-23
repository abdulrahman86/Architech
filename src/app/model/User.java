package app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author abdulsattar
 * 
 * User model.
 *
 */
public class User {
		
	@NotNull
	@Size(min=5, max=16)
	private String userName;
	
	@NotNull
	@Size(min=5, max=25)
	private String password;
		
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserName()
	{
		return userName;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
}

