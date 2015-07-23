package app.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * 
 * @author abdulsattar
 * 
 * Programmatically configure dispatcher servlet.
 *
 */

public class ArchitechWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer 
{

	@Override
	protected String[] getServletMappings() 
	{
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() 
	{
		return new Class<?>[] { RootConfig.class };

	}
	@Override
	protected Class<?>[] getServletConfigClasses() 
	{
		return new Class<?>[] { WebConfig.class, SecurityConfig.class };
	}
}
