package test;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import test.context.TestContext;
import app.config.WebConfig;
import app.dao.UserDAO;
import app.model.User;
import app.web.AppWideExceptionHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WebConfig.class, TestContext.class, AppWideExceptionHandler.class})
@WebAppConfiguration
public class RegistrationControllerTest
{

	private MockMvc mockMvc;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		//Reset mock to avoid leak between tests
		Mockito.reset(userDAO);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test if redirect happens when user hits the welcome page.
	 * @throws Exception
	 */
	@Test
	public void testWelcomePageHandler() throws Exception 
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(view().name("redirect:/register"))
		.andExpect(status().is3xxRedirection());
	}

	/**
	 * Test use case when user hits /register
	 * @throws Exception
	 */
	@Test
	public void testRegistrationPageHandler() throws Exception 
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/register"))
		.andExpect(view().name("registerForm"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/views/registerForm.jsp"));
	}

	/**
	 * Test use case when user hits /duplicate/{username}
	 * @throws Exception
	 */
	@Test
	public void testDuplicatePageHandler() throws Exception 
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/duplicate/abdulrahaman86"))
		.andExpect(view().name("redirect:/"))
		.andExpect(status().is3xxRedirection());
	}

	/**
	 * Test post request to form. User name and password match the specified pattern.
	 * @throws Exception
	 */
	@Test
	//@ExpectedDatabase("toDoData.xml")
	public void testRegistrationUserNamePasswordValid() throws Exception {
		
		String sUserName = "abdulrahman86";
		String sPassword = "Test112358";
		
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
		
		mockMvc.
		perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("userName", sUserName)
				.param("password", sPassword)
				)
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/profile/"+sUserName))
				.andExpect(flash().attribute("user", hasProperty("userName")))
				.andExpect(flash().attribute("user", hasProperty("userName", IsEqual.equalTo(sUserName))))
				.andExpect(flash().attribute("user", hasProperty("password", IsEqual.equalTo(sPassword))));
		
				verify(userDAO, times(1)).save(argument.capture());
				
				assertEquals(sUserName, argument.getValue().getUserName());
				assertEquals(sPassword, argument.getValue().getPassword());
	}
	
	/**
	 * Test post request to form. User name has special characters and is therefore invalid.
	 * @throws Exception
	 */
	@Test
	//@ExpectedDatabase("toDoData.xml")
	public void testRegistrationUserNameInvalid() throws Exception {
		
		String sUserName = "abdulrahman86@";
		String sPassword = "Test112358";
		
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
		
		mockMvc.
		perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("userName", sUserName)
				.param("password", sPassword)
				)
				.andExpect(status().isOk())
				.andExpect(view().name("registerForm"))
				.andExpect(model().attribute("invalidUserNamePasswordFlag", IsEqual.equalTo("true")));
		
				verify(userDAO, times(0)).save(argument.capture());
	}
}

