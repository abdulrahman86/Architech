package app.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import app.exceptions.InvalidRedirectException;

/**
 * Handler to handle duplicate user exceptions
 * @author abdulsattar
 *
 */
@ControllerAdvice
public class AppWideExceptionHandler 
{
	
	@ExceptionHandler(InvalidRedirectException.class)
	public String handleInvalidRedirectException()
	{
		
		return "redirect:/";
	}
}
