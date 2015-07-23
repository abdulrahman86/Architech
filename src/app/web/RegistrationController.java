package app.web;



import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.dao.UserDAO;
import app.exceptions.InvalidRedirectException;
import app.model.User;
import app.util.RedirectUtil;

/**
 * 
 * @author abdulsattar
 *
 * User Registration Controller
 */
@Controller
@RequestMapping(value={"/"})
public class RegistrationController {

	private UserDAO userDAO;

	protected static final String USER_NAME = "userName";

	protected static final String USER_PASSWORD = "password";
	
	protected static final Pattern USER_PASSWORD_PATTERN = Pattern.compile("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*");
	
	protected static final Pattern USER_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9 ]+");

	@Autowired
	public RegistrationController(UserDAO userDAO) 
	{
		this.userDAO = userDAO;
	}

	@RequestMapping(value={"/"}, method=RequestMethod.GET)
	public String showWelcomeForm() 
	{
		return "redirect:/register";
	}

	@RequestMapping(value={"/register"}, method=RequestMethod.GET)
	public String showUserRegistrationForm(Model model) 
	{
		model.addAttribute("invalidUserNamePasswordFlag", "false");
		return "registerForm";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processUserRegistration(@Valid User user, Model model, RedirectAttributes reDirectModel)
	{
		if (! validateNewUser(user))
		{
			model.addAttribute("invalidUserNamePasswordFlag", "true");
			
			return "registerForm";
		}
		
		try
		{
			userDAO.save(user);
		}
		catch (Exception ex)
		{
			return handleDuplicateUser(user.getUserName(), reDirectModel);
		}

		reDirectModel.addFlashAttribute("user", user);
		
		RedirectUtil.addUserRedirectIndictor(reDirectModel);

	    return "redirect:/profile/" + user.getUserName();
	}
	

	@RequestMapping(value="/profile/{username}", method=RequestMethod.GET)
	public String showUserProfile(
			@PathVariable String username, Model model) 
	{
		throwExceptionOnInvalidRedirect(model);
		
		if (! model.containsAttribute("user"))
		{
			User user = userDAO.findByUsername(username);
			model.addAttribute(user);
		}

		return "profile";
	}
	
	@RequestMapping(value="/duplicate/{userName}", method=RequestMethod.GET)
	public String showUserDuplicate(@PathVariable String userName, Model model) 
	{
		throwExceptionOnInvalidRedirect(model);
		
		model.addAttribute(userName);
		return "duplicate";
	}
	
	protected static void throwExceptionOnInvalidRedirect(Model model)
	{
		if (! RedirectUtil.isValidRedirectRequest(model))
		{
			throw new InvalidRedirectException();
		}
	}
	
	protected String handleDuplicateUser(String userName, RedirectAttributes model)
	{
		RedirectUtil.addUserRedirectIndictor(model);
			
		return "redirect:/duplicate/" + userName;
	}
	
	protected boolean validateNewUser(User user)
	{
		//validate user name
		
		String sUserName = user.getUserName();
		
		if (sUserName == null || sUserName.length() <= 5)
		{
			return false;
		}
		
		if ( ! USER_NAME_PATTERN.matcher(sUserName).matches())
		{
			return false;
		}
		
		String sUserPassword = user.getPassword();
		
		if (sUserPassword == null || sUserPassword.length() <= 8)
		{
			return false;
		}
		
		if ( ! USER_PASSWORD_PATTERN.matcher(sUserPassword).matches())
		{
			return false;
		}
		
		return true;
	}
}
