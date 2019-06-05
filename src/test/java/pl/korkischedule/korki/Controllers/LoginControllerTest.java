package pl.korkischedule.korki.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.korkischedule.korki.Repository.AddRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTest {

    private MockMvc mockMvc;
    @Mock
    private AddRepo addRepo;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:templates");
        viewResolver.setSuffix(".html");
        LoginController loginController = new LoginController();
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void givenLogin_whenMockMVC_thenVerifyStatus() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }


    @Test
    public void testTest() throws Exception {
        mockMvc.perform(get("test/test"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
}