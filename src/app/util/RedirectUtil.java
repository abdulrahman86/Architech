package app.util;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class RedirectUtil 
{

	public static void addUserRedirectIndictor(RedirectAttributes model)
	{
		model.addFlashAttribute("redirect", true);
	}
	
	public static boolean isValidRedirectRequest(Model model)
	{
		return model.containsAttribute("redirect");
	}
}
