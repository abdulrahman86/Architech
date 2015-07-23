package test.context;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.dao.UserDAO;
 
@Configuration
public class TestContext {
 
    @Bean
    public UserDAO userDAO() 
    {
        return Mockito.mock(UserDAO.class);
    }
}