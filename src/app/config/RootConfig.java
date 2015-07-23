package app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 * @author abdullahsattar
 *
 * Configure scanning for components in application model package.
 */

@Configuration
@ComponentScan(basePackages={"app", "app.model"},
excludeFilters={
		@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
})
public class RootConfig 
{

}